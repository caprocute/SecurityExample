package com.untralvious.demo.security.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.untralvious.demo.security.IntegrationTest;
import com.untralvious.demo.security.domain.SysUserRole;
import com.untralvious.demo.security.repository.SysUserRoleRepository;
import com.untralvious.demo.security.service.dto.SysUserRoleDTO;
import com.untralvious.demo.security.service.mapper.SysUserRoleMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SysUserRoleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysUserRoleResourceIT {

    private static final String ENTITY_API_URL = "/api/sys-user-roles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysUserRoleMockMvc;

    private SysUserRole sysUserRole;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysUserRole createEntity(EntityManager em) {
        SysUserRole sysUserRole = new SysUserRole();
        return sysUserRole;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysUserRole createUpdatedEntity(EntityManager em) {
        SysUserRole sysUserRole = new SysUserRole();
        return sysUserRole;
    }

    @BeforeEach
    public void initTest() {
        sysUserRole = createEntity(em);
    }

    @Test
    @Transactional
    void createSysUserRole() throws Exception {
        int databaseSizeBeforeCreate = sysUserRoleRepository.findAll().size();
        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);
        restSysUserRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeCreate + 1);
        SysUserRole testSysUserRole = sysUserRoleList.get(sysUserRoleList.size() - 1);
    }

    @Test
    @Transactional
    void createSysUserRoleWithExistingId() throws Exception {
        // Create the SysUserRole with an existing ID
        sysUserRole.setId(1L);
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        int databaseSizeBeforeCreate = sysUserRoleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysUserRoleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysUserRoles() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get all the sysUserRoleList
        restSysUserRoleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUserRole.getId().intValue())));
    }

    @Test
    @Transactional
    void getSysUserRole() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        // Get the sysUserRole
        restSysUserRoleMockMvc
            .perform(get(ENTITY_API_URL_ID, sysUserRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysUserRole.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingSysUserRole() throws Exception {
        // Get the sysUserRole
        restSysUserRoleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysUserRole() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();

        // Update the sysUserRole
        SysUserRole updatedSysUserRole = sysUserRoleRepository.findById(sysUserRole.getId()).get();
        // Disconnect from session so that the updates on updatedSysUserRole are not directly saved in db
        em.detach(updatedSysUserRole);
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(updatedSysUserRole);

        restSysUserRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysUserRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
        SysUserRole testSysUserRole = sysUserRoleList.get(sysUserRoleList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(count.incrementAndGet());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysUserRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(count.incrementAndGet());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(count.incrementAndGet());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysUserRoleWithPatch() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();

        // Update the sysUserRole using partial update
        SysUserRole partialUpdatedSysUserRole = new SysUserRole();
        partialUpdatedSysUserRole.setId(sysUserRole.getId());

        restSysUserRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysUserRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysUserRole))
            )
            .andExpect(status().isOk());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
        SysUserRole testSysUserRole = sysUserRoleList.get(sysUserRoleList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateSysUserRoleWithPatch() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();

        // Update the sysUserRole using partial update
        SysUserRole partialUpdatedSysUserRole = new SysUserRole();
        partialUpdatedSysUserRole.setId(sysUserRole.getId());

        restSysUserRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysUserRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysUserRole))
            )
            .andExpect(status().isOk());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
        SysUserRole testSysUserRole = sysUserRoleList.get(sysUserRoleList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(count.incrementAndGet());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysUserRoleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(count.incrementAndGet());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysUserRole() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRoleRepository.findAll().size();
        sysUserRole.setId(count.incrementAndGet());

        // Create the SysUserRole
        SysUserRoleDTO sysUserRoleDTO = sysUserRoleMapper.toDto(sysUserRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysUserRoleMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysUserRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysUserRole in the database
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysUserRole() throws Exception {
        // Initialize the database
        sysUserRoleRepository.saveAndFlush(sysUserRole);

        int databaseSizeBeforeDelete = sysUserRoleRepository.findAll().size();

        // Delete the sysUserRole
        restSysUserRoleMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysUserRole.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findAll();
        assertThat(sysUserRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
