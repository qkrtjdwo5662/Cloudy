export type AccessToken = string;
export type RefreshToken = string;
export type ServerId = number;
export type Role = string;
export type RegistrationNumber = string;

export interface TokenPair {
  accessToken: AccessToken;
  refreshToken: RefreshToken;
  serverId: ServerId;
  role: Role;
  registrationNumber: RegistrationNumber;
}

export interface LoginCredentials {
  loginId: string;
  password: string;
}
