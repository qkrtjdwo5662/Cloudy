"use client";
import { useState, useRef, useEffect } from "react";
import { DropDownItem } from "@/shared/ui/drop-down/drop-down-item/DropDownItem";
import { Button } from "@/shared/ui/button/Button";

export const AddDropDownBox = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedIndex, setSelectedIndex] = useState(0);
  const dropdownRef = useRef<HTMLDivElement>(null);

  const items = ["Dropdown Item 1", "Dropdown Item 2", "Dropdown Item 3"];
  const selectedItem = items[selectedIndex];

  const toggleDropdown = () => setIsOpen((prev) => !prev);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (
        dropdownRef.current &&
        !dropdownRef.current.contains(event.target as Node)
      ) {
        setIsOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  return (
    <div
      ref={dropdownRef}
      className="relative w-full flex-col items-center justify-center gap-2 rounded-md border border-gray-200 p-4"
    >
      <Button
        size="m"
        variant="tertiary"
        design="fill"
        mainText={selectedItem}
        type="button"
        onClick={toggleDropdown}
      />
      {isOpen && (
        <div className="absolute top-full z-10 mt-2 w-full rounded-md bg-white shadow-lg">
          {items.map((item, index) => (
            <DropDownItem
              key={index}
              isSelected={selectedIndex === index}
              onClick={() => {
                setSelectedIndex(index);
                setIsOpen(false);
              }}
            >
              {item}
            </DropDownItem>
          ))}
        </div>
      )}
    </div>
  );
};
