# SpanDigitalExercise

The Problem 

We want you to create a production ready, maintainable, testable command-line application that will calculate the ranking table for a league. 

Input/output 

The input and output will be text. Either using stdin/stdout or taking filenames on the command line is fine. The input contains results of games, one per line. See “Sample input” for details. The output should be ordered from most to least points, following the format specified in “Expected output”. You can expect that the input will be well-formed. There is no need to add special handling for malformed input files.


Instructions

Make sure the version of java 17 is installed

Make sure maven is installed

- Open command line
- Go to the main Folder

cd C:\Users\%USER%\%Project_Location%\Ranking_Teams

- Run "mvn clean install"

- Execute 
java -jar target/RankingTeams-0.0.1-SNAPSHOT.jar com.spandigital.rankingteams.RankingTeamsApplication Lions 3, Snakes 3 Tarantulas 1, FC Awesome 0 Lions 1, FC Awesome 1 Tarantulas 3, Snakes 1 Lions 4, Grouches 0

Change the arguments with following format, (remove line break for better interaction on CMD)

Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0

