package com.untralvious.demo.security.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SysRoleMapperTest {

    private SysRoleMapper sysRoleMapper;

    @BeforeEach
    public void setUp() {
        sysRoleMapper = new SysRoleMapperImpl();
    }
}
