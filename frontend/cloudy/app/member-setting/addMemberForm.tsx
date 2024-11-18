"use client";
import { useState, useEffect } from "react";
import { Button } from "@/shared/ui/button/Button";
import { Input } from "@/shared/ui/input/Input";
import { useCheckIdDuplicate } from "@/features/join/hooks/useCheckIdDuplicate";
import {
  registerNormalMember,
  RegisterMemberRequest,
} from "@/features/member-setting/hooks/useRegisterMember";
import { useAuthStore } from "@/shared/stores/authStore";

interface AddMemberFormProps {
  onAddMember: (newMember: RegisterMemberRequest) => Promise<void>;
}

export default function AddMemberForm({ onAddMember }: AddMemberFormProps) {
  const [departmentName, setDepartmentName] = useState("");
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [departmentWarning, setDepartmentWarning] = useState("");
  const [idWarning, setIdWarning] = useState("");
  const [passwordWarning, setPasswordWarning] = useState("");
  const [confirmPasswordWarning, setConfirmPasswordWarning] = useState("");
  const [idChecked, setIdChecked] = useState(false);
  const [isFormValid, setIsFormValid] = useState(false);
  const [loading, setLoading] = useState(false);

  const { idAvailable, idError, checkIdDuplicate } = useCheckIdDuplicate();
  const accessToken = useAuthStore((state) => state.accessToken);
  const registrationNumber = useAuthStore((state) => state.registrationNumber);

  const handleCheckIdClick = () => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(id)) {
      setIdWarning("유효한 이메일 형식이 아닙니다.");
      setIdChecked(false);
      return;
    }
    setIdWarning("");
    checkIdDuplicate(id);
  };

  useEffect(() => {
    if (idError) {
      setIdWarning("아이디 중복 확인 오류가 발생했습니다.");
      setIdChecked(false);
    } else if (idAvailable === true) {
      setIdWarning("이미 사용 중인 아이디입니다.");
      setIdChecked(false);
    } else if (idAvailable === false) {
      setIdWarning("");
      setIdChecked(true);
    }
  }, [idAvailable, idError]);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!isFormValid || !accessToken || !registrationNumber) return;

    const requestData: RegisterMemberRequest = {
      loginId: id,
      password,
      departmentName,
      businessRegistrationNumber: registrationNumber,
    };

    setLoading(true);

    try {
      await onAddMember(requestData);
      setDepartmentName("");
      setId("");
      setPassword("");
      setConfirmPassword("");
      setIdChecked(false);
    } catch (error: any) {
      if (error.response?.status === 400) {
        alert("요청이 잘못되었습니다. 입력값을 확인하세요.");
      } else if (error.response?.status === 500) {
        alert("서버 오류가 발생했습니다. 나중에 다시 시도하세요.");
      } else {
        alert("알 수 없는 오류가 발생했습니다.");
      }
      // console.error("회원 추가 중 오류 발생:", error);
    } finally {
      setLoading(false);
    }
  };
  useEffect(() => {
    if (
      departmentName.length > 0 &&
      (departmentName.length < 2 || departmentName.length > 10)
    ) {
      setDepartmentWarning("부서명은 2자리 이상 10자리 이하로 입력해주세요.");
    } else {
      setDepartmentWarning("");
    }

    const passwordRegex = /^(?=.*[!@#$%^&*(),.?":{}|<>]).{6,15}$/;
    if (password.length > 0 && !passwordRegex.test(password)) {
      setPasswordWarning(
        "비밀번호는 6-15자리이며, 특수 문자를 포함해야 합니다.",
      );
    } else {
      setPasswordWarning("");
    }

    if (confirmPassword.length > 0 && password !== confirmPassword) {
      setConfirmPasswordWarning("비밀번호가 일치하지 않습니다.");
    } else {
      setConfirmPasswordWarning("");
    }

    const isValid =
      departmentName.length > 0 &&
      id.length > 0 &&
      password.length > 0 &&
      confirmPassword.length > 0 &&
      !departmentWarning &&
      !idWarning &&
      !passwordWarning &&
      !confirmPasswordWarning &&
      idChecked;

    setIsFormValid(isValid);
  }, [
    departmentName,
    id,
    password,
    confirmPassword,
    departmentWarning,
    idWarning,
    passwordWarning,
    confirmPasswordWarning,
    idChecked,
  ]);

  return (
    <div className="flex flex-col pt-20">
      <form className="flex flex-col gap-15" onSubmit={handleSubmit}>
        <Input
          label="부서명"
          placeholder="부서명을 입력하세요"
          warning={departmentWarning}
          showStar={true}
          value={departmentName}
          onChange={(e) => setDepartmentName(e.target.value)}
        />
        <Input
          label="아이디"
          placeholder="이메일을 입력하세요"
          warning={idWarning}
          showButton
          showStar={true}
          buttonContent={idChecked ? "확인완료" : "중복확인"}
          buttonType="button"
          value={id}
          onChange={(e) => {
            setId(e.target.value);
            setIdChecked(false);
          }}
          onButtonClick={handleCheckIdClick}
        />
        <Input
          label="비밀번호"
          placeholder="비밀번호를 입력하세요"
          warning={passwordWarning}
          showStar={true}
          value={password}
          type="password"
          onChange={(e) => setPassword(e.target.value)}
        />
        <Input
          label="비밀번호 확인"
          placeholder="비밀번호를 다시 입력하세요"
          warning={confirmPasswordWarning}
          showStar={true}
          value={confirmPassword}
          type="password"
          onChange={(e) => setConfirmPassword(e.target.value)}
        />

        <Button
          size="l"
          variant="primary"
          design="fill"
          mainText={loading ? "추가 중..." : "추가하기"}
          type="submit"
          disabled={!isFormValid || loading}
        />
      </form>
    </div>
  );
}
