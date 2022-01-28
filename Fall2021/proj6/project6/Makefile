CC = gcc
CFLAGS = -ansi -Wall -g -O0 -Wwrite-strings -Wshadow -pedantic-errors -fstack-protector-all
PROGS = d8sh
 
all: $(PROGS)
 
d8sh: d8sh.o parser.tab.o lexer.o executor.o
	$(CC) -lreadline d8sh.o parser.tab.o lexer.o executor.o -o d8sh
 
parser.tab.o: parser.tab.c command.h
	$(CC) $(CFLAGS) -c parser.tab.c
 
lexer.o: lexer.c
	$(CC) $(CFLAGS) -c lexer.c
 
executor.o: executor.c executor.h command.h
	$(CC) $(CFLAGS) -c executor.c
 
d8sh.o: d8sh.c executor.h lexer.h
	$(CC) $(CFLAGS) -c d8sh.c
 
clean:
	rm -f a.out *.o
