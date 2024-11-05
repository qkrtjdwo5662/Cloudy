"use client";

export default function CostSummary() {
  return (
    <div className="max-w-xs rounded-lg bg-white p-20 shadow-md">
      <div className="flex items-center px-20">
        <span className="text-lg font-bold text-black">비용 요약</span>
      </div>

      <div className="p-20">
        <p className="text-sm text-gray-500">월간 비용 누계</p>
        <p className="text-2xl font-bold text-indigo-500">US$0.09</p>
        <p className="text-xs text-gray-500">
          <span className="text-blue-600">↓</span> 10% 동일 기간의 지난 달과
          비교
        </p>
      </div>

      <div className="p-20">
        <p className="text-sm text-gray-500">이번 달의 총 예상 비용</p>
        <p className="text-2xl font-bold text-indigo-500">US$0.11</p>
        <p className="text-xs text-gray-500">
          <span className="text-blue-600">↓</span> 5% 지난 달의 총 비용 비교
        </p>
      </div>
    </div>
  );
}
