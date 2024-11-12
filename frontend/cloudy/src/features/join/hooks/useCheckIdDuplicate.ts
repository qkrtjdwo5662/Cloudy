import { useState } from "react";
import axios from "axios";

export const useCheckIdDuplicate = () => {
  const [idAvailable, setIdAvailable] = useState<boolean | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const checkIdDuplicate = async (id: string) => {
    setLoading(true);
    setError(null);

    try {
      const response = await axios.get(
        `http://k11a606.p.ssafy.io:8081/auth/duplicate/loginid?loginId=${id}`,
        {
          headers: {
            Accept: "*/*",
          },
        },
      );

      setIdAvailable(response.data.data);
    } catch (err) {
      setError("Failed to check ID availability.");
      console.error("Error checking ID:", err);
    } finally {
      setLoading(false);
    }
  };

  return { idAvailable, loading, error, checkIdDuplicate };
};
