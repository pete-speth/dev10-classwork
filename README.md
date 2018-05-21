# dev10-classwork

This repository contains all of my coursework from the Software Guild Java bootcamp. The Exercises folder contains code from examples and code-alongs. The projects folder contains all of the code for graded assignments.

## Outline of Projects

### StatsTracker
This is a console application that tracks video game statistics. I wrote it specifically to track individual statistics for head to head gameplay in any NHL video game. The user has the ability to add, remove or edit games. The application keeps a master log of all games played, as well as an individual log for each player in the system. It allows the user to view recent games and search for games by date, either for all players or a specific player. It also keeps a list of all players who have  played at least one game, and gives the user the ability to view the rankings of all players. 

I created a ranking point system that is not dependent on the number of games played. For example, a player who has only played 10 games can still compete in the rankings against one who has played 100. I also adjusted ranking points based on strength of opponent, so that the ranking point differential between two players is a factor in the amount of points earned for a win or loss.

### VendingMachine
This was originally a console application that allows a user to purchase items from a vending machine and keep track of the inventory. It is now wired to be a web application, but the console application code is still there. It can persist data either through a text file or a database, and also logs transactions to a text file.

### Movies
This is a console application that allows users to create, read, update, and delete movies. This project contains classes that allow for persitence of data through either text files or a database.

### Summative 1
This project contains 4 different console applications that perform simple tasks:
  - RockPaperScissors: Allows a user to play anywhere from 1-10 games of rock, paper, scissors against a computer player with randomly generated moves. Tracks game outcomes and presents the final stats at the end of the match.
  - DogGenetics: Takes in a dog name from the user and generates a random report of it's genetic makeup
  - HealthyHearts: Takes in an age from the user and calculates their max heart rate and target heart rate zone
  - SummativeSums: Sums the numbers in three different test arrays
