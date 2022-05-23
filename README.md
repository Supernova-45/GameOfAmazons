# Game Of The Amazons

This is a text-based simulation of two-player Game of the Amazons. (AI to be developed later)

## Rules

Invented in 1988 by Walter Zamkauskas from Argentina, *Game of the Amazons* is a strategy game with similarities to chess and go. Like other popular combinatorial games such as Nim and Domineering, Game of the Amazons is fun but deceivingly complex.

The game is typically played on a 10x10 chessboard, set up with four white and four black Amazons that move like chess queens. However, in this program, there's the option to play on a simpler 8x8, 6x6, or 5x5 board. Players alternate moves, and white starts. Each turn, an Amazon moves to a square, then shoots an arrow (orthogonally or diagonally) and burns that square. The arrows and Amazons cannot cross (or land on) other Amazons or claimed squares. 

As the game progresses, possible moves will become increasingly limited. The last player to be able to move wins (the game ends when one of the players cannot make a move).

## Features

The program will, at the end, print a record of the moves played. It will also offer to play again. I was going to add a feature to simplify the moving, so that you can use the notation *AmazonrowAmazoncol-MoverowMovecol(ArrowrowArrowcol)*, but didn't get to it (See samplegames.txt.)

## Strategy

Much of the game is based on "territorial average," a concept used in my program to predict the winner of the game without having to actually play the whole thing out. (Not implemented)

## AI algorithm

I tried to implement the minimax algorithm.

*Written for APCS class.*
