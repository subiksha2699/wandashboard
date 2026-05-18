package com.example.sdwan.model.dto;

/**
 * Represents a network port (WAN or LAN) on an edge device.
 */
public class DevicePort {

    private String id;
    private String deviceId;
    private String portName;
    private String portType;        // WAN or LAN
    private String status;          // UP, DOWN
    private String ipAddress;
    private String subnet;
    private String speed;           // e.g. "1 Gbps", "10 Gbps"
    private Double bandwidthUtilization; // percentage 0-100
    private Double latencyMs;
    private Double packetLossPercent;

    public DevicePort() {}

    public DevicePort(String id, String deviceId, String portName, String portType,
                      String status, String ipAddress, String subnet, String speed,
                      Double bandwidthUtilization, Double latencyMs, Double packetLossPercent) {
        this.id = id;
        this.deviceId = deviceId;
        this.portName = portName;
        this.portType = portType;
        this.status = status;
        this.ipAddress = ipAddress;
        this.subnet = subnet;
        this.speed = speed;
        this.bandwidthUtilization = bandwidthUtilization;
        this.latencyMs = latencyMs;
        this.packetLossPercent = packetLossPercent;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getPortName() { return portName; }
    public void setPortName(String portName) { this.portName = portName; }

    public String getPortType() { return portType; }
    public void setPortType(String portType) { this.portType = portType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getSubnet() { return subnet; }
    public void setSubnet(String subnet) { this.subnet = subnet; }

    public String getSpeed() { return speed; }
    public void setSpeed(String speed) { this.speed = speed; }

    public Double getBandwidthUtilization() { return bandwidthUtilization; }
    public void setBandwidthUtilization(Double bandwidthUtilization) { this.bandwidthUtilization = bandwidthUtilization; }

    public Double getLatencyMs() { return latencyMs; }
    public void setLatencyMs(Double latencyMs) { this.latencyMs = latencyMs; }

    public Double getPacketLossPercent() { return packetLossPercent; }
    public void setPacketLossPercent(Double packetLossPercent) { this.packetLossPercent = packetLossPercent; }
}
