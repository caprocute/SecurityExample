package com.untralvious.demo.security.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sys_log", schema = "securityexample", catalog = "")
public class SysLog {
    private String id;
    private Integer logType;
    private String logContent;
    private Integer operateType;
    private String userid;
    private String username;
    private String ip;
    private String method;
    private String requestUrl;
    private String requestParam;
    private String requestType;
    private Long costTime;
    private String createBy;
    private Timestamp createTime;
    private String updateBy;
    private Timestamp updateTime;
    private String responseCode;
    private String responseContent;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "log_type", nullable = true)
    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    @Basic
    @Column(name = "log_content", nullable = true, length = 1000)
    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    @Basic
    @Column(name = "operate_type", nullable = true)
    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    @Basic
    @Column(name = "userid", nullable = true, length = 32)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 100)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "method", nullable = true, length = 500)
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "request_url", nullable = true, length = 255)
    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Basic
    @Column(name = "request_param", nullable = true, length = -1)
    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    @Basic
    @Column(name = "request_type", nullable = true, length = 10)
    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Basic
    @Column(name = "cost_time", nullable = true)
    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
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
    @Column(name = "response_code", nullable = true, length = 100)
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @Basic
    @Column(name = "response_content", nullable = true, length = 100)
    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysLog sysLog = (SysLog) o;

        if (id != null ? !id.equals(sysLog.id) : sysLog.id != null) return false;
        if (logType != null ? !logType.equals(sysLog.logType) : sysLog.logType != null) return false;
        if (logContent != null ? !logContent.equals(sysLog.logContent) : sysLog.logContent != null) return false;
        if (operateType != null ? !operateType.equals(sysLog.operateType) : sysLog.operateType != null) return false;
        if (userid != null ? !userid.equals(sysLog.userid) : sysLog.userid != null) return false;
        if (username != null ? !username.equals(sysLog.username) : sysLog.username != null) return false;
        if (ip != null ? !ip.equals(sysLog.ip) : sysLog.ip != null) return false;
        if (method != null ? !method.equals(sysLog.method) : sysLog.method != null) return false;
        if (requestUrl != null ? !requestUrl.equals(sysLog.requestUrl) : sysLog.requestUrl != null) return false;
        if (requestParam != null ? !requestParam.equals(sysLog.requestParam) : sysLog.requestParam != null)
            return false;
        if (requestType != null ? !requestType.equals(sysLog.requestType) : sysLog.requestType != null) return false;
        if (costTime != null ? !costTime.equals(sysLog.costTime) : sysLog.costTime != null) return false;
        if (createBy != null ? !createBy.equals(sysLog.createBy) : sysLog.createBy != null) return false;
        if (createTime != null ? !createTime.equals(sysLog.createTime) : sysLog.createTime != null) return false;
        if (updateBy != null ? !updateBy.equals(sysLog.updateBy) : sysLog.updateBy != null) return false;
        if (updateTime != null ? !updateTime.equals(sysLog.updateTime) : sysLog.updateTime != null) return false;
        if (responseCode != null ? !responseCode.equals(sysLog.responseCode) : sysLog.responseCode != null)
            return false;
        if (responseContent != null ? !responseContent.equals(sysLog.responseContent) : sysLog.responseContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (logType != null ? logType.hashCode() : 0);
        result = 31 * result + (logContent != null ? logContent.hashCode() : 0);
        result = 31 * result + (operateType != null ? operateType.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (requestUrl != null ? requestUrl.hashCode() : 0);
        result = 31 * result + (requestParam != null ? requestParam.hashCode() : 0);
        result = 31 * result + (requestType != null ? requestType.hashCode() : 0);
        result = 31 * result + (costTime != null ? costTime.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (responseCode != null ? responseCode.hashCode() : 0);
        result = 31 * result + (responseContent != null ? responseContent.hashCode() : 0);
        return result;
    }
}
