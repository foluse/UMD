CC = gcc
CFLAGS = -ansi -Wall -g -O0 -Wwrite-strings -Wshadow -pedantic-errors \
-fstack-protector-all -Wextra
PROGS = public01 public02 public03 public04 public05 public06 \
student_tests
OBJ_FILES = calendar.o my_memory_checker_216.o
HEADER_FILES = calendar.h event.h


#Creates all public and student test targets
all: $(PROGS)

#Creates executables
public01: public01.o $(OBJ_FILES) 
	$(CC) -o public01 public01.o calendar.o

public02: public02.o $(OBJ_FILES) 
	$(CC) -o public02 public02.o calendar.o

public03: public03.o $(OBJ_FILES) 
	$(CC) -o public03 public03.o calendar.o

public04: public04.o $(OBJ_FILES) 
	$(CC) -o public04 public04.o calendar.o

public05: public05.o $(OBJ_FILES) 
	$(CC) -o public05 public05.o calendar.o

public06: public06.o $(OBJ_FILES) 
	$(CC) -o public06 public06.o calendar.o

student_tests: student_tests.o $(OBJ_FILES) 
	$(CC) -o student_tests student_tests.o calendar.o my_memory_checker_216.o

#Compiles .c tests into obj files
public01.o: public01.c $(HEADER_FILES) 
       $(CC) $(CFLAGS) -c public01.c


public02.o: public02.c $(HEADER_FILES) 
       $(CC) $(CFLAGS) -c public02.c


public03.o: public03.c $(HEADER_FILES) 
       $(CC) $(CFLAGS) -c public03.c


public04.o: public04.c $(HEADER_FILES) 
       $(CC) $(CFLAGS) -c public04.c


public05.o: public05.c $(HEADER_FILES) 
       $(CC) $(CFLAGS) -c public05.c


public06.o: public06.c $(HEADER_FILES) 
       $(CC) $(CFLAGS) -c public06.c


student_tests.o: student_tests.c $(HEADER_FILES) 
       $(CC) $(CFLAGS) -c student_tests.c


calendar.o: calendar.h calendar.c 
       $(CC) $(CFLAGS) -c calendar.c


my_memory_checker_216.o: my_memory_checker_216.h my_memory_checker_216.h 
       $(CC) $(CFLAGS) -c my_memory_checker_216.c


#Deletes all object files & executables
clean: rm $(PROGS) ./*.o



#Deletes all object files & executables
clean: rm $(PROGS) ./*.o


