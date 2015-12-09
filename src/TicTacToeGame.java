import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Ye Vang on 5/18/2015.
 * Play Tic-Tac-Toe with a friend!
 */
public class TicTacToeGame extends GraphicsProgram {
    private GImage[][] arrayOfX;
    private GImage[][] arrayOfO;
    private static final int GRID_LINE_DEPTH = 5;
    private static final int GRID_LINE_LENGTH = 615;
    private int numXs = 0;
    private int numOs = 0;

    // Constructor
    public TicTacToeGame() {
        this.arrayOfX = new GImage[3][3];
        this.arrayOfO = new GImage[3][3];
    }

    // Initialize mouse listeners for clicking
    public void init() {
        addMouseListeners();
    }

    // Main run method for GUI
    public void run() {
        setBackground(Color.LIGHT_GRAY);
        Dimension min = new Dimension(620,620);
        setMinimumSize(min);
        createGrid();
        waitForClick();
    }

    /**
     * Create the grid with lines
     */
    private void createGrid() {
        for (int i = 0; i < 4; i++) {
            GRect horizontalLine = new GRect(GRID_LINE_LENGTH, GRID_LINE_DEPTH);
            GRect verticalLine = new GRect(GRID_LINE_DEPTH, GRID_LINE_LENGTH);
            horizontalLine.setFilled(true);
            verticalLine.setFilled(true);
            horizontalLine.setFillColor(Color.DARK_GRAY);
            verticalLine.setFillColor(Color.DARK_GRAY);
            add(horizontalLine, 0, i * 205);
            add(verticalLine, i*205, 0);
        }
    }

    /**
     * Handles all mouse events based on where the mouse was clicked.
     * @param event from mouse clicks
     */
    public void mousePressed(MouseEvent event) {
        if (event.getX() <= 205 && event.getY() <= 205
                && getElementAt(event.getX(), event.getY()) == null) {
            if (numXs == numOs) {
                makeNewX(arrayOfX, 0, 0);
            } else if (numXs > numOs) {
                makeNewO(arrayOfO, 0, 0);
            }
        } if (event.getX() > 205 && event.getX() <= 405 && event.getY() <= 205
               && getElementAt(event.getX(), event.getY()) == null) {
            if (numXs == numOs) {
                makeNewX(arrayOfX, 1, 0);
            } else if (numXs > numOs) {
                makeNewO(arrayOfO, 1, 0);
            }
        } if (event.getX() >= 410 && event.getX() < 615 && event.getY() <= 205
                && getElementAt(event.getX(), event.getY()) == null) {
            if (numXs == numOs) {
                makeNewX(arrayOfX, 2, 0);
            } else if (numXs > numOs) {
                makeNewO(arrayOfO, 2, 0);
            }
        } if (event.getX() <= 205 && event.getY() < 410 && event.getY() > 205
                && getElementAt(event.getX(), event.getY()) == null) {
            if (numXs == numOs) {
                makeNewX(arrayOfX, 0, 1);
            } else if (numXs > numOs) {
                makeNewO(arrayOfO, 0, 1);
            }
        } if (event.getX() < 410 && event.getX() > 205 && event.getY() < 410 && event.getY() > 205
                && getElementAt(event.getX(), event.getY()) == null) {
            if (numXs == numOs) {
                makeNewX(arrayOfX, 1, 1);
            } else if (numXs > numOs) {
                makeNewO(arrayOfO, 1, 1);
            }
        } if (event.getX() >= 410 && event.getX() < 615 && event.getY() >= 205 && event.getY() < 410
                && getElementAt(event.getX(), event.getY()) == null) {
            if (numXs == numOs) {
                makeNewX(arrayOfX, 2, 1);
            } else if (numXs > numOs) {
                makeNewO(arrayOfO, 2, 1);
            }
        } if (event.getX() < 205 && event.getY() >= 410 && event.getY() < 615
                && getElementAt(event.getX(), event.getY()) == null) {
            if (numXs == numOs) {
                makeNewX(arrayOfX, 0, 2);
            } else if (numXs > numOs) {
                makeNewO(arrayOfO, 0, 2);
            }
        } if (event.getX() >= 205 && event.getX() < 410 && event.getY() >= 410 && event.getY() < 615
            && getElementAt(event.getX(), event.getY()) == null) {
            if (numXs == numOs) {
                makeNewX(arrayOfX, 1, 2);
            } else if (numXs > numOs) {
                makeNewO(arrayOfO, 1, 2);
            }
        } if (event.getX() >= 410 && event.getX() < 615 && event.getY() >= 410 && event.getY() < 615
                && getElementAt(event.getX(), event.getY()) == null) {
            if (numXs == numOs) {
                makeNewX(arrayOfX, 2, 2);
            } else if (numXs > numOs) {
                makeNewO(arrayOfO, 2, 2);
            }
        }
        printWin();
        printTie();
    }

    /**
     * Creates new X GImages to populate the array holding Xs
     * @param arrayOfX 2d array to hold all Xs
     * @param index1 outer index
     * @param index2 inner index
     * @return 2d Array with newly inserted X GImage
     */
    public GImage[][] makeNewX(GImage[][] arrayOfX, int index1, int index2) {
        arrayOfX[index1][index2] = new GImage("/X.png");
        add(arrayOfX[index1][index2], 5 + (index1*205), 5 + (index2*205));
        numXs++;
        return arrayOfX;
    }

    /**
     * Creates new O GImages to populate array holding Os
     * @param arrayOfO 2d array holding all Os
     * @param index1 outer index
     * @param index2 inner index
     * @return 2d Array with newly inserted O GImage
     */
    public GImage[][] makeNewO(GImage[][] arrayOfO, int index1, int index2) {
        arrayOfO[index1][index2] = new GImage("/O.png");
        add(arrayOfO[index1][index2], 5 + (index1*205), 5 + (index2*205));
        numOs++;
        return arrayOfO;
    }

    /**
     * Checks to see if player 1 has achieved a winning move of 3 in a row
     * @return
     */
    public boolean checkXWin() {
        if (arrayOfX[0][0] != null && arrayOfX[1][0] != null && arrayOfX[2][0] != null) {
            return true;
        } else if (arrayOfX[0][1] != null && arrayOfX[1][1] != null && arrayOfX[2][1] != null) {
            return true;
        } else if (arrayOfX[0][2] != null && arrayOfX[1][2] != null && arrayOfX[2][2] != null) {
            return true;
        } else if (arrayOfX[0][0] != null && arrayOfX[0][1] != null && arrayOfX[0][2] != null) {
            return true;
        } else if (arrayOfX[1][0] != null && arrayOfX[1][1] != null && arrayOfX[1][2] != null) {
            return true;
        } else if (arrayOfX[2][0] != null && arrayOfX[2][1] != null && arrayOfX[2][2] != null) {
            return true;
        } else if (arrayOfX[0][0] != null && arrayOfX[1][1] != null && arrayOfX[2][2] != null) {
            return true;
        } else if (arrayOfX[2][0] != null && arrayOfX[1][1] != null && arrayOfX[0][2] != null) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if player 2 has won
     * @return
     */
    public boolean checkOWin() {
        if (arrayOfO[0][0] != null && arrayOfO[1][0] != null && arrayOfO[2][0] != null) {
            return true;
        } else if (arrayOfO[0][1] != null && arrayOfO[1][1] != null && arrayOfO[2][1] != null) {
            return true;
        } else if (arrayOfO[0][2] != null && arrayOfO[1][2] != null && arrayOfO[2][2] != null) {
            return true;
        } else if (arrayOfO[0][0] != null && arrayOfO[0][1] != null && arrayOfO[0][2] != null) {
            return true;
        } else if (arrayOfO[1][0] != null && arrayOfO[1][1] != null && arrayOfO[1][2] != null) {
            return true;
        } else if (arrayOfO[2][0] != null && arrayOfO[2][1] != null && arrayOfO[2][2] != null) {
            return true;
        } else if (arrayOfO[0][0] != null && arrayOfO[1][1] != null && arrayOfO[2][2] != null) {
            return true;
        } else if (arrayOfO[2][0] != null && arrayOfO[1][1] != null && arrayOfO[0][2] != null) {
            return true;
        }
        return false;
    }

    /**
     * Prints a win statement for the player that won
     */
    public void printWin() {
        if (checkXWin()) {
            JOptionPane.showMessageDialog(null, "Congratulations! Player X wins! You win an imaginary cookie!",
                    "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
            pause(600);
            exit();
        }
        if (checkOWin()) {
            JOptionPane.showMessageDialog(null, "Congratulations! Player O wins! You win an imaginary cookie!",
                    "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
            pause(600);
            exit();
        }
    }

    /**
     * If no winning move is achieved and the board is full
     */
    public void printTie() {
        if (!checkOWin() && !checkXWin() && numXs == 5 && numOs == 4) {
            JOptionPane.showMessageDialog(null, "Cat's game! You two are equally clever!",
                    "Shoot!", JOptionPane.ERROR_MESSAGE);
        }
    }
}