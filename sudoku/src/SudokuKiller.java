

/**
 * @author      Alexander Goobar
 * @version     1.0
 */

public class SudokuKiller {

    private int[][] board;

    /**
     * A constructor for sudoku solving algoritm. Creates a integer matrix (9x9).
     */
    public SudokuKiller() {
        board = new int[9][9];
    }


    /**
     * Controls if the inserted values are allowed with help from a private recursive method.
     * @return true if the inserted values follow the sudoku rules
     */
    public boolean userInputCheck() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (!checkRules(r, c)) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean checkRules(int r, int c) {
        if (board[r][c] == 0){
            return true;
        }
        for (int i = 0; i < 9; i++) {
            if (i != c && board[r][i] == board[r][c]) {
                return false;
            }
            if (i != r && board[i][c] == board[r][c]) {
                return false;
            }
        }

        int cornerRow = r - (r % 3);
        int cornerCol = c - (c % 3);

        for (int row = cornerRow; row <= cornerRow + 2; row++) {
            for (int col = cornerCol; col <= cornerCol + 2; col++) {
                if (row != r && col != c && board[row][col] == board[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Algoritm that solves the sudoku game. Uses private recursive method that are instructed to start in the top left corner of the board.
     * @return true if the sudoku is solved, otherwise false
     */
    public boolean solve() {
        return solve(0, 0);
    }


    private boolean solve(int r, int c){
        if (r > 8) {
            return true;
        }
        if (board[r][c] == 0) {
            for (int x = 1; x <= 9; x++) {
                board[r][c] = x;
                if (startSolving(r, c)){
                    return true;
                }
            }
            board[r][c] = 0;
            return false;

        } else {
            if (startSolving(r, c)){
                return true;
            }
        }
        return false;
    }

    private boolean startSolving(int r, int c){
        if (checkRules(r, c)) {
            if (c < 8) {
                if (solve(r, c+1)) {
                    return true;
                }
            } else {
                if (solve(r+1, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the value of a specific square.
     * @param r the row
     * @param c the column
     * @return the integer value
     */
    public int getValue(int r, int c){
        return board[r][c];
    }

    /**
     * Sets the value of a specific square.
     * @param r the row
     * @param c the column
     * @param value the value
     */
    public void setValue(int r, int c, int value){
        board[r][c] = value;
    }

}