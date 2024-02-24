import React, { useEffect } from "react";
import { DndContext } from "@dnd-kit/core";
import { useState } from "react";
import Xarrow, { Xwrapper, useXarrow } from "react-xarrows";
import uniqueId from "lodash/uniqueId";
import Task from "./Task";
import TaskContainer from "./TaskContainer";
import api from "@/services/api";
import moment from "moment";

const Base = () => {
  const updateXarrow = useXarrow();
  const [features, setFeatures] = useState<Array<Feature>>([]);
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

  const getAllFeatures = () => {
    api.get("/feature").then(() => console.log());
    // setFeatures([
    //   {
    //     idFeature: 1,
    //     title: "string",
    //     hypothesis: "string",
    //     acceptanceCriteria: "string",
    //     priority: 0,
    //     valueDate: "2024-02-18",
    //     effort: 0,
    //     idSprint: 1,
    //     idTime: 1,
    //   },
    // ]);
  };

  useEffect(() => {
    getAllFeatures();
  }, []);

  return (
    <DndContext
      onDragMove={updateXarrow}
      onDragOver={updateXarrow}
      onDragEnd={handleDragEnd}
    >
      <div className="flex h-full w-full p-5 bg-neutral-100">
        <div className="h-full bg-white rounded-md shadow-md w-1/4">
          <div className="font-bold text-lg p-5 pb-0">Features</div>
            <hr className="my-4 h-0.5 border-t-0 bg-neutral-300 opacity-100 dark:opacity-50" />
          <div className="w-full h-full flex flex-col flex-wrap overflow-hidden px-2">
            {features?.map((feature, key) => (
              <div
                key={key}
                className="w-full border-gray-300 border-[1px] rounded-md p-2"
              >
                <div className="flex justify-between w-full text-sm font-bold">
                  <div className="text-green-500">{`F-${feature.idFeature}`}</div>
                  <div>{feature.priority}</div>
                </div>
                <div className="capitalize w-full">{feature.title}</div>
                <div className="w-full">
                  <span className="inline-block whitespace-nowrap rounded-lg bg-green-300 px-[0.65em] pb-[0.25em] pt-[0.35em] text-center align-baseline text-[0.75em] font-semibold leading-none">
                    {`S-${feature.idSprint}`}
                  </span>
                </div>
                <div className="w-full">
                  <hr className="my-4 h-0.5 border-t-0 bg-neutral-100 opacity-100 dark:opacity-50" />
                </div>
                <div className="w-full flex justify-between">
                  <span className="inline-block whitespace-nowrap border-red-300 border-[1px] rounded-sm px-[0.65em] pb-[0.25em] pt-[0.35em] text-center align-baseline text-[0.75em] leading-none">
                    0
                  </span>
                  <div className="capitalize">
                    {moment(feature.valueDate).format("MMM D")}
                  </div>
                  <div>{feature.valueDate} </div>
                </div>
              </div>
            ))}
          </div>
        </div>
        <div className="flex gap-1 flex-col h-full w-full">
          <table className="border-separate border-spacing-2 h-full">
            <thead>
              <th className="bg-green-300 p-2" />
              {iterations.map((iteration, key) => (
                <th className="bg-blue-300 p-2" key={key}>
                  Iteração {key + 1}
                </th>
              ))}
            </thead>
            <tbody>
              {teams.map((team, key) => (
                <tr key={key} className="h-1/4">
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
