/* Fiyin Oluseye, UID: 117165661, directory ID: moluseye */       
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sysexits.h>
#include <ctype.h>
#include "document.h"

/* removes quotes*/
char *quotes(char *command, char *result){
    char *begin, *end;

    begin = strstr(command, "\"");
    if(begin != NULL){
        end = strstr(begin + 1, "\"");
        if(end != NULL){
            strncpy(result, begin + 1, end - begin);
            result[end - (begin + 1)] = '\0';
            return result;
        }
    }
    return NULL;
}
/* executes reset_document function*/
int ex_reset_doc(Document *doc, char *command){
    char command_name[MAX_STR_SIZE + 1], extra[MAX_STR_SIZE + 1];
    int num_values = 0;

    num_values = sscanf(command, "%s%s", command_name, extra);
    if(num_values == 1){
        reset_document(doc);
        return SUCCESS;
    }
    return FAILURE;
}

/* executes print_document function*/
int ex_print_document(Document *doc, char *command){
    char command_name[MAX_STR_SIZE + 1], extra[MAX_STR_SIZE + 1];
    int num_values = 0;

    num_values = sscanf(command, "%s%s", command_name, extra);
    if(num_values == 1){
        if(print_document(doc) == FAILURE){
            printf("print_document failed \n");
        }
        return SUCCESS;
    }
    if(strcmp(command_name, "print_doc") != 0){
        printf("Invalid Command\n");
    }
    printf("Invalid Command\n");
    return FAILURE;
}

/* executes add_paragraph function*/
int ex_add_paragraph_after(Document *doc, char *command){
    char command_name[MAX_STR_SIZE + 1], extra[MAX_STR_SIZE + 1];
    int num_values = 0, num = 0;

    num_values = sscanf(command, "%s%d%s", command_name, &num, extra);
    if(num_values == 2 && num >= 0){
        if(add_paragraph_after(doc, num) == FAILURE){
            printf("add_paragraph_after failed\n");
        }
        return SUCCESS;
    }
    return FAILURE;
}

/* executes add_line_after function*/
int ex_add_line_after(Document *doc, char *command){
    char command_name[MAX_STR_SIZE + 1], line[MAX_STR_SIZE + 1],
    extra[MAX_STR_SIZE + 1];
    int input = 0, para_num = 0, line_num = 0; 

    input = sscanf(command, "%s%d%d%s", command_name, &para_num, &line_num, extra);
    if(input == 4 && para_num > 0  && line_num >=0){
        if((strchr(command, '*')) != NULL){
            strcpy(line, strchr(command, '*') + 1);
            if(add_line_after(doc, para_num, line_num, line) == FAILURE){
                printf("add_line_after failed \n");
            }
            return SUCCESS;
        }
    }
    return FAILURE;
}

/* executes append_line function*/
int ex_append_line(Document *doc, char *command){
    char command_name[MAX_STR_SIZE + 1], extra[MAX_STR_SIZE + 1],
    line[MAX_STR_SIZE + 1];
    int num_values, num;

    num_values = sscanf(command, "%s%d%s", command_name, &num, extra);
    if(num_values == 3 && num > 0){
        if(strchr(command, '*') != NULL) {
            strcpy(line, strchr(command, '*') + 1);

            if(append_line(doc, num, line) == FAILURE){
                printf("append_line failed\n");
            }
            return SUCCESS;
        }
    }
    return FAILURE;
}

/* executes remove_line function*/
int ex_remove_line(Document *doc, char *command){
    char command_name[MAX_STR_SIZE + 1], para[MAX_STR_SIZE + 1],
    line[MAX_STR_SIZE + 1], extra[MAX_STR_SIZE + 1];

    int input = 0, para_num = 0, line_num = 0;

    input = sscanf(command, "%s%s%s%s", command_name, para, line, extra);

    para_num = atoi(para);
    line_num = atoi(line);
    if(input == 3 && para_num > 0 && line_num > 0){
        if(remove_line(doc, para_num, line_num) == FAILURE){
            printf("remove_line failed\n");
        }
        return SUCCESS;
    }
    return FAILURE;
}


/* executes replace_text function*/ /* TEST LATER!~! */
int ex_replace_text(Document *doc, char *command){
    char target[MAX_STR_SIZE + 1] = "\0", replace[MAX_STR_SIZE + 1] = "\0"; 
    char *targetQ, *replaceQ; /* quotes*/
    int i = 0;
    
    /* finds first quotation mark, "makes" an array and points to first index*/
    targetQ = strchr(command, '"');
    if(targetQ != NULL){
        for(i = 1; i < strlen(targetQ); i++){
            if(targetQ[i] == '"'){
                break;
            } else {
                /* stores the target to be replaced into the target array*/
                target[i - 1] = targetQ[i];
            }
        }
    } else {
        printf("Invalid Command\n");
        return FAILURE;
    }
    targetQ = (targetQ + i) + 1;
    replaceQ = strchr(targetQ, '"');
    if(replaceQ != NULL){
        for(i = 1; i < strlen(replaceQ); i++){
            if(replaceQ[i] == '"'){
                break;
            } else {
                replace[i - 1] = replaceQ[i];
            }
        }
    } else {
        printf("Invalid Command\n");
        return FAILURE;
    }
    if(replace_text(doc, target, replace) == SUCCESS){
        return SUCCESS;
    } else {
        printf("replace_text failed\n");
    }
}

/* executes highlight_text function*/ /* TEST LATER */
int ex_highlight_text(Document *doc, char *command){
    char command_name[MAX_STR_SIZE + 1], target[MAX_STR_SIZE + 1];
    char temp[MAX_STR_SIZE + 1], *beginQ, *endQ; /* quotes*/
    int input = 0;

    input = sscanf(command, "%s%s", command_name, target);
    beginQ = strstr(command, "\"");
    if(input == 2){
        if(beginQ != NULL){
            endQ = strstr(beginQ + 1, "\"");
            if(endQ != NULL){
                strncpy(temp, beginQ + 1, endQ - beginQ - 1);
                
                highlight_text(doc, temp);
                return SUCCESS;
                }
            }
        }
    return FAILURE;
}

/* executes remove_text function*/ /* TEST LATER */
int ex_remove_text(Document *doc, char *command){
    char command_name[MAX_STR_SIZE + 1], target[MAX_STR_SIZE + 1], 
    temp[MAX_STR_SIZE + 1], *str, *result;
    int num_values = 0;

    num_values = sscanf(command, "%s%s", command_name, temp);
    str = strstr(command, "\"");

    if(num_values == 2 && str != NULL){
        if((result = quotes(str, target)) != NULL){
            remove_text(doc, result);
            return SUCCESS;
        }
    }
    return FAILURE;
}

/* executes load_file function*/
int ex_load_file(Document *doc, char *command){
    char command_name[MAX_STR_SIZE + 1], file_name[MAX_STR_SIZE + 1],
    extra[MAX_STR_SIZE + 1];

    int input = 0;

    input = sscanf(command, "%s%s%s", command_name, file_name, extra);

    if(input == 2){
        if(load_file(doc, file_name) == FAILURE){
            printf("load_file failed\n");
        }
        return SUCCESS;
    }
    return FAILURE;
}

/* executes save_doc function*/ /* TEST LATER */
int ex_save_doc(Document *doc, char *command){
    char command_name[MAX_STR_SIZE + 1], file[MAX_STR_SIZE + 1],
    extra[MAX_STR_SIZE + 1];
    int num_values = 0;

    num_values = sscanf(command, "%s%s%s", command_name, file, extra);
    if(num_values == 2){
        if(save_document(doc, file) == FAILURE){
            printf("save_document failed\n");
        }
        return SUCCESS;
    }
    return FAILURE;
}

/* FINISH THIS */
/* checks if the command passed in is valid */
int valid(Document *doc, char *command){
   int result = SUCCESS; /* tracks if command was valid */
 
   if(strstr(command, "reset_document") != NULL){
       if(ex_reset_doc(doc, command) == FAILURE){
           result = FAILURE;
       }
   } else if(strstr(command, "print_document") != NULL){
       if(ex_print_document(doc,command) == FAILURE){
           result = FAILURE;
       }
   } else if(strstr(command, "add_paragraph_after") != NULL){
       if(ex_add_paragraph_after(doc,command) == FAILURE){
           result = FAILURE;
       }
   } else if(strstr(command, "add_line_after") != NULL){
       if(ex_add_line_after(doc,command) == FAILURE ){
           result = FAILURE;
       }
   } else if(strstr(command, "append_line") != NULL){
       if(ex_append_line(doc,command) == FAILURE){
           result = FAILURE;
       }
   } else if(strstr(command, "remove_line") != NULL){
       if(ex_remove_line(doc,command) == FAILURE){
           result = FAILURE;
       }
   } else if(strstr(command, "replace_text") != NULL){
       if(ex_replace_text(doc, command) == FAILURE){
           result = FAILURE;
       }
   } else if(strstr(command, "highlight_text") != NULL){
       ex_highlight_text(doc, command);
   } else if(strstr(command, "remove_text") != NULL){
       if(ex_remove_text(doc, command) == FAILURE){
           result = FAILURE;
       }
   } else if(strstr(command, "load_file") != NULL){
       if(ex_load_file(doc, command) == FAILURE){
           result = FAILURE;
       }
   } else if(strstr(command, "save_document") != NULL){
       if(ex_save_doc(doc, command) == FAILURE){
           result = FAILURE;
       }
   } else if(strcmp(command, "") == 0 || command[0] == '#' || strcmp(command, "\n") == 0){
       result = SUCCESS;
   } else if(strstr(command, "quit") == NULL && strstr(command, "exit") == NULL){
       result = FAILURE;
   }
   return result;
}




int main(int argc, char *argv[]){
   Document doc;
   FILE *input;
   const char *name = "main_document";
   char line[MAX_STR_SIZE + 1], cmd[MAX_STR_SIZE + 1], val;
   const char empty[] = {'\0'};
   int num_values = 0;
 
   if(init_document(&doc, name) == FAILURE){
       printf("Initialization failed\n");
   } else {
       if(argc == 1){
           input = stdin;
           printf("> ");
           fgets(line, MAX_STR_SIZE + 1, input); /* check notes on this*/
           while((strcmp(line, "quit\n") != 0) && (strcmp(line, "exit\n") != 0)){
               sscanf(line, "%c%s", &val, cmd);
               if(strcmp(line, empty) != 0 && val != '#'){
                   valid(&doc, line);
               }
               printf("> ");
               fgets(line, MAX_STR_SIZE + 1, input);
           }
       }
       else if(argc == 2){
           input = fopen(argv[1], "r");
           if(input != NULL){
               while(fgets(line, MAX_STR_SIZE + 1, input) != NULL){
                   if(strcmp(line, "quit") && strcmp(line, "exit")){
                       num_values = sscanf(line, "%c%s", &val, cmd);
                       if(num_values > 0 && val != '#'){
                           valid(&doc, line);
                       }
                   }
               }
           } else {
               fprintf(stderr, "%s cannot be opened.\n", argv[1]);
               exit(EX_OSERR);
           }
       } else {
           fprintf(stderr, "Usage: user_interface\n");
           fprintf(stderr, "Usage: user_interface <filename>\n");
           exit(EX_USAGE);
       }
       fclose(input);
       exit(EXIT_SUCCESS);
   }
}
     