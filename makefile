# 205956634
# saadaty

compile: bin
	find src/ -name '*.java' > sources.txt
	javac -d bin -cp biuoop-1.4.jar:resources @sources.txt
	rm sources.txt
jar: compile
	jar cfm ass7game.jar Manifest.mf -C bin . -C resources .
run: jar
	java -jar ass7game.jar levelset.txt
bin:
	mkdir bin
