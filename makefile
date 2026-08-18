JAVAC = javac
JAVA = java
JAVADOC = javadoc

SRC_DIR = src
OUT_DIR = out
DOC_DIR = javadoc

SOURCES = $(shell find $(SRC_DIR) -name "*.java")

.PHONY: all build run javadoc docs clean

all: build

build:
	mkdir -p $(OUT_DIR)
	$(JAVAC) -d $(OUT_DIR) $(SOURCES)

run: build
	$(JAVA) -cp $(OUT_DIR) Main

javadoc:
	$(JAVADOC) -d $(DOC_DIR) $(SOURCES)

docs: javadoc
ifeq ($(OS),Windows_NT)
	start $(DOC_DIR)/index.html
else
	xdg-open $(DOC_DIR)/index.html
endif

clean:
	rm -rf $(OUT_DIR)