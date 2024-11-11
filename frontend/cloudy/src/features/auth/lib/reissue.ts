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
    },
    { bypassInterceptor: true } as ApiRequestConfig,
  );
};
