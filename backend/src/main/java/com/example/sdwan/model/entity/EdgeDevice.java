package com.example.sdwan.model.entity;

import com.example.sdwan.enums.DeviceStatus;

import java.util.Date;

public class EdgeDevice {
    private Integer id;
    private String  deviceName;
    private Integer siteId;
    private String role;
    private String systemIp;
    private DeviceStatus status;
    private Date lastUpTime;
    private Date lastDownTime;
    private Date createTime;
    private Date updateTime;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSystemIp() {
        return systemIp;
    }

    public void setSystemIp(String systemIp) {
        this.systemIp = systemIp;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public Date getLastUpTime() {
        return lastUpTime;
    }

    public void setLastUpTime(Date lastUpTime) {
        this.lastUpTime = lastUpTime;
    }

    public Date getLastDownTime() {
        return lastDownTime;
    }

    public void setLastDownTime(Date lastDownTime) {
        this.lastDownTime = lastDownTime;
    }
}
