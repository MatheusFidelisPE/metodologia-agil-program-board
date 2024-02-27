import { IoIosArrowDown, IoIosArrowForward } from "react-icons/io";
import { FaRegDotCircle } from "react-icons/fa";
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import AccordionDetails from "@mui/material/AccordionDetails";
import moment from "moment";
import React, { useState } from "react";
import api from "@/services/api";

const FeatureItem: React.FC<any> = ({
  showDependency = true,
  onTaskOpen = () => {},
  onFeatureOpen = () => {},
  ...feature
}) => {
  const [isExpanded, setExpanded] = useState(false);
  const [tasks, setTasks] = useState<Array<Task>>([]);

  const handleTaskOpen = async (id: any) => {
    const { data: task } = await api.get(`/task/${id}`);
    onTaskOpen(task);
  };
  
  const handleFeatureOpen = async (id: any) => {
    const { data: feature } = await api.get(`/feature/${id}`);
    onFeatureOpen(feature);
  };


  const getAllTasks = () =>
    api
      .get(`/feature/tasks-from-feature/${feature.idFeature}`)
      .then(({ data }) => setTasks(data));

  const handleChange =
    () => (_event: React.SyntheticEvent, isExpanded: boolean) => {
      if (isExpanded) {
        getAllTasks();
      }
      setExpanded(!!isExpanded);
    };

  return (
    <Accordion onChange={handleChange()} expanded={isExpanded}>
      <AccordionSummary className="p-0 [&>*]:!m-0 border-gray-300 border-[1px]">
        <div className="w-full bg-white border-gray-300 border-[1px] p-2">
          <div
            onClick={(e) => {
              handleFeatureOpen(feature.idFeature);
              e.stopPropagation();
            }}
          >
            <div className="flex justify-between w-full text-sm font-bold">
              <div className="text-green-500">{`F-${feature.idFeature}`}</div>
              <div>
                {showDependency && <FaRegDotCircle className="text-red-500" />}
              </div>
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
          </div>
          <div
            className="w-full flex justify-between text-sm"
            onClick={() => setExpanded(!!isExpanded)}
          >
            <span className=" border-red-300 border-[1px] rounded-sm flex justify-center items-center px-2">
              0
            </span>
            <div className="capitalize">
              {moment(feature.valueDate).format("MMM D")}
            </div>
            <div>
              {!isExpanded ? <IoIosArrowForward /> : <IoIosArrowDown />}
            </div>
          </div>
        </div>
      </AccordionSummary>
      <AccordionDetails className="p-0">
        <div className="flex flex-col gap-2 m-2 justify-center items-center">
          {tasks?.length === 0 && "Sem tarefas"}
          {tasks?.map((task, key) => (
            <div
              className="flex-1 bg-blue-300 rounded-md px-3 py-1"
              key={key}
              onClick={() => handleTaskOpen(task.id)}
            >
              {`${task.status} - ${task.prioridade} | ${task.titulo}`}
            </div>
          ))}
        </div>
      </AccordionDetails>
    </Accordion>
  );
};

export default FeatureItem;
