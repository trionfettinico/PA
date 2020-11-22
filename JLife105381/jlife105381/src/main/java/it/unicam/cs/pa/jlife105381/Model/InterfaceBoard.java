package it.unicam.cs.pa.jlife105381.Model;

/**
 * interfaccia che viene estesa alla Board di una partita e ne identifica i metodi
 */
public interface InterfaceBoard {

    /**
     * metodo che ritorna il valore della Board
     * @return
     */
    DeadOrAlive[][] getBoard();

    /**
     * metodo per ottenere la dimensione delle righe della Board
     * @return
     */
    int getRows();
    /**
     * metodo per ottenere la dimensione delle colonne della Board
     * @return
     */
    int getColumns();
    /**
     * metodo per cambiare di valore il contenuto di una cella della Board
     * @return
     */
    void changeValue(int rows, int columns);

    /**
     * metodo per ottenere il valore di una cella
     * @param rows
     * @param columns
     * @return
     */
    DeadOrAlive getValue(int rows, int columns);

    /**
     * metodo per controllare se una cella esiste
     * @param rows
     * @param columns
     * @return
     */
    int exist(int rows,int columns);

    /**
     * metodo per contare il numero delle celle vicene vive
     * @param rows
     * @param columns
     * @return
     */
    int countAliveNeighbours(int rows,int columns);

    /**
     * metodo per impostare una cella al valore DEAD
     * @param rows
     * @param columns
     */
    void setDead(int rows , int columns);
    /**
     * metodo per impostare una cella al valore ALIVE
     * @param rows
     * @param columns
     */
    void setAlive(int rows , int columns);

    static InterfaceBoard newBoard() {
        return new Board();
    }

    /**
     * metodo per controllare se una cellula e' viva
     * @param row
     * @param column
     * @return
     */
    boolean isAlive(int row,int column);

}
