package com.untralvious.demo.security.repository;

import com.untralvious.demo.security.domain.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysLogRepository extends JpaRepository<SysLog, String> {
}
