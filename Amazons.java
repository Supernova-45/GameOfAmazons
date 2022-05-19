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
        System.out.println("Let's begin! This is a two player game.");
        System.out.println(printBoard(board));

        // GAMEPLAY 


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
                } else {
                    printBoard += "x ";
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
        board[6][0] = 1;
        board[6][9] = 1;
        board[9][3] = 1;
        board[9][6] = 1;

        return board;
    }

    public static int askRowMove(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", which row do you want to move to?");
        return in.nextInt();
    }

    public static int askColumnMove(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", which column do you want to move to?");
        return in.nextInt();
    }
    public static int askRowArrow(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", to which row do you want to move your arrow?");
        return in.nextInt();
    }

    public static int askColumnArrow(int player) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player " + player + ", to which column do you want to move your arrow?");
        return in.nextInt();
    }

    public static boolean checkDone() {
        return true;
    }

    public static boolean isLegalMove(int[][] board, int currRow, int currCol, int moveRow, int moveCol) {
        for (int r = currRow; r <= moveRow; r++) {
            for (int c = currCol; c <= moveCol; c++) {
                if ((board[r][c] == 1) || (board[r][c] == 2)) {
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