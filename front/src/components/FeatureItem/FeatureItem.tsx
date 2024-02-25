import { IoIosArrowForward } from "react-icons/io";
import moment from "moment";

const FeatureItem = ({ ...feature }) => {
  return (
    <div className="w-full bg-white border-gray-300 border-[1px] rounded-md p-2" >
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
      <div className="w-full flex justify-between text-sm">
        <span className=" border-red-300 border-[1px] rounded-sm flex justify-center items-center px-2">
          0
        </span>
        <div className="capitalize">
          {moment(feature.valueDate).format("MMM D")}
        </div>
        <div>
          <IoIosArrowForward />
        </div>
      </div>
    </div>
  );
};

export default FeatureItem;
