package com.untralvious.demo.security.repository;

import com.untralvious.demo.security.domain.SysUserRole;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysUserRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserRoleRepository extends JpaRepository<SysUserRole, String> {
    List<SysUserRole> findByUserId(String userId);
}
