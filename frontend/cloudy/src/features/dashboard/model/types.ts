export interface ServerMonitoringResponse {
  countList: [];
  timeList: [];
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

export interface ServerUsageGetResponse {
  cpuPercent: number;
  memUsage: number;
  memLimit: number;
}
