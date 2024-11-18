export interface ContainerMonitoringResponse {
  timeList: string[];
  countLists: number[][];
  containerNameList: string[];
}

export interface ContainerGetUseResponse {
  containerId: number;
  containerName: string;
  serviceRequestCount: number;
}

export interface ContainerGetUseResponses {
  containerGetUseResponses: ContainerGetUseResponse[];
}
