/**
 * @author Alexandra Kim
 * @version 5-20-2022
 * Text-based implementation of Game of the Amazons
 */

import java.util.Scanner;

public class Amazons {
    public static void main(String[] args) {
        // SETUP
        Scanner in = new Scanner(System.in);

        int size = startGame();

        int[][] board = initialBoard(size);
        boolean gameOver = false;
        boolean wantPlay = true;
        int moveCounter = 0;
        String gameRecord = "";

        System.out.println("Let's begin!");

        // GAMEPLAY 

        while (wantPlay) {

            int pt = 1; // pt = player turn, player 1

            while (!gameOver) {
                System.out.println(printBoard(board));

                //MOVE
                moveCounter++;

                int amazonsRow = amazonsRow(pt); int amazonsCol = amazonsCol(pt);
                // checking legality
                if (board[amazonsRow][amazonsCol] != pt) {
                    System.out.println("That's not your piece.");
                    amazonsRow = amazonsRow(pt); amazonsCol = amazonsCol(pt);
                }
        
                int moveRow = moveRow(pt); int moveCol = moveCol(pt);
        
                if (isLegalMove(board, amazonsRow, amazonsCol, moveRow, moveCol)) {
                    board[amazonsRow][amazonsCol] = 0;
                    board[moveRow][moveCol] = pt;
                } else {
                    System.out.println("Not legal.");
                    moveRow = moveRow(pt); moveCol = moveCol(pt);
                }
        
                int arrowRow = arrowRow(pt); int arrowCol = arrowCol(pt);
        
                if (isLegalMove(board, moveRow, moveCol, arrowRow, arrowCol)) {
                    board[arrowRow][arrowCol] = 3;
                } else {
                    System.out.println("Not legal.");
                    arrowRow = arrowRow(pt); arrowCol = arrowCol(pt);
                }
        
                gameRecord += formatGame(moveCounter, gameRecord, amazonsRow, amazonsCol, moveRow, moveCol, arrowRow, arrowCol); // saving the move
                 
                // next player's turn
                pt++;
                if (pt % 2 == 0) {
                    pt = 2;
                } else {
                    pt = 1;
                }

                System.out.println("--------------------");

                // check for winners, see if they want to play again
                if (checkWinner(board, pt) == true) {
                    System.out.println("Congratulations player " + pt + ", you've triumphed!");
                    System.out.println("Game record:");
                    System.out.println(gameRecord);
                    gameOver = true;
                }
            }

            System.out.println("Would you like to play again? (y/n)");
            if (in.next().equalsIgnoreCase("Y")) {
                gameOver = false;
                board = initialBoard(size);
                System.out.println("\n Starting a new game... \n");
            } else {
                System.out.println("\n Thanks for playing!");
                wantPlay = false;
            }
        }
        in.close();
    }

    // formats the board so that it's readable
    public static String printBoard(int[][] board) {
        String printBoard = "\n  ";
        for (int i = 0; i < board.length; i++) {
            printBoard += i + " ";
        }
        printBoard += "\n";

        for (int r = 0; r < board.length; r++) {
            printBoard += r + " ";
            for (int c = 0; c < board[0].length; c++) { // 0s are open, 1s have queens, 2s are blocked
                if (board[r][c] == 0) {
                    printBoard += ". ";
                } else if (board[r][c] == 1) {
                    printBoard += "o ";
                } else if (board[r][c] == 2) {
                    printBoard += "x ";
                } else {
                    printBoard += "  ";
                }
            }
            printBoard += "\n";
        }
        return printBoard;
    }

    // formats the starting board
    public static int[][] initialBoard(int size) {
        int[][] board = new int[size][size];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = 0;
            }
        }
        if (size == 10) {
            // black queens
            board[0][3] = 1;
            board[0][6] = 1;
            board[3][0] = 1;
            board[3][9] = 1;
            // white queens
            board[6][0] = 2;
            board[6][9] = 2;
            board[9][3] = 2;
            board[9][6] = 2;
        } else if (size == 8) {
            // black queens
            board[0][3] = 1;
            board[1][7] = 1;
            board[2][0] = 1;
            // white queens
            board[5][7] = 2;
            board[6][0] = 2;
            board[7][4] = 2;
        } else if (size == 6) {
            // black queens
            board[0][3] = 1;
            board[5][2] = 1;
            // white queens
            board[2][0] = 2;
            board[3][5] = 2;
        } else if (size == 5) {
            // black queens
            board[0][1] = 1;
            board[0][3] = 1;
            board[1][0] = 1;
            board[1][4] = 1;
            // white queens
            board[3][0] = 2;
            board[3][4] = 2;
            board[4][1] = 2;
            board[4][3] = 2;
        } else {
            System.out.println("Not a valid size, defaulting to 10x10");
            // black queens
            board[0][3] = 1;
            board[0][6] = 1;
            board[3][0] = 1;
            board[3][9] = 1;
            // white queens
            board[6][0] = 2;
            board[6][9] = 2;
            board[9][3] = 2;
            board[9][6] = 2;
        }
        return board;
    }

    public static int amazonsRow(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", what row is the piece you want to move in?");
        return in.nextInt();
    }

    public static int amazonsCol(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", what column is the piece you want to move in?");
        return in.nextInt();
    }

    public static int moveRow(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", which row do you want to move the piece to?");
        return in.nextInt();
    }

    public static int moveCol(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", which column do you want to move the piece to?");
        return in.nextInt();
    }

    public static int arrowRow(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", to which row do you want to move your arrow?");
        return in.nextInt();
    }

    public static int arrowCol(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", to which column do you want to move your arrow?");
        return in.nextInt();
    }

    public static boolean checkWinner(int[][] board, int player) { // checks if player won by seeing if other player has a move
        // next player's turn
        player++;
        if (player % 2 == 0) {
            player = 2;
        } else {
            player = 1;
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == player) { // checking for location of the amazons
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[0].length; j++) {
                            if (board[i][j] == 0) {
                                if (isLegalMove(board, r, c, i, j)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isLegalMove(int[][] board, int currRow, int currCol, int moveRow, int moveCol) {
        if (currRow == moveRow) { // horizontal
            if (currCol < moveCol) {
                for (int c = currCol + 1; c <= moveCol; c++) {
                    if (board[currRow][c] != 0) {
                        return false;
                    }
                }
            } else {
                for (int c = moveCol; c <= currCol - 1; c++) {
                    if (board[currRow][c] != 0) {
                        return false;
                    }
                }
            }
        } else if (currCol == moveCol) { // vertical
            if (currRow < moveRow) {
                for (int r = currRow + 1; r <= moveRow; r++) {
                    if (board[r][currCol] != 0) {
                        return false;
                    }
                }
            } else {
                for (int r = moveRow; r <= currRow - 1; r++) {
                    if (board[r][currCol] != 0) {
                        return false;
                    }
                }
            }

        } else if (Math.abs(currCol - moveCol) == Math.abs(currRow - moveRow)) { // 4 cases for diagonal moves
            for (int a = 1; a < Math.abs(currRow - moveRow); a++) {
                if (currCol < moveCol) {
                    if (currRow < moveRow) {
                        if (board[currRow + a][currCol + a] != 0) {
                            return false;
                        }
                    } else {
                        if (board[currRow - a][currCol + a] != 0) {
                            return false;
                        }
                    }
                } else {
                    if (currRow < moveRow) {
                        if (board[currRow + a][currCol - a] != 0) {
                            return false;
                        }
                    } else {
                        if (board[currRow - a][currCol - a] != 0) {
                            return false;
                        }
                    }
                }
            }
        } else { // not diagonal, horizontal or vertical
            return false;
        }
        return true;
    }

    public static int startGame() { // returns size
        Scanner in = new Scanner(System.in);

        System.out.println("-----------------------------------");
        System.out.println("| WELCOME TO GAME OF THE AMAZONS! | ");
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("Would you like to review the rules? (y/n)");
        if (in.next().equalsIgnoreCase("Y")) {
            System.out.println(
                    "Players alternate moves. Each turn, an Amazon moves to a square, then shoots an arrow (orthogonally or diagonally) to burn another square. The arrows and Amazons cannot cross (or land on) other Amazons or claimed squares. As the game progresses, the set of possible moves becomes increasingly limited. The last player to be able to move wins (the game ends when one of the players cannot make a move).");
        }
        System.out.println();
        System.out.println(
                "This is a two player game: o is player 1, and x is player 2. Do you want to play on a 10x10, 8x8, 6x6, or 5x5? (type 10, 8, 6, or 5)");
        return in.nextInt();
    }

    // Records the game for future reference
    public static String formatGame(int moveNum, String gameRecord, int amazonsRow, int amazonsCol, int moveRow, int moveCol, int arrowRow, int arrowCol) {
        return "\n" + moveNum + ". " +
                Integer.toString(amazonsRow) + Integer.toString(amazonsCol) + "-" + 
                Integer.toString(moveRow) + Integer.toString(moveCol) + "(" + 
                Integer.toString(arrowRow) + Integer.toString(arrowCol) + ")";
    }
}