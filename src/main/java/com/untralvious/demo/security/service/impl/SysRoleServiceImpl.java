package com.untralvious.demo.security.service.impl;

import com.untralvious.demo.security.domain.SysRole;
import com.untralvious.demo.security.domain.SysUserRole;
import com.untralvious.demo.security.repository.SysRoleRepository;
import com.untralvious.demo.security.service.SysRoleService;
import com.untralvious.demo.security.service.SysUserRoleService;
import com.untralvious.demo.security.service.dto.SysRoleDTO;
import com.untralvious.demo.security.service.mapper.SysRoleMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SysRole}.
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    private final Logger log = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    private final SysRoleRepository sysRoleRepository;

    private final SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    public SysRoleServiceImpl(SysRoleRepository sysRoleRepository, SysRoleMapper sysRoleMapper) {
        this.sysRoleRepository = sysRoleRepository;
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public SysRoleDTO save(SysRoleDTO sysRoleDTO) {
        log.debug("Request to save SysRole : {}", sysRoleDTO);
        SysRole sysRole = sysRoleMapper.toEntity(sysRoleDTO);
        sysRole = sysRoleRepository.save(sysRole);
        return sysRoleMapper.toDto(sysRole);
    }

    @Override
    public SysRoleDTO update(SysRoleDTO sysRoleDTO) {
        log.debug("Request to update SysRole : {}", sysRoleDTO);
        SysRole sysRole = sysRoleMapper.toEntity(sysRoleDTO);
        // no save call needed as we have no fields that can be updated
        return sysRoleMapper.toDto(sysRole);
    }

    @Override
    public Optional<SysRoleDTO> partialUpdate(SysRoleDTO sysRoleDTO) {
        log.debug("Request to partially update SysRole : {}", sysRoleDTO);

        return sysRoleRepository
            .findById(sysRoleDTO.getId())
            .map(existingSysRole -> {
                sysRoleMapper.partialUpdate(existingSysRole, sysRoleDTO);

                return existingSysRole;
            })
            // .map(sysRoleRepository::save)
            .map(sysRoleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SysRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysRoles");
        return sysRoleRepository.findAll(pageable).map(sysRoleMapper::toDto);
    }

    @Override
    public List<SysRoleDTO> findAllNotPaging() {
        return sysRoleRepository.findAll().stream().map(sysRoleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SysRoleDTO> findOne(String id) {
        log.debug("Request to get SysRole : {}", id);
        return sysRoleRepository.findById(id).map(sysRoleMapper::toDto);
    }

    @Override
    public Optional<SysRole> findOneByCode(String code) {
        return sysRoleRepository.findByRoleCode(code);
    }

    @Override
    public List<SysRole> getRolesById(List<String> roleIds) {
        return sysRoleRepository.findByIdIn(roleIds);
    }

    @Override
    public List<SysRole> getRoles(String userId) {
        return null;
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SysRole : {}", id);
        sysRoleRepository.deleteById(id);
    }
}
