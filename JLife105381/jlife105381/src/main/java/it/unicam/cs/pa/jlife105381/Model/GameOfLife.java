package it.unicam.cs.pa.jlife105381.Model;

import java.io.Serializable;

/**
 * classe che identifica una singola partita
 */
public class GameOfLife implements InterfaceGameOfLife, Serializable {

    private InterfaceBoard board;

    private int sleep;

    private String id;

    private int lifeCycle;

    /**
     * metodo costruttore di una nuova partita
     * @param id
     * @param board
     * @param sleep
     */
    public GameOfLife(String id, InterfaceBoard board, int sleep) {
        setBoard(board);
        setSleep(sleep);
        setId(id);
        setLifeCycle(0);
    }

    /**
     * metodo di caricamento di una partita esistente
     * @param id
     * @param board
     * @param sleep
     * @param lifeCycle
     */
    public GameOfLife(String id, InterfaceBoard board, int sleep, int lifeCycle) {
        setBoard(board);
        setSleep(sleep);
        setId(id);
        setLifeCycle(lifeCycle);
    }

    @Override
    public InterfaceBoard getBoard() {
        return board;
    }

    @Override
    public void setBoard(InterfaceBoard board) {
        this.board = board;
    }

    @Override
    public int getSleep() {
        return sleep;
    }

    @Override
    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getLifeCycle() {
        return lifeCycle;
    }

    @Override
    public void setLifeCycle(int lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    /**
     * metodo che ha il compito di incrementare di 1 il valore lifeCycle
     */
    @Override
    public void upgradeLifeCycle() {
        setLifeCycle(getLifeCycle() + 1);
    }

    @Override
    public String toString() {
        return (getId() + "/" + getSleep() + "/" + getLifeCycle());
    }
}
