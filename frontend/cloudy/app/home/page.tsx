// import Button from "@/shared/ui/button/Button";

export default function Home() {
  return (
    <>
      <section className="flex h-full w-full gap-10">
        <article className="flex h-full w-2/3 rounded-5 border border-gray-200 bg-white"></article>
        <aside className="flex h-full w-1/3 rounded-5 border border-gray-200 bg-white"></aside>
      </section>
      <section className="flex h-full w-full gap-10">
        <article className="flex h-full w-1/4 rounded-5 border border-gray-200 bg-white"></article>
        <article className="flex h-full w-1/4 rounded-5 border border-gray-200 bg-white"></article>
        <article className="flex h-full w-1/4 rounded-5 border border-gray-200 bg-white"></article>
        <article className="flex h-full w-1/4 rounded-5 border border-gray-200 bg-white"></article>
      </section>
    </>
  );
}
