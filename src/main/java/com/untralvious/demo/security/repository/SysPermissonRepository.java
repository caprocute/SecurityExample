package com.untralvious.demo.security.repository;

import com.untralvious.demo.security.domain.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysPermissonRepository extends JpaRepository<SysPermission, String> {

    @Query(value =  " Select sp from SysPermission sp " +
                    " join SysRolePermission srp on srp.permissionId = sp.id " +
                    " join SysRole sr on sr.id = srp.roleId" +
                    " join SysUserRole sur on sur.roleId = sr.id " +
                    " join SysUser su on su.id = sur.userId " +
                    " where 1=1 and su.login= :user ")
    List<SysPermission> getAllPermission(@Param("user") String user);
}
