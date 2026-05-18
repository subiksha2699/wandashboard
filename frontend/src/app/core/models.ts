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
