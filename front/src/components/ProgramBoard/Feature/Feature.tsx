import React from "react";
import { useDraggable } from "@dnd-kit/core";
import FeatureItem from "@/components/FeatureItem";

const Feature: React.FC<any> = ({ ...props }) => {
  const { attributes, listeners, setNodeRef, transform } = useDraggable({
    id: props.idFeature,
    data: {
      type: "feature",
      ...props,
    },
  });
  const style = transform
    ? {
        transform: `translate3d(${transform.x}px, ${transform.y}px, 0)`,
      }
    : undefined;

  return (
    <div
      id={String(props.idFeature)}
      ref={setNodeRef}
      style={style}
      {...listeners}
      {...attributes}
    >
      <FeatureItem {...props} />
    </div>
  );
};

export default Feature;
