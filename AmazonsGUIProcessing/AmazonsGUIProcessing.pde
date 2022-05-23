int h = 5, w = 5, s = 100; // s is the square size
int[][]board = new int[h][w];
String begin = "Let's begin!";
String clear = "";
int t; // time

boolean gameOver = false;
boolean wantPlay = true;
int moveCounter = 0;
String gameRecord = "";

void setup() {
  surface.setTitle("Game of the Amazons");
  size(1000, 500); ellipseMode(CORNER);
  background(173,216,230);
  frameRate(120);
  t = millis();
  print(t);
  board = initialBoard(5);
  
}

void draw() {
  /**
  while (true) { 
    if (millis() - t > 3000) {
      begin = clear;
      t = millis();
      text(begin, 600, 300);
      break;
    }
    textSize(50);
    text(begin, 600, 300);
  }
  */
  
  //code in arrow drawing

  for (int i = 0; i < h; i++) {
    for (int j = 0; j < w; j++) {
      fill(255);
      rect(i*s, j*s, s, s);
      if(board[j][i] == 1) {
        fill(0);
        ellipse(i*s, j*s, s, s); // black amazon? make more realistic
      } else if (board[j][i] == 2) {
        fill(255,0,0);
        ellipse(i*s, j*s, s, s); // white amazon? make more realistic
      } else if (board[j][i] == 3) {
        fill(105,105,105);
        rect(i*s, j*s, s, s);
        // fill with grey
      }
    }
  }
}

void mousePressed() {
  int x = mouseX / s;
  int y = mouseY / s;
  print(mouseX);
  println(mouseY);
  if ((x < s*w) && (y < w*s)) {
    board[y][x] = 1;
  } else { }
  printBoard(board);
  //draw();
}

void keyPressed() {
  
}
void start() {
  
}
String printBoard(int[][] board) {
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
    
int[][] initialBoard(int size) {
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