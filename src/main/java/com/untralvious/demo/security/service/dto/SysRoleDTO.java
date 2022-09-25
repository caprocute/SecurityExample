package com.untralvious.demo.security.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.untralvious.demo.security.domain.SysRole} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysRoleDTO implements Serializable {

    private String id;
    private String roleName;
    private String roleCode;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRoleDTO)) {
            return false;
        }

        SysRoleDTO sysRoleDTO = (SysRoleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sysRoleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysRoleDTO{" +
            "id=" + getId() +
            "}";
    }
}
