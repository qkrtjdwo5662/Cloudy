import { useEffect, useState } from "react";
import axios from "axios";
import { useAuthStore } from "@/shared/stores/authStore";

interface Server {
  containerId: number;
  containerName: string;
}

export const useFetchContainers = () => {
  const [containers, setContainers] = useState<Server[]>([]);
  const [loading, setLoading] = useState(true);
  const accessToken = useAuthStore((state) => state.accessToken);
  const serverId = useAuthStore((state) => state.serverId);

  useEffect(() => {
    if (!accessToken) return;

    const fetchContainers = async () => {
      setLoading(true);
      try {
        const response = await axios.get(
          `http://k11a606.p.ssafy.io:8081/containers/${serverId}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
              Accept: "*/*",
            },
          },
        );
        setContainers(response.data.data.containerGetResponses);
        // console.log("Container", containers);
      } catch (error) {
        // console.error("Failed to fetch containers", error);
      } finally {
        setLoading(false);
      }
    };

    fetchContainers();
  }, [accessToken]);

  return { containers, loading };
};
