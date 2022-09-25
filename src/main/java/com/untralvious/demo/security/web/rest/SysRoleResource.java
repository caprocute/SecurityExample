package com.untralvious.demo.security.web.rest;

import com.untralvious.demo.security.repository.SysRoleRepository;
import com.untralvious.demo.security.service.SysRoleService;
import com.untralvious.demo.security.service.dto.SysRoleDTO;
import com.untralvious.demo.security.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.untralvious.demo.security.domain.SysRole}.
 */
@RestController
@RequestMapping("/api")
public class SysRoleResource {

    private final Logger log = LoggerFactory.getLogger(SysRoleResource.class);

    private static final String ENTITY_NAME = "sysRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRoleService sysRoleService;

    private final SysRoleRepository sysRoleRepository;

    public SysRoleResource(SysRoleService sysRoleService, SysRoleRepository sysRoleRepository) {
        this.sysRoleService = sysRoleService;
        this.sysRoleRepository = sysRoleRepository;
    }

    /**
     * {@code POST  /sys-roles} : Create a new sysRole.
     *
     * @param sysRoleDTO the sysRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRoleDTO, or with status {@code 400 (Bad Request)} if the sysRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-roles")
    public ResponseEntity<SysRoleDTO> createSysRole(@RequestBody SysRoleDTO sysRoleDTO) throws URISyntaxException {
        log.debug("REST request to save SysRole : {}", sysRoleDTO);
        if (sysRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRoleDTO result = sysRoleService.save(sysRoleDTO);
        return ResponseEntity
            .created(new URI("/api/sys-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-roles/:id} : Updates an existing sysRole.
     *
     * @param id the id of the sysRoleDTO to save.
     * @param sysRoleDTO the sysRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRoleDTO,
     * or with status {@code 400 (Bad Request)} if the sysRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-roles/{id}")
    public ResponseEntity<SysRoleDTO> updateSysRole(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysRoleDTO sysRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SysRole : {}, {}", id, sysRoleDTO);
        if (sysRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysRoleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysRoleDTO result = sysRoleService.update(sysRoleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysRoleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-roles/:id} : Partial updates given fields of an existing sysRole, field will ignore if it is null
     *
     * @param id the id of the sysRoleDTO to save.
     * @param sysRoleDTO the sysRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRoleDTO,
     * or with status {@code 400 (Bad Request)} if the sysRoleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sysRoleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-roles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SysRoleDTO> partialUpdateSysRole(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SysRoleDTO sysRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysRole partially : {}, {}", id, sysRoleDTO);
        if (sysRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysRoleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysRoleDTO> result = sysRoleService.partialUpdate(sysRoleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysRoleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /sys-roles} : get all the sysRoles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRoles in body.
     */
    @GetMapping("/sys-roles")
    public ResponseEntity<List<SysRoleDTO>> getAllSysRoles(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SysRoles");
        Page<SysRoleDTO> page = sysRoleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-roles/:id} : get the "id" sysRole.
     *
     * @param id the id of the sysRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-roles/{id}")
    public ResponseEntity<SysRoleDTO> getSysRole(@PathVariable Long id) {
        log.debug("REST request to get SysRole : {}", id);
        Optional<SysRoleDTO> sysRoleDTO = sysRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRoleDTO);
    }

    /**
     * {@code DELETE  /sys-roles/:id} : delete the "id" sysRole.
     *
     * @param id the id of the sysRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-roles/{id}")
    public ResponseEntity<Void> deleteSysRole(@PathVariable Long id) {
        log.debug("REST request to delete SysRole : {}", id);
        sysRoleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
