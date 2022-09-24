package com.untralvious.demo.security.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sys_role_permission", schema = "securityexample", catalog = "")
public class SysRolePermission {
    private String id;
    private String roleId;
    private String permissionId;
    private String dataRuleIds;
    private Timestamp operateDate;
    private String operateIp;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_id", nullable = true, length = 32)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "permission_id", nullable = true, length = 32)
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "data_rule_ids", nullable = true, length = 1000)
    public String getDataRuleIds() {
        return dataRuleIds;
    }

    public void setDataRuleIds(String dataRuleIds) {
        this.dataRuleIds = dataRuleIds;
    }

    @Basic
    @Column(name = "operate_date", nullable = true)
    public Timestamp getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Timestamp operateDate) {
        this.operateDate = operateDate;
    }

    @Basic
    @Column(name = "operate_ip", nullable = true, length = 100)
    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRolePermission that = (SysRolePermission) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false;
        if (dataRuleIds != null ? !dataRuleIds.equals(that.dataRuleIds) : that.dataRuleIds != null) return false;
        if (operateDate != null ? !operateDate.equals(that.operateDate) : that.operateDate != null) return false;
        if (operateIp != null ? !operateIp.equals(that.operateIp) : that.operateIp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
        result = 31 * result + (dataRuleIds != null ? dataRuleIds.hashCode() : 0);
        result = 31 * result + (operateDate != null ? operateDate.hashCode() : 0);
        result = 31 * result + (operateIp != null ? operateIp.hashCode() : 0);
        return result;
    }
}
