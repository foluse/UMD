/* Fiyin Oluseye, UID: 117165661, directory ID: moluseye */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <err.h>
#include <sysexits.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "command.h"
#include "executor.h"
#define PERMISSIONS 0644

static void print_tree(struct tree *t);
static int execute_aux(struct tree *t, int input_fd, int output_fd);

int execute(struct tree *t) {
   return execute_aux(t, STDIN_FILENO, STDOUT_FILENO);
}

/* this function recursively processes commands on the tree*/
static int execute_aux(struct tree *t, int input_fd, int output_fd){
   pid_t process_id;
   int status, statusL; /* status for commands & status of left tree*/

   /* 1st case: NONE conjunction */
   if(t->conjunction == NONE){

      if(!strcmp(t->argv[0], "exit")){
         exit(0);
      }
      /* changes directory to t->argv[1] */
      else if(!strcmp(t->argv[0], "cd")){
         chdir(t->argv[1]);
      }

      /* handles all other commands */
      else {
         process_id = fork();
         if(process_id < 0){
            err(EX_OSERR, "fork error");
         } 
         
         else {
            if(process_id != 0){    /* parent process*/
               wait(&status);       /* reaps child created on line 42*/
               return status;
            } else {
               /* input */
               if(t->input){
                  if((input_fd = open(t->input, O_RDONLY)) < 0){
                     err(EX_OSERR, "input file failed to open.\n");
                  }
                  /* redirects STDIN to input_fd*/
                  if(dup2(input_fd, STDIN_FILENO) < 0){  
                     err(EX_OSERR, "dup2 input error");
                  }
                  close(input_fd);     /* closing write end*/
               }
               /* output */
               if(t->output){
                  if((output_fd = open(t->output,O_WRONLY | O_CREAT | O_TRUNC, 
                  PERMISSIONS)) < 0){
                     err(EX_OSERR, "ouput file failed to open.\n");
                  }
                  if(dup2(output_fd, STDOUT_FILENO) < 0){
                     err(EX_OSERR, "dup2 output error");
                  }
                  close(output_fd);
               }
               execvp(t->argv[0], t->argv);
               fprintf(stderr, "Failed to execute %s\n", t->argv[0]);
               exit(-1);
            }
         }
      }
   }
/* --------------------------------- */
   /* 2nd case: AND conjunction*/
   else if(t->conjunction == AND){

      if(t->input){
         if((input_fd = open(t->input, O_RDONLY) < 0)){
            err(EX_OSERR, "input file failed to open.\n");
         }
      }
      if(t->output){
         if((output_fd = open(t->output, O_WRONLY | O_CREAT | 
         O_TRUNC, PERMISSIONS)) < 0){
            err(EX_OSERR, "output file failed to open.\n");
         }
      }

      statusL = execute_aux(t->left, input_fd, output_fd);
      if(statusL == 0){
         execute_aux(t->right, input_fd, output_fd);
      }
   }
/* --------------------------------- */
   /* 3rd case: PIPE conjunction*/
   else if(t->conjunction == PIPE){
      int pipe_fd[2];      /* for read & write of pipe*/

      /* ensures left subtree doesn't have output*/
      if(t->left->output){
         printf("Ambiguous output redirect.\n");
      } 
      /* ensures right subtree doesn't have input*/
      else{ 
         if (t->right->input){
            printf("Ambiguous input redirect.\n");
         } 
         /* if right subtree doesn't have input then we */
         else {
            if(t->input){
               if((input_fd = open(t->input, O_RDONLY)) < 0){
                  err(EX_OSERR, "pipe: input file failed to open\n");
               }
            }
            if(t->output){
               if((output_fd = open(t->output, O_WRONLY | O_CREAT | O_TRUNC, 
               PERMISSIONS)) < 0){
                  err(EX_OSERR, "pipe: output file failed to open\n");
               }
            }
            /* makes the pipe and then forks*/
            if(pipe(pipe_fd) < 0){
               err(EX_OSERR, "pipe error");
            }
            if((process_id = fork()) < 0){
               err(EX_OSERR, "fork error");
            }
           
            if(process_id == 0){     /* child code */
               close(pipe_fd[0]);
               /* changes STDOUT to pipe_fd[1]*/
               if(dup2(pipe_fd[1], STDOUT_FILENO) < 0){
                  err(EX_OSERR, "dup2 error");
               }
               execute_aux(t->left, input_fd, pipe_fd[1]);
               close(pipe_fd[1]);
               exit(0);
            }

            if(process_id != 0){
               close(pipe_fd[1]);
               if(dup2(pipe_fd[0], STDIN_FILENO) < 0){
                  err(EX_OSERR, "dup2 error");
               }
               execute_aux(t->right, pipe_fd[0], output_fd);
               close(pipe_fd[0]);
               wait(NULL);
            }
         }
      }
   }
/* --------------------------------- */
   /* 4th case: SUBSHELL conjunction*/
   else if(t->conjunction == SUBSHELL){
      /* checks for input & output redirection*/
      if(t->input){
         if((input_fd = open(t->input, O_RDONLY)) < 0){
            err(EX_OSERR, "subshell: input failed to open.\n");
         }
      }
      if(t->output){ 
         if((output_fd = open(t->output,O_WRONLY | O_CREAT | O_TRUNC, 
         PERMISSIONS))){
            err(EX_OSERR, "subshell: output failed to open.\n");
         }
      }

      if((process_id = fork()) < 0){
         err(EX_OSERR, "subshell: fork error");
      } 
      else {
         if(process_id != 0){    /* parent code */
            wait(NULL);    /* parent reaps child*/
         }
         else {         /* child code*/
            /* recursively traverse left tree*/
            execute_aux(t->left, input_fd, output_fd);  
            exit(0);
         }
      }
   }
   return 0;
}

static void print_tree(struct tree *t) {
   if (t != NULL) {
      print_tree(t->left);

      if (t->conjunction == NONE) {
         printf("NONE: %s, ", t->argv[0]);
      } else {
         printf("%s, ", conj[t->conjunction]);
      }
      printf("IR: %s, ", t->input);
      printf("OR: %s\n", t->output);

      print_tree(t->right);
   }
}

