import { useState } from "react";
import axios from "axios";

export const useCheckIdDuplicate = () => {
  const [idAvailable, setIdAvailable] = useState<boolean | null>(null);
  const [idLoading, setIdLoading] = useState<boolean>(false);
  const [idError, setIdError] = useState<string | null>(null);

  const checkIdDuplicate = async (id: string) => {
    setIdLoading(true);
    setIdError(null);

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
      setIdError("Failed to check ID availability.");
      // console.error("Error checking ID:", err);
    } finally {
      setIdLoading(false);
    }
  };

  return { idAvailable, idLoading, idError, checkIdDuplicate };
};
