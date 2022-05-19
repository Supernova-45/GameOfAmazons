public class Amazons {
    public static void main(String[] args) {
        int[][] board = new int[10][10];

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
        return "";
    }

}