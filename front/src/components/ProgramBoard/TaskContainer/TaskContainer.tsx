import React from "react";
import { useDraggable } from "@dnd-kit/core";

const TaskContainer: React.FC<any> = ({ id, label, iterationId, teamId }) => {
  const { attributes, listeners, setNodeRef, transform } = useDraggable({
    id,
    data: {
      type: "task",
      iterationId,
      teamId,
    },
  });
  const style = transform
    ? {
        transform: `translate3d(${transform.x}px, ${transform.y}px, 0)`,
      }
    : undefined;

  return (
    <div
      id={id}
      ref={setNodeRef}
      className="bg-slate-50 p-5"
      style={style}
      {...listeners}
      {...attributes}
    >
      {label}
    </div>
  );
};

export default TaskContainer;
