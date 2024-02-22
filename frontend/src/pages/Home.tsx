import JourneyCard, { Journey } from "@/components/JourneyCard";
import JourneySearchForm, { JourneySearchFormData } from "@/components/JourneySearchForm";
import { useState } from "react";

export default function Home() {
    const [journeys, setJourneys] = useState<Journey[]>([]);

    const fetchJourneys = async (formData: JourneySearchFormData) => {
        const params = new URLSearchParams();
        Object.entries(formData).forEach(([key, value]) => {
        if (value instanceof Date) {
            params.append(key, value.toISOString().split('T')[0]);
        } else {
            params.append(key, String(value));
        }
        });

        try {
            const response = await fetch(`/api/journeys?${params.toString()}`, {
                method: 'GET'
            });

            if (response.ok) {
                const data: Journey[] = await response.json();
                setJourneys(data);
            } else {
                console.error('Failed to fetch journeys');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <>
            <JourneySearchForm onSearch={fetchJourneys} />
            <div className='search-results flex flex-col items-center mt-16'>
                <h1>Journeys</h1>
                {journeys.map(journey =>
                <div key={journey.serviceId}>
                    <JourneyCard journey={journey} />
                </div>
                )}
            </div>
        </>
    );
}