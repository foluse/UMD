/* Fiyin Oluseye, UID: 117165661, directory ID: moluseye */
 
#include <ctype.h>
#include <math.h>
#include <stdio.h>
 
/* limits the max number of assignments for input to 50*/
#define MAX_ASSIGNMENTS 50 
 
int compute_numeric_score(int late_penalty, int scores[], int days_late[], 
int assign_num);
double compute_mean(int num_assign, int late_penalty, int scores[], 
int days_late[]);
double compute_sd(int num_assign, int late_penalty, int scores[], 
int days_late[]);
double compute_value(int scores[], int weights[], int assign_num);
double drop_assign(int scores[], int days_late[], int weights[], int num_assign,
int late_penalty, int dropped);
 
/* computes numeric score by subtracting late day penalty from assign. score */
/* should not be confused with numeric score that is output on console*/
int compute_numeric_score(int late_penalty, int scores[], int days_late[],
int assign_num) {
 
int numeric_score = scores[assign_num] - (late_penalty * days_late[assign_num]);
 
if (numeric_score <= 0) {
   return 0;
} else {
   return numeric_score;
}
}
 
/* computes an assignment's value: score * assignment weight */
double compute_value(int scores[], int weights[], int assign_num) {
  double value = 0;
  value = scores[assign_num] * weights[assign_num];
  return value;
}
 
/* drops # of lowest assignments input by the user and returns total grade*/
double drop_assign(int scores[], int days_late[], int weights[],
int num_assign, int late_penalty, int dropped) {
  double grade; /* the final grade computed (total numeric score) */
  double new_score;
  double tmp = 0, temp = 0 ; /* temp variable used to order the assignments*/
  double updated_scores[MAX_ASSIGNMENTS]; /* stores updated scores*/
  double assign_values[MAX_ASSIGNMENTS]; /*stores all assignment values
  (score * weight)*/
  double remaining_weights[MAX_ASSIGNMENTS]; /* stores decimal weights */
  double total_weights; /* stores total weights for remaining assignments*/
 
  int i = 0, j = 0;
 
  if (dropped != 0) {
     for(i = 0; i < num_assign; i++){
        assign_values[i] = compute_value(scores, weights, i);
        remaining_weights[i] = weights[i];
        updated_scores[i] = compute_numeric_score(late_penalty, scores, 
        days_late, i);
     }

     /* orders weights and score array by assignment value*/
     for(i = 0; i < num_assign; i++){
        for(j = i + 1; j < num_assign; j++){
           /* stores the score and weight of the current assignment if its
           larger than the following assignment*/
           if(assign_values[i] > assign_values[j]){
              tmp = remaining_weights[i];
              remaining_weights[i] = remaining_weights[j];
              remaining_weights[j] = tmp;

              temp = updated_scores[i];
              updated_scores[i] = updated_scores[j];
              updated_scores[j] = temp;
           }
        }
     }

     /* multiplies the scores by their associated weights*/
     for(i = 0; i < num_assign; i++){
        assign_values[i] = updated_scores[i] * remaining_weights[i];
     }
     for(i = dropped; i < num_assign; i++){
        total_weights += remaining_weights[i];
     }
     for(i = dropped; i < num_assign; i++){
        assign_values[i] /= total_weights;
        grade += assign_values[i];
     }
  /* if there are no assignments to drop..*/
  } else {
     /* computes and stores numeric score of all assignments*/
     for (i = 0; i < num_assign; i++) {
        new_score = compute_numeric_score(late_penalty, scores, days_late, i);
        updated_scores[i] = new_score;
     }
     /* computes and stores assignment value */
     for (i = 0; i < num_assign; i++) {
        remaining_weights[i] = ((double)(weights[i]) / 100);
        remaining_weights[i] = remaining_weights[i] * updated_scores[i];
     }
     /* comptues grade*/
     for (i = 0; i < num_assign; i++) {
        grade += remaining_weights[i];
     }
  }
  return grade;
}
 
/*finds the mean value of all scores in the dataset */
double compute_mean(int num_assign, int late_penalty, int scores[],
int days_late[]) {
   double mean = 0;
   int i = 0;
   /* updated versions of scores to be used to calculate mean */
   int numeric_scores[MAX_ASSIGNMENTS] = {0};
  
   /* calculates sum of numeric value for all assignments*/
   for (i = 0; i < num_assign; i++) {
       numeric_scores[i] = compute_numeric_score(late_penalty, scores, days_late
       , i);
       }
      
   for (i = 0; i < num_assign; i++) {
       mean += numeric_scores[i];
     }
 
  mean /= num_assign;
 
  return mean;
}
 
/* finds the standard deviation amongst scores */
double compute_sd(int num_assign, int late_penalty, int scores[],
int days_late[]) {
   
   double mean = compute_mean(num_assign, late_penalty, scores, days_late);
   double var = 0; /* variance */
 
   int i = 0;
 
   for (i = 0; i < num_assign; i++) {
      double temp = 0;
      
      if (days_late[i] == 0) {
         temp = scores[i] - mean;
      } else {
         temp = scores[i] - (days_late[i] * late_penalty) - mean;
      }
      
      temp *= temp;
      var += temp;
  }
  
  var /= num_assign;
  return sqrt(var);
}
 
/* Determines if statistical information was requested, returns 1 if it was
requested otherwise returns 0*/
int stat(char user_In) {
  if (user_In == 'Y' || user_In == 'y') {
     return 1;
  } else {
     return 0;
  }
}
 
int main() {
  int late_penalty = 0; /* points penalty input*/
  int num_dropped = 0; /* # of assignments dropped input*/
  char character = '-'; /* input that records if statistical info is needed*/
  int num_assign = 1; /* # of assignments input*/
  int assign_num = 0; /* assignment #*/
  int scores[MAX_ASSIGNMENTS] = {0}; /* collection of assignment scores*/
  int weights[MAX_ASSIGNMENTS] = {0};   /* collection of assignment weights */
  int days_late[MAX_ASSIGNMENTS] = {0}; /* num days late */
 
  /*Output variables for Assignment Info lines: */
  int i = 0;           /* index variable (used as assignment # in info line) */
  int score = 0;         /* score of individual assingments */
  int assign_weight = 0; /*weight of individual assignments */
  int late = 0;     /* stores how many days late an assignment was submitted */
  int sum = 0; /* sum of all weights */
 
  /* Processes user input */
  scanf(" %d %d %c\n", &late_penalty, &num_dropped, &character);
  scanf(" %d", &num_assign);
 
  /* orders assignments by #*/
  for (i = 0; i < num_assign; i++) {
     scanf("%d, %d, %d,%d", &assign_num, &score, &assign_weight,&late);
     scores[assign_num - 1] = score;
     weights[assign_num - 1] = assign_weight;
     days_late[assign_num - 1] = late;
  }
  
   /* computes sum of weights */
   for(i = 0; i < num_assign; i++){
       sum += weights[i];
   }
 
  /* checks if sum of weights is equal to 100 */
  if (sum != 100) {
     printf("ERROR: Invalid values provided");
   /* Prints output on console*/
  } else {
     printf("Numeric Score: %5.4f\n", drop_assign(scores, days_late, weights,
     num_assign, late_penalty, num_dropped));
     printf("Points Penalty Per Day Late: %d\n",late_penalty);
     printf("Number of Assignments Dropped: %d\n", num_dropped);
     printf("Values Provided: \n");
     printf("Assignment, Score, Weight, Days Late\n");
     
     /*prints assignment #, score, assignment weight, and # of days late for
     each assignment line*/
     for (i = 0; i < num_assign; i++) {
       printf("%d, %d, %d, %d\n", (i+1), scores[i], weights[i], days_late[i]);
     }
 
     /* prints statistical information if requested*/
     if ((stat(character)) == 1) {
        printf("Mean: %5.4f", compute_mean(num_assign, late_penalty, scores,
        days_late));
        printf(", Standard Deviation: %5.4f\n", compute_sd(num_assign,
        late_penalty, scores, days_late));
     }
  }
  return 0;
}