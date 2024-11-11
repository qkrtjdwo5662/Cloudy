import { useEffect, useState } from "react";
import { useAuthStore } from "@/shared/stores/authStore";

interface Server {
  serverId: number;
  serverName: string;
}

export const useFetchServers = () => {
  const [servers, setServers] = useState<Server[]>([]);
  const [loading, setLoading] = useState(true);
  const accessToken = useAuthStore((state) => state.accessToken);

  useEffect(() => {
    if (!accessToken) return;

    const fetchServers = async () => {
      setLoading(true);
      try {
        const response = await fetch("http://k11a606.p.ssafy.io:8081/servers", {
          headers: {
            Authorization: `Bearer ${accessToken}`,
            Accept: "*/*",
          },
        });
        const data = await response.json();
        setServers(data.data);
      } catch (error) {
        console.error("Failed to fetch servers", error);
      } finally {
        setLoading(false);
      }
    };

    fetchServers();
  }, [accessToken]);

  return { servers, loading };
};
