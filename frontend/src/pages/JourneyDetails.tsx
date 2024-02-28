import { DateTimeDisplay, Separator } from "@/components/JourneyCard";
import { Button } from "@/components/ui/button";
import { FaUser } from "react-icons/fa";

export default function JourneyDetails() {
    return (
        <>
            <h1 className="text-left my-16">Journey Details</h1>
            <h2 className="text-right mb-8">Outbound</h2>
            <div className="outbound-journey bg-white">
                <div className="header p-4 flex flex-row space-x-8 justify-between items-center">
                    <div className="journey-details flex flex-row">
                        <img className="w-[150px]" src="/rewind-logo.svg" alt="Rewind Logo" />
                        <DateTimeDisplay date={new Date("2024-02-22")} time={"12:24:51"} />
                        <Separator />
                        <DateTimeDisplay date={new Date("1800-09-24")} time={"15:00:51"} />
                    </div>
                    <div className="journey-info bg-blue-100 p-3 space-y-1 flex flex-col flex-grow-0 justify-end">
                        <div className="passengers inline-flex items-center space-x-2 text-xs text-slate-800"><FaUser /><span>2 Passengers</span></div>
                        <div className="time-machine inline-flex items-center space-x-2 text-xs text-slate-800">QuantumCorp QM-3000</div>
                    </div>
                </div>
            </div>
            <h2 className="text-right mb-8 mt-12">Return</h2>
            <div className="return-journey bg-white">
                <div className="header p-4 flex flex-row space-x-8 justify-between items-center">
                    <div className="journey-details flex flex-row">
                        <img className="w-[150px]" src="/rewind-logo.svg" alt="Rewind Logo" />
                        <DateTimeDisplay date={new Date("1800-09-26")} />
                        <Separator />
                        <DateTimeDisplay date={new Date("2024-02-21")} />
                    </div>
                    <div className="journey-info bg-blue-100 p-3 space-y-1 flex flex-col flex-grow-0 justify-end">
                        <div className="passengers inline-flex items-center space-x-2 text-xs text-slate-800"><FaUser /><span>2 Passengers</span></div>
                        <div className="time-machine inline-flex items-center space-x-2 text-xs text-slate-800">QuantumCorp QM-3000</div>
                    </div>
                </div>
            </div>
            <Button className="checkout mt-12">Checkout</Button>
        </>
    );
}