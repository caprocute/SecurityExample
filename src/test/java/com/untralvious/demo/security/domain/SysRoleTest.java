package com.untralvious.demo.security.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.untralvious.demo.security.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysRoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRole.class);
        SysRole sysRole1 = new SysRole();
        sysRole1.setId(1L);
        SysRole sysRole2 = new SysRole();
        sysRole2.setId(sysRole1.getId());
        assertThat(sysRole1).isEqualTo(sysRole2);
        sysRole2.setId(2L);
        assertThat(sysRole1).isNotEqualTo(sysRole2);
        sysRole1.setId(null);
        assertThat(sysRole1).isNotEqualTo(sysRole2);
    }
}
