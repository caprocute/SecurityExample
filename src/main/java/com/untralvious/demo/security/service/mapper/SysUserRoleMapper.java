package com.untralvious.demo.security.service.mapper;

import com.untralvious.demo.security.domain.SysUserRole;
import com.untralvious.demo.security.service.dto.SysUserRoleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysUserRole} and its DTO {@link SysUserRoleDTO}.
 */
@Mapper(componentModel = "spring")
public interface SysUserRoleMapper extends EntityMapper<SysUserRoleDTO, SysUserRole> {}
