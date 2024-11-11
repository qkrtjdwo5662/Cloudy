import { create } from "zustand";

interface AuthState {
  accessToken: string | null;
  email: string | null;
  serverId: string | null;
  setAccessToken: (token: string) => void;
  setEmail: (email: string) => void;
  setServerId: (id: string) => void;
  resetAuth: () => void;
}

export const useAuthStore = create<AuthState>((set) => ({
  accessToken: null,
  email: null,
  serverId: null,
  setAccessToken: (token: string) => set({ accessToken: token }),
  setEmail: (email: string) => set({ email }),
  setServerId: (id: string) => set({ serverId: id }),
  resetAuth: () => set({ accessToken: null, email: null, serverId: null }),
}));
