export interface ContainerDailyCostInfo {
  day: number;
  cost: number;
}

export interface CostCalendarResponse {
  containerId: number;
  containerDailiCostInfos: ContainerDailyCostInfo[];
  containerDailiyCostInfosSize: number;
}
