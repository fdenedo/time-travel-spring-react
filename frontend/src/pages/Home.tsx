import JourneyCard, { Journey } from "@/components/JourneyCard";
import JourneySearchForm, { JourneySearchFormData } from "@/components/JourneySearchForm";
import { fetchJourneys } from "@/services/journeyService";
import { useState } from "react";

export default function Home() {
    const [journeys, setJourneys] = useState<Journey[]>([]);

    const handleSearch = async (formData: JourneySearchFormData) => {
        try {
            const journeys = await fetchJourneys(formData);
            setJourneys(journeys);
        } catch (error) {
            console.error('Error fetching journeys:', error);
        }
    }

    return (
        <>
            <div className="hero h-[540px] w-full bg-slate-300 p-12 mb-16">
                <h2 className="text-left">Book Your Time Journey</h2>
            </div>
            <JourneySearchForm onSearch={handleSearch} />
            <div className='search-results flex flex-col items-center mt-16'>
                {journeys.length > 0 && <p>{`Showing ${journeys.length} results`}</p>}
                {journeys.map(journey =>
                <div key={journey.serviceId}>
                    <JourneyCard journey={journey} />
                </div>
                )}
            </div>
        </>
    );
}