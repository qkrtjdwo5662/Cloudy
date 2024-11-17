import { useState, useRef, useEffect } from "react";
import { DropDownItem } from "@/shared/ui/drop-down/drop-down-item/DropDownItem";
import { Button } from "@/shared/ui/button/Button";

interface AddDropDownBoxProps {
  options: { label: string; id: number }[]; // 옵션 배열에 serverId 포함
  onSelect: (id: number) => void;
  initialSelectedId?: number;
}

export const AddDropDownBox = ({
  options,
  onSelect,
  initialSelectedId,
}: AddDropDownBoxProps) => {
  const [isOpen, setIsOpen] = useState(false);
  const dropdownRef = useRef<HTMLDivElement>(null);

  // initialSelectedId에 따른 초기 선택값 계산
  const initialIndex = options.findIndex(
    (option) => option.id === initialSelectedId,
  );
  const [selectedIndex, setSelectedIndex] = useState(
    initialIndex !== -1 ? initialIndex : 0,
  );

  const selectedItem = options[selectedIndex]?.label;

  useEffect(() => {
    // initialSelectedId가 변경될 때마다 selectedIndex 업데이트
    const index = options.findIndex(
      (option) => option.id === initialSelectedId,
    );
    if (index !== -1) {
      setSelectedIndex(index);
    }
  }, [initialSelectedId, options]);

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

  const handleSelect = (index: number) => {
    setSelectedIndex(index);
    onSelect(options[index].id); // serverId를 전달
    setIsOpen(false);
  };

  return (
    <div
      ref={dropdownRef}
      className="relative w-full flex-col items-center justify-center gap-2 rounded-md border border-gray-200 p-4"
    >
      <Button
        size="m"
        variant="tertiary"
        design="fill"
        mainText={selectedItem || "Select an option"}
        type="button"
        onClick={toggleDropdown}
      />
      {isOpen && (
        <div className="absolute top-full z-10 mt-2 w-full rounded-md bg-white shadow-lg">
          {options.map((item, index) => (
            <DropDownItem
              key={item.id}
              isSelected={selectedIndex === index}
              onClick={() => handleSelect(index)}
            >
              {item.label}
            </DropDownItem>
          ))}
        </div>
      )}
    </div>
  );
};
