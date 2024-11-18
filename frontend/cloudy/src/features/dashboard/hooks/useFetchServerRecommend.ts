import { useState, useCallback } from "react";
import axios from "axios";
import { useAuthStore } from "@/shared/stores/authStore";

export const useFetchServerRecommend = () => {
  const [serverRecommend, setServerRecommend] = useState<any[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const serverId = useAuthStore((state) => state.serverId);
  const accessToken = useAuthStore((state) => state.accessToken);

  const fetchServerRecommend = useCallback(async () => {
    if (!serverId || !accessToken) {
      setError("Server ID or Access token is missing.");
      return;
    }

    setLoading(true);
    setError(null);

    try {
      const response = await axios.get(
        `http://k11a606.p.ssafy.io:8081/servers/recommend?serverId=${serverId}`,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
            Accept: "*/*",
          },
        },
      );

      setServerRecommend(response.data.data || []);
    } catch (err) {
      setError("Failed to fetch server recommendation data.");
      // console.error("Error fetching server recommendation:", err);
    } finally {
      setLoading(false);
    }
  }, [serverId, accessToken]);

  return { serverRecommend, loading, error, fetchServerRecommend };
};
