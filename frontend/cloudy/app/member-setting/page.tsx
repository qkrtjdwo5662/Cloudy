"use client";
import { useState, useEffect } from "react";
import {
  fetchNormalMembers,
  MemberResponse,
} from "@/features/member-setting/hooks/useFetchNormalMembers";
import {
  registerNormalMember,
  RegisterMemberRequest,
} from "@/features/member-setting/hooks/useRegisterMember";
import { deleteNormalMember } from "@/features/member-setting/hooks/useDeleteMember";
import { useAuthStore } from "@/shared/stores/authStore";
import AddMemberForm from "./addMemberForm";
import MemberTable from "./memberTable";
import { Title } from "@/shared/ui";

export default function AddMember() {
  const [members, setMembers] = useState<MemberResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const accessToken = useAuthStore((state) => state.accessToken);

  const fetchMembers = async () => {
    if (!accessToken) return;
    setLoading(true);

    try {
      const data = await fetchNormalMembers(accessToken);
      setMembers(data);
    } catch (error) {
      // console.error("fetchMembers Error:", error);
    } finally {
      setLoading(false);
    }
  };

  const handleAddMember = async (newMember: RegisterMemberRequest) => {
    if (!accessToken) return;
    try {
      const response = await registerNormalMember(newMember, accessToken);
      // console.log("Register Response:", response);
      alert("회원이 성공적으로 추가되었습니다.");
      await fetchMembers();
    } catch (error) {
      // console.error("회원 추가 실패:", error);
      alert("회원 추가에 실패했습니다.");
    }
  };

  const handleDeleteMember = async (memberId: number) => {
    if (!accessToken) return;
    const confirmDelete = confirm("정말로 이 회원을 삭제하시겠습니까?");
    if (!confirmDelete) return;

    try {
      await deleteNormalMember(memberId, accessToken);
      setMembers((prevMembers) =>
        prevMembers.filter((member) => member.memberId !== memberId),
      );
      alert("회원이 성공적으로 삭제되었습니다.");
    } catch (error) {
      // console.error("회원 삭제 실패:", error);
      alert("회원 삭제에 실패했습니다.");
    }
  };

  useEffect(() => {
    fetchMembers();
  }, [accessToken]);

  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">회원 설정</Title>

        <div className="mt-10 flex h-full gap-10">
          <section className="flex w-2/5 rounded-5 border border-gray-200 bg-white">
            <div className="flex w-full flex-col rounded-lg bg-white p-24">
              <h1 className="text-xl font-bold text-indigo-500">회원 추가</h1>
              <AddMemberForm onAddMember={handleAddMember} />
            </div>
          </section>

          <section className="flex h-full w-3/5 gap-10">
            <div className="flex h-full w-full flex-col rounded-5 border border-gray-200 bg-white p-20">
              <h1 className="pb-20 text-xl font-bold text-indigo-500">
                회원 목록
              </h1>
              <MemberTable
                members={members}
                onDeleteMember={handleDeleteMember}
                loading={loading}
              />
            </div>
          </section>
        </div>
      </div>
    </div>
  );
}
