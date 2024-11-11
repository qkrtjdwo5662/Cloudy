import { create } from "zustand";
import { persist, createJSONStorage } from "zustand/middleware";

interface AuthState {
  accessToken: string | null;
  email: string | null;
  serverId: number | null;
  setAccessToken: (token: string) => void;
  setEmail: (email: string) => void;
  setServerId: (id: number) => void;
  resetAuth: () => void;
  hasHydrated: boolean; // 상태 로드 완료 여부
}

export const useAuthStore = create<AuthState>()(
  persist(
    (set) => ({
      accessToken: null,
      email: null,
      serverId: null,
      hasHydrated: false, // 초기값은 false
      setAccessToken: (token: string) => set({ accessToken: token }),
      setEmail: (email: string) => set({ email }),
      setServerId: (id: number) => set({ serverId: id }),
      resetAuth: () => set({ accessToken: null, email: null, serverId: null }),
    }),
    {
      name: "auth-storage",
      storage: createJSONStorage(() => localStorage),
      onRehydrateStorage: () => (state, error) => {
        if (state) {
          state.hasHydrated = true; // 상태가 로드 완료되면 true로 설정
        }
      },
    },
  ),
);
