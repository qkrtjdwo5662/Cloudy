"use client";

import { useState } from "react";
import { NavigationItem } from "../navigation-item/NavigationItem";

export const NavigationBox = () => {
  const items = [
    {
      leftIcon: "cloudy",
      rightIcon: "keyboard_arrow_down",
      label: "Navigation Item",
      isSubItem: false,
      subItems: [
        { to: "/sub1", label: "Sub Item 1" },
        { to: "/sub2", label: "Sub Item 2" },
      ],
    },
    { leftIcon: "cloudy", to: "/", label: "Navigation Item" },
    { leftIcon: "cloudy", to: "/test2", label: "Navigation Item" },
    { leftIcon: "cloudy", to: "/test3", label: "Navigation Item" },
  ];

  const [openIndex, setOpenIndex] = useState<number | null>(null);

  const handleToggle = (index: number) => {
    setOpenIndex(openIndex === index ? null : index);
  };

  return (
    <div className="flex w-270 flex-col border-r border-gray-200 pt-28">
      <div className="flex border-b border-gray-200 p-20">
        dayoungpyo@gmail.com
      </div>
      <div className="flex flex-col gap-12 px-12 py-10">
        {items.map((item, index) => (
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
      <div className="flex border-t border-gray-200 px-12">
        <NavigationItem leftIcon="settings" to="/settings">
          settings
        </NavigationItem>
      </div>
    </div>
  );
};
