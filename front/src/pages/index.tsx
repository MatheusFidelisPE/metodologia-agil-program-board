import React from "react";
import Draggable from "react-draggable";
import Xarrow, { Xwrapper, useXarrow } from "react-xarrows";

const DraggableBox = ({ id, label }) => {
  const updateXarrow = useXarrow();
  return (
    <Draggable onDrag={updateXarrow} onStop={updateXarrow}>
      <div id={id} className="bg-slate-50 p-5">
        {label}
      </div>
    </Draggable>
  );
};

const Page = () => {
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "space-evenly",
        width: "100%",
        height: "100%",
      }}
    >
      <Xwrapper>
        <DraggableBox id="task1" label={"Tarefa 1"} />
        <DraggableBox id="task2" label={"Tarefa 2"} />
        <Xarrow start={"task1"} end="task2" />
      </Xwrapper>
    </div>
  );
};

export default Page;
