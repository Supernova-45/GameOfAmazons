# Game Of The Amazons

This is primarily a web-based simulation of two-player Game of the Amazons, but there is a Processing-based and text-based version as well. 

## Rules

Invented in 1988 by Walter Zamkauskas from Argentina, *Game of the Amazons* is a strategy game with similarities to chess and go. Like other popular combinatorial games such as Nim and Domineering, Game of the Amazons is fun but deceivingly complex.

The game is typically played on a 10x10 chessboard, set up with four white and four black Amazons that move like chess queens. However, the website uses a much simpler 5x5 game. Players alternate moves, and white starts. Each turn, an Amazon moves to a square, then shoots an arrow (orthogonally or diagonally) and burns that square. The arrows and Amazons cannot cross (or land on) other Amazons or claimed squares. 

As the game progresses, possible moves will become increasingly limited. The last player to be able to move wins (the game ends when one of the players cannot make a move).

## Features

The Amazons.java program will, at the end, print a record of the moves played with the notation *AmazonrowAmazoncol-MoverowMovecol(ArrowrowArrowcol)* (see samplegames.txt). In the text-based version, the user can play on a 10x10, 8x8, 6x6, or 5x5 board. 

## Future

I'm working on implementing a minimax algorithm that will allow the user to play against an AI. I'd also like to add a territorial average method that predicts the winner of the game without having to play the whole thing out.


*Written for APCS class.*
