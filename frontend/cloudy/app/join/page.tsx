"use client";
import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { Button } from "@/shared/ui/button/Button";
import { Input } from "@/shared/ui/input/Input";
import { Title } from "@/shared/ui/title/Title";
import { useCheckIdDuplicate } from "@/features/join/hooks/useCheckIdDuplicate";
import { useCheckNumberDuplicate } from "@/features/join/hooks/useCheckNumberDuplicate";
import { useRegister } from "@/features/join/hooks/useRegister";

export default function JoinPage() {
  const router = useRouter();
  const [id, setId] = useState("");
  const [companyName, setCompanyName] = useState("");
  const [number, setNumber] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [companyWarning, setCompanyWarning] = useState("");
  const [numberWarning, setNumberWarning] = useState("");
  const [passwordWarning, setPasswordWarning] = useState("");
  const [idWarning, setIdWarning] = useState("");
  const [idChecked, setIdChecked] = useState(false);
  const [numberChecked, setNumberChecked] = useState(false);
  const [isFormValid, setIsFormValid] = useState(false);

  const { idAvailable, idLoading, idError, checkIdDuplicate } =
    useCheckIdDuplicate();
  const { numberAvailable, numLoading, numError, checkNumberDuplicate } =
    useCheckNumberDuplicate();
  const { register, loading, error, success } = useRegister();

  const handleCheckIdClick = () => {
    if (id) {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(id)) {
        setIdWarning("유효한 이메일 형식이 아닙니다.");
        setIdChecked(false);
        return;
      }
      setIdWarning("");
      checkIdDuplicate(id);
      setIdChecked(true);
    }
  };

  const handleCheckNumberClick = () => {
    if (number && number.length === 10) {
      checkNumberDuplicate(number);
      setNumberWarning("");
      setNumberChecked(true);
    }
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!idWarning && !companyWarning && !numberWarning && !passwordWarning) {
      const registerData = {
        loginId: id,
        password: password,
        departmentName: companyName,
        businessRegistrationNumber: number,
      };
      register(registerData);
    }
  };

  useEffect(() => {
    if (idError) {
      setIdWarning("아이디 확인 오류가 발생했습니다.");
      setIdChecked(false);
    } else if (numError) {
      setNumberWarning("사업자 등록 번호 확인 오류가 발생했습니다.");
      setNumberChecked(false);
    } else if (idAvailable === false) {
      setIdWarning("");
      setIdChecked(true);
    } else if (idAvailable === true) {
      setIdWarning("이미 사용 중인 아이디입니다.");
      setIdChecked(false);
    } else if (numberAvailable === false) {
      setNumberWarning("");
      setNumberChecked(true);
    } else if (numberAvailable === true) {
      setNumberWarning("이미 사용 중인 사업자 등록 번호입니다.");
      setNumberChecked(false);
    }
  }, [idAvailable, idLoading, idError, numberAvailable, numLoading, numError]);

  useEffect(() => {
    const isValid =
      !idWarning &&
      !companyWarning &&
      !numberWarning &&
      !passwordWarning &&
      idChecked &&
      numberChecked &&
      companyName.length > 0 &&
      number.length > 0 &&
      password.length > 0 &&
      confirmPassword.length > 0;

    setIsFormValid(isValid);
  }, [
    idWarning,
    companyWarning,
    numberWarning,
    passwordWarning,
    idChecked,
    numberChecked,
    companyName,
    number,
    password,
    confirmPassword,
  ]);

  useEffect(() => {
    if (
      companyName.length > 0 &&
      (companyName.length < 2 || companyName.length > 10)
    ) {
      setCompanyWarning("부서명은 2자리 이상 10자리 이하로 입력해주세요.");
    } else {
      setCompanyWarning("");
    }

    if (number.length > 0 && number.length !== 10) {
      setNumberWarning("사업자 등록 번호는 10자리 숫자로 입력해주세요.");
    } else {
      setNumberWarning("");
    }

    const passwordRegex = /^(?=.*[!@#$%^&*(),.?":{}|<>]).{6,15}$/;
    if (password && !passwordRegex.test(password)) {
      setPasswordWarning(
        "비밀번호는 6-15자리이며, 특수 문자를 포함해야 합니다.",
      );
    } else if (confirmPassword && password !== confirmPassword) {
      setPasswordWarning("비밀번호가 일치하지 않습니다.");
    } else {
      setPasswordWarning("");
    }
  }, [companyName, number, password, confirmPassword]);

  useEffect(() => {
    if (success) {
      alert("회원가입이 성공적으로 완료되었습니다!");
      router.push("/signin");
    }
  }, [success, router]);

  return (
    <div className="w-500px flex h-full items-center justify-center">
      <div className="flex h-650 w-1/2 flex-col items-center justify-center rounded-10 border border-gray-200 bg-white py-30 shadow-lg">
        <Title size="l">가입하기</Title>

        <form
          className="flex w-3/5 flex-col gap-20 p-8"
          onSubmit={handleSubmit}
        >
          <Input
            label="부서명"
            placeholder="부서명을 입력하세요"
            warning={companyWarning}
            showStar={true}
            maxLength={10}
            value={companyName}
            onChange={(e) => setCompanyName(e.target.value)}
          />
          <Input
            label="사업자 등록 번호"
            placeholder="사업자 등록 번호를 입력하세요"
            warning={numberWarning}
            showButton
            showStar={true}
            maxLength={10}
            buttonContent={numberChecked ? "확인완료" : "중복확인"}
            buttonType="button"
            value={number}
            onChange={(e) => {
              const value = e.target.value.replace(/\D/g, "");
              setNumber(value);
              setNumberChecked(false);
            }}
            onButtonClick={handleCheckNumberClick}
          />
          <Input
            label="이메일"
            placeholder="이메일을 입력하세요"
            warning={idWarning}
            showButton
            showStar={true}
            buttonContent={idChecked ? "확인완료" : "중복확인"}
            buttonType="button"
            value={id}
            maxLength={30}
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
            maxLength={20}
            type="password"
            onChange={(e) => setPassword(e.target.value)}
          />
          <Input
            label="비밀번호 확인"
            placeholder="비밀번호를 다시 입력하세요"
            warning={passwordWarning}
            showStar={true}
            value={confirmPassword}
            maxLength={20}
            type="password"
            onChange={(e) => setConfirmPassword(e.target.value)}
          />

          <Button
            size="l"
            variant="primary"
            design="fill"
            mainText={loading ? "회원 가입 중..." : "회원 가입"}
            type="submit"
            disabled={!isFormValid || loading}
          />
          {error && <p className="text-red-500">{error}</p>}
        </form>
      </div>
    </div>
  );
}
