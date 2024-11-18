export interface ServerMonitoringResponse {
  countList: [];
  timeList: [];
}

export interface ServerUsageGetResponse {
  cpuPercent: number;
  memUsage: number;
  memLimit: number;
}
