package it.unicam.cs.pa.jlife105381.Model;

/**
 * interfaccia che viene estesa ad una classe che identifica la struttura di una partita Game Of Life
 * e ne identifica i metodi
 */
public interface InterfaceGameOfLife {

    /**
     * metodo che ritorna la Board
     * @return
     */
    InterfaceBoard getBoard();

    /**
     * metodo per impostare una board
     * @param board
     */
    void setBoard(InterfaceBoard board);

    /**
     * metodo che ritorna il parametro sleep
     * @return
     */
    int getSleep();

    /**
     * metodo per impostare il paremetro sleep
     * @param sleep
     */
    void setSleep(int sleep);

    /**
     * metodo che ritorna l'id di una partita
     */
    String getId();

    /**
     * metodo per impostare l'id di una partita
     * @param id
     */
    void setId(String id);

    /**
     * metodo che ritorna il paramtro lifeCycle
     * @return
     */
    int getLifeCycle();

    /**
     * metodo per impostare il paramentro lifeCycle
     * @param lifeCycle
     */
    void setLifeCycle(int lifeCycle);

    /**
     * metodo per incrementare di 1 il parametro lifeCycle
     */
    void upgradeLifeCycle();

}
