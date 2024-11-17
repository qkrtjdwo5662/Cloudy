import { handleApiRequest } from "@/shared/api/client";
import {
  ServerGetResponses,
  ServerCreateRequest,
  InstanceGetResponses,
  InstanceDetailGetResponses,
} from "../model/types";

export const fetchServer = async () => {
  return handleApiRequest<ServerGetResponses, "get">(`/servers`, "get");
};

export const createServer = async (data: ServerCreateRequest) => {
  return handleApiRequest<void, "post", ServerCreateRequest>(
    `/servers/create`,
    "post",
    data,
  );
};

export const deleteServer = async (serverId: number) => {
  return handleApiRequest<void, "delete">(
    `/servers/update?serverId=${serverId}`,
    "delete",
  );
};

export const fetchInstances = async (cloudType: string) => {
  return handleApiRequest<InstanceGetResponses, "get">(
    `/instances/type?cloudType=${cloudType}`,
    "get",
  );
};

export const fetchInstanceDetail = async () => {
  return handleApiRequest<InstanceDetailGetResponses, "get">(
    `/instances/details`,
    "get",
  );
};
