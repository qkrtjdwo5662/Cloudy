"use client";

import * as React from "react";
import { Calendar } from "react-calendar";
import "../dashboard/style.css";
import { useFetchDailyCost } from "@/features/cost-calendar";

interface CalendarComponentProps {
  onDateChange: (selectedDate: string) => void;
}

export default function Component({ onDateChange }: CalendarComponentProps) {
  const [date, setDate] = React.useState<Date | null>(null);
  const { cost, loading, error, fetchDailyCost } = useFetchDailyCost();

  const formatDateToString = (date: Date) => {
    return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, "0")}-${String(
      date.getDate(),
    ).padStart(2, "0")}`;
  };

  React.useEffect(() => {
    if (!date) {
      const today = new Date();
      setDate(today);
      const formattedDate = formatDateToString(today);
      onDateChange(formattedDate);
      fetchDailyCost(formattedDate);
      console.log("초기화된 날짜:", today);
    } else {
      const formattedDate = formatDateToString(date);
      onDateChange(formattedDate);
      fetchDailyCost(formattedDate);
    }
  }, [date, onDateChange, fetchDailyCost]);

  if (!date) return null;

  return (
    <div className="h-full w-full">
      <div className="max-h-[150px] rounded-lg bg-white">
        <Calendar
          calendarType="gregory"
          onChange={(value) => {
            if (value instanceof Date) {
              console.log("선택한 날짜:", value);
              setDate(value);
              const formattedDate = formatDateToString(value);
              onDateChange(formattedDate);
              fetchDailyCost(formattedDate);
            }
          }}
          value={date}
          className="rounded-lg"
          locale="ko-KR"
          tileClassName={({ date: tileDate }) => {
            const isSelected =
              date && tileDate.toDateString() === date.toDateString();

            return [
              "hover:bg-gray-100 rounded-full flex items-center justify-center",
              isSelected ? "bg-blue-500 text-white" : "",
            ]
              .filter(Boolean)
              .join(" ");
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
            date.toLocaleDateString("ko", { weekday: "short" })
          }
          showNeighboringMonth={false}
        />
        <div className="mt-10 text-center">
          {/* {loading && <p>비용을 불러오는 중...</p>} */}
          {!loading && error && <p>비용을 불러오는 데 실패했습니다.</p>}
          {!loading && !error && cost !== null && (
            <p className="text-m">
              선택한 날짜의 비용은{" "}
              <span className="text-indigo-500">${cost}</span> 입니다.
            </p>
          )}
        </div>
      </div>
    </div>
  );
}
