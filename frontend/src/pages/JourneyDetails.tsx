import { DateTimeDisplay, Separator } from "@/components/JourneyCard";
import { FaUser } from "react-icons/fa";

export default function JourneyDetails() {
    return (
        <>
            <h1 className="text-left my-16">Journey Details</h1>
            <h2 className="text-right mb-8">Outbound</h2>
            <div className="outbound-journey bg-white">
                <div className="header p-4 flex flex-row space-x-8 justify-end items-center">
                    <DateTimeDisplay date={new Date("2024-02-22")} time={"12:24:51"} />
                    <Separator />
                    <DateTimeDisplay date={new Date("1800-09-24")} time={"15:00:51"} />
                    <div className="journey-info flex flex-col">
                        <div className="passengers inline-flex items-center space-x-2"><FaUser /><span>2 Passengers</span></div>
                        <div className="time-machine"></div>
                    </div>
                </div>
            </div>
        </>
    );
}