package com.untralvious.demo.security.service;

import com.untralvious.demo.security.domain.SysRole;
import com.untralvious.demo.security.domain.SysUserRole;
import com.untralvious.demo.security.service.dto.SysRoleDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.untralvious.demo.security.domain.SysRole}.
 */
public interface SysRoleService {
    /**
     * Save a sysRole.
     *
     * @param sysRoleDTO the entity to save.
     * @return the persisted entity.
     */
    SysRoleDTO save(SysRoleDTO sysRoleDTO);

    /**
     * Updates a sysRole.
     *
     * @param sysRoleDTO the entity to update.
     * @return the persisted entity.
     */
    SysRoleDTO update(SysRoleDTO sysRoleDTO);

    /**
     * Partially updates a sysRole.
     *
     * @param sysRoleDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SysRoleDTO> partialUpdate(SysRoleDTO sysRoleDTO);

    /**
     * Get all the sysRoles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysRoleDTO> findAll(Pageable pageable);

    List<SysRoleDTO> findAllNotPaging();

    /**
     * Get the "id" sysRole.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysRoleDTO> findOne(String id);

    Optional<SysRole> findOneByCode(String code);

    List<SysRole> getRolesById(List<String> roleIds);

    List<SysRole> getRoles(String userId);

    /**
     * Delete the "id" sysRole.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
