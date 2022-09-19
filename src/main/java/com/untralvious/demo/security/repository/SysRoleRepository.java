package com.untralvious.demo.security.repository;

import com.untralvious.demo.security.domain.SysRole;
import com.untralvious.demo.security.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
    Optional<SysRole> findOneByRoleCode(String roleCode);

    @Query(
        value = "Select sysRole.roleName from SysRole sysRole " +
            " join SysUserRole sysUserRole on sysRole.id=sysUserRole.roleId" +
            " join SysUser sysUser on sysUser.id=sysUserRole.userId " +
            " where sysUser.id= :id"
    )
    List<String> findAllRoleByUserId(@Param("id") String id);
}
