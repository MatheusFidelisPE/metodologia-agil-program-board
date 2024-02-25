import React from "react";
import { useDroppable } from "@dnd-kit/core";

const Task: React.FC<any> = ({
  id,
  children,
  teamId,
  iterationId,
  ...props
}) => {
  const { setNodeRef } = useDroppable({
    id,
    data: {
      accepts: ["feature"],
      iterationId,
      teamId,
    },
  });

  return (
    <td
      ref={setNodeRef}
      className="bg-slate-300 p-1 align-baseline"
      {...props}
    >
      <div className="flex gap-2 flex-col">{children}</div>
    </td>
  );
};

export default Task;
