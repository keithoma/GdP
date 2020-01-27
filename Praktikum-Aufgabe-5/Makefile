# vim:noet:ts=4
FILES = Dezidoku.class testDezidoku.class
all: $(FILES)

%.class: %.java
	@javac -classpath .:stdlib.jar $<

clean:
	@rm -vf $(FILES)

F ?= in1
run: all
	@echo "Testing with $(F) ..."
	@./run.sh <$(F)

.PHONY: all clean
