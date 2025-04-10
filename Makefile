all: clean publish

clean:
	./gradlew clean

build:
	./gradlew buildPlugin

publish:
	./gradlew publishPlugin

.PHONY: all