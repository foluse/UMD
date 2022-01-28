#include <stdio.h>
#include <string.h>
#include "document.h"

int main() {
    Document doc;
    const char *doc_name = "Exercise Description";
    int paragraph_number, line_number;

    init_document(&doc, doc_name);
    
    /* Adding first paragraph */
    paragraph_number = 0;
    add_paragraph_after(&doc, paragraph_number);
    
    /* Adding second paragraph */
    paragraph_number = 1;
    add_paragraph_after(&doc, paragraph_number);

    line_number = 0;
    paragraph_number = 1;
    add_line_after(&doc, paragraph_number, line_number, "First Paragraph, First line");
    add_line_after(&doc, paragraph_number, line_number + 1, "First Paragraph, Second line");
    add_line_after(&doc, paragraph_number, line_number + 2, "First Paragraph, Third line");
    add_line_after(&doc, paragraph_number, line_number + 3, "First Paragraph, Fourth line");
    add_line_after(&doc, paragraph_number, line_number + 4, "First Paragraph, Fifth line");
    print_document(&doc);
    
    replace_text(&doc, "First", "1st");
    replace_text(&doc, "Second", "2nd");
    replace_text(&doc, "Third", "3rd");
    replace_text(&doc, "Fourth", "4th");
    replace_text(&doc, "Fifth", "5th");
    print_document(&doc);

    replace_text(&doc, "1st", "First");
    replace_text(&doc, "2nd", "Second");
    print_document(&doc);

    printf("After Remove: \n");
    remove_line(&doc, paragraph_number, 2);
    print_document(&doc); 

    return 0;
}