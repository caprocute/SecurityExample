package com.untralvious.demo.security.repository;

import com.untralvious.demo.security.domain.SysUser;
import com.untralvious.demo.security.domain.User;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    String USERS_BY_LOGIN_CACHE = "usersByLogin";

    String USERS_BY_EMAIL_CACHE = "usersByEmail";
    Optional<SysUser> findOneByActivationKey(String activationKey);
    List<SysUser> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);
    Optional<SysUser> findOneByResetKey(String resetKey);
    Optional<SysUser> findOneByEmailIgnoreCase(String email);
    Optional<SysUser> findOneByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<SysUser> findOneWithAuthoritiesByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<SysUser> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<SysUser> findAllByIdNotNullAndActivatedIsTrue(Pageable pageable);
}
