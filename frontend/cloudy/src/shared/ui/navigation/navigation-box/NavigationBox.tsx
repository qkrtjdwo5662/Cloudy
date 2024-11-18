"use client";

import { useState, useEffect } from "react";
import { usePathname } from "next/navigation";
import { NavigationItem } from "../navigation-item/NavigationItem";
import { useAuthStore } from "@/shared/stores/authStore";
import { AddDropDownBox } from "@/shared/ui/drop-down/drop-down-box/dropDown";
import { useFetchContainers } from "@/features/server/hooks/useFetchContainerList";
import { useFetchServers } from "@/features/server/hooks/useFetchServers";

interface NavigationSubItem {
  to: string;
  label: string;
}

interface NavigationItemType {
  leftIcon: string;
  to?: string;
  label: string;
  isSubItem?: boolean;
  rightIcon?: string;
  subItems?: NavigationSubItem[];
}

const NAVIGATION_ITEMS: NavigationItemType[] = [
  { leftIcon: "cloudy", to: "/dashboard", label: "대시보드" },
  { leftIcon: "dns", to: "/server-usage", label: "서버 사용량" },
  {
    leftIcon: "sort",
    rightIcon: "keyboard_arrow_down",
    label: "컨테이너 사용량",
    isSubItem: false,
    subItems: [],
  },
  { leftIcon: "date_range", to: "/cost-calendar", label: "비용 캘린더" },
  { leftIcon: "alarm", to: "/alarm-list", label: "알람 목록" },
  { leftIcon: "Group", to: "/member-setting", label: "회원 설정" },
  {
    leftIcon: "vertical_align_top",
    to: "/limit-setting",
    label: "임계치 설정",
  },
  { leftIcon: "addchart", to: "/add-server", label: "서버 설정" },
];

export const NavigationBox = () => {
  const pathname = usePathname();
  const { containers, loading } = useFetchContainers();
  const { servers } = useFetchServers();
  const email = useAuthStore((state) => state.email);
  const serverId = useAuthStore((state) => state.serverId);
  const setServerId = useAuthStore((state) => state.setServerId);
  const role = useAuthStore((state) => state.role);
  const hasHydrated = useAuthStore((state) => state.hasHydrated);

  const [openIndex, setOpenIndex] = useState<number | null>(() => {
    const initialIndex = NAVIGATION_ITEMS.findIndex((item) =>
      item.subItems?.some((subItem) => subItem.to === pathname),
    );
    return initialIndex !== -1 ? initialIndex : null;
  });

  useEffect(() => {
    const parentIndex = NAVIGATION_ITEMS.findIndex((item) =>
      item.subItems?.some((subItem) => subItem.to === pathname),
    );

    if (parentIndex !== -1) {
      setOpenIndex(parentIndex);
    }
  }, [pathname]);

  useEffect(() => {
    const containerNavigationIndex = NAVIGATION_ITEMS.findIndex(
      (item) => item.label === "컨테이너 사용량",
    );

    if (containerNavigationIndex !== -1 && containers.length > 0) {
      NAVIGATION_ITEMS[containerNavigationIndex].subItems = containers.map(
        (container) => ({
          to: `/containers/${container.containerId}`,
          label: container.containerName,
        }),
      );
    }
  }, [containers]);

  const handleToggle = (index: number) => {
    setOpenIndex(openIndex === index ? null : index);
  };

  if (!hasHydrated) {
    return <div className="flex w-270"></div>;
  }

  return (
    <div className="flex h-screen w-270 flex-col border-r border-gray-200">
      <div className="flex border-b border-gray-200 p-20">
        {email ? (
          <AddDropDownBox
            options={servers.map((server) => ({
              label: server.serverName,
              id: server.serverId,
            }))}
            onSelect={(id: number) => setServerId(id)}
            initialSelectedId={serverId !== null ? serverId : 0}
          />
        ) : (
          <span className="text-sm text-gray-500">로그인을 해주세요</span>
        )}
      </div>
      <div className="flex-1 overflow-y-auto">
        <div className="flex flex-col gap-12 px-12 py-10">
          {NAVIGATION_ITEMS.map((item, index) => (
            <div key={index}>
              <NavigationItem
                leftIcon={item.leftIcon}
                rightIcon={item.rightIcon}
                to={item.to}
                isSubItem={item.isSubItem}
                onClick={() => item.subItems && handleToggle(index)}
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
      </div>
    </div>
  );
};
