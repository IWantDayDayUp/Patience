# Patience Card Game

## Info

- Course Name: Software Engineering
- Project Type: Individual Project
- Student Name: Shaofeng Wang

## Project

The program have the following features:

- Displays the cards on the console and takes input from the keyboard (i.e. no GUI).
- The display lists the score and number of moves so far. The scoring should be as follows for successful
moves:
  - 10 points per card moving from the uncovered pile to one of the suits
  - 20 points per card moving from one of the lanes to one of the suits
  - 5 points per card moving between lanes
- The uncovered pile is labelled “P”, the card “lanes” are numbered 1-7, and suite piles are labelled D
(diamonds), H (hearts), C (clubs) and S (spades).
- Prompt the user to enter a command.
- The user can enter the following commands:
  - Q = quit
  - D = uncover a new card from the draw pile
  - EITHER:
    - "\<label1>\<label2>" = move one card from "\<label1>" to "\<label2>". E.g. “P2” or “2D”
    - "\<label1>\<label2>\<number>" = move "\<number>" cards from "\<label1>" to "\<label2>". E.g. “413”.
  - OR:
    - "\<label1>\<label2>" = move card(s) from "\<label1>" to "\<label2>" detecting the number of cards that need to move so as to make a legal move if at all possible. E.g. “P2” or “2D”
- Allow uppercase and lowercase commands.
- Display an error message and ask for a new command if an invalid command is entered.
- Display an error message and ask for a new command if a play cannot be made.
- Display a game over message if all cards are in their correct suites
