package com.untralvious.demo.security.domain;

import java.sql.Timestamp;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sys_user", schema = "security_example", catalog = "")
public class SysUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "realname")
    private String realname;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "salt")
    private String salt;

    @Basic
    @Column(name = "avatar")
    private String avatar;

    @Basic
    @Column(name = "birthday")
    private Timestamp birthday;

    @Basic
    @Column(name = "sex")
    private Byte sex;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "org_code")
    private String orgCode;

    @Basic
    @Column(name = "status")
    private Byte status;

    @Basic
    @Column(name = "del_flag")
    private Byte delFlag;

    @Basic
    @Column(name = "third_id")
    private String thirdId;

    @Basic
    @Column(name = "third_type")
    private String thirdType;

    @Basic
    @Column(name = "activiti_sync")
    private Byte activitiSync;

    @Basic
    @Column(name = "work_no")
    private String workNo;

    @Basic
    @Column(name = "post")
    private String post;

    @Basic
    @Column(name = "telephone")
    private String telephone;

    @Basic
    @Column(name = "create_by")
    private String createBy;

    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;

    @Basic
    @Column(name = "update_by")
    private String updateBy;

    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Basic
    @Column(name = "user_identity")
    private Byte userIdentity;

    @Basic
    @Column(name = "depart_ids")
    private String departIds;

    @Basic
    @Column(name = "rel_tenant_ids")
    private String relTenantIds;

    @Basic
    @Column(name = "client_id")
    private String clientId;

    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public Byte getActivitiSync() {
        return activitiSync;
    }

    public void setActivitiSync(Byte activitiSync) {
        this.activitiSync = activitiSync;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(Byte userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getDepartIds() {
        return departIds;
    }

    public void setDepartIds(String departIds) {
        this.departIds = departIds;
    }

    public String getRelTenantIds() {
        return relTenantIds;
    }

    public void setRelTenantIds(String relTenantIds) {
        this.relTenantIds = relTenantIds;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLogin() {
        return this.username;
    }

    public void setLogin(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUser sysUser = (SysUser) o;

        if (id != null ? !id.equals(sysUser.id) : sysUser.id != null) return false;
        if (username != null ? !username.equals(sysUser.username) : sysUser.username != null) return false;
        if (realname != null ? !realname.equals(sysUser.realname) : sysUser.realname != null) return false;
        if (password != null ? !password.equals(sysUser.password) : sysUser.password != null) return false;
        if (salt != null ? !salt.equals(sysUser.salt) : sysUser.salt != null) return false;
        if (avatar != null ? !avatar.equals(sysUser.avatar) : sysUser.avatar != null) return false;
        if (birthday != null ? !birthday.equals(sysUser.birthday) : sysUser.birthday != null) return false;
        if (sex != null ? !sex.equals(sysUser.sex) : sysUser.sex != null) return false;
        if (email != null ? !email.equals(sysUser.email) : sysUser.email != null) return false;
        if (phone != null ? !phone.equals(sysUser.phone) : sysUser.phone != null) return false;
        if (orgCode != null ? !orgCode.equals(sysUser.orgCode) : sysUser.orgCode != null) return false;
        if (status != null ? !status.equals(sysUser.status) : sysUser.status != null) return false;
        if (delFlag != null ? !delFlag.equals(sysUser.delFlag) : sysUser.delFlag != null) return false;
        if (thirdId != null ? !thirdId.equals(sysUser.thirdId) : sysUser.thirdId != null) return false;
        if (thirdType != null ? !thirdType.equals(sysUser.thirdType) : sysUser.thirdType != null) return false;
        if (activitiSync != null ? !activitiSync.equals(sysUser.activitiSync) : sysUser.activitiSync != null) return false;
        if (workNo != null ? !workNo.equals(sysUser.workNo) : sysUser.workNo != null) return false;
        if (post != null ? !post.equals(sysUser.post) : sysUser.post != null) return false;
        if (telephone != null ? !telephone.equals(sysUser.telephone) : sysUser.telephone != null) return false;
        if (createBy != null ? !createBy.equals(sysUser.createBy) : sysUser.createBy != null) return false;
        if (createTime != null ? !createTime.equals(sysUser.createTime) : sysUser.createTime != null) return false;
        if (updateBy != null ? !updateBy.equals(sysUser.updateBy) : sysUser.updateBy != null) return false;
        if (updateTime != null ? !updateTime.equals(sysUser.updateTime) : sysUser.updateTime != null) return false;
        if (userIdentity != null ? !userIdentity.equals(sysUser.userIdentity) : sysUser.userIdentity != null) return false;
        if (departIds != null ? !departIds.equals(sysUser.departIds) : sysUser.departIds != null) return false;
        if (relTenantIds != null ? !relTenantIds.equals(sysUser.relTenantIds) : sysUser.relTenantIds != null) return false;
        if (clientId != null ? !clientId.equals(sysUser.clientId) : sysUser.clientId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (orgCode != null ? orgCode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        result = 31 * result + (thirdId != null ? thirdId.hashCode() : 0);
        result = 31 * result + (thirdType != null ? thirdType.hashCode() : 0);
        result = 31 * result + (activitiSync != null ? activitiSync.hashCode() : 0);
        result = 31 * result + (workNo != null ? workNo.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (userIdentity != null ? userIdentity.hashCode() : 0);
        result = 31 * result + (departIds != null ? departIds.hashCode() : 0);
        result = 31 * result + (relTenantIds != null ? relTenantIds.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }
}
