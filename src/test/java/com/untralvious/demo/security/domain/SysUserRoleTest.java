package com.untralvious.demo.security.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.untralvious.demo.security.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysUserRoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserRole.class);
        SysUserRole sysUserRole1 = new SysUserRole();
        sysUserRole1.setId(1L);
        SysUserRole sysUserRole2 = new SysUserRole();
        sysUserRole2.setId(sysUserRole1.getId());
        assertThat(sysUserRole1).isEqualTo(sysUserRole2);
        sysUserRole2.setId(2L);
        assertThat(sysUserRole1).isNotEqualTo(sysUserRole2);
        sysUserRole1.setId(null);
        assertThat(sysUserRole1).isNotEqualTo(sysUserRole2);
    }
}
