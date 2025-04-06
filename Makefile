yaml_resources:
	jar cf yaml_resources.jar -C resources .

# Makefile for building the YAML library
# Compiler and flags
KOTLINC = kotlinc
JAVA_COMPILER = javac
JAR = jar
KOTLIN_STD_LIB = path/to/kotlin-stdlib.jar
KOTLINX_SERIALIZATION_CORE = path/to/kotlinx-serialization-core.jar
FASTUTIL_MIN = path/to/fastutil-min.jar
# Source files
KOTLIN_SRCS = $(wildcard src/**/*.kt) $(wildcard gen/**/*.kt)
JAVA_SRCS = $(wildcard src/**/*.java) $(wildcard gen/**/*.java)
# Output
OUTPUT_JAR = yaml.jar
# Dependencies
DEPS = $(KOTLIN_STD_LIB) $(KOTLINX_SERIALIZATION_CORE) $(FASTUTIL_MIN)
# Build target
all: $(OUTPUT_JAR)
# Compile Kotlin and Java files
$(OUTPUT_JAR): $(KOTLIN_SRCS) $(JAVA_SRCS)
	$(KOTLINC) -cp $(DEPS) -d bin $(KOTLIN_SRCS)
	$(JAVA_COMPILER) -cp $(DEPS):bin -d bin $(JAVA_SRCS)
	$(JAR) cf $@ -C bin .
# Clean target
clean:
	rm -rf bin/*
	rm -f $(OUTPUT_JAR)

.PHONY: all clean