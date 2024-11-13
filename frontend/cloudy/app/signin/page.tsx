"use client";

import { Button } from "@/shared/ui/button/Button";
import { Input } from "@/shared/ui/input/Input";
import { Title } from "@/shared/ui/title/Title";
import { useLogin } from "@/features/auth/hooks/useLogin";
import { useRouter } from "next/navigation";
import {
  useState,
  FormEventHandler,
  ChangeEventHandler,
  useEffect,
} from "react";
import { useAuthStore } from "@/shared/stores/authStore";

export default function JoinPage() {
  const [loginId, setLoginId] = useState("");
  const [password, setPassword] = useState("");
  const [mounted, setMounted] = useState(false);
  const mutation = useLogin();
  const router = useRouter();

  const setAccessToken = useAuthStore((state) => state.setAccessToken);
  const setEmail = useAuthStore((state) => state.setEmail);
  const setServerId = useAuthStore((state) => state.setServerId);
  const setRole = useAuthStore((state) => state.setRole);
  const setRegistrationNumber = useAuthStore(
    (state) => state.setRegistrationNumber,
  );

  useEffect(() => {
    setMounted(true);
  }, []);

  const handleSubmit: FormEventHandler<HTMLFormElement> = async (event) => {
    event.preventDefault();

    console.log(loginId, password);
    try {
      const response = await mutation.mutateAsync({ loginId, password });

      if (response.accessToken) {
        setAccessToken(response.accessToken);
        setEmail(loginId);
        setServerId(response.serverId);
        setRegistrationNumber(response.registrationNumber);
        setRole(response.role);
      }
      console.log("서버아이디", response.serverId);

      router.push("/dashboard");
    } catch (error) {
      alert("로그인 정보를 다시 확인하세요");
      console.log("❌ Login Failed", error);
    }
  };

  const onChangeloginId: ChangeEventHandler<HTMLInputElement> = (e) => {
    setLoginId(e.target.value.trim());
  };

  const onChangePassword: ChangeEventHandler<HTMLInputElement> = (e) => {
    setPassword(e.target.value.trim());
  };

  if (!mounted) {
    return null;
  }

  return (
    <div className="flex h-full w-full items-center justify-center">
      <div className="flex w-1/2 flex-col items-center justify-center rounded-10 border border-gray-200 bg-white py-160 shadow-lg">
        <Title size="l">로그인</Title>

        <form
          onSubmit={handleSubmit}
          className="flex w-3/5 flex-col gap-32 p-8"
        >
          <Input
            label="아이디"
            placeholder="placeholder"
            onChange={onChangeloginId}
            value={loginId}
          />
          <Input
            label="비밀번호"
            placeholder="placeholder"
            type="password"
            onChange={onChangePassword}
            value={password}
          />
          <Button
            size="l"
            variant="primary"
            design="fill"
            mainText="로그인"
            type="submit"
          />
        </form>
      </div>
    </div>
  );
}
