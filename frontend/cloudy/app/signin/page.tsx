import { Button } from "@/shared/ui/button/Button";
import { Input } from "@/shared/ui/input/Input";
import { Title } from "@/shared/ui/title/Title";

export default function JoinPage() {
  return (
    <div className="flex h-full w-full items-center justify-center">
      <div className="flex w-1/2 flex-col items-center justify-center rounded-10 border border-gray-200 bg-white py-160 shadow-lg">
        <Title size="l">로그인</Title>

        <form className="flex w-3/5 flex-col gap-32 p-8">
          <Input label="아이디" placeholder="placeholder" />
          <Input label="비밀번호" placeholder="placeholder" />
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
