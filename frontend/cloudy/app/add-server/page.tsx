import MainLineChart from "../dashboard/mainLineChart";
import { Button } from "@/shared/ui/button/Button";
import { Input } from "@/shared/ui/input/Input";
import { AddDropDownBox } from "./dropDownBox";
import { CloudOptions } from "./cloudOption";
import TableContainer from "./serverTable";
import { Title } from "@/shared/ui";

export default function AddServer() {
  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">서버 설정</Title>
        <div className="flex h-full w-full flex-col gap-6">
          <section className="flex h-full w-full flex-col gap-6 rounded-5 border border-gray-200 bg-white p-10">
            <section className="flex h-full w-full gap-6">
              <div className="flex w-1/3 rounded-lg bg-white p-4">
                <div className="w-full p-20">
                  <Input
                    placeholder="내용을 입력하세요."
                    showStar={false}
                    showButton={false}
                    label="서버 이름"
                  />
                </div>
              </div>
              <div className="flex h-full w-1/3 rounded-lg">
                <div className="flex w-full p-20">
                  <CloudOptions />
                </div>
              </div>
              <div className="flex w-1/3 rounded-lg">
                <div className="flex w-full flex-col gap-20 p-20">
                  <div className="flex w-full flex-col gap-6">
                    <h1>인스턴스 종류</h1>
                    <AddDropDownBox />
                  </div>
                  <div className="flex w-full flex-col gap-6">
                    <h1>결제 방식</h1>
                    <AddDropDownBox />
                  </div>
                </div>
              </div>
            </section>
            <section className="flex h-1/6 w-full gap-6">
              <div className="flex w-full rounded-lg bg-white">
                <Button
                  size="l"
                  variant="primary"
                  design="fill"
                  mainText="서버 추가하기"
                  type="button"
                />
              </div>
            </section>
          </section>
          <section className="flex h-full w-full">
            <div className="flex w-full rounded-5 border border-gray-200 bg-white p-20">
              <TableContainer />
            </div>
          </section>
        </div>
      </div>
    </div>
  );
}
