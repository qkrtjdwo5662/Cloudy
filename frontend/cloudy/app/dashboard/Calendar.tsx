"use client";

import * as React from "react";
import { Calendar } from "react-calendar";
import "../dashboard/style.css";

export default function Component() {
  const [date, setDate] = React.useState<Date | null>(null);

  // 클라이언트에서만 초기화
  React.useEffect(() => {
    setDate(new Date());
  }, []);

  if (!date) return null;

  return (
    <div className="h-full w-full">
      {/* 최소 높이만 지정하여 4주일 때는 여백이 생기고, 5~6주일 경우 크기 확장 */}
      <div className="max-h-[150px] rounded-lg bg-white">
        <Calendar
          onChange={setDate}
          value={date}
          className="rounded-lg"
          locale="ko-KR"
          tileClassName={({ date: tileDate, view }) => {
            const isSameMonth = tileDate.getMonth() === date.getMonth();
            const isToday =
              tileDate.toDateString() === new Date().toDateString();
            let classes =
              "hover:bg-gray-100 rounded-full flex items-center justify-center";

            return classes;
          }}
          navigationLabel={({ date }) => (
            <div className="flex items-center justify-center">
              <span className="whitespace-nowrap text-lg font-semibold">
                {date.toLocaleDateString("ko", {
                  year: "numeric",
                  month: "long",
                })}
              </span>
            </div>
          )}
          prevLabel={<span className="text-lg">&lt;</span>}
          nextLabel={<span className="text-lg">&gt;</span>}
          formatDay={(locale, date) => date.getDate().toString()}
          formatShortWeekday={(locale, date) =>
            date.toLocaleDateString("ko", { weekday: "short" })[0]
          }
          showNeighboringMonth={false}
        />
      </div>
    </div>
  );
}
