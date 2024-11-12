"use client";
import { useState, useEffect } from "react";
import { Button } from "@/shared/ui/button/Button";
import { Input } from "@/shared/ui/input/Input";
import { Title } from "@/shared/ui/title/Title";
import { useCheckIdDuplicate } from "@/features/join/hooks/useCheckIdDuplicate"; // Import the custom hook

export default function JoinPage() {
  const [id, setId] = useState("");
  const { idAvailable, loading, error, checkIdDuplicate } =
    useCheckIdDuplicate();
  const [warningMessage, setWarningMessage] = useState("");

  const handleCheckIdClick = () => {
    if (id) {
      checkIdDuplicate(id);
      console.log(idAvailable, loading, error);
    }
  };

  useEffect(() => {
    if (loading) {
      setWarningMessage("");
    } else if (error) {
      setWarningMessage(error);
    } else if (idAvailable === false) {
      setWarningMessage("사용 가능한 아이디입니다.");
    } else if (idAvailable === true) {
      setWarningMessage("이미 사용 중인 아이디입니다.");
    } else {
      setWarningMessage("");
    }
  }, [idAvailable, loading, error]);

  return (
    <div className="flex h-full w-full items-center justify-center">
      <div className="flex w-1/2 flex-col items-center justify-center rounded-10 border border-gray-200 bg-white py-30 shadow-lg">
        <Title size="l">가입하기</Title>

        <form className="flex w-3/5 flex-col gap-12 p-8">
          <Input label="기업명" placeholder="placeholder" showStar={true} />
          <Input
            label="사업자 등록 번호"
            placeholder="placeholder"
            showButton
            showStar={true}
            buttonContent="중복확인"
            buttonType="button"
          />
          <Input
            label="아이디"
            placeholder="아이디를 입력하세요"
            warning={warningMessage}
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
            warning="warning"
            showStar={true}
          />
          <Input
            label="비밀번호 확인"
            placeholder="비밀번호를 다시 입력하세요"
            warning="warning"
            showStar={true}
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
