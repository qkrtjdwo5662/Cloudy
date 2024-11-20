import { useState } from "react";
import axios from "axios";

interface RegisterData {
  loginId: string;
  password: string;
  departmentName: string;
  businessRegistrationNumber: string;
}

export const useRegister = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState(false);

  const register = async (data: RegisterData) => {
    setLoading(true);
    setError(null);
    setSuccess(false);

    try {
      const response = await axios.post(
        "http://k11a606.p.ssafy.io:8081/auth/register/super",
        data,
      );
      // console.log(response);

      if (response.status === 200) {
        setSuccess(true);
      }
    } catch (err) {
      setError("회원가입에 실패했습니다.");
      // console.error("Registration error:", err);
    } finally {
      setLoading(false);
    }
  };

  return { register, loading, error, success };
};
