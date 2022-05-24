int h = 5, w = 5, s = 100; // s is the square size
int[][]board = new int[h][w];
int pt = 1; // player turn
int clicks = 0;
int t; // time
String displayText = "";


void setup() {
    surface.setTitle("Game of the Amazons");
    size(1000, 500);
    ellipseMode(CORNER);
    background(173, 216, 230);
    frameRate(120);
    t = millis();
    textSize(24);
    text("Let's begin!", 700, 210);
    board = initialBoard(5);
    //rules?
}

void draw() {
    fill(0);
    stroke(0);
    strokeWeight(4);

    if (checkWinner(board, pt)) {
        textSize(48);
        background(173, 216, 230);
        text("Congratulations player " + pt + "!", 300, 250);
        noLoop();
    }

    load(board);

    if (millis() - t > 3000) { // clear the let's begin message
        stroke(173, 216, 230);
        fill(173, 216, 230);
        rect(550, 100, 400, 250);
    } else {
        textSize(24);
        text(displayText, 700, 250);
    }

    if (displayLine) { // if you are moving amazon
        stroke(0);
        strokeWeight(10);
        line(x*s+50, y*s+50, mouseX, mouseY);
    }
}

int x, y, prevX, prevY, moveX, moveY;
boolean displayLine;

void mousePressed() {
    displayLine = false;
    clicks++;
    // determining whose turn it is
    if (clicks % 6 == 1) {
        pt = 1;
    } else if (clicks % 6 == 4) {
        pt = 2;
    }

    // mouse position
    x = mouseX / s;
    y = mouseY / s;

    // determining type of move and legality
    if ((x < s*w) && (y < w*s)) {
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
}

void load(int[][] board) {
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board.length; j++) {
            fill(255);
            rect(i*s, j*s, s, s);
            if (board[j][i] == 1) {
                fill(0); // black
                ellipse(i*s, j*s, s, s); // black amazon? make more realistic
            } else if (board[j][i] == 2) {
                fill(255, 0, 0);
                ellipse(i*s, j*s, s, s); // red amazon? make more realistic
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

public static boolean isLegalMove(int[][] board, int currRow, int currCol, int moveRow, int moveCol) {
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
