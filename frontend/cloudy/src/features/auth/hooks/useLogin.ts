import { useMutation } from "@tanstack/react-query";
import { loginUser } from "../lib/login";
import { handleSuccess, handleError } from "@/shared/utils/mutationUtils";

export const useLogin = () => {
  return useMutation({
    mutationFn: loginUser,
    onSuccess: (data) => {
      // handleSuccess("✅ Login successful", data);
      localStorage.setItem("accessToken", data.accessToken);
      localStorage.setItem("refreshToken", data.refreshToken);
    },
    onError: (error) => {
      handleError("😥 Login failed", error);
    },
  });
};
