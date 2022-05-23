int h = 5, w = 5;
int[][]board = new int[h][w];

void setup() {
  size(600, 500); ellipseMode(CORNER);
  background(173,216,230);
  board[0][0]=1;
  board[1][1]=2;
  
}

void draw() {
  for (int i = 0; i < h; i++) {
    for (int j = 0; j < w; j++) {
      fill(255);
      rect(i*100, j*100, 100, 100);
      if(board[j][i] == 1) {
        fill(255);
        ellipse(i*100, j*100, 100, 100); // black amazon? make more realistic
      } else if (board[j][i] == 2) {
        fill(255,0,0);
        ellipse(i*100, j*100, 100, 100); // white amazon? make more realistic
      } else if (board[j][i] == 3) {
        fill(105,105,105)
        rect(i*100, j*100, 100, 100);
        // fill with grey
      }
    }
  }
}
