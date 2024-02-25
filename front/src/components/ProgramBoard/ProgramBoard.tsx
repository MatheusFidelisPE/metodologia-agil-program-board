import React, { useCallback, useEffect } from "react";
import { DndContext } from "@dnd-kit/core";
import { useState } from "react";
import Xarrow, { Xwrapper, useXarrow } from "react-xarrows";
import uniqueId from "lodash/uniqueId";
import FeatureContainer from "./FeatureContainer";
import Feature from "./Feature";
import api from "@/services/api";
import { IoIosArrowForward } from "react-icons/io";
import moment from "moment";
import FeatureItem from "../FeatureItem";

const Base = () => {
  const updateXarrow = useXarrow();
  const [features, setFeatures] = useState<Array<Feature>>([]);
  const [iterations, setIterations] = useState<Array<Iteration>>([]);

  const [teams, setTeams] = useState<Array<Team>>([]);

  const handleDragEnd = async (event: any) => {
    const { active, over } = event;

    if (over && over.data.current.accepts.includes(active.data.current.type)) {
      const originFeatureId = active.id;
      const iterationId = over.data.current.iterationId;
      const teamId = over.data.current.teamId;
      const originIterationId = active.data.current.idSprint;
      const originTeamId = active.data.current.idTime;

      if(originTeamId !== teamId) {
        await api.get(`/feature/mudar-feature-de-time/${teamId}/${originFeatureId}`)
      }

      if(originIterationId !== iterationId) {
        await api.get(`/feature/mudar-feature-de-sprint/${iterationId}/${originFeatureId}`)
      }

      getAllFeatures()
    }

    updateXarrow();
  };

  const getAllFeatures = () =>
    api.get("/feature").then(({ data }) => setFeatures(data));

  const getAllIterations = () =>
    api.get("/sprint").then(({ data }) => setIterations(data));

  const getProgramBoardData = useCallback(async () => {
    const { data } = await api.get<Array<Team>>("/team");
    data?.forEach((team, key) => {
      team.features = features.filter((x) => x.idTime === team.id) || [];
    });
    setTeams(data);
  }, [JSON.stringify(features)]);

  useEffect(() => {
    getAllIterations();
    getAllFeatures();
  }, [])

  useEffect(() => {
    getProgramBoardData();
  }, [getProgramBoardData]);

  return (
    <DndContext
      onDragMove={updateXarrow}
      onDragOver={updateXarrow}
      onDragEnd={handleDragEnd}
    >
      <div className="flex h-full w-full p-5 bg-neutral-100">
        <div className="h-full bg-white rounded-md shadow-md w-1/4 flex flex-col overflow-hidden">
          <div className="font-bold text-lg p-5 pb-0">Features</div>
          <hr className="my-4 h-0.5 border-t-0 bg-neutral-300 opacity-100 dark:opacity-50" />
          <div className="w-full flex flex-col overflow-auto px-2 gap-2 flex-1 pb-5">
            {features?.map((feature, key) => (
              <FeatureItem key={key} {...feature} />
            ))}
          </div>
        </div>
        <div className="flex gap-1 flex-col h-full w-full ">
          <table className="border-separate border-spacing-2 h-full">
            <thead>
              <th className="bg-green-300 p-2" />
              {iterations.map((iteration, key) => (
                <th className="bg-blue-300 p-2" key={key}>
                  Sprint {iteration.id}
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
                    {team.nome}
                  </td>
                  {iterations?.map((iteration, key) => (
                    <FeatureContainer
                      key={key}
                      id={`feature-${team.nome}-iteration-${key}`}
                      width={`${100 / (iterations.length + 1)}%`}
                      iterationId={iteration.id}
                      teamId={team.id}
                    >
                      {team.features
                        ?.filter((x) => x.idSprint === iteration.id)
                        ?.map(({ ...feature }, key) => (
                          <React.Fragment key={uniqueId()}>
                            <Feature {...feature} key={key} />
                            {/* {dependencies?.map((dependency) => (
                              <Xarrow
                                key={uniqueId()}
                                start={task.id}
                                end={dependency}
                                showHead={false}
                                lineColor="red"
                              />
                            ))} */}
                          </React.Fragment>
                        ))}
                    </FeatureContainer>
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
