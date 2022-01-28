/* Fiyin Oluseye, UID: 117165661, directory ID: moluseye */
#include "document.h"
#include <stdio.h>
#include <string.h>

/* initializes doc to have 0 paragraphs and sets the name based on provided input*/
int init_document(Document *doc, const char *name) {
   if (doc != NULL && name != NULL && strlen(name) <= MAX_STR_SIZE) {
      doc->number_of_paragraphs = 0;
      strcpy(doc->name, name);
      return SUCCESS;
   }
   return FAILURE;
}

/* sets the number of paragraphs in the document to 0*/
/* returns FAILURE if document is null, otherwise returns SUCCESS*/
int reset_document(Document *doc) {
   if (doc != NULL) {
      doc->number_of_paragraphs = 0;
      return SUCCESS;
   }
   return FAILURE;
}

/* prints the document's name, number of paragraphs, and the paragraphs*/
/* returns FAILURE if document is null, otherwise returns SUCCESS*/
int print_document(Document *doc) {
  if (doc != NULL) {
      int paragraph = 0, line = 0; /* indecies*/
       int curr_lines = 0;
     printf("Document name: \"%s\"\n", doc->name);
     printf("Number of Paragraphs: %d\n", doc->number_of_paragraphs);
 
     /* traverses through all paragraphs in document*/
     for (paragraph = 0; paragraph < doc->number_of_paragraphs; paragraph++) {
        curr_lines = doc->paragraphs[paragraph].number_of_lines;
 
        /* prints lines in paragraph, if paragraph is NOT empty*/
        for (line = 0; line < curr_lines; line++) {
           printf("%s", doc->paragraphs[paragraph].lines[line]);
        }
 
        /* prints one empty line between paragraphs */
        if (paragraph < (doc->number_of_paragraphs - 1)) {
           printf("\n");
        }
     }
     return SUCCESS;
  }
  return FAILURE;
}

/* adds a paragraph after the specified paragraph number*/
/* function assumes 3 cases for paragraph location, either at the beginning,
middle, or end of the array*/

int add_paragraph_after(Document *doc, int paragraph_number) {
   /* i represents paragraph location in array*/
   int i = 0, num_paragraphs = 0, insertion = 0, end = 0;

   if (doc == NULL || doc->number_of_paragraphs >= MAX_PARAGRAPHS ||
       doc->number_of_paragraphs < 0) {
      return FAILURE;
   } else {
      num_paragraphs = doc->number_of_paragraphs;
      if (MAX_PARAGRAPHS > num_paragraphs && paragraph_number <= num_paragraphs) {
         /* inserts paragraph @beginning of array*/
         if (paragraph_number == 0 && !num_paragraphs) {
            doc->paragraphs[0].number_of_lines = 0;
         }
         /* inserts paragraph @end of array*/
         if (paragraph_number == num_paragraphs) {
            end = doc->number_of_paragraphs;
            doc->paragraphs[end].number_of_lines = 0;
         }
         /* inserts paragraph @insertion point*/
         else {
            insertion = doc->number_of_paragraphs; /* added +1 */
            for (i = insertion; i >= paragraph_number; i--) {
               doc->paragraphs[i + 1] = doc->paragraphs[i];
            }
            doc->paragraphs[paragraph_number].number_of_lines = 0;
         }

         /* increments the number of paragraphs*/
         (doc->number_of_paragraphs)++;
         return SUCCESS;
      }
   }
   return FAILURE;
}

/* adds a line after the specified line number*/
int add_line_after(Document *doc, int paragraph_number, int line_number,
                  const char *new_line) {
  /* num_lines stores number of lines in doc*/
  int i, num_lines = 0;
  /* stores a copy of all lines from current paragraph */
  char updated_para[MAX_PARAGRAPH_LINES][MAX_STR_SIZE + 1];
  /* tracks size of updated paragraph array*/
  int updated_size = 0;
 
  /* checks if the document or new_line to be added aren't null */
  if (doc != NULL && new_line != NULL) {
     /* checks if paragraph_number is within the range of paragraphsmin doc */
     if (paragraph_number <= doc->number_of_paragraphs) {
        num_lines = doc->paragraphs[paragraph_number - 1].number_of_lines;
 
        /* checks if the number of lines is less than the max and if the
       line_number is within range*/
        if ((num_lines < MAX_PARAGRAPH_LINES) && line_number <= num_lines) {
           /*adds new_line at the beginning of paragraph*/
           if (line_number == 0) {
              strcpy(updated_para[0], new_line);
              updated_size++;
              for (i = 0; i < num_lines; i++) {
                 strcpy(updated_para[i], doc->paragraphs[paragraph_number - 1].lines[i]);
                 updated_size++;
              }
              for (i = 0; i < updated_size; i++) {
                 strcpy(doc->paragraphs[paragraph_number - 1].lines[i], updated_para[i]);
              }
              doc->paragraphs[paragraph_number - 1].number_of_lines++;
 
              /*adds new_line at the end of the paragraph */
           } else if (line_number == num_lines) {
              strcpy(doc->paragraphs[paragraph_number - 1].lines[line_number], new_line);
              doc->paragraphs[paragraph_number - 1].number_of_lines++;
 
           } else {
              for (i = 0; i < line_number - 1; i++) {
                 strcpy(updated_para[i], doc->paragraphs[paragraph_number - 1].lines[i]);
                 updated_size++;
              }
 
              strcpy(updated_para[i], new_line);
              updated_size++;
 
              for (i = line_number; i < num_lines; i++) {
                 strcpy(updated_para[i], doc->paragraphs[paragraph_number].lines[i - 1]);
                 updated_size++;
              }
 
              for (i = 0; i < updated_size; i++) {
                 strcpy(doc->paragraphs[paragraph_number - 1].lines[i], updated_para[i]);
              }
              doc->paragraphs[paragraph_number].number_of_lines++;
           }
           return SUCCESS;
        }
     }
  }
  return FAILURE;
}

int get_number_lines_paragraph(Document *doc, int paragraph_number, int *number_of_lines) {
   int lines = 0;

   if (doc != NULL) {
      if (paragraph_number <= doc->number_of_paragraphs) {
         lines = doc->paragraphs[paragraph_number].number_of_lines;
         *number_of_lines = lines;
         return SUCCESS;
      }
   }
   return FAILURE;
}

/* appends a new line to the specified paragraph*/
/* returns SICCESS if the line is appended*/

int append_line(Document *doc, int paragraph_number, const char *new_line) {
   if (doc != NULL && new_line != NULL) {
      if (paragraph_number <= MAX_PARAGRAPHS &&
          doc->paragraphs[paragraph_number - 1].number_of_lines < MAX_PARAGRAPH_LINES) {
         add_line_after(doc, paragraph_number, doc->paragraphs[paragraph_number - 1].number_of_lines, new_line);
         return SUCCESS;
      }
   }
   return FAILURE;
}

/* removes the specified line number from the paragraph */
/* returns FAILURE if doc is NULL, paragraph_number does not refer to an existing
paragraph, or line_number does not refer to an existing line; otherwise returns
SUCCESS */

int remove_line(Document *doc, int paragraph_number, int line_number) {
   int num_lines = 0, i;
   /* updated version of the paragraph after the line is removed*/
   char updated_para[MAX_PARAGRAPH_LINES][MAX_STR_SIZE + 1];
   if (doc != NULL) {
      num_lines = doc->paragraphs[paragraph_number - 1].number_of_lines;
      if (paragraph_number <= doc->number_of_paragraphs &&
          line_number <= num_lines) {
         if (line_number < num_lines) {
            /* removes the first line of the paragraph*/
            if (line_number == 1) {
               for (i = 1; i < num_lines; i++) {
                  strcpy(updated_para[i - 1], doc->paragraphs[paragraph_number - 1].lines[i]);
               }
               for (i = 0; i < num_lines; i++) {
                  strcpy(doc->paragraphs[paragraph_number - 1].lines[i], updated_para[i]);
               }
               doc->paragraphs[paragraph_number - 1].number_of_lines--;
            } else {
               for (i = 0; i < line_number - 1; i++) {
                  strcpy(updated_para[i], doc->paragraphs[paragraph_number - 1].lines[i]);
               }
               /* copies the lines after the removal point*/
               for (i = line_number; i < num_lines; i++) {
                  strcpy(updated_para[i - 1], doc->paragraphs[paragraph_number - 1].lines[i]);
               }
               for (i = 0; i < num_lines; i++) {
                  strcpy(doc->paragraphs[paragraph_number - 1].lines[i], updated_para[i]);
               }
               doc->paragraphs[paragraph_number - 1].number_of_lines--;
            }
            return SUCCESS;
         }
      }
   }
   return FAILURE;
}

/* adds the first data_lines number of rows from the data array to the document,
starting a new paragraph at the beginning of the document*/
int load_document(Document *doc, char data[][MAX_STR_SIZE + 1], int data_lines) {
  int i = 0, para = 0; /* index for data */
 
  if (doc != NULL && data != NULL && data_lines) {
     add_paragraph_after(doc, 0);
     for (i = 0; i < data_lines; i++) {
        if (strcmp(data[i], "") == 0) {
           para++;
           add_paragraph_after(doc, para);
 
        } /*else if(line[0] == '\n'){
           add_paragraph_after(doc,doc->number_of_paragraphs);
        }*/ else {
              append_line(doc, para + 1, data[i]);
         }
        }
        return SUCCESS;
     }
  return FAILURE;
   }


int replace_text(Document * doc, const char *target, const char *replacement) {
   int para = 0, line = 0; /* indeces for paragraphs & lines*/
   char *substring;        /* substring in doc*/
 
   if (doc != NULL && target != NULL && replacement != NULL) {
       /* traverses through all paragraphs */
       for (para = 0; para < doc->number_of_paragraphs; para++) {
           /* traverses through all lines*/
           for (line = 0; line < doc->paragraphs[para].number_of_lines; line++) {
               /* sets the substring to target*/
               substring = strstr(doc->paragraphs[para].lines[line], target);
               /* traverses through the entire line looking for substring*/
               while (substring != '\0') {
                   /* creates space for replacement substring by copying and
                   moving everything that follows the substring to the location
                   where the replacement substring ends (beginning of substring
                   + end of the replacement string) */
 
                   memmove(substring + strlen(replacement),
                   substring + strlen(target), strlen(substring) - strlen(target) + 1);
                   /* replaces the now allocated space in the string with
                   replacement substring*/
                  
                   memcpy(substring, replacement, strlen(replacement));
                   substring = strstr(substring + strlen(replacement), target);
                 }
              }
           }
           return SUCCESS;
        }
   return FAILURE;

}

/* highlights every appearance of the text target in the document, by surrounding
the text with the strings HIGHLIGHT_START_STR and HIGHLIGHT_END_STR*/
int highlight_text(Document * doc, const char *target) {
    char highlight[MAX_STR_SIZE + 1] = ""; /* stores the highlighted string*/
    if (doc != NULL && target != NULL) {
        strcat(highlight, HIGHLIGHT_START_STR);
        strcat(highlight, target);
        strcat(highlight, HIGHLIGHT_END_STR);
        
        /* replaces text with target*/
        replace_text(doc, target, highlight);
        return SUCCESS;
    }
    
    return FAILURE;
}

/* removes every appearance of the text target in the document*/
int remove_text(Document * doc, const char *target) {
    char remove[MAX_STR_SIZE + 1] = "";

    if (doc != NULL && target != NULL) {
        strcat(remove, target);
        strcat(remove, " ");
        replace_text(doc, target, "");
        return SUCCESS;
    }
    return FAILURE;
}

/* Similar to load_document, except that data is loaded from a file instead of
from an array in memory*/
int load_file(Document *doc, const char *filename){
   FILE *input = fopen(filename, "r");
   char line[MAX_STR_SIZE + 1], value;
   int num_values = 0, i = 0; /*  stores number of values returned by sscanf*/
   if(doc != NULL && filename != NULL && fopen(filename, "r") != NULL){
      add_paragraph_after(doc, 0);
      i++;
      while(fgets(line, MAX_STR_SIZE + 1, input) != NULL){
         if(line[strlen(line) - 1] == '\n'){
            line[strlen(line) - 1] = '\0';
            }
         if(strcmp(line, "") == 0 || line[0] == ' '){
            add_paragraph_after(doc, i);
            i++;
         } else {
            strcat(line, "\n");
            append_line(doc, i, line);
         }
      }
      fclose(input);
      return SUCCESS;
   }
   return FAILURE;
}

/* prints the document to the specified file */
int save_document(Document *doc, const char *filename){
   FILE *output;
   int num_lines = 0, para = 0, line = 0;

   if(doc != NULL && filename != NULL){
      if((output = fopen(filename, "w")) != NULL){
         for(para = 0; para <= doc->number_of_paragraphs; para++){
            num_lines = doc->paragraphs[para].number_of_lines;
            for(line = 0; line <= num_lines; line++){
               fputs(doc->paragraphs[para].lines[line], output);
               if(line < num_lines){
                  fputs("\n", output);
               }
            }
            if((para + 1) < doc->number_of_paragraphs){
               fputs("\n", output);
            }
         }
         fclose(output);
         return SUCCESS;
      }
   }
   return FAILURE;
}
     