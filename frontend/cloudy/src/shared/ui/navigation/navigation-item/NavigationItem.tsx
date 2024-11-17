import { ReactNode } from "react";
import Link from "next/link";
import { usePathname } from "next/navigation";

interface NavigationItemProps {
  leftIcon?: string;
  rightIcon?: string;
  isSubItem?: boolean;
  to?: string;
  onClick?: (e: React.MouseEvent<HTMLDivElement>) => void;
  children: ReactNode;
}

export const NavigationItem = ({
  leftIcon,
  rightIcon,
  isSubItem,
  to,
  onClick,
  children,
}: NavigationItemProps) => {
  const pathname = usePathname();
  const isActive = pathname === to;

  const content = (
    <div
      className={`flex w-full cursor-pointer items-center gap-12 rounded-4 ${
        isSubItem ? "pl-56 pr-20" : "px-20"
      } py-12 ${isActive ? "bg-indigo-100" : ""}`}
      onClick={onClick}
    >
      {leftIcon && (
        <span className="material-symbols-outlined">{leftIcon}</span>
      )}
      <span className="text-base">{children}</span>
      {rightIcon && (
        <span className="material-symbols-outlined">{rightIcon}</span>
      )}
    </div>
  );

  return to ? (
    <Link href={to} className="w-full">
      {content}
    </Link>
  ) : (
    content
  );
};
