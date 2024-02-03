import { DndContext, useDraggable, useDroppable } from "@dnd-kit/core";
import React, { useState } from "react";
import { DndProvider, useDrag, useDrop } from "react-dnd";
import { HTML5Backend } from "react-dnd-html5-backend";
import Draggable from "react-draggable";
import Xarrow, { Xwrapper, useXarrow } from "react-xarrows";

const DraggableBox = ({ id, label }) => {
  const updateXarrow = useXarrow();
  const {attributes, listeners, setNodeRef, transform} = useDraggable({
    id,
    data: {
      type: 'task',
    },
  });
  const style = transform ? {
    transform: `translate3d(${transform.x}px, ${transform.y}px, 0)`,
  } : undefined;

  
  return (
    <div
      ref={setNodeRef}
      className="bg-slate-50 p-5"
       style={style}
      {...listeners} {...attributes}
    >
      {label}
    </div>
  );
};

const DropBox = ({ id, children }) => {
  const {setNodeRef} = useDroppable({
    id,
    data: {
      accepts: ['task'],
    },
  });

  return (
    <td ref={setNodeRef} className="bg-slate-300 p-1 min-h-full">
      {children}
    </td>
  );
};

interface Team {
  name: string;
  iterations?: Array<Iteration>
  tasks: Array<Task>
}

interface Iteration {
  tasks: Array<Task>;
}

interface Task {
  label: string;
  id: string;
  dependencies?: Array<string>;
}

const Page = () => {
  const [teams, setTeams] = useState<Array<Team>>([
    {
      name: "Milestones",
      tasks: [
        {
          label: "Tarefa 1",
          id: "task-one",
          dependencies: ["task-two"],
        },
      ],
    },
    {
      name: "Team 1",
      tasks: [],
    },
    {
      name: "Shared Services Team",
      tasks: [
        {
          label: "Tarefa 2",
          id: "task-two",
        },
      ],
    },
  ]);

  return (
    <DndContext>
      <div className="h-full w-full p-5">
        <div className="flex gap-1 flex-col">
          <Xwrapper>
            <table className="border-separate border-spacing-2">
              <thead>
                <th className="bg-green-300 p-2 min-h-full " />
                {Array.of(5).map((iteration, key) => (
                  <th className="bg-blue-300 p-2 " key={key}>
                    Iteração {key + 1}
                  </th>
                ))}
              </thead>
              <tbody>
                {teams.map((team, key) => (
                  <tr key={key}>
                    <td className="bg-green-300 p-2">{team.name}</td>
                    {iterations?.map((iteration, key) => (
                      <DropBox key={key} id={`task-${team.name}-iteration-${key}`}>
                        {iteration.tasks?.map(
                          ({ dependencies, ...task }, key) => (
                            <React.Fragment key={key}>
                              <DraggableBox {...task} key={key} />
                              {dependencies?.map((dependency, key) => (
                                <Xarrow
                                  key={key}
                                  start={task.id}
                                  end={dependency}
                                />
                              ))}
                            </React.Fragment>
                          )
                        )}
                      </DropBox>
                    ))}
                  </tr>
                ))}
              </tbody>
            </table>
          </Xwrapper>
        </div>
      </div>
    </DndContext>
  );
};

export default Page;
