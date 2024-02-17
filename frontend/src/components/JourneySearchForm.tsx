import { z } from "zod"
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import { format } from "date-fns"

import { cn } from "@/lib/utils"
import { CalendarIcon } from "@radix-ui/react-icons"
import { Button } from "@/components/ui/button"
import { Calendar } from "@/components/ui/calendar"
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form"
import {
    Popover,
    PopoverContent,
    PopoverTrigger,
} from "@/components/ui/popover"
import { Input } from "@/components/ui/input"
import { Switch } from "@/components/ui/switch"

const formSchema = z.object({
    departureDate: z.coerce.date().min(new Date()),
    arrivalDate: z.coerce.date(),
    passengers: z.coerce.number().min(1),
    needReturn: z.boolean(),
    returnDate: z.coerce.date().optional(),
    journeyLengthInDays: z.coerce.number().min(0).optional(),
})
  .refine(schema => {
    return (!schema.needReturn || schema.returnDate !== undefined);
  })

export interface SingleJourneySearchFormData {
  departureDate: Date;
  arrivalDate: Date;
  passengers: number;
}

export interface ReturnJourneySearchFormData {
  departureDate: Date;
  arrivalDate: Date;
  passengers: number;
  returnDate: Date;
  journeyLengthInDays: number;
}

export type JourneySearchFormData = SingleJourneySearchFormData | ReturnJourneySearchFormData;

interface JourneySearchFormProps {
  onSearch: (formData: JourneySearchFormData) => void;
}

export function JourneySearchForm({ onSearch }: JourneySearchFormProps) {
    const form = useForm<z.infer<typeof formSchema>>({
      resolver: zodResolver(formSchema),
      defaultValues: {
        departureDate: new Date(),
        arrivalDate: new Date(),
        passengers: 1,
        needReturn: false,
        journeyLengthInDays: 0,
      },
    })
   
    function onSubmit(values: z.infer<typeof formSchema>) {
      console.log(values)
      let journeySearchData: JourneySearchFormData;
      if (values.needReturn) {
        journeySearchData = {
          departureDate: values.departureDate,
          arrivalDate: values.arrivalDate,
          passengers: values.passengers,
          returnDate: values.returnDate,
          journeyLengthInDays: values.journeyLengthInDays,
        } as ReturnJourneySearchFormData
      } else {
        journeySearchData = {
          departureDate: values.departureDate,
          arrivalDate: values.arrivalDate,
          passengers: values.passengers,
        } as SingleJourneySearchFormData
      }

      onSearch(journeySearchData);
    }

    return (
        <Form {...form}>
          <form onSubmit={form.handleSubmit(onSubmit)} className="flex flex-row space-x-6 justify-center rounded-lg border p-8 shadow-sm">
            <div className="flex flex-col justify-start">
              <FormField 
                control={form.control}
                name="needReturn"
                render={({ field }) => (
                  <FormItem className="flex flex-col items-center">
                    <div className="space-y-0.5">
                      <FormLabel>Return</FormLabel>
                    </div>
                    <FormControl>
                      <Switch
                        checked={field.value}
                        onCheckedChange={field.onChange}
                      />
                    </FormControl>
                  </FormItem>
                )}
              />
            </div>
            <div className="flex flex-col space-y-8">
              <div className="flex flex-row space-x-8 items-end">
                <FormField
                  control={form.control}
                  name="departureDate"
                  render={({ field }) => (
                    <FormItem className='flex flex-col'>
                      <FormLabel>Departure Date</FormLabel>
                      <Popover>
                        <PopoverTrigger asChild>
                          <FormControl>
                            <Button
                              variant={"outline"}
                              className={cn(
                                "w-[240px] pl-3 text-left font-normal",
                                !field.value && "text-muted-foreground"
                              )}
                            >
                              {field.value ? (
                                format(field.value, "PPP")
                              ) : (
                                <span>Pick a date</span>
                              )}
                              <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                            </Button>
                          </FormControl>
                        </PopoverTrigger>
                        <PopoverContent className="w-auto p-0" align="start">
                          <Calendar
                            mode="single"
                            selected={field.value}
                            onSelect={field.onChange}
                            disabled={(date) => date <= new Date()}
                            initialFocus
                          />
                        </PopoverContent>
                      </Popover>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FormField
                  control={form.control}
                  name="arrivalDate"
                  render={({ field }) => (
                    <FormItem className='flex flex-col'>
                      <FormLabel>Arrival Date</FormLabel>
                      <Popover>
                        <PopoverTrigger asChild>
                          <FormControl>
                            <Button
                              variant={"outline"}
                              className={cn(
                                "w-[240px] pl-3 text-left font-normal",
                                !field.value && "text-muted-foreground"
                              )}
                            >
                              {field.value ? (
                                format(field.value, "PPP")
                              ) : (
                                <span>Pick a date</span>
                              )}
                              <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                            </Button>
                          </FormControl>
                        </PopoverTrigger>
                        <PopoverContent className="w-auto p-0" align="start">
                          <Calendar
                            mode="single"
                            selected={field.value}
                            onSelect={field.onChange}
                            initialFocus
                          />
                        </PopoverContent>
                      </Popover>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FormField
                  control={form.control}
                  name="passengers"
                  render={({ field }) => (
                    <FormItem className='flex flex-col'>
                      <FormLabel>Passengers</FormLabel>
                      <FormControl>
                        <Input {...field} />
                      </FormControl>
                    </FormItem>
                  )}
                />
              </div>
              {form.getValues("needReturn") && <div className="flex flex-row space-x-8 items-end">
                <FormField
                  control={form.control}
                  name="returnDate"
                  render={({ field }) => (
                    <FormItem className='flex flex-col'>
                      <FormLabel>Return Date</FormLabel>
                      <Popover>
                        <PopoverTrigger asChild>
                          <FormControl>
                            <Button
                              variant={"outline"}
                              className={cn(
                                "w-[240px] pl-3 text-left font-normal",
                                !field.value && "text-muted-foreground"
                              )}
                            >
                              {field.value ? (
                                format(field.value, "PPP")
                              ) : (
                                <span>Pick a date</span>
                              )}
                              <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                            </Button>
                          </FormControl>
                        </PopoverTrigger>
                        <PopoverContent className="w-auto p-0" align="start">
                          <Calendar
                            mode="single"
                            onSelect={field.onChange}
                          />
                        </PopoverContent>
                      </Popover>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FormField
                  control={form.control}
                  name="journeyLengthInDays"
                  render={({ field }) => (
                    <FormItem className='flex flex-col'>
                      <FormLabel>Journey Length (Days)</FormLabel>
                      <FormControl>
                        <Input {...field} />
                      </FormControl>
                    </FormItem>
                  )}
                />
              </div>}
            </div>
            <div className="flex flex-col justify-center">
              <Button type="submit">Search</Button>
            </div>
          </form>
        </Form>
      )
}

export default JourneySearchForm