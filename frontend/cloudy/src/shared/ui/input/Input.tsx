import { ClickType } from "../button/Button";
import { Button } from "../button/Button";

interface InputProps {
  placeholder: string;
  showStar?: boolean;
  label?: string;
  warning?: string;
  showButton?: boolean;
  buttonContent?: string;
  buttonType?: ClickType;
  type?: "text" | "password";
  value?: string;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export const Input = ({
  placeholder = "placeholder",
  showStar,
  label,
  warning,
  showButton,
  buttonContent,
  buttonType,
  type,
  value,
  onChange,
}: InputProps) => {
  return (
    <div className="flex flex-col gap-4">
      {label && (
        <p className="pl-6 text-sm text-gray-500">
          {showStar ? "*" : ""}
          {label}
        </p>
      )}
      <div className="flex gap-8">
        <input
          type={type}
          placeholder={placeholder}
          className="h-40 w-full rounded-8 border border-gray-200 p-16"
          onChange={onChange}
          value={value}
        />
        {showButton && (
          <div>
            <Button
              size="m"
              variant="primary"
              design="fill"
              mainText={buttonContent}
              type={buttonType as ClickType}
            />
          </div>
        )}
      </div>
      {warning && <p className="pl-6 text-sm text-red-500">warning</p>}
    </div>
  );
};
