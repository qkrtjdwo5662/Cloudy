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
