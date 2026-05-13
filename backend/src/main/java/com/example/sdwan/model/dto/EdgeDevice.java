package com.example.sdwan.model.dto;

import com.example.sdwan.model.enums.DeviceStatus;

import java.util.Date;

public class EdgeDevice {

    private String id;
    private String deviceName;
    private String siteId;
    private String role;
    private String systemIp;
    private DeviceStatus status;
    private Date lastUpTime;
    private Date lastDownTime;
    private Date createdAt;
    private Date updatedAt;

    public EdgeDevice() {}

    public EdgeDevice(String id, String deviceName, String siteId, String role, String systemIp,
                      DeviceStatus status, Date lastUpTime, Date lastDownTime) {
        this.id = id;
        this.deviceName = deviceName;
        this.siteId = siteId;
        this.role = role;
        this.systemIp = systemIp;
        this.status = status;
        this.lastUpTime = lastUpTime;
        this.lastDownTime = lastDownTime;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getSiteId() { return siteId; }
    public void setSiteId(String siteId) { this.siteId = siteId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getSystemIp() { return systemIp; }
    public void setSystemIp(String systemIp) { this.systemIp = systemIp; }

    public DeviceStatus getStatus() { return status; }
    public void setStatus(DeviceStatus status) { this.status = status; }

    public Date getLastUpTime() { return lastUpTime; }
    public void setLastUpTime(Date lastUpTime) { this.lastUpTime = lastUpTime; }

    public Date getLastDownTime() { return lastDownTime; }
    public void setLastDownTime(Date lastDownTime) { this.lastDownTime = lastDownTime; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
