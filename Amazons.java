import java.util.Scanner;

public class Amazons {
    public static void main(String[] args) {
        // SETUP
        Scanner in = new Scanner(System.in);
        int[][] board = initialBoard(10);

        System.out.println("-----------------------------------");
        System.out.println("| WELCOME TO GAME OF THE AMAZONS! | ");
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("Would you like to review the rules? (y/n)");
        if (in.next().equalsIgnoreCase("Y")) {
            System.out.println(rules());
        }
        System.out.println();
        System.out.println("Let's begin! This is a two player game: o is player 1, and x is player 2.");

        // GAMEPLAY 
        boolean gameOver = false;
        int pt = 1; // pt = player turn, player 1

        while (!gameOver) {
            System.out.println(printBoard(board));
            int amazonsRow = amazonsRow(pt);
            int amazonsCol = amazonsCol(pt);
            int moveRow = moveRow(pt);
            int moveCol = moveCol(pt);
            int arrowRow = arrowRow(pt);
            int arrowCol = arrowCol(pt);
            
            // checking legality
            if (board[amazonsRow][amazonsCol] != pt) {
                System.out.println("That's not your piece.");
            }
            if (isLegalMove(board, amazonsRow, amazonsCol, moveRow, moveCol)) {
                board[amazonsRow][amazonsCol] = 0;
                board[moveRow][moveCol] = pt;
            } else {
                System.out.println("Not legal.");
                //ADD STUFF TO ALLOW LEGAL MOVE
            }
            if (isLegalMove(board, moveRow, moveCol, arrowRow, arrowCol)) {
                board[arrowRow][arrowCol] = 3;
            } else {
                System.out.println("Not legal.");
            }

            // check for winners, see if they want to play again

            // next player's turn
            pt++;
            if (pt % 2 == 0) {
                pt = 2;
            } else {
                pt = 1;
            }
        }

        in.close();
    }

    // formats the board so that it's readable
    public static String printBoard(int[][] board) {
        String printBoard = "\n  0 1 2 3 4 5 6 7 8 9 \n";
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

        return board;
    }

    public static int amazonsRow(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", which row is the piece you want to move in?");
        return in.nextInt();
    }

    public static int amazonsCol(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", what column is the piece you want to move in?");
        return in.nextInt();
    }

    public static int moveRow(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", which row do you want to move to?");
        return in.nextInt();
    }

    public static int moveCol(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", which column do you want to move to?");
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

    public static boolean checkWinner(int player) { // checks if player 1 or 2 won
        return true;
    }

    public static boolean isLegalMove(int[][] board, int currRow, int currCol, int moveRow, int moveCol) {
        if (currRow == moveRow) { // horizontal
            for (int c = Math.min(currCol+1, moveCol); c <= Math.max(currCol+1, moveCol); c++) {
                if (board[currRow][c] != 0) {
                    return false;
                }
            }
        } else if (currCol == moveCol) { // vertical
            for (int r = Math.min(currRow+1, moveRow); r <= Math.max(currRow+1, moveRow); r++) {
                if (board[r][currCol] != 0) {
                    return false;
                }
            }
        } else { // diagonal
            for (int a = 0; a < Math.abs(currRow-moveRow); a++) {
                if (board[Math.min(currRow+1, moveRow) + a][Math.min(currCol+1, moveCol) + a] != 0) {
                    System.out.println(board[Math.min(currRow+1, moveRow) + a][Math.min(currCol+1, moveCol)+a]);
                    return false;
                }
            }
        }
        return true;
    }

    public static String rules() {
        return "Players alternate moves, and white starts. Each turn, an Amazon moves to a square, then shoots an arrow from that landing square and claim that square. The arrows and Amazons cannot cross (or land on) other Amazons or claimed squares. As the game progresses, possible moves will become increasingly limited. The last player to be able to move wins (the game ends when one of the players cannot make a move).";
    }

}