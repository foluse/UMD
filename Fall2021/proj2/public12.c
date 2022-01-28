#include <stdio.h>
#include <string.h>
#include "document.h"

int main() {
    

    Document doc;
   const char *doc_name = "my doc ";
   int data_lines = 8;
   char data[20][MAX_STR_SIZE + 1] = {
        "The first course you need to take",
	"is cmsc131.  This course will be",
	"followed by cmsc132 (which is also based on Java).",
	"",
	"The next course you will take is cmsc216.",
	"This course relies on C.",
	"",
	"Ruby and Ocaml will be covered in cmsc330"
   };
   int test_init = init_document(&doc, doc_name);
   int test_load = load_document(&doc, data, data_lines);
   int test_load2 = load_document(&doc, data, data_lines);
   int test_print = print_document(&doc);
  
   printf("init returned: %d\n", test_init);
   printf("load1 returned: %d\n", test_load);
   printf("load2 returned: %d\n", test_load2);
   printf("print returned: %d\n", test_print);

   return 0;
}
