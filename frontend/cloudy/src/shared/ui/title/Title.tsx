import { ReactNode } from "react";
import { Button } from "../button/Button";

type Size = "l" | "m";

interface TitleProps {
  size: Size;
  showButton?: boolean;
  buttonContent?: string;
  buttonIcon?: string;
  children: ReactNode;
  onClick?: (e: React.MouseEvent<HTMLDivElement>) => void;
}

export const Title = ({
  size,
  showButton,
  buttonContent,
  buttonIcon,
  children,
  onClick,
}: TitleProps) => {
  const styles = {
    size: {
      l: "text-indigo-500 text-4xl font-bold",
      m: "text-indigo-500 text-2xl",
    },
  };

  const classes = [styles.size[size]].join(" ");
  return (
    <div className="flex items-center justify-between pl-4 pr-2 pt-9">
      <span className={classes}>{children}</span>
      {showButton && (
        <div>
          <Button
            size={size === "l" ? "m" : "s"}
            variant="primary"
            design="fill"
            type="button"
            mainText={buttonContent}
            rightIcon={buttonIcon}
          />
        </div>
      )}
    </div>
  );
};
