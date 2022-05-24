let h, w, s; // s is the square size
let board;
let pt; // player turn
let clicks;
let t, origT; // time
let displayText;
let rqueen;
let bqueen;

let x, y, prevX, prevY, moveX, moveY;
let displayLine, done, restart;

function setup() {
    createCanvas(1000, 500);
    ellipseMode(CORNER);
    background(173, 216, 230);
    frameRate(120);
    pixelDensity(2);

    // initialize variables
    origT = millis();  t = millis();
    rqueen = loadImage("redqueen.png");
    bqueen = loadImage("blackqueen.png");
    h = 5; w = 5; s = 100;
    board = new Array(h);
    board = initialBoard(5);
    pt = 1;
    clicks = 1;
    displayText = "";
    done = false;
    restart = false;
}

function draw() {

    if (millis() - origT < 3000) { // start screen
        background(173, 216, 230);
        fill(0,0,205);
        textSize(48);
        text("WELCOME TO GAME OF THE AMAZONS!", 25, 200);
        // start instructions
        textSize(24);
        text("Let's begin! Black moves first.", 350, 250);
    }
    else if (millis() - origT < 3500) { // clear the screen
        background(173, 216, 230);
    } else {
        stroke(0);
        strokeWeight(4);

        load(board);

        textSize(18);
        fill(0,0,205);
        strokeWeight(0);
        text("Press q to quit, r to restart", 650, 50);

        if (restart) { // fresh game, resetting every variable
            restart = false;
            board = initialBoard(5);
            clicks = 1;
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
                line(x * s + 50, y * s + 50, mouseX, mouseY);
            }
        }
    }
}

function mousePressed() {

    displayLine = false;
    // mouse position
    x = Math.floor(mouseX / s);
    y = Math.floor(mouseY / s);

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
    } else {
        clicks--;
    }

    clicks++;
    // determining whose turn it is
    if (clicks % 6 == 1) {
        pt = 1;
    } else if (clicks % 6 == 4) {
        pt = 2;
    }
}

function load(board) {
    for (let i = 0; i < board.length; i++) {
        for (let j = 0; j < board.length; j++) {
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

function initialBoard(size) {
    let board = [];
    
    for (let r = 0; r < size; r++) {
        board[r] = [];
        for (let c = 0; c < size; c++) {
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

function isLegalMove(board, currRow, currCol, moveRow, moveCol) {
    if ((currRow == moveRow) && (currCol == moveCol)) {
        return false;
    } else if (currRow == moveRow) { // horizontal
        if (currCol < moveCol) {
            for (let c = currCol + 1; c <= moveCol; c++) {
                if (board[currRow][c] != 0) {
                    return false;
                }
            }
        } else {
            for (let c = moveCol; c <= currCol - 1; c++) {
                if (board[currRow][c] != 0) {
                    return false;
                }
            }
        }
    } else if (currCol == moveCol) { // vertical
        if (currRow < moveRow) {
            for (let r = currRow + 1; r <= moveRow; r++) {
                if (board[r][currCol] != 0) {
                    return false;
                }
            }
        } else {
            for (let r = moveRow; r <= currRow - 1; r++) {
                if (board[r][currCol] != 0) {
                    return false;
                }
            }
        }
    } else if (Math.abs(currCol - moveCol) == Math.abs(currRow - moveRow)) { // 4 cases for diagonal moves
        for (let a = 1; a <= Math.abs(currRow - moveRow); a++) {
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

function checkWinner(board, player) { // checks if player won by seeing if other player has a move
    // next player's turn
    player++;
    if (player % 2 == 0) {
        player = 2;
    } else {
        player = 1;
    }

    for (let r = 0; r < board.length; r++) {
        for (let c = 0; c < board[0].length; c++) {
            if (board[r][c] == player) { // checking for location of the amazons
                for (let i = 0; i < board.length; i++) {
                    for (let j = 0; j < board[0].length; j++) {
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

function keyPressed() {
    if (key == 'q') done = true;
    if (key == 'r') restart = true;
}