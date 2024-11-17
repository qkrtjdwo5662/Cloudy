import { handleApiRequest } from "@/shared/api/client";
import {
  AccessToken,
  RefreshToken,
  ApiRequestConfig,
  TokenPair,
} from "@/shared/api";

export const reissueToken = async () => {
  const currentAccessToken: AccessToken | null =
    localStorage.getItem("accessToken");
  const refreshToken: RefreshToken | null =
    localStorage.getItem("refreshToken");

  if (!currentAccessToken || !refreshToken) {
    throw new Error("🔑Tokens are missing");
  }

  return handleApiRequest<{ accessToken: AccessToken }, "post", TokenPair>(
    "/auth/reissue",
    "post",
    {
      accessToken: currentAccessToken,
      refreshToken,
      serverId: 1, // 예: 기본값 추가
      role: "user", // 예: 기본값 추가
      registrationNumber: "12345", // 예: 기본값 추가
    },
    { bypassInterceptor: true } as ApiRequestConfig,
  );
};
