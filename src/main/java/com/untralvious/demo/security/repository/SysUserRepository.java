package com.untralvious.demo.security.repository;

import com.untralvious.demo.security.domain.SysUser;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link SysUser} entity.
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    String USERS_BY_LOGIN_CACHE = "usersByLogin";

    String USERS_BY_EMAIL_CACHE = "usersByEmail";
//    Optional<SysUser> findOneByActivationKey(String activationKey);
//    List<SysUser> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);
//    Optional<SysUser> findOneByResetKey(String resetKey);
    Optional<SysUser> findOneByEmailIgnoreCase(String email);
    Optional<SysUser> findOneByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<SysUser> findOneWithAuthoritiesByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<SysUser> findOneWithAuthoritiesByEmailIgnoreCase(String email);

//    Page<SysUser> findAllByIdNotNullAndActivatedIsTrue(Pageable pageable);
}
