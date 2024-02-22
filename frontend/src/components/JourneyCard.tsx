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

const JourneyCard = ({ journey }: JourneyProps) => {
  const [, dMonth, dDay, dYear] = new Date(journey.departureDate).toDateString().split(' ');
  const dTime = journey.departureTime.split(':').slice(0, -1).join(':');
  const [, aMonth, aDay, aYear] = new Date(journey.targetArrivalDate).toDateString().split(' ');

  const isReturnJourney = journey.targetReturnArrivalDate !== null || undefined;
  const [, rdMonth, rdDay, rdYear] = journey.targetReturnDepartureDate 
      && new Date(journey.targetReturnDepartureDate).toDateString().split(' ')
      || [null, null, null, null];
  const [, raMonth, raDay, raYear] = journey.targetReturnArrivalDate 
      && new Date(journey.targetReturnArrivalDate).toDateString().split(' ')
      || [null, null, null, null];

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
  
  return (
      <Card className="w-[60rem] my-8">
        <CardContent className="p-2 flex flex-row">
          <div className="journey-details pr-10 flex w-[70%] flex-col justify-between">
            <div className="first-journey-details p-4 flex flex-row justify-between items-top">
              <div className="provider-section flex flex-col justify-center">
                <h2>{journey.providerName}</h2>
              </div>
              <div className="time-section flex flex-row">
                <div className="journey-from flex flex-col justify-start">
                  <p className="text-slate-400">{`${dDay} ${dMonth}`}</p>
                  <p className="text-2xl">{dYear}</p>
                  <div className="rounded-lg bg-slate-200 p-[1px] mt-2">
                    <p className="text-slate-600">{dTime}</p>
                  </div>
                </div>
                <div className="separator flex flex-col justify-center items-center mx-6">
                  <div className="h-[2px] w-28 bg-slate-600"></div>
                </div>
                <div className="journey-to flex flex-col justify-start">
                  <p className="text-slate-400">{`${aDay} ${aMonth}`}</p>
                  <p className="text-2xl">{aYear}</p>
                </div>
              </div>
            </div>
            {isReturnJourney && 
              <div className="return-journey-details p-4 flex flex-row justify-between items-top">
                <div className="provider-section flex flex-col justify-center">
                  <h2>{journey.providerName}</h2>
                </div>
                <div className="time-section flex flex-row">
                  <div className="journey-from flex flex-col justify-start">
                    <p className="text-slate-400">{`${rdDay} ${rdMonth}`}</p>
                    <p className="text-2xl">{rdYear}</p>
                  </div>
                  <div className="separator flex flex-col justify-center items-center mx-6">
                    <div className="h-[2px] w-32 bg-slate-600"></div>
                  </div>
                  <div className="journey-to flex flex-col justify-start">
                    <p className="text-slate-400">{`${raDay} ${raMonth}`}</p>
                    <p className="text-2xl">{raYear}</p>
                  </div>
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