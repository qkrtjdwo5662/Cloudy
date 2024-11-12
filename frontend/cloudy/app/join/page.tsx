"use client";
import { useState, useEffect } from "react";
import { Button } from "@/shared/ui/button/Button";
import { Input } from "@/shared/ui/input/Input";
import { Title } from "@/shared/ui/title/Title";
import { useCheckIdDuplicate } from "@/features/join/hooks/useCheckIdDuplicate"; // Import the custom hook
import { useCheckNumberDuplicate } from "@/features/join/hooks/useCheckNumberDuplicate";

export default function JoinPage() {
  const [id, setId] = useState("");
  const [number, setNumber] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [warningMessage, setWarningMessage] = useState("");
  const [numberWarning, setNumberWarning] = useState("");
  const [passwordWarning, setPasswordWarning] = useState("");
  const [idWarning, setIdWarning] = useState("");

  const { idAvailable, idLoading, idError, checkIdDuplicate } =
    useCheckIdDuplicate();
  const { numberAvailable, numLoading, numError, checkNumberDuplicate } =
    useCheckNumberDuplicate();

  const handleCheckIdClick = () => {
    if (id) {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(id)) {
        setIdWarning("유효한 이메일 형식이 아닙니다.");
        return;
      }
      setIdWarning("");
      checkIdDuplicate(id);
    }
  };

  const handleCheckNumberClick = () => {
    if (number) {
      checkNumberDuplicate(number);
      setNumberWarning("");
      console.log(numberAvailable, numLoading, numError);
    }
  };

  useEffect(() => {
    if (idLoading || numLoading) {
      setWarningMessage("");
    } else if (idError) {
      setWarningMessage(idError);
    } else if (numError) {
      setNumberWarning(numError);
    } else if (idAvailable === false) {
      setWarningMessage("사용 가능한 아이디입니다.");
    } else if (idAvailable === true) {
      setWarningMessage("이미 사용 중인 아이디입니다.");
    } else if (numberAvailable === false) {
      setNumberWarning("사용 가능한 사업자 등록 번호입니다.");
    } else if (numberAvailable === true) {
      setNumberWarning("이미 사용 중인 사업자 등록 번호입니다.");
    } else {
      setWarningMessage("");
    }
  }, [idAvailable, idLoading, idError, numberAvailable, numLoading, numError]);

  useEffect(() => {
    if (confirmPassword && password !== confirmPassword) {
      setPasswordWarning("비밀번호가 일치하지 않습니다.");
    } else {
      setPasswordWarning("");
    }
  }, [password, confirmPassword]);

  return (
    <div className="w-500px flex h-full items-center justify-center">
      <div className="flex h-650 w-1/2 flex-col items-center justify-center rounded-10 border border-gray-200 bg-white py-30 shadow-lg">
        <Title size="l">가입하기</Title>

        <form className="flex w-3/5 flex-col gap-20 p-8">
          <Input
            label="기업명"
            placeholder="기업명을 입력하세요"
            showStar={true}
          />
          <Input
            label="사업자 등록 번호"
            placeholder="사업자 등록 번호를 입력하세요"
            warning={numberWarning || warningMessage}
            showButton
            showStar={true}
            buttonContent="중복확인"
            buttonType="button"
            value={number}
            onChange={(e) => setNumber(e.target.value)}
            onButtonClick={handleCheckNumberClick}
          />
          <Input
            label="이메일"
            placeholder="이메일를 입력하세요"
            warning={idWarning || warningMessage}
            showButton
            showStar={true}
            buttonContent="중복확인"
            buttonType="button"
            value={id}
            onChange={(e) => setId(e.target.value)}
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
            warning={passwordWarning}
            showStar={true}
            value={confirmPassword}
            type="password"
            onChange={(e) => setConfirmPassword(e.target.value)}
          />

          <Button
            size="l"
            variant="primary"
            design="fill"
            mainText="회원 가입"
            type="submit"
          />
        </form>
      </div>
    </div>
  );
}
