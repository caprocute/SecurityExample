package com.untralvious.demo.security.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.untralvious.demo.security.IntegrationTest;
import com.untralvious.demo.security.domain.SysRole;
import com.untralvious.demo.security.repository.SysRoleRepository;
import com.untralvious.demo.security.service.dto.SysRoleDTO;
import com.untralvious.demo.security.service.mapper.SysRoleMapper;
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
 * Integration tests for the {@link SysRoleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SysRoleResourceIT {

    private static final String ENTITY_API_URL = "/api/sys-roles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSysRoleMockMvc;

    private SysRole sysRole;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRole createEntity(EntityManager em) {
        SysRole sysRole = new SysRole();
        return sysRole;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRole createUpdatedEntity(EntityManager em) {
        SysRole sysRole = new SysRole();
        return sysRole;
    }

    @BeforeEach
    public void initTest() {
        sysRole = createEntity(em);
    }

    @Test
    @Transactional
    void createSysRole() throws Exception {
        int databaseSizeBeforeCreate = sysRoleRepository.findAll().size();
        // Create the SysRole
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);
        restSysRoleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isCreated());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeCreate + 1);
        SysRole testSysRole = sysRoleList.get(sysRoleList.size() - 1);
    }

    @Test
    @Transactional
    void createSysRoleWithExistingId() throws Exception {
        // Create the SysRole with an existing ID
        sysRole.setId(1L);
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        int databaseSizeBeforeCreate = sysRoleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysRoleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSysRoles() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get all the sysRoleList
        restSysRoleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRole.getId().intValue())));
    }

    @Test
    @Transactional
    void getSysRole() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        // Get the sysRole
        restSysRoleMockMvc
            .perform(get(ENTITY_API_URL_ID, sysRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysRole.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingSysRole() throws Exception {
        // Get the sysRole
        restSysRoleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSysRole() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();

        // Update the sysRole
        SysRole updatedSysRole = sysRoleRepository.findById(sysRole.getId()).get();
        // Disconnect from session so that the updates on updatedSysRole are not directly saved in db
        em.detach(updatedSysRole);
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(updatedSysRole);

        restSysRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO))
            )
            .andExpect(status().isOk());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
        SysRole testSysRole = sysRoleList.get(sysRoleList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingSysRole() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();
        sysRole.setId(count.incrementAndGet());

        // Create the SysRole
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sysRoleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSysRole() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();
        sysRole.setId(count.incrementAndGet());

        // Create the SysRole
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRoleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSysRole() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();
        sysRole.setId(count.incrementAndGet());

        // Create the SysRole
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRoleMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(sysRoleDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSysRoleWithPatch() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();

        // Update the sysRole using partial update
        SysRole partialUpdatedSysRole = new SysRole();
        partialUpdatedSysRole.setId(sysRole.getId());

        restSysRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysRole))
            )
            .andExpect(status().isOk());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
        SysRole testSysRole = sysRoleList.get(sysRoleList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateSysRoleWithPatch() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();

        // Update the sysRole using partial update
        SysRole partialUpdatedSysRole = new SysRole();
        partialUpdatedSysRole.setId(sysRole.getId());

        restSysRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSysRole.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSysRole))
            )
            .andExpect(status().isOk());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
        SysRole testSysRole = sysRoleList.get(sysRoleList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingSysRole() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();
        sysRole.setId(count.incrementAndGet());

        // Create the SysRole
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sysRoleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSysRole() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();
        sysRole.setId(count.incrementAndGet());

        // Create the SysRole
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRoleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(sysRoleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSysRole() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleRepository.findAll().size();
        sysRole.setId(count.incrementAndGet());

        // Create the SysRole
        SysRoleDTO sysRoleDTO = sysRoleMapper.toDto(sysRole);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSysRoleMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(sysRoleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SysRole in the database
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSysRole() throws Exception {
        // Initialize the database
        sysRoleRepository.saveAndFlush(sysRole);

        int databaseSizeBeforeDelete = sysRoleRepository.findAll().size();

        // Delete the sysRole
        restSysRoleMockMvc
            .perform(delete(ENTITY_API_URL_ID, sysRole.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        assertThat(sysRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
