import { Journey } from "@/components/JourneyCard";
import { JourneySearchFormData } from "@/components/JourneySearchForm";

export const fetchJourneys = async (formData: JourneySearchFormData): Promise<Journey[]> => {
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

        if (!response.ok) {
            throw new Error('Failed to fetch journeys');
        }

        return response.json() as Promise<Journey[]>;
    } catch (error) {
        console.error('Error fetching journeys:', error);
        throw error;
    }
};