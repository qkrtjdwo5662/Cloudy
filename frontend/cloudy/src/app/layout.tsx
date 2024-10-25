import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";

const pretendard = localFont({
  src: "./fonts/PretendardVariable.woff2",
  display: "swap",
  weight: "45 920",
});

export const metadata: Metadata = {
  title: "Cloudy",
  description: "Cloudy",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={`${pretendard.className} antialiased`}>
        <div className="flex h-full gap-20 bg-stone-100 pr-20">
          <aside className="flex h-full w-300 flex-col border-r border-gray-200 bg-white">
            <nav></nav>
          </aside>

          <div className="flex h-full w-full flex-col gap-10 pb-20">
            <header className="flex pb-10 pt-20">
              <h1 className="text-4xl font-bold text-indigo-500">title</h1>
            </header>
            {children}
          </div>
        </div>
      </body>
    </html>
  );
}
