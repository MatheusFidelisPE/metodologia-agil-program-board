import React from "react";
import dynamic from "next/dynamic";

const NoSsrProgramBoard = dynamic(() => import("@/components/ProgramBoard"), {
  ssr: false,
});

const Page = () => <NoSsrProgramBoard />;

export default Page;
