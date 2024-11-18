import { useState } from "react";
import axios from "axios";

export const useCheckNumberDuplicate = () => {
  const [numberAvailable, setNumberAvailable] = useState<boolean | null>(null);
  const [numLoading, setNumLoading] = useState<boolean>(false);
  const [numError, setNumError] = useState<string | null>(null);

  const checkNumberDuplicate = async (number: string) => {
    setNumLoading(true);
    setNumError(null);

    try {
      const response = await axios.get(
        `http://k11a606.p.ssafy.io:8081/auth/duplicate/businessnumber?businessRegistrationNumber=${number}`,
        {
          headers: {
            Accept: "*/*",
          },
        },
      );

      setNumberAvailable(response.data.data);
    } catch (err) {
      setNumError("Failed to check Number availability.");
      // console.error("Error checking Number:", err);
    } finally {
      setNumLoading(false);
    }
  };

  return { numberAvailable, numLoading, numError, checkNumberDuplicate };
};
