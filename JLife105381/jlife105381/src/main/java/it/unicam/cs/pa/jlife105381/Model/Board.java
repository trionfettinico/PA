package it.unicam.cs.pa.jlife105381.Model;


import java.io.Serializable;

/**
 * classe che ha il compito di gestire il contenuto della board di una partita
 */
public class Board implements InterfaceBoard, Serializable {

    private static final int columns = 10;

    private static final int rows = 10;

    private final DeadOrAlive[][] board;

    public DeadOrAlive[][] getBoard() {
        return this.board;
    }

    /**
     * metodo costruttore che inizializza una nuva board vuota
     */
    public Board() {
        board = new DeadOrAlive[getRows()][getColumns()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                setDead(i, j);
            }
        }
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    /**
     * metodo che data riga e colonna imposta il contenuto della cella DEAD
     * @param rows
     * @param columns
     */
    @Override
    public void setDead(int rows, int columns) {
        getBoard()[rows][columns] = DeadOrAlive.DEAD;
    }

    /**
     * metodo che data riga e colonna imposta il contenuto della cella ALIVE
     * @param rows
     * @param columns
     */
    @Override
    public void setAlive(int rows, int columns) {
        getBoard()[rows][columns] = DeadOrAlive.ALIVE;
    }

    /**
     * metodo per controllare se una cellula e' viva
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isAlive(int row, int column) {
        return this.board[row][column].equals(DeadOrAlive.ALIVE);
    }

    /**
     * metodo che conta il numero delle celle adiacenti ad una impostate ALIVE
     * @param rows
     * @param columns
     * @return
     */
    @Override
    public int countAliveNeighbours(int rows, int columns) {
        int count = 0;
        count += exist(rows - 1, columns - 1);
        count += exist(rows, columns - 1);
        count += exist(rows + 1, columns - 1);
        count += exist(rows - 1, columns);
        count += exist(rows + 1, columns);
        count += exist(rows - 1, columns + 1);
        count += exist(rows, columns + 1);
        count += exist(rows + 1, columns + 1);
        return count;

    }

    /**
     * metodo utilizzato per impedire possibili errori dovuto all'utilizzo di celle inesistenti
     * @param rows
     * @param columns
     * @return
     */
    @Override
    public int exist(int rows, int columns) {
        if (rows < 0 || rows >= getRows())
            return 0;
        if (columns < 0 || columns >= getColumns())
            return 0;
        if (getBoard()[rows][columns] == DeadOrAlive.ALIVE)
            return 1;
        return 0;
    }

    /**
     * metodo che cambia lo stato di una cella
     * DEAD -> ALIVE
     * ALIVE -> DEAD
     * @param rows
     * @param columns
     */
    @Override
    public void changeValue(int rows, int columns) {
        if (board[rows][columns].equals(DeadOrAlive.ALIVE))
            this.board[rows][columns] = DeadOrAlive.DEAD;
        else
            this.board[rows][columns] = DeadOrAlive.ALIVE;
    }

    /**
     * metodo che ritorna il valore di una cella
     * @param rows
     * @param columns
     * @return
     */
    @Override
    public DeadOrAlive getValue(int rows, int columns) {
        return this.board[rows][columns];
    }

}
