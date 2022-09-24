package com.untralvious.demo.security.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sys_permission", schema = "securityexample", catalog = "")
public class SysPermission {
    private String id;
    private String parentId;
    private String name;
    private String url;
    private String component;
    private String componentName;
    private String redirect;
    private Integer menuType;
    private String perms;
    private String permsType;
    private Double sortNo;
    private Byte alwaysShow;
    private String icon;
    private Byte isRoute;
    private Byte isLeaf;
    private Byte keepAlive;
    private Byte hidden;
    private Byte hideTab;
    private String description;
    private String createBy;
    private Timestamp createTime;
    private String updateBy;
    private Timestamp updateTime;
    private Integer delFlag;
    private Integer ruleFlag;
    private String status;
    private Byte internalOrExternal;

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
    @Column(name = "parent_id", nullable = true, length = 32)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "component", nullable = true, length = 255)
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Basic
    @Column(name = "component_name", nullable = true, length = 100)
    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    @Basic
    @Column(name = "redirect", nullable = true, length = 255)
    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    @Basic
    @Column(name = "menu_type", nullable = true)
    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    @Basic
    @Column(name = "perms", nullable = true, length = 255)
    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Basic
    @Column(name = "perms_type", nullable = true, length = 10)
    public String getPermsType() {
        return permsType;
    }

    public void setPermsType(String permsType) {
        this.permsType = permsType;
    }

    @Basic
    @Column(name = "sort_no", nullable = true, precision = 2)
    public Double getSortNo() {
        return sortNo;
    }

    public void setSortNo(Double sortNo) {
        this.sortNo = sortNo;
    }

    @Basic
    @Column(name = "always_show", nullable = true)
    public Byte getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(Byte alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    @Basic
    @Column(name = "icon", nullable = true, length = 100)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "is_route", nullable = true)
    public Byte getIsRoute() {
        return isRoute;
    }

    public void setIsRoute(Byte isRoute) {
        this.isRoute = isRoute;
    }

    @Basic
    @Column(name = "is_leaf", nullable = true)
    public Byte getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Byte isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Basic
    @Column(name = "keep_alive", nullable = true)
    public Byte getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Byte keepAlive) {
        this.keepAlive = keepAlive;
    }

    @Basic
    @Column(name = "hidden", nullable = true)
    public Byte getHidden() {
        return hidden;
    }

    public void setHidden(Byte hidden) {
        this.hidden = hidden;
    }

    @Basic
    @Column(name = "hide_tab", nullable = true)
    public Byte getHideTab() {
        return hideTab;
    }

    public void setHideTab(Byte hideTab) {
        this.hideTab = hideTab;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_by", nullable = true, length = 32)
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "del_flag", nullable = true)
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Basic
    @Column(name = "rule_flag", nullable = true)
    public Integer getRuleFlag() {
        return ruleFlag;
    }

    public void setRuleFlag(Integer ruleFlag) {
        this.ruleFlag = ruleFlag;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "internal_or_external", nullable = true)
    public Byte getInternalOrExternal() {
        return internalOrExternal;
    }

    public void setInternalOrExternal(Byte internalOrExternal) {
        this.internalOrExternal = internalOrExternal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysPermission that = (SysPermission) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (component != null ? !component.equals(that.component) : that.component != null) return false;
        if (componentName != null ? !componentName.equals(that.componentName) : that.componentName != null)
            return false;
        if (redirect != null ? !redirect.equals(that.redirect) : that.redirect != null) return false;
        if (menuType != null ? !menuType.equals(that.menuType) : that.menuType != null) return false;
        if (perms != null ? !perms.equals(that.perms) : that.perms != null) return false;
        if (permsType != null ? !permsType.equals(that.permsType) : that.permsType != null) return false;
        if (sortNo != null ? !sortNo.equals(that.sortNo) : that.sortNo != null) return false;
        if (alwaysShow != null ? !alwaysShow.equals(that.alwaysShow) : that.alwaysShow != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (isRoute != null ? !isRoute.equals(that.isRoute) : that.isRoute != null) return false;
        if (isLeaf != null ? !isLeaf.equals(that.isLeaf) : that.isLeaf != null) return false;
        if (keepAlive != null ? !keepAlive.equals(that.keepAlive) : that.keepAlive != null) return false;
        if (hidden != null ? !hidden.equals(that.hidden) : that.hidden != null) return false;
        if (hideTab != null ? !hideTab.equals(that.hideTab) : that.hideTab != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (delFlag != null ? !delFlag.equals(that.delFlag) : that.delFlag != null) return false;
        if (ruleFlag != null ? !ruleFlag.equals(that.ruleFlag) : that.ruleFlag != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (internalOrExternal != null ? !internalOrExternal.equals(that.internalOrExternal) : that.internalOrExternal != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (component != null ? component.hashCode() : 0);
        result = 31 * result + (componentName != null ? componentName.hashCode() : 0);
        result = 31 * result + (redirect != null ? redirect.hashCode() : 0);
        result = 31 * result + (menuType != null ? menuType.hashCode() : 0);
        result = 31 * result + (perms != null ? perms.hashCode() : 0);
        result = 31 * result + (permsType != null ? permsType.hashCode() : 0);
        result = 31 * result + (sortNo != null ? sortNo.hashCode() : 0);
        result = 31 * result + (alwaysShow != null ? alwaysShow.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (isRoute != null ? isRoute.hashCode() : 0);
        result = 31 * result + (isLeaf != null ? isLeaf.hashCode() : 0);
        result = 31 * result + (keepAlive != null ? keepAlive.hashCode() : 0);
        result = 31 * result + (hidden != null ? hidden.hashCode() : 0);
        result = 31 * result + (hideTab != null ? hideTab.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        result = 31 * result + (ruleFlag != null ? ruleFlag.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (internalOrExternal != null ? internalOrExternal.hashCode() : 0);
        return result;
    }

}
