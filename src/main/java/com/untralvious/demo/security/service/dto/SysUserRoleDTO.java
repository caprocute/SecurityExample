package com.untralvious.demo.security.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.untralvious.demo.security.domain.SysUserRole} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysUserRoleDTO implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserRoleDTO)) {
            return false;
        }

        SysUserRoleDTO sysUserRoleDTO = (SysUserRoleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sysUserRoleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysUserRoleDTO{" +
            "id=" + getId() +
            "}";
    }
}
