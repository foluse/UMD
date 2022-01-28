/* Fiyin Oluseye, UID: 117165661, directory ID: moluseye */
#include "calendar.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* initializes a Calendar struct based on the parameters */
int init_calendar(const char *name, int days,
                  int (*comp_func) (const void *ptr1, const void *ptr2),
                  void (*free_info_func) (void *ptr), Calendar ** calendar){
    
    int name_len;
    int i = 0;

    if(calendar != NULL && name != NULL && days >= 1){
        /* allocates space for calendar */
        (*calendar) = malloc(sizeof(Calendar));
        if(*calendar == NULL){
            exit(EXIT_FAILURE);
        }
        /* allocates space for calendar name & initializes it */
        name_len = strlen(name) + 1;
        (*calendar)->name = malloc(name_len);
        if((*calendar)->name == NULL){
            return FAILURE;
        }
        strcpy((*calendar)->name, name);

        /* initializes a the # of days */
        (*calendar)->days = days;

        /* allocates space for events & initializes it to 0 */
        (*calendar)->events = calloc(days, sizeof(Event));
        if((*calendar)->events == NULL){
            return FAILURE;
        }
        for(i = 0; i < days; i++){
            (*calendar)->events[i] = NULL;
        }

        /* initializes total events*/
        (*calendar)->total_events = 0;

        /* initializes comp_func to parameter*/
        (*calendar)->comp_func = comp_func;
        /* initializes free_info_func to parameter*/
        (*calendar)->free_info_func = free_info_func;
        return SUCCESS;
    }
        return FAILURE;
}

/* prints, to the designated output stream, the calendar's name, days, and total
number of events if print_all is true */
int print_calendar(Calendar *calendar, FILE *output_stream, int print_all){
    Event *event_pt;
    int day_num = 0; /* tracks the day # for each event*/

    if(calendar != NULL && output_stream != NULL){
        if(print_all){
            fprintf(output_stream, "Calendar's Name: \"%s\"\n", calendar->name);
            fprintf(output_stream, "Days: %d\n", calendar->days);
            fprintf(output_stream, "Total Events: %d\n", calendar->total_events);
            fprintf(output_stream, "\n");
        }

        /* Prints the heading*/
        fprintf(output_stream, "**** Events ****\n");

        if(calendar->total_events > 0){
            for(day_num = 0; day_num < calendar->days; day_num++){
                printf("Day %d\n", day_num + 1);

                for(event_pt = calendar->events[day_num]; event_pt != NULL; 
                event_pt = event_pt->next){
                    fprintf(output_stream, "Event's Name: \"%s\", ", event_pt->name);
                    fprintf(output_stream, "Start_time: %d, ", event_pt->start_time);
                    fprintf(output_stream, "Duration: %d\n", event_pt->duration_minutes);
                }
            }
        }
        return SUCCESS;
    }
    return FAILURE;
}

/* adds the specified event to the event list associated with parameter day */
int add_event(Calendar *calendar, const char *name, int start_time,
              int duration_minutes, void *info, int day){
    Event *curr, *new, *prev = NULL, *tmp;
    if(calendar != NULL && name!= NULL && (start_time >= 0 && start_time <= 2400)){
        if(duration_minutes > 0 && (day >= 1 && day <= calendar->days)){ /********** MAKE SURE CONDITIONALS ARE CORRECT *****/
            if(find_event(calendar, name, &tmp) == FAILURE){
                
                /* allocates space for the added event and its name*/
                new = malloc(sizeof(Event));
                if(new == NULL){
                    exit(EXIT_FAILURE);
                }
                new->name = malloc(sizeof(char) * (strlen(name) + 1));
                if(new->name == NULL){
                    exit (EXIT_FAILURE);
                }

                /* initializes the new event based on parameters*/
                strcpy(new->name, name);
                new->start_time = start_time;
                new->duration_minutes = duration_minutes;
                new->info = info;

                curr = calendar->events[day - 1];

                /* adds event to the beginning of the list*/
                while(curr != NULL && calendar->comp_func(curr, new) < 0){
                    prev = curr;
                    curr = curr->next;
                }

                /* checks if the new event is added at the front of the list*/
                if(prev == NULL){
                    new->next = curr;
                    calendar->events[day - 1] = new;
                    /* increments num of events*/
                    calendar->total_events++;
                    return SUCCESS;
                }

                new->next = curr;
                prev->next = new;
                calendar->total_events++;
                return SUCCESS;
            }
        }
    }
    return FAILURE;
}

/* returns a pointer to an event via parameter event iff the parameter is not 
NULL */
int find_event(Calendar *calendar, const char *name, Event **event){
    int i = 1;

    if(calendar != NULL && name != NULL){
        /* traverses through the calendar looking for the event based on its name*/
        for(i = 1; i <= calendar->days; i++){
            if(find_event_in_day(calendar, name, i, event) == SUCCESS){
                return SUCCESS;
            }
        }
    }
    return FAILURE;
}

/* returns a pointer to that event via parameter event iff the parameter is not 
NULL */
int find_event_in_day(Calendar *calendar, const char *name, int day,
                      Event **event){
    Event *ptr = NULL;
    if(calendar != NULL && name != NULL && day >= 1 && day <= calendar->days){
        ptr = calendar->events[day - 1];
        while(ptr != NULL){
            if(strcmp(ptr->name, name) == 0 && event != NULL){
                *event = ptr;
                return SUCCESS;
            }
            ptr = ptr->next;
        }
    }
    return FAILURE;
}

/* removes the event from the calendar, updates the number of events, and frees
any memory allocated for the event */
int remove_event(Calendar *calendar, const char *name){
   Event *curr = NULL, *prv, **event;
   int i = 0;
 
   if(calendar != NULL && name != NULL 
   && find_event(calendar, name, event) == FAILURE){
       for(i = 0; i < calendar->days; i++){
           /* points to the curr event*/
           curr = calendar->events[i];
 
           if(curr != NULL){
               /* checks if the current event is the same as the one passed in*/
               if(strcmp(curr->name, name) == 0){
                   /* removes the current event and sets its next pointer to
                   NULL */
                   calendar->events[i] = curr->next;
                   curr->next = NULL;
               } else {
                   /* iterates through the calendar setting the previous event
                   to curr and curr to the next event */
                   while(curr != NULL && strcmp(curr->name, name) != 0){
                       prv = curr;
                       curr = curr->next;
                   }
                   /* removes the current event by setting the previous next
                   to the curr's next, and sets the curr's next to NULL */
                   prv->next = curr->next;
                   curr->next = NULL;
               }
               /* deallocates space for curr*/
               free(curr->name);
               if((calendar->free_info_func) != NULL && curr->info != NULL){
                   calendar->free_info_func(curr->info);
               }
               free(curr);
 
               /* updates the number of events*/
               (calendar->total_events)--;
               return SUCCESS;
           }
       }
   }
   return FAILURE;
}

/* returns the info associated with the specified event*/
void *get_event_info(Calendar *calendar, const char *name){
    Event *event;
    
    if(find_event(calendar, name, &event) != FAILURE){
        return event->info;
    }
    return NULL;
}

/* removes ever event and sets every event list to empty */
int clear_calendar(Calendar *calendar){
    int i = 0;

    if(calendar != NULL){
        for(i = 0; i < calendar->days; i++){
            clear_day(calendar, (i+1));
        }
        return SUCCESS;
    }
    return FAILURE;

}

/* removes all events for the specified day */
int clear_day(Calendar *calendar, int day){
    Event *curr, *tmp;  
    int num_events = 0;

    if(calendar != NULL && (day >= 1 && day <= calendar->days)){
        if(calendar->total_events > 0){

            for(curr = calendar->events[day - 1]; curr != NULL; curr = curr->next){
                if(curr != NULL){
                    num_events++;
                }
            }
            curr = calendar->events[day-1];
            while(curr != NULL){
                free(curr->name);
                if(calendar->free_info_func != NULL && curr->info != NULL){
                    calendar->free_info_func(curr->info);
                }
                tmp = curr;
                curr = curr->next;
                free(tmp);
            }
        }
        /* decremenets the total events */
        calendar->total_events -= num_events;
        return SUCCESS;
    }
    return FAILURE;
}

/* removes every event, frees all memory that was dynamically allocated for the
calendar*/
int destroy_calendar(Calendar *calendar){
    if(calendar != NULL){
        free(calendar->name);
        clear_calendar(calendar);
        free(calendar->events);
        free(calendar);
        return SUCCESS;
    }
return FAILURE;
}


