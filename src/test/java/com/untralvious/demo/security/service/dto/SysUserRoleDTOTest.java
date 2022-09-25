package com.untralvious.demo.security.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.untralvious.demo.security.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysUserRoleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserRoleDTO.class);
        SysUserRoleDTO sysUserRoleDTO1 = new SysUserRoleDTO();
        sysUserRoleDTO1.setId(1L);
        SysUserRoleDTO sysUserRoleDTO2 = new SysUserRoleDTO();
        assertThat(sysUserRoleDTO1).isNotEqualTo(sysUserRoleDTO2);
        sysUserRoleDTO2.setId(sysUserRoleDTO1.getId());
        assertThat(sysUserRoleDTO1).isEqualTo(sysUserRoleDTO2);
        sysUserRoleDTO2.setId(2L);
        assertThat(sysUserRoleDTO1).isNotEqualTo(sysUserRoleDTO2);
        sysUserRoleDTO1.setId(null);
        assertThat(sysUserRoleDTO1).isNotEqualTo(sysUserRoleDTO2);
    }
}
