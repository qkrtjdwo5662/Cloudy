export interface ServerMonitoringResponse {
  serverId: number;
  duration: number;
  containerCount: number;
  callCount: number;
  internalResourceUsage: number;
  externalResourceUsage: number;
}

export interface ServerMonitoringResponses {
  data: ServerMonitoringResponse[];
}

export interface ContainerGetUsageResponse {
  serviceId: number;
  serviceName: string;
  usage: number;
  cost: number;
}

export interface ContainerUsageCostResponse {
  data: ContainerGetUsageResponse[];
  size: number;
}
