"use client";
import { useState } from "react";

interface MemberRowProps {
  department: string;
  email: string;
  onDelete: () => void;
}

const MemberRow = ({ department, email, onDelete }: MemberRowProps) => (
  <tr className="border-b">
    <td className="px-35 py-14">{department}</td>
    <td className="px-6 py-4">{email}</td>
    <td className="px-6 py-4">
      <button
        className="rounded border border-gray-400 px-4 py-2 text-gray-500 hover:border-red-500 hover:text-red-500"
        onClick={onDelete}
      >
        삭제
      </button>
    </td>
  </tr>
);

export const MemberTable = () => {
  const [rows, setRows] = useState([
    { department: "부서명", email: "아이디(이메일)" },
    { department: "부서명", email: "아이디(이메일)" },
    { department: "부서명", email: "아이디(이메일)" },
    { department: "부서명", email: "아이디(이메일)" },
    { department: "부서명", email: "아이디(이메일)" },
  ]);

  // 행 삭제 함수
  const handleDelete = (index: number) => {
    setRows((prevRows) => prevRows.filter((_, i) => i !== index));
  };

  return (
    <div className="w-full overflow-x-auto bg-white">
      <table className="min-w-full border-collapse">
        <thead>
          <tr className="border-b bg-gray-100">
            <th className="px-35 py-13 text-left font-medium text-gray-700">
              부서명
            </th>
            <th className="px-6 py-3 text-left font-medium text-gray-700">
              아이디(이메일)
            </th>
            <th className="px-6 py-3 text-left font-medium text-gray-700">
              삭제
            </th>
          </tr>
        </thead>
        <tbody>
          {rows.map((row, index) => (
            <MemberRow
              key={index}
              department={row.department}
              email={row.email}
              onDelete={() => handleDelete(index)}
            />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default MemberTable;
