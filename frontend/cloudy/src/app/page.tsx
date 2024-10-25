export default function Home() {
  return (
    <>
      <section className="flex w-full h-full gap-10">
        <article className="flex w-2/3 h-full bg-white border border-gray-200 rounded-5"></article>
        <aside className="flex w-1/3 h-full bg-white border border-gray-200 rounded-5"></aside>
      </section>
      <section className="flex w-full h-full gap-10">
        <article className="flex w-1/4 h-full bg-white border border-gray-200 rounded-5"></article>
        <article className="flex w-1/4 h-full bg-white border border-gray-200 rounded-5"></article>
        <article className="flex w-1/4 h-full bg-white border border-gray-200 rounded-5"></article>
        <article className="flex w-1/4 h-full bg-white border border-gray-200 rounded-5"></article>
      </section>
    </>
  );
}
