package com.untralvious.demo.security.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.untralvious.demo.security.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SysRoleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRoleDTO.class);
        SysRoleDTO sysRoleDTO1 = new SysRoleDTO();
        sysRoleDTO1.setId(1L);
        SysRoleDTO sysRoleDTO2 = new SysRoleDTO();
        assertThat(sysRoleDTO1).isNotEqualTo(sysRoleDTO2);
        sysRoleDTO2.setId(sysRoleDTO1.getId());
        assertThat(sysRoleDTO1).isEqualTo(sysRoleDTO2);
        sysRoleDTO2.setId(2L);
        assertThat(sysRoleDTO1).isNotEqualTo(sysRoleDTO2);
        sysRoleDTO1.setId(null);
        assertThat(sysRoleDTO1).isNotEqualTo(sysRoleDTO2);
    }
}
