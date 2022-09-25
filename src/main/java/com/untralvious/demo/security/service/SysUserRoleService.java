package com.untralvious.demo.security.service;

import com.untralvious.demo.security.domain.SysUserRole;
import com.untralvious.demo.security.service.dto.SysUserRoleDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.untralvious.demo.security.domain.SysUserRole}.
 */
public interface SysUserRoleService {
    /**
     * Save a sysUserRole.
     *
     * @param sysUserRoleDTO the entity to save.
     * @return the persisted entity.
     */
    SysUserRoleDTO save(SysUserRoleDTO sysUserRoleDTO);

    /**
     * Updates a sysUserRole.
     *
     * @param sysUserRoleDTO the entity to update.
     * @return the persisted entity.
     */
    SysUserRoleDTO update(SysUserRoleDTO sysUserRoleDTO);

    /**
     * Partially updates a sysUserRole.
     *
     * @param sysUserRoleDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysUserRoleDTO> partialUpdate(SysUserRoleDTO sysUserRoleDTO);

    /**
     * Get all the sysUserRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysUserRoleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sysUserRole.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysUserRoleDTO> findOne(String id);

    List<SysUserRole> getByUserId(String userId);

    /**
     * Delete the "id" sysUserRole.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
