export interface HealthResponse {
  status: string;
  service: string;
  timestamp: string;
  assignment: string;
}

export interface SiteSummary {
  id: string;
  name: string;
  organisationId: string;
  totalEdges: number;
  healthyDevices: number;
  degradedDevices: number;
  downDevices: number;
  healthStatus: 'HEALTHY' | 'DEGRADED' | 'DOWN';
  createdAt: string;
  updatedAt: string;
}

export interface Organisation {
  id: string;
  name: string;
  totalSites: number;
  createdAt: string;
  updatedAt: string;
}

export interface EdgeDevice {
  id: string;
  deviceName: string;
  siteId: string;
  role: string;
  systemIp: string;
  status: 'HEALTHY' | 'DOWN' | 'DEGRADED' | 'MAINTENANCE';
  lastUpTime: string | null;
  lastDownTime: string | null;
}

export interface DevicePort {
  id: string;
  deviceId: string;
  portName: string;
  portType: 'WAN' | 'LAN';
  status: 'UP' | 'DOWN';
  ipAddress: string;
  subnet: string;
  speed: string;
  bandwidthUtilization: number;
  latencyMs: number;
  packetLossPercent: number;
}

export interface EdgeDeviceDetail {
  id: string;
  deviceName: string;
  siteId: string;
  role: string;
  systemIp: string;
  status: 'HEALTHY' | 'DOWN' | 'DEGRADED' | 'MAINTENANCE';
  lastUpTime: string | null;
  lastDownTime: string | null;
  wanPorts: DevicePort[];
  lanPorts: DevicePort[];
}
