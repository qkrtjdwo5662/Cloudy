import { handleApiRequest } from "@/shared/api/client";
import {
  ContainerMonitoringResponse,
  ContainerGetUseResponses,
} from "../model/types";

export const fetchContainerMonitoring = async (
  serverId: number,
  unit: string,
  interval: number,
  count: number,
) => {
  const getCurrentDateTime = () => {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day = String(now.getDate()).padStart(2, "0");
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    const seconds = String(now.getSeconds()).padStart(2, "0");
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  };
  const response = await handleApiRequest<ContainerMonitoringResponse, "get">(
    `/containers/monitoring?serverId=${serverId}&dateTime=${getCurrentDateTime()}&unit=${unit}&interval=${interval}&count=${count}`,
    "get",
  );
  // console.log("API Response:", response);
  return response;
};

export const fetchContainerCount = async (
  serverId: number,
  unit: string,
  interval: number,
  count: number,
) => {
  const getCurrentDateTime = () => {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day = String(now.getDate()).padStart(2, "0");
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    const seconds = String(now.getSeconds()).padStart(2, "0");
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  };

  const response = await handleApiRequest<ContainerGetUseResponses, "get">(
    `/containers/usage?serverId=${serverId}&dateTime=${getCurrentDateTime()}&unit=${unit}&interval=${interval}&count=${count}`,
    "get",
  );

  // 응답 데이터 확인
  // console.log("fetchContainerCount Response:", response);

  // 필요한 데이터만 반환
  return response;
};
