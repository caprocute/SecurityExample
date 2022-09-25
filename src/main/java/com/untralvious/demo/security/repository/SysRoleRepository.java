package com.untralvious.demo.security.repository;

import com.untralvious.demo.security.domain.SysRole;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SysRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, String> {
    Optional<SysRole> findByRoleCode(String code);

    List<SysRole> findByIdIn(List<String> roleIds);
}
