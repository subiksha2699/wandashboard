package com.example.sdwan.model.dto;

import com.example.sdwan.model.enums.DeviceStatus;

import java.util.Date;
import java.util.List;

/**
 * DTO for edge device detail view, includes port information.
 */
public class EdgeDeviceDetailDTO {

    private String id;
    private String deviceName;
    private String siteId;
    private String role;
    private String systemIp;
    private DeviceStatus status;
    private Date lastUpTime;
    private Date lastDownTime;
    private List<DevicePort> wanPorts;
    private List<DevicePort> lanPorts;

    public EdgeDeviceDetailDTO() {}

    public EdgeDeviceDetailDTO(EdgeDevice device, List<DevicePort> wanPorts, List<DevicePort> lanPorts) {
        this.id = device.getId();
        this.deviceName = device.getDeviceName();
        this.siteId = device.getSiteId();
        this.role = device.getRole();
        this.systemIp = device.getSystemIp();
        this.status = device.getStatus();
        this.lastUpTime = device.getLastUpTime();
        this.lastDownTime = device.getLastDownTime();
        this.wanPorts = wanPorts;
        this.lanPorts = lanPorts;
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

    public List<DevicePort> getWanPorts() { return wanPorts; }
    public void setWanPorts(List<DevicePort> wanPorts) { this.wanPorts = wanPorts; }

    public List<DevicePort> getLanPorts() { return lanPorts; }
    public void setLanPorts(List<DevicePort> lanPorts) { this.lanPorts = lanPorts; }
}
