import { Button } from "@/shared/ui/button/Button";
import { Input } from "@/shared/ui/input/Input";
import { Title } from "@/shared/ui/title/Title";

export default function AddMemberForm() {
  return (
    <div className="flex flex-col pt-20">
      <form className="flex flex-col gap-15">
        <Input
          label="부서명"
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
          mainText="추가하기"
          type="submit"
        />
      </form>
    </div>
  );
}
