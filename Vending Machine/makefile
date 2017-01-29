# Adam Austin
# 4/22/2016
# TA: Quesada

# Makefiles! Build an executable for the Student example
# Author: Adam Austin
# -Wall = extra warnings


# Macros/variables
OBJS = Item.o VendingMachine.o VendingMachineDriver.o
CPPFLAGS = -Wall -std=c++11
PROG = prog
CC = g++

# Target: Dependancys
$(PROG): $(OBJS)
	$(CC) -o $(PROG) $(OBJS)

#Actions
Item.o: Item.h Item.cpp
	$(CC) $(CPPFLAGS) -c Item.cpp
	
VendingMachine.o: VendingMachine.h VendingMachine.cpp
	$(CC) $(CPPFLAGS) -c VendingMachine.cpp


VendingMachineDriver.o: VendingMachineDriver.cpp
	$(CC) $(CPPFLAGS) -c VendingMachineDriver.cpp

clean:
	$(RM) $(PROG) $(OBJS)
