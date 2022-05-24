import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonGrid {

    // need way to use keyboard to exit
	JFrame frame=new JFrame(); //creates frame
	JButton[][] grid; //names the grid of buttons

    public ButtonGrid(int[][] board) { //constructor
        JFrame frame = new JFrame("Game of the Amazons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

		frame.setLayout(new GridLayout(board.length,board[0].length)); //set layout
		grid=new JButton[board.length][board[0].length]; //allocate the size of grid
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board[0].length; col++){
				grid[row][col]=new JButton(Integer.toString(board[row][col])); //creates new button	  
                frame.add(grid[row][col]); //adds button to grid
                grid[row][col].setBackground(Color.blue);
			}
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); //sets appropriate size for frame
		frame.setVisible(true); //makes frame visible
	}

    public static void main(String[] args) {
        int[][] board = initialBoard(5);
		new ButtonGrid(board);//makes new ButtonGrid with 2 parameters
	}

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
}