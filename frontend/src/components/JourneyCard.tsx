import { Card, CardContent, CardFooter } from "@/components/ui/card";
import { Button } from "./ui/button";

export interface Journey {
  serviceId: string;
  providerName: string;
  timeMachineMakeAndModel: string;
  departureDate: string;
  departureTime: string;
  targetArrivalDate: string;
  journeyLength?: string;
  targetReturnDepartureDate?: string;
  targetReturnArrivalDate?: string;
  passengers: number;
  totalPrice: number;
}

interface JourneyProps {
  journey: Journey
}

export interface DateTimeDisplayProps {
  date: Date
  time?: string
}

export const DateTimeDisplay = ({
  date, 
  time
}: DateTimeDisplayProps) => {
  const [, month, day, year] = new Date(date).toDateString().split(' ');
  const displayTime = time?.split(':').slice(0, -1).join(':');

  return (
    <div className="journey-from flex flex-col justify-start">
      <p className="text-slate-400">{`${day} ${month}`}</p>
      <p className="text-2xl">{year}</p>
      {displayTime && 
        <div className="rounded-lg bg-slate-200 p-[1px] mt-2">
          <p className="text-slate-600">{displayTime}</p>
        </div>
      }
    </div>
  )
}

export const Separator = () => {
  return (
    <div className="separator flex flex-col justify-center items-center mx-6">
      <div className="h-[2px] w-28 bg-slate-600"></div>
    </div>
  )
}

const JourneyCard = ({ journey }: JourneyProps) => {
  function formatPrice(price: number) {
    let formattedPrice;
    if (price >= 1e9) {
        formattedPrice = (price / 1e9).toFixed(2).replace(/\.?0+$/, '') + ' Billion';
    } else if (price >= 1e6) {
        formattedPrice = (price / 1e6).toFixed(2).replace(/\.?0+$/, '') + ' Million';
    } else if (price >= 1e3) {
        formattedPrice = (price / 1e3).toFixed(2).replace(/\.?0+$/, '') + 'k';
    } else {
        formattedPrice = price.toFixed(2);
    }
    return formattedPrice.replace(/\.+$/, '');
  }

  // TODO: change code to pick the correct logo without hard-coding
  const Logo = () => {
    if (journey.providerName === "Rewind") {
      return <img className="w-[150px]" src="/rewind-logo.svg" alt="Rewind Logo" />;
    }
    else if (journey.providerName === "FirstTravel") {
      return <img className="w-[150px]" src="/firsttravel-logo.svg" alt="FirstTravel Logo" />;
    }
    else {
      return <h2>{journey.providerName}</h2>
    }
  }
  
  return (
      <Card className="w-[60rem] my-8">
        <CardContent className="p-2 flex flex-row">
          <div className="journey-details pr-10 flex w-[70%] flex-col justify-between">
            <div className="first-journey-details p-4 flex flex-row justify-between items-top">
              <div className="provider-section flex flex-col justify-center">
                <Logo />
              </div>
              <div className="time-section flex flex-row">
                <DateTimeDisplay date={new Date(journey.departureDate)} time={journey.departureTime}/>
                <Separator />
                <DateTimeDisplay date={new Date(journey.targetArrivalDate)}/>
              </div>
            </div>
            {journey.targetReturnDepartureDate && journey.targetReturnArrivalDate &&
              <div className="return-journey-details p-4 flex flex-row justify-between items-top">
                <div className="provider-section flex flex-col justify-center">
                  <h2>{journey.providerName}</h2>
                </div>
                <div className="time-section flex flex-row">
                  <DateTimeDisplay date={new Date(journey.targetReturnDepartureDate)} />
                  <Separator />
                  <DateTimeDisplay date={new Date(journey.targetReturnArrivalDate)} />
                </div>
              </div>
            }
          </div>
          <div className="price border-l px-12 flex flex-col w-[30%] justify-center items-center space-y-2">
            <p className="text-xl"><span>£</span>{formatPrice(journey.totalPrice)}</p>
            <Button className="w-[6rem]">View</Button>
          </div>
        </CardContent>
        <CardFooter className="bg-slate-100 flex flex-row items-center p-2 pl-6">
          <p className="text-xs text-slate-600"><strong>Time Machine:</strong> {journey.timeMachineMakeAndModel}</p>
        </CardFooter>
      </Card>
  );
}

export default JourneyCard;