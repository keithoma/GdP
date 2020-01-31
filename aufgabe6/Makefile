# vim:noet:ts=4
FILES = SelectionSort.class
all: $(FILES)

%.class: %.java
	@javac -classpath .:stdlib.jar $<

clean:
	@rm -vf $(FILES)

F ?= SelectionSort.java
run: all
	@echo "Testing with $(F) ..."
	@./run.sh <$(F)

.PHONY: all clean
