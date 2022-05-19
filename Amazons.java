import java.util.Scanner;

public class Amazons {
    public static void main(String[] args) {
        // SETUP
        Scanner in = new Scanner(System.in);
        int[][] board = new int[10][10];

        System.out.println("-----------------------------------");
        System.out.println("| WELCOME TO GAME OF THE AMAZONS! | ");
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("Would you like to review the rules? (y/n)");
        if (in.next().equalsIgnoreCase("Y")) {
            System.out.println(rules());
        }
        System.out.println();
        System.out.println("Let's begin! You will need two players.");
    }

    // formats the board so that it's readable
    public static String printBoard(int[][] board) {
        String printBoard = "";
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 0) {
                    printBoard += ". ";
                } else {
                    printBoard += board[r][c] + " ";
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
        return board;
    }

    public static void play() {
        // might not need
    }

    public static boolean checkDone() {
        return true;
    }

    public static String rules() {
        return "Players alternate moves, and white starts. Each turn, an Amazon moves to a square, then shoots an arrow from that landing square and claim that square. The arrows and Amazons cannot cross (or land on) other Amazons or claimed squares. As the game progresses, possible moves will become increasingly limited. The last player to be able to move wins (the game ends when one of the players cannot make a move).";
    }

}