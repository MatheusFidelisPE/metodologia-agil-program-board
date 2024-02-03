import React from "react";
import { DndContext } from "@dnd-kit/core";
import { useState } from "react";
import Xarrow, { Xwrapper, useXarrow } from "react-xarrows";
import uniqueId from "lodash/uniqueId";
import Task from "./Task";
import TaskContainer from "./TaskContainer";

const Base = () => {
  const updateXarrow = useXarrow();
  const [iterations, setIterations] = useState<Array<Iteration>>([
    {
      id: "iteration-one",
      tasks: ["task-one", "task-three"],
    },
    {
      id: "iteration-two",
      tasks: [],
    },
    {
      id: "iteration-three",
      tasks: [],
    },
    {
      id: "iteration-four",
      tasks: ["task-two"],
    },
    {
      id: "iteration-five",
      tasks: [],
    },
    {
      id: "iteration-six",
      tasks: [],
    },
  ]);

  const [teams, setTeams] = useState<Array<Team>>([
    {
      id: "team-one",
      name: "Milestones",
      tasks: [
        {
          label: "Tarefa 1",
          id: "task-one",
          dependencies: ["task-two"],
          iteration: "iteration-one",
        },
        {
          label: "Tarefa 3",
          id: "task-three",
          dependencies: [],
          iteration: "iteration-one",
        },
      ],
    },
    {
      id: "team-two",
      name: "Team 1",
      tasks: [],
    },
    {
      id: "team-three",
      name: "Shared Services Team",
      tasks: [
        {
          label: "Tarefa 2",
          id: "task-two",
          iteration: "iteration-three",
        },
      ],
    },
  ]);

  const handleDragEnd = (event: any) => {
    const { active, over } = event;

    if (over && over.data.current.accepts.includes(active.data.current.type)) {
      const originTaskId = active.id;
      const iterationId = over.data.current.iterationId;
      const teamId = over.data.current.teamId;
      const originIterationId = active.data.current.iterationId;
      const originTeamId = active.data.current.teamId;

      setIterations((prev) => {
        const newArray = [...prev];
        newArray.forEach((item) => {
          if (item.id === originIterationId) {
            item.tasks = item.tasks.filter((x) => x !== originTaskId);
          }
          if (item.id === iterationId) {
            item.tasks = [...item.tasks, originTaskId.toString()];
          }
        });

        return newArray;
      });

      setTeams((prev) => {
        const newArray = [...prev];
        const originTeamIndex = newArray.findIndex(
          (x) => x.id === originTeamId
        );
        const destinationTeamIndex = newArray.findIndex((x) => x.id === teamId);

        if (originTeamIndex !== -1) {
          const taskToMove = newArray[originTeamIndex].tasks?.find(
            (x) => x.id.toString() === originTaskId
          ) as Task;

          newArray[originTeamIndex].tasks = newArray[
            originTeamIndex
          ].tasks.filter((item) => {
            if (item.id === originTaskId) {
              return false;
            }
            return true;
          });

          if (taskToMove) {
            newArray[destinationTeamIndex].tasks = [
              ...newArray[destinationTeamIndex].tasks,
              taskToMove,
            ];

            taskToMove.iteration = iterationId;
          }
        }

        return newArray;
      });
    }

    updateXarrow();
  };

  return (
    <DndContext
      onDragMove={updateXarrow}
      onDragOver={updateXarrow}
      onDragEnd={handleDragEnd}
    >
      <div className="h-full w-full p-5">
        <div className="flex gap-1 flex-col h-full">
          <div className="flex overflow-auto">
            Iterações:
            <pre id="json">{JSON.stringify(iterations, undefined, 2)}</pre>
            Equipes:
            <pre id="json">{JSON.stringify(teams, undefined, 2)}</pre>
          </div>
          <table className="border-separate border-spacing-2 h-full">
            <thead>
              <th className="bg-green-300 p-2 min-h-full" />
              {iterations.map((iteration, key) => (
                <th className="bg-blue-300 p-2 min-h-full" key={key}>
                  Iteração {key + 1}
                </th>
              ))}
            </thead>
            <tbody>
              {teams.map((team, key) => (
                <tr key={key}>
                  <td
                    className="bg-green-300 p-2"
                    width={`${100 / (iterations.length + 1)}%`}
                  >
                    {team.name}
                  </td>
                  {iterations?.map((iteration, key) => (
                    <Task
                      key={key}
                      id={`task-${team.name}-iteration-${key}`}
                      width={`${100 / (iterations.length + 1)}%`}
                      iterationId={iteration.id}
                      teamId={team.id}
                    >
                      {team.tasks
                        ?.filter((x) => iteration.tasks.includes(x.id))
                        ?.map(({ dependencies, ...task }, key) => (
                          <React.Fragment key={key}>
                            <TaskContainer
                              {...task}
                              iterationId={iteration.id}
                              teamId={team.id}
                              key={key}
                            />
                            {dependencies?.map((dependency) => (
                              <Xarrow
                                key={uniqueId()}
                                start={task.id}
                                end={dependency}
                                showHead={false}
                                lineColor="red"
                              />
                            ))}
                          </React.Fragment>
                        ))}
                    </Task>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </DndContext>
  );
};

const ProgramBoard = () => (
  <Xwrapper>
    <Base />
  </Xwrapper>
);

export default ProgramBoard;
