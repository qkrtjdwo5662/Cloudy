"use client";
import AddMemberForm from "./addMemberForm";
import MemberTable from "./memberTable";
import { Title } from "@/shared/ui";

export default function AddMember() {
  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">회원 설정</Title>

        <div className="mt-10 flex h-full gap-10">
          <section className="flex w-2/5 rounded-5 border border-gray-200 bg-white">
            <div className="flex w-full flex-col rounded-lg bg-white p-24">
              <h1 className="text-xl font-bold text-indigo-500">회원 추가</h1>
              <AddMemberForm />
            </div>
          </section>

          <section className="flex h-full w-3/5 gap-10">
            <div className="flex h-full w-full flex-col rounded-5 border border-gray-200 bg-white p-20">
              <h1 className="pb-20 text-xl font-bold text-indigo-500">
                회원 목록
              </h1>
              <MemberTable />
            </div>
          </section>
        </div>
      </div>
    </div>
  );
}
