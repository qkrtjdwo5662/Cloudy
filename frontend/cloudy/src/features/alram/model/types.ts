export interface AlarmGetResponse {
  alarmId: number;
  serverName: string;
  title: string;
  content: string;
  createdAt: string;
  read: boolean;
}

export interface AlarmGetResponses {
  data: AlarmGetResponse[];
}
