"use client";

import { Button } from "@/shared/ui";
import { Chips } from "@/shared/ui";
import { Input } from "@/shared/ui";
import { Title } from "@/shared/ui";
import { NavigationBox } from "@/shared/ui";
import { DropDownBox } from "@/shared/ui";

export default function Home() {
  return (
    <div className="flex flex-col gap-10">
      <div className="flex gap-10">
        <Button
          size="l"
          variant="primary"
          design="fill"
          type="button"
          mainText="Button"
        />
        <Button
          size="m"
          variant="primary"
          design="fill"
          type="button"
          mainText="Button"
        />
        <Button
          size="s"
          variant="primary"
          design="fill"
          type="button"
          mainText="Button"
        />
      </div>
      <div className="flex gap-10">
        <Button
          size="l"
          variant="primary"
          design="fill"
          type="button"
          mainText="Button"
        />
        <Button
          size="l"
          variant="primary"
          design="outline"
          type="button"
          mainText="Button"
        />
        <Button
          size="l"
          variant="secondary"
          design="fill"
          type="button"
          mainText="Button"
        />
        <Button
          size="l"
          variant="tertiary"
          design="fill"
          type="button"
          mainText="Button"
        />
      </div>
      <div className="flex gap-10">
        <Chips size="l" color="indigo">
          Label
        </Chips>
        <Chips size="m" color="indigo">
          Label
        </Chips>
        <Chips size="s" color="indigo">
          Label
        </Chips>
      </div>
      <div className="flex gap-10">
        <Chips size="l" color="indigo">
          Label
        </Chips>
        <Chips size="l" color="red">
          Label
        </Chips>
        <Chips size="l" color="yellow">
          Label
        </Chips>
        <Chips size="l" color="gray">
          Label
        </Chips>
      </div>
      <div>
        <Input
          placeholder="내용을 입력하세요."
          showStar={true}
          showButton={true}
          buttonContent="Button"
          buttonType="button"
          label="label"
          warning="warning"
        />
      </div>
      <div>
        <Title size="l" showButton={true} buttonContent="Button">
          Title
        </Title>
        <Title size="m" showButton={true} buttonContent="Button">
          Title
        </Title>
      </div>
      <NavigationBox />
      <div className="flex w-300">
        <DropDownBox />
      </div>
    </div>
  );
}
