export interface ServerCreateRequest {
  serverName: string;
  cloudType: string;
  instanceType: string;
  paymentType: string;
}

export interface ServerGetResponse {
  serverId: number;
  serverName: string;
  cloudType: string;
  instanceType: string;
  paymentType: string;
  memory: string;
  instanceStorage: string;
  networkBandwidth: string;
  cpu: string;
}

export interface ServerGetResponses {
  data: ServerGetResponse[];
}

export interface InstanceGetResponse {
  instanceId: number;
  instanceName: string;
}

export interface InstanceGetResponses {
  data: InstanceGetResponse[];
}

export interface InstanceDetailGetResponse {
  instanceId: number;
  instanceName: string;
  memory: string;
  instanceStorage: string;
  networkBandwidth: string;
  vcpu: string;
}

export interface InstanceDetailGetResponses {
  data: InstanceDetailGetResponse[];
}
