"use client";

import { useState, useEffect, useMemo } from "react";
import { usePathname } from "next/navigation";
import { NavigationItem } from "../navigation-item/NavigationItem";

const NAVIGATION_ITEMS = [
  {
    leftIcon: "cloudy",
    rightIcon: "keyboard_arrow_down",
    label: "컨테이너 사용량",
    isSubItem: false,
    subItems: [
      { to: "/sub1", label: "컨테이너 1" },
      { to: "/sub2", label: "컨테이너 2" },
    ],
  },
  { leftIcon: "cloudy", to: "/dashboard", label: "메인" },
  { leftIcon: "cloudy", to: "/server-usage", label: "서버 사용량" },
  { leftIcon: "cloudy", to: "/cost-calendar", label: "비용 캘린더" },
  { leftIcon: "cloudy", to: "/member-setting", label: "회원 설정" },
  { leftIcon: "cloudy", to: "/alarm-list", label: "알람 목록" },
  { leftIcon: "cloudy", to: "/join", label: "회사 등록" },
] as const;

export const NavigationBox = () => {
  const pathname = usePathname();
  const [openIndex, setOpenIndex] = useState<number | null>(() => {
    const initialIndex = NAVIGATION_ITEMS.findIndex((item) =>
      item.subItems?.some((subItem) => subItem.to === pathname),
    );
    return initialIndex !== -1 ? initialIndex : null;
  });

  // URL 변경 시 열린 메뉴 상태 업데이트
  useEffect(() => {
    const parentIndex = NAVIGATION_ITEMS.findIndex((item) =>
      item.subItems?.some((subItem) => subItem.to === pathname),
    );

    if (parentIndex !== -1) {
      setOpenIndex(parentIndex);
    }
  }, [pathname]);

  const handleToggle = (index: number) => {
    setOpenIndex(openIndex === index ? null : index);
  };

  return (
    <div className="flex w-270 flex-col justify-between border-r border-gray-200 pt-28">
      <div className="flex border-b border-gray-200 p-20">
        dayoungpyo@gmail.com
      </div>
      <div className="flex h-full flex-col gap-12 px-12 py-10">
        {NAVIGATION_ITEMS.map((item, index) => (
          <div key={index}>
            <NavigationItem
              leftIcon={item.leftIcon}
              rightIcon={item.rightIcon}
              to={item.to}
              isSubItem={item.isSubItem}
              onClick={() => item.rightIcon && handleToggle(index)}
            >
              {item.label}
            </NavigationItem>
            {openIndex === index && item.subItems && (
              <div className="flex flex-col gap-4">
                {item.subItems.map((subItem, subIndex) => (
                  <NavigationItem
                    key={subIndex}
                    to={subItem.to}
                    isSubItem={true}
                  >
                    {subItem.label}
                  </NavigationItem>
                ))}
              </div>
            )}
          </div>
        ))}
      </div>
      <div className="flex border-t border-gray-200 px-12 pt-12">
        <NavigationItem leftIcon="settings" to="/add-server">
          settings
        </NavigationItem>
      </div>
    </div>
  );
};
