int h = 5, w = 5, s = 100; // s is the square size
int[][]board = new int[h][w];
int pt = 0; // player turn
int clicks = 0;

void setup() {
  surface.setTitle("Game of the Amazons");
  size(1000, 500); ellipseMode(CORNER);
  background(173,216,230);
  frameRate(120);
  board = initialBoard(5);
}

void draw() {
  load(board);
  pt++;
  if (pt % 2 == 0) {
      pt = 2;
  } else {
      pt = 1;
  }
}

void mousePressed() {
  clicks++;
  int x = mouseX / s;
  int y = mouseY / s;
  if ((x < s*w) && (y < w*s)) {
    if (clicks % 3 == 0) {
      board[y][x] = 3;
    } else if (clicks % 3 == 2) {
      board[pmouseX / s][pmouseY / s] = 0;
      board[y][x] = pt;
    }
  }
}

void load(int[][] board) {
  for (int i = 0; i < board.length; i++) {
    for (int j = 0; j < board.length; j++) {
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

int[][] initialBoard(int size) {
        int[][] board = new int[size][size];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = 0;
            }
        }
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
        
        return board;
    }
