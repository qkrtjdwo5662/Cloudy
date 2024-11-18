"use client";
import { Button } from "@/shared/ui/button/Button";

interface MemberRowProps {
  memberId: number;
  department: string;
  email: string;
  onDelete: () => void;
}

const MemberRow = ({
  memberId,
  department,
  email,
  onDelete,
}: MemberRowProps) => (
  <tr className="border-b">
    <td className="px-35 py-14 text-center align-middle">{department}</td>
    <td className="px-6 py-4 text-center align-middle">{email}</td>
    <td className="px-6 py-4 text-center align-middle">
      <div className="px-4 py-4">
        <Button
          size="s"
          variant="primary"
          design="fill"
          mainText="삭제"
          type="button"
          onClick={onDelete}
        />
      </div>
    </td>
  </tr>
);

interface MemberTableProps {
  members: Array<{
    memberId: number;
    departmentName: string;
    loginId: string;
  }>;
  onDeleteMember: (memberId: number) => void;
  loading: boolean;
}

export const MemberTable = ({
  members,
  onDeleteMember,
  loading,
}: MemberTableProps) => {
  if (loading) {
    return (
      <div className="flex h-full w-full items-center justify-center text-lg">
        로딩중
      </div>
    );
  }

  if (!members || members.length === 0) {
    return (
      <div className="flex h-full w-full items-center justify-center text-lg">
        등록된 회원이 없습니다.
      </div>
    );
  }

  return (
    <div className="w-full overflow-x-auto bg-white">
      <table className="min-w-full border-collapse">
        <thead>
          <tr className="border-b bg-gray-100">
            <th className="px-35 py-13 text-center font-medium text-gray-700">
              부서명
            </th>
            <th className="px-6 py-3 text-center font-medium text-gray-700">
              아이디(이메일)
            </th>
            <th className="px-6 py-3 text-center font-medium text-gray-700">
              삭제
            </th>
          </tr>
        </thead>
        <tbody>
          {members.map((member) => (
            <MemberRow
              key={member.memberId}
              memberId={member.memberId}
              department={member.departmentName}
              email={member.loginId}
              onDelete={() => onDeleteMember(member.memberId)}
            />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default MemberTable;
