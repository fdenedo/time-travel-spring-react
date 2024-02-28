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
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"
import { Input } from "@/components/ui/input"
import { Switch } from "@/components/ui/switch"
import { FaClock } from "react-icons/fa";

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
          <form onSubmit={form.handleSubmit(onSubmit)} className="flex flex-row space-x-6 justify-center bg-white rounded-lg border p-8">
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
            <div className="flex flex-row space-x-8">
              <div className="flex flex-col space-y-4">
                <div className="flex flex-row items-center space-x-[-16px]">
                  <FormField
                    control={form.control}
                    name="departureDate"
                    render={({ field }) => (
                      <FormItem className='flex flex-col'>
                        <Popover>
                          <PopoverTrigger asChild>
                            <FormControl>
                              <Button
                                variant={"outline"}
                                className={cn(
                                  "flex-row items-center w-[280px] px-6 py-8 text-lg font-medium bg-white",
                                  !field.value && "text-muted-foreground"
                                )}
                              >
                                <div className="flex flex-col items-start">
                                  <FormLabel className="block text-xs">Departure Date</FormLabel>
                                  {field.value ? (
                                    format(field.value, "do MMMM yyyy")
                                  ) : (
                                    <span>Pick a date</span>
                                  )}
                                </div>
                                <CalendarIcon className="ml-auto h-5 w-5 opacity-50" />
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
                  <Button 
                    variant={"outline"}
                    className="rounded-full z-10 w-13 h-13 aspect-square bg-white"
                  >
                    <FaClock size={18} />
                  </Button>
                  <FormField
                    control={form.control}
                    name="arrivalDate"
                    render={({ field }) => (
                      <FormItem className='flex flex-col'>
                        <Popover>
                          <PopoverTrigger asChild>
                            <FormControl>
                              <Button
                                variant={"outline"}
                                className={cn(
                                  "flex-row items-center w-[280px] px-6 py-8 text-lg font-medium bg-white",
                                  !field.value && "text-muted-foreground"
                                )}
                              >
                                <div className="flex flex-col items-start">
                                  <FormLabel className="block text-xs">Arrival Date</FormLabel>
                                  {field.value ? (
                                    format(field.value, "do MMMM yyyy")
                                  ) : (
                                    <span>Pick a date</span>
                                  )}
                                </div>
                                <CalendarIcon className="ml-auto h-5 w-5 opacity-50" />
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
                </div>
                {form.getValues("needReturn") && 
                  <div className="flex flex-row space-x-8 items-end">
                    <FormField
                      control={form.control}
                      name="returnDate"
                      render={({ field }) => (
                        <FormItem className='flex flex-col'>
                          <Popover>
                            <PopoverTrigger asChild>
                              <FormControl>
                                <Button
                                  variant={"outline"}
                                  className={cn(
                                    "flex-row items-center w-[280px] px-6 py-8 text-lg font-medium bg-white",
                                    !field.value && "text-muted-foreground"
                                  )}
                                >
                                  <div className="flex flex-col items-start">
                                    <FormLabel className="block text-xs">Return Date</FormLabel>
                                    {field.value ? (
                                      format(field.value, "do MMMM yyyy")
                                    ) : (
                                      <span>Pick a date</span>
                                    )}
                                  </div>
                                  <CalendarIcon className="ml-auto h-5 w-5 opacity-50" />
                                </Button>
                              </FormControl>
                            </PopoverTrigger>
                            <PopoverContent className="w-auto p-0" align="start">
                              <Calendar
                                mode="single"
                                selected={field.value}
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
                  </div>
                }
              </div>
              <div className="flex flex-row space-x-8 items-center">
                <FormField
                  control={form.control}
                  name="passengers"
                  render={({ field }) => (
                    <FormItem className='flex flex-col space-y-0'>
                      <FormLabel className="text-xs">Passengers</FormLabel>
                      <Select onValueChange={field.onChange} defaultValue={field.value.toString()}>
                        <FormControl>
                          <SelectTrigger className="border-0 shadow-none pl-1 pr-0 font-medium">
                            <SelectValue placeholder="1" />
                          </SelectTrigger>
                        </FormControl>
                        <SelectContent>
                          {Array.from({ length: 12 }, (_, index) => index + 1).map(i => {
                            return <SelectItem key={i} value={i.toString()}>{i}</SelectItem>
                          })}
                        </SelectContent>
                      </Select>
                    </FormItem>
                  )}
                />
              </div>
            </div>
            <div className="flex flex-col justify-center">
              <Button className="search text-lg p-6" type="submit">Search</Button>
            </div>
          </form>
        </Form>
      )
}

export default JourneySearchForm