export interface ServiceMonitoringResponse {
  timeList: string[];
  countLists: number[][];
  serviceNameList: string[];
}

export interface ServiceGetCount {
  serviceId: number;
  serviceName: string;
  count: number;
}

export interface ServiceGetCounts {
  serviceUseGetResponses: ServiceGetCount[];
}
