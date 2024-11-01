import { Button } from "@/shared/ui/button/Button";
import { Input } from "@/shared/ui/input/Input";
import { Title } from "@/shared/ui/title/Title";

export default function JoinPage() {
  return (
    <div className="flex min-h-screen flex-col items-center justify-center px-4">
      <Title size="l">가입하기</Title>

      <form className="flex w-3/5 flex-col gap-12 p-8">
        <Input
          label="기업명"
          placeholder="placeholder"
          warning="warning"
          showStar={true}
        />
        <Input
          label="사업자 등록 번호"
          placeholder="placeholder"
          warning="warning"
          showButton
          showStar={true}
          buttonContent="중복확인"
          buttonType="button"
        />
        <Input
          label="아이디"
          placeholder="placeholder"
          warning="warning"
          showButton
          showStar={true}
          buttonContent="중복확인"
          buttonType="button"
        />
        <Input
          label="비밀번호"
          placeholder="placeholder"
          warning="warning"
          showStar={true}
        />
        <Input
          label="비밀번호 확인"
          placeholder="placeholder"
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
  );
}
