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
        <div className="flex h-full bg-stone-100 gap-20 pr-20">
          <aside className="flex flex-col h-full w-300 border-r border-gray-200 bg-white">
            <nav></nav>
          </aside>

          <div className="flex flex-col w-full h-full pb-20 gap-10">
            <header className="flex pt-20 pb-10">
              <h1 className="font-bold text-indigo-500 text-4xl">title</h1>
            </header>
            {children}
          </div>
        </div>
      </body>
    </html>
  );
}
