import { useMutation } from "@tanstack/react-query";
import { deleteServer } from "../lib/fetchers";

export const useDeleteServer = () => {
  return useMutation({
    mutationFn: (serverId: number) => deleteServer(serverId),
    onSuccess: () => {},
    onError: (error) => {
      console.error("Error deleting server:", error);
    },
  });
};
