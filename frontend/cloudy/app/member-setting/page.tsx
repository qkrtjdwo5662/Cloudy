"use client";
import MainLineChart from "../dashboard/mainLineChart";
import AddMemberForm from "./addMemberForm";
import MemberTable from "./memberTable";

export default function AddMember() {
  return (
    <div className="flex h-full w-full bg-gray-100">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <header className="flex items-center justify-between pb-6">
          <h1 className="text-3xl font-bold text-indigo-500">회원 설정</h1>
        </header>

        <div className="flex h-full gap-10">
          <section className="flex w-2/5 rounded-md bg-white">
            <div className="flex w-full flex-col rounded-lg bg-white p-24">
              <h1 className="text-xl font-bold text-indigo-500">회원 추가</h1>
              <AddMemberForm />
            </div>
          </section>

          <section className="flex h-full w-3/5 gap-10">
            <div className="flex h-full w-full flex-col rounded-lg bg-white p-20">
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
