/* Fiyin Oluseye, UID: 117165661, directory ID: moluseye */
#include <stdio.h>
#include <stdlib.h>
#include "event.h"
#include "calendar.h"
#include "my_memory_checker_216.h"

/*****************************************************/
/* In this file you will provide tests for your      */
/* calendar application.  Each test should be named  */
/* test1(), test2(), etc. Each test must have a      */
/* brief description of what it is testing (this     */
/* description is important).                        */
/*                                                   */
/* You can tell whether any test failed if after     */
/* executing the students tests, you executed        */
/* "echo $?" on the command line and you get a value */
/* other than 0.  "echo $?" prints the status of     */
/* the last command executed by the shell.           */ 
/*                                                   */
/* Notice that main just calls test1(), test2(), etc.*/
/* and once one fails, the program eventually        */
/* return EXIT_FAILURE; otherwise EXIT_SUCCESS will  */
/* be returned.                                      */
/*****************************************************/

static int comp_minutes(const void *ptr1, const void *ptr2) {
   return ((Event *)ptr1)->duration_minutes - ((Event *)ptr2)->duration_minutes;
}

/* Tests initialization, printing, and destroying on an empty calendar */
static int test1() {
   int days = 7;
   Calendar *calendar;
   printf("\n\nTest 1: \n");

   if (init_calendar("Spr", days, comp_minutes, NULL, &calendar) == SUCCESS) {
      if (print_calendar(calendar, stdout, 1) == SUCCESS) {
         return destroy_calendar(calendar);
      }
   }

   return FAILURE;
}

/* Tests add event & destroy calendar*/
static int test2(){
   Calendar *calendar = NULL;
   int result = FAILURE, days = 2;
   printf("\n\nTest 2: \n");
   
   if(init_calendar("Test2", days, comp_minutes, NULL, &calendar) == SUCCESS){
      if(add_event(calendar, "Event1", 1200, 30, NULL, 2) == SUCCESS && 
        add_event(calendar, "Event2", 200, 60, NULL, 7) == FAILURE) {
           if(add_event(calendar, "Event3", 400, 180, NULL, 1) == SUCCESS &&
           add_event(calendar, "Event1", 1200, 30, NULL, 2) == FAILURE){
              result = SUCCESS;
              printf("Test 2 SUCCESS\n");
           }
        
     }
     if(destroy_calendar(calendar) == FAILURE){
        printf("Test 2 FAILURE\n");
        result = FAILURE;
     }
  }

  return result;
}

/* Tests find event w/ duplicate names*/
static int test3(){
   Calendar *calendar;
   int result = FAILURE;
   printf("\n\nTest 3: \n");
   
   if(init_calendar("Test3", 5, comp_minutes, NULL, &calendar) == SUCCESS){
      add_event(calendar, "Event1", 400, 40, NULL, 2);
      if(add_event(calendar, "Event1", 400, 60, NULL, 2) == FAILURE && 
      add_event(calendar, "Event1", 400, 60, NULL, 1) == FAILURE &&
      add_event(calendar, "Event2", 400, 60, NULL, 1) == SUCCESS){
         result = SUCCESS;
         printf("Test 3 SUCCESS\n");
         return result;
      }
   }
   printf("Test 4 FAILURE\n");
   return result;
}

/* Tests find event in a day */
static int test4(){
   Calendar *calendar = NULL;
   int result = FAILURE;
   Event *event; 
   printf("\n\nTest 4: \n");

   if(init_calendar("Test4", 7, comp_minutes, NULL, &calendar) == SUCCESS){
      if(add_event(calendar, "Event1", 1200, 100, NULL, 4) == SUCCESS && 
      add_event(calendar, "Event2", 300, 40, NULL, 2) == SUCCESS &&
      add_event(calendar, "Event3", 400, 20, NULL, 3) == SUCCESS &&
      add_event(calendar, "Event4", 300, 12, NULL, 1) == SUCCESS &&
      add_event(calendar, "Event5", 400, 100, NULL, 4) == SUCCESS){
         if(find_event_in_day(calendar, "Event1", 3, NULL) == FAILURE && 
         find_event_in_day(calendar, "Event2", 2, &event) == SUCCESS &&
         find_event_in_day(calendar, "Event4", 2, NULL) == FAILURE &&
         find_event_in_day(calendar, "Event4", 4, &event) == SUCCESS){
            result = SUCCESS;
            printf("Test 4 SUCCESS\n");
            return result;
         }
      }
   }

   printf("Test 4 FAILURE\n");
   return result;
}

/* Tests removing an event from the middle of list*/
static int test5(){
   Calendar *calendar;
   int result = FAILURE;
   printf("\n\nTest 5: \n");

   if(init_calendar("Test5", 4, comp_minutes, NULL, &calendar) == SUCCESS){
      add_event(calendar, "Event1", 1200, 100, NULL, 1);
      add_event(calendar, "Event2", 1200, 100, NULL, 2);
      add_event(calendar, "Event3", 1200, 80, NULL, 1);
      add_event(calendar, "Event4", 1200, 30, NULL, 2);

      if(remove_event(calendar, "Event3") == SUCCESS && 
      remove_event(calendar, "Event4") == SUCCESS){
         result = SUCCESS;
         printf("Test 5 SUCCESS\n");
         return result;
      }
   }
   printf("Test 5 FAILURE\n");
   return result;
}

/* Tests clear calendar*/
static int test6(){
   Calendar *calendar;
   int result = FAILURE;
   printf("\n\nTest 6: \n");

   if(init_calendar("Test6", 4, comp_minutes, NULL, &calendar) == SUCCESS){
      add_event(calendar, "Event1", 1200, 100, NULL, 1);
      add_event(calendar, "Event2", 1200, 100, NULL, 2);
      add_event(calendar, "Event3", 1200, 80, NULL, 3);

      if(clear_calendar(calendar) == SUCCESS){
         result = SUCCESS;
         printf("Test 6 SUCCESS\n");
         return result;
      }
   }
   printf("Test 6 FAILURE\n");
   return result;
}

/* Tests clear day*/
static int test7(){
   Calendar *calendar;
   int result = FAILURE;
   printf("\n\nTest 7: \n");

   if(init_calendar("Test7", 4, comp_minutes, NULL, &calendar) == SUCCESS){
      add_event(calendar, "Event1", 1200, 100, NULL, 1);
      add_event(calendar, "Event2", 1200, 100, NULL, 2);
      add_event(calendar, "Event3", 1200, 80, NULL, 3);

      if(clear_day(calendar, 1) == SUCCESS && 
      clear_day(calendar, 2) == SUCCESS){
         result = SUCCESS;
         printf("Test 7 SUCCESS\n");
         return result;
      }
   }
   printf("Test 7 FAILURE\n");
   return result;
}

/* Tests return value for clear day & clear calendar on an empty calendar */
static int test8(){
   Calendar *calendar;
   int result = FAILURE;
   printf("\n\nTest 8: \n");

   if(init_calendar("Test 8", 2, comp_minutes, NULL, &calendar) == SUCCESS){
      if(clear_day(calendar, 1) == SUCCESS && 
      clear_day(calendar, 2) == SUCCESS &&
      clear_calendar(calendar) == SUCCESS){
         result = SUCCESS;
         printf("Test 8 SUCCESS\n");
         return result;
      }
   }
   printf("Test 8 FAILURE");
   return result;
}

/* Tests get event info + destroy*/
static int test9(){
   Calendar *calendar;
   int result = FAILURE;
   char *info;
   printf("\n\nTest 9: \n");

   if(init_calendar("Test 9", 2, comp_minutes, NULL, &calendar) == SUCCESS){
      add_event(calendar, "Event1", 1200, 100, NULL, 1);
      info = get_event_info(calendar, "Event1");
      if(!info){
         return destroy_calendar(calendar);
      }
   }
   return result;
} 

/* Tests  */
static int test10(){
   Calendar *calendar;
   int result = FAILURE;
   printf("\n\nTest 10: \n");

   return result;
}

/* Tests  */
static int test11(){
   Calendar *calendar;
   int result = FAILURE;
   printf("\n\nTest 11: \n");

   return result;
}

int main() {
   int result = SUCCESS;

   /***** Starting memory checking *****/
   start_memory_check();
   /***** Starting memory checking *****/

   if (test1() == FAILURE) result = FAILURE;
   if (test2() == FAILURE) result = FAILURE;
   if (test3() == FAILURE) result = FAILURE;
   if (test4() == FAILURE) result = FAILURE;
   if (test5() == FAILURE) result = FAILURE;
   if (test6() == FAILURE) result = FAILURE;
   if (test7() == FAILURE) result = FAILURE;
   if (test8() == FAILURE) result = FAILURE;
   if (test9() == FAILURE) result = FAILURE;
   /*if (test10() == FAILURE) result = FAILURE;
   if (test11() == FAILURE) result = FAILURE; */

   /****** Gathering memory checking info *****/
   stop_memory_check();
   /****** Gathering memory checking info *****/
   
   if (result == FAILURE) {
      exit(EXIT_FAILURE);
   }

   return EXIT_SUCCESS;
}
