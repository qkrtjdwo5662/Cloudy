import { create } from "zustand";
import { persist, createJSONStorage } from "zustand/middleware";

interface AuthState {
  accessToken: string | null;
  email: string | null;
  serverId: number | null;
  registrationNumber: string | null;
  role: string | null;
  serverName: string | null;
  setAccessToken: (token: string) => void;
  setEmail: (email: string) => void;
  setServerId: (id: number) => void;
  setRegistrationNumber: (registrationNumber: string) => void;
  setRole: (role: string) => void;
  setServerName: (serverName: string) => void;
  resetAuth: () => void;
  hasHydrated: boolean;
}

export const useAuthStore = create<AuthState>()(
  persist(
    (set) => ({
      accessToken: null,
      email: null,
      serverId: null,
      registrationNumber: null,
      role: null,
      hasHydrated: false,
      serverName: null,
      setAccessToken: (token: string) => set({ accessToken: token }),
      setEmail: (email: string) => set({ email }),
      setServerId: (id: number) => set({ serverId: id }),
      setRegistrationNumber: (registrationNumber: string) =>
        set({ registrationNumber }),
      setRole: (role: string) => set({ role }),
      setServerName: (serverName: string) => set({ serverName }),
      resetAuth: () =>
        set({
          accessToken: null,
          email: null,
          serverId: null,
          registrationNumber: null,
          role: null,
          serverName: null,
        }),
    }),
    {
      name: "auth-storage",
      storage: createJSONStorage(() => localStorage),
      onRehydrateStorage: () => (state, error) => {
        if (state) {
          state.hasHydrated = true;
        }
      },
    },
  ),
);
