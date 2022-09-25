package com.untralvious.demo.security.service.mapper;

import com.untralvious.demo.security.domain.SysRole;
import com.untralvious.demo.security.service.dto.SysRoleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysRole} and its DTO {@link SysRoleDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysRoleMapper extends EntityMapper<SysRoleDTO, SysRole> {}
