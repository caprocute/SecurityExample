package com.untralvious.demo.security.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A SysRole.
 */
@Entity
@Table(name = "sys_role", schema = "securityexample", catalog = "")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "role")
    Set<SysUserRole> sysUserRoles;

    @Id
    @GenericGenerator(name = "uuid", strategy = "com.untralvious.demo.security.service.IdGenerator")
    @GeneratedValue(generator = "uuid")
    private String id;

    private String roleName;
    private String roleCode;
    private String description;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public SysRole id(String id) {
        this.setId(id);
        return this;
    }

    public Set<SysUserRole> getSysUserRoles() {
        return sysUserRoles;
    }

    public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
        this.sysUserRoles = sysUserRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRole)) {
            return false;
        }
        return id != null && id.equals(((SysRole) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysRole{" +
            "id=" + getId() +
            "}";
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_code")
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
