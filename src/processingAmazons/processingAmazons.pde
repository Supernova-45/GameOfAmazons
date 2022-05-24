/** 5x5 Game of the Amazons, GUI version
 * @author Alexandra Kim
 * @version 5-24-2022
*/

int h = 5; int w = 5; int s = 100; // s is the square size
int[][]board;
int pt; // player turn
int clicks; // times mouse has been clicked
int t; // time
int origT; // time the game started
String displayText;
PImage rqueen;
PImage bqueen;
int x, y, prevX, prevY, moveX, moveY;
boolean displayLine, done, restart, showRules, start;
//String rules = "Players alternate moves. Each turn, an Amazon moves to a square, " + 
    //"then shoots an arrow (orthogonally or diagonally) to burn another square. " + 
    //"The arrows and Amazons cannot cross (or land on) other Amazons or claimed squares. " + 
    //"The player who cannot make a move loses.";

void setup() {
  // variable initializations
  t = millis(); origT = millis();
  board = initialBoard(5);
  rqueen = loadImage("redqueen.png");
  bqueen = loadImage("blackqueen.png");
  done = false;
  restart = false;
  displayText = "";
  pt = 1; 
  clicks = 1;
  // graphics settings
  surface.setTitle("Game of the Amazons");
  size(1000, 500);
  ellipseMode(CORNER);
  pixelDensity(2);
  background(173, 216, 230);
}

void draw() {
  // start screen
  if (millis() - origT < 4000) {
    textSize(48);
    text("WELCOME TO GAME OF THE AMAZONS!",125,200); 
    // start instructions
    textSize(24);
    text("Let's begin! Black moves first.", 350, 250);
  }
  else if (millis() - origT < 4500) {
    background(173, 216, 230);
  }
  else {
    fill(0);
    stroke(0);
    strokeWeight(4);
  
    load(board);
    
    textSize(18);
    text("Press q to quit, r to restart", 650, 50);
  
    if (restart) { // fresh game, resetting every variable
      restart = false;
      board = initialBoard(5);
      clicks = 0;
      t = millis();
      pt = 1;
      displayText = "New game...";
    }
  
    if (done) {
      background(173, 216, 230);
      textSize(48);
      text("Thanks for playing!", 300, 250);
      noLoop();
    } else if (checkWinner(board, pt)) { // checking for winner
      background(173, 216, 230);
      textSize(48);
      text("Congratulations player " + pt + "!", 250, 250);
      noLoop();
    } else {
      // display whose turn it is
      strokeWeight(0);
      fill(pt == 1 ? 0 : 255, 0, 0);
      rect(950, 450, 40, 40);
  
      if (millis() - t > 3000) { // clear any messages
        stroke(173, 216, 230);
        fill(173, 216, 230);
        rect(550, 100, 400, 250);
      } else {
        textSize(24);
        text(displayText, 700, 250);
      }
  
      if (displayLine) { // if you are moving amazon
        stroke(pt == 1 ? 0 : 255, 0, 0);
        strokeWeight(10);
        line(x*s+50, y*s+50, mouseX, mouseY);
      }
    }
  }
}


void mousePressed() {

  displayLine = false;
  // mouse position
  x = mouseX / s;
  y = mouseY / s;

  // determining type of move and legality
  if ((x < w) && (y < h)) {
    if (clicks % 3 == 0) { // shooting an arrow
      if (!isLegalMove(board, moveY, moveX, y, x)) {
        t = millis();
        displayText = "Not legal.";
        clicks--;
      } else {
        board[y][x] = 3;
      }
    } else if (clicks % 3 == 1) { // picking the piece to move
      if (board[y][x] != pt) {
        t = millis();
        displayText = "Not your piece.";
        clicks--;
      } else {
        prevX = x;
        prevY = y;
        displayLine = true;
      }
    } else if (clicks % 3 == 2) { // the spot to move to
      if (!isLegalMove(board, prevY, prevX, y, x)) {
        t = millis();
        displayText = "Not legal.";
        clicks--;
      } else {
        moveX = x;
        moveY = y;
        board[prevY][prevX] = 0;
        board[y][x] = pt;
        displayLine = true;
      }
    }
  }

  clicks++;
  // determining whose turn it is
  if (clicks % 6 == 1) {
    pt = 1;
  } else if (clicks % 6 == 4) {
    pt = 2;
  }
}

void load(int[][] board) {
  for (int i = 0; i < board.length; i++) {
    for (int j = 0; j < board.length; j++) {
      fill(255);
      rect(i*s, j*s, s, s);
      if (board[j][i] == 1) {
        fill(0); // black
        image(bqueen, i*s+5, j*s+5, s-10, s-10);

        //ellipse(i*s, j*s, s, s); // black amazon
      } else if (board[j][i] == 2) {
        fill(255, 0, 0);
        image(rqueen, i*s+5, j*s+5, s-10, s-10);

        // ellipse(i*s, j*s, s, s); // red amazon
      } else if (board[j][i] == 3) {
        fill(105, 105, 105);
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

boolean isLegalMove(int[][] board, int currRow, int currCol, int moveRow, int moveCol) {
  if ((currRow == moveRow) && (currCol == moveCol)) {
    return false;
  } else if (currRow == moveRow) { // horizontal
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
    for (int a = 1; a <= Math.abs(currRow - moveRow); a++) {
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

boolean checkWinner(int[][] board, int player) { // checks if player won by seeing if other player has a move
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

void keyPressed() {
  if (key == 'q') done = true;
  if (key == 'r') restart = true;
  //if (key == 'm') showRules = true;
}
