# GameOfAmazons

This is a text-based simulation of two-player Game of the Amazons. (AI to be developed later)

## Rules

Invented in 1988 by Walter Zamkauskas from Argentina, *Game of the Amazons* is a strategy game with similarities to chess and go. Like other popular combinatorial games such as Nim and Domineering, Game of the Amazons is simple but deceivingly fun.

The game is typically played on a 10x10 chessboard, set up with four white and four black Amazons that move like chess queens. However, in this program, there's the option to play on a simpler 8x8, 6x6, or 5x5 board. Players alternate moves, and white starts. Each turn, an Amazon moves to a square, then shoots an arrow (orthogonally or diagonally) and burns that square. The arrows and Amazons cannot cross (or land on) other Amazons or claimed squares. 

As the game progresses, possible moves will become increasingly limited. The last player to be able to move wins (the game ends when one of the players cannot make a move).

## Strategy

Much of the game is based on "territorial average," a concept used in my program to predict the winner of the game without .

## AI algorithm

## Other information

Determining the winner of Game of the Amazons is NP-hard.

## APCS Class

Written for APCS class, and I learned more combinatorial game theory as a bonus. 