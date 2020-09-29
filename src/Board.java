import java.util.Random;

public class Board {
    private final Tile[][] board;

    /**
     * Constructor for Board
     * @param width width of the board
     * @param height height of the board
     * @param proportion number of squares per mine
     */
    Board(int width, int height, int proportion) {
        board = new Tile[width][height];
        initRandBoard(proportion);
    }

    /**
     * Performs a player move
     * @param x x coordinate
     * @param y y coordinate
     * @return true if move is valid
     */
    boolean move(int x, int y) {

        // if invalid location
        if (x < 0 || x >= board.length ||
                y < 0 || y >= board[x].length ||
                board[x][y].getMark() ||
                board[x][y].isVisible()) {
            return false;
        }

        board[x][y].setVisible();

        //if value == 0
        if (board[x][y].getType() == Tile.TileType.square) {
            if (((Square) board[x][y]).getValue() == 0) {
                for (int i = -1; i <=1; i++) {
                    for (int j = -1; j <=1; j++) {
                        if (!(i == 0 && j == 0)) {
                            move(x+i, y+j);
                        }
                    }
                }
            }
        }

        return true;
    }

    boolean mark(int x, int y) {
        // if invalid location
        if (x < 0 || x >= board.length ||
                y < 0 || y >= board[x].length ||
                board[x][y].isVisible()) {
            return false;
        }

        board[x][y].setMark(true);

        return true;
    }

    boolean unmark(int x, int y) {
        // if invalid location
        if (x < 0 || x >= board.length ||
                y < 0 || y >= board[x].length ||
                !board[x][y].getMark() ||
                board[x][y].isVisible()) {
            return false;
        }

        board[x][y].setMark(false);

        return true;
    }

    /**
     * Returns true if the index is a mine
     * @param x x coord
     * @param y y coord
     * @return true if index is mine
     */
    boolean isMine(int x, int y) {
        return board[x][y].getType() == Tile.TileType.mine;
    }

    /**
     * Returns true if the board has no more free tiles
     * @return boolean for board has no more free tiles
     */
    boolean isFull() {
        for (Tile[] col : board) {
            for (Tile t : col) {
                if (!t.isVisible()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Draws the board to the console
     */
    void draw() {

        // print column numbers
        System.out.print("   ");
        for (int i = 0; i < board.length; i++) {
            System.out.print((i+1) + " ");
        }
        System.out.println();

        for (int i = 0; i < board.length; i++) {

            // print row numbers
            System.out.print((i+1));
            if ((i+1)/10>0) System.out.print(" ");
            else System.out.print("  ");

            for (int j = 0; j < board[i].length; j++) {

                //print tile values
                System.out.print(board[i][j].toString() + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    /**
     * Initialise board with a random set of mines and squares
     */
    void initRandBoard(int proportion) {
        Random rand = new Random();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int r = rand.nextInt(proportion+1);
                if (r == 0) {
                    board[i][j] = new Mine();
                } else {
                    board[i][j] = new Square();
                }
            }
        }

        setSquareValues();
    }

    /**
     * Sets the values for each square tile on the board
     * according to the number of surrounding square tiles
     */
    private void setSquareValues() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getType() == Tile.TileType.square) {
                    ((Square) board[i][j]).setValue(getValue(i, j));
                }
            }
        }
    }

    /**
     * Gets the value for a tile
     * according to the number of surrounding square tiles
     * @param x x index
     * @param y y index
     * @return tile value
     */
    private int getValue(int x, int y) {
        int value = 0;

        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <=1; j++) {
                if (!(i == 0 && j == 0)) {
                    if (x+i >= 0 && x+i < board.length &&
                            y+j >= 0 && y+j < board[x+i].length) {
                        value += board[x + i][y + j].getType() == Tile.TileType.mine ? 1 : 0;
                    }
                }
            }
        }

        return value;
    }
}
