export type AccessToken = string;
export type RefreshToken = string;
export type ServerId = number;

export interface TokenPair {
  accessToken: AccessToken;
  refreshToken: RefreshToken;
  serverId: ServerId;
}

export interface LoginCredentials {
  loginId: string;
  password: string;
}
