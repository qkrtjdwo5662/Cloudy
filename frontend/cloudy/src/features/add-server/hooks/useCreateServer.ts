import { useMutation } from "@tanstack/react-query";
import { handleSuccess, handleError } from "@/shared/utils/mutationUtils";
import { ServerCreateRequest } from "../model/types";
import { createServer } from "../lib/fetchers";

export const useCreateServer = () => {
  return useMutation<void, Error, ServerCreateRequest>({
    mutationFn: (data: ServerCreateRequest) => createServer(data),
    onSuccess: (data) => {
      handleSuccess("✅ Created successfully", data);
    },
    onError: (error, Error) => {
      handleError("😥 Failed to create", error);
    },
  });
};
