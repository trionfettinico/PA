package it.unicam.cs.pa.jlife105381.Controller;

import it.unicam.cs.pa.jlife105381.Controller.Data.InterfaceDataController;
import it.unicam.cs.pa.jlife105381.Model.*;

import java.util.ArrayList;

/**
 * classe controllore per la gestione di Game Of Life
 */
public class GameOfLifeController implements InterfaceGameOfLifeController {

    private static InterfaceMemoryGame memoryGame = InterfaceMemoryGame.newMemoryGame();

    private final InterfaceDataController data;

    /**
     * metodo per l'iniziazzione dei dati e che richiama la lettura dei dati
     * @param data
     */
    public GameOfLifeController(InterfaceDataController data) {
        this.data = data;
        memoryGame = data.read();
        if (memoryGame == null)
            memoryGame = new MemoryGame();
    }


    /**
     * metodo che ritorna true se la lista delle partite e' vuota altrimenti false
     * @return
     */
    @Override
    public boolean isEmpty() {
        return memoryGame.getListGameOfLife().isEmpty();
    }


    @Override
    public ArrayList<InterfaceGameOfLife> getListGameOfLife() {
        return memoryGame.getListGameOfLife();
    }

    /**
     * metodo che controlla il giusto formato dell'id e se e' gia presente
     * e nel caso che sia presente o che ci sia un errore , ritorna un eccezione
     * @param id
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    @Override
    public String checkId(String id) throws NullPointerException, IllegalArgumentException {
        if (id.equals(""))
            throw new NullPointerException();
        if (!(gameExist(id) == null))
            throw new IllegalArgumentException();
        return id;
    }

    /**
     * metodo che controlla che il valore sleep sia corretto e che sia compreso in un certo range
     * e nel caso contrario ritorna la giusta eccezzione
     * @param sleep
     * @return
     * @throws NumberFormatException
     * @throws ArithmeticException
     */
    @Override
    public int checkSleep(String sleep) throws NumberFormatException,ArithmeticException {
        int sleepInt = Integer.parseInt(sleep);
        if (sleepInt < 1 || sleepInt > 60)
            throw new ArithmeticException();
        return sleepInt;
    }

    /**
     * metodo che controlla che il valore inserito sia realmente compreso nella dimensione della colonna/riga
     * @param columnsOrRows
     * @return
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    @Override
    public int checkColumnOrRow(String columnsOrRows) throws NumberFormatException {
        int columnsOrRowsInt = Integer.parseInt(columnsOrRows.trim());
        if (columnsOrRowsInt <= 0 || columnsOrRowsInt > InterfaceBoard.newBoard().getColumns())
            throw new NumberFormatException();
        else
            return columnsOrRowsInt - 1;
    }

    @Override
    public int checkIndexOfGame(String index) throws NumberFormatException ,NullPointerException{
        int indice = Integer.parseInt(index.trim());
        if (indice < 1 || indice > memoryGame.getListGameOfLife().size() + 1)
            throw new NullPointerException();
        else
            return indice - 1;
    }

    /**
     * metodo che ha il compito di modificafe il contenuto di una data cella di una data partita
     * @param rows
     * @param columns
     * @param gameOfLife
     */
    @Override
    public void changeValueBoard(int rows, int columns, InterfaceGameOfLife gameOfLife) {
        memoryGame.getListGameOfLife().get(memoryGame.getListGameOfLife().indexOf(gameOfLife)).getBoard().changeValue(rows, columns);
        writeData();
    }

    /**
     * metodo che ha il compito di ritornare un array contenente i dati di ogni partita come stringa
     * @return
     */
    @Override
    public ArrayList<String> listGameOfLifeToString() {
        return memoryGame.listToString();
    }

    /**
     * metodo che ha il compito di controllare se una partita ha un dato id e ritornare tale partita
     * @param id
     * @return
     */
    @Override
    public InterfaceGameOfLife gameExist(String id) {
        for (InterfaceGameOfLife game : memoryGame.getListGameOfLife()) {
            if (game.getId().toLowerCase().equals(id.toLowerCase()))
                return game;
        }
        return null;
    }

    /**
     * metodo che ha il compito di ricercare e una partita da un data la sua posizione e di eseguirne il passo successivo
     * @param indice
     */
    @Override
    public void loadGame(int indice) {
        nextStep(getListGameOfLife().get(indice));
    }

    /**
     * metodo che permette di eseguire una evoluzione delle cellule
     * @param gameOfLife
     */
    @Override
    public void nextStep(InterfaceGameOfLife gameOfLife) {
        InterfaceBoard newBoard = InterfaceBoard.newBoard();
        for (int i = 0; i < gameOfLife.getBoard().getRows(); i++) {
            for (int j = 0; j < gameOfLife.getBoard().getColumns(); j++) {
                int aliveNeighbours = gameOfLife.getBoard().countAliveNeighbours(i, j);
                if (gameOfLife.getBoard().isAlive(i,j))
                    if (aliveNeighbours < 2 || aliveNeighbours > 3)
                        newBoard.setDead(i, j);
                    else
                        newBoard.setAlive(i, j);
                else if (aliveNeighbours == 3)
                    newBoard.setAlive(i, j);
                else
                    newBoard.setDead(i, j);
            }
        }
        gameOfLife.upgradeLifeCycle();
        gameOfLife.setBoard(newBoard);
        writeData();
    }

    /**
     * metodo per l'inserimento di una nuvoa partita
     * @param id
     * @param board
     * @param sleep
     */
    @Override
    public void addGameOfLife(String id, InterfaceBoard board, int sleep) {
        memoryGame.addGameOfLife(new GameOfLife(id, board, sleep));
        writeData();
    }

    /**
     * metodo per la rimozione di una partita dato il suo indice nella list delle partite
     * @param index
     */
    @Override
    public void removeGameOfLifeIndex(int index) {
        memoryGame.removeGameOfLifeIndex(index);
        writeData();
    }

    /**
     * metodo per la rimozione di una partita dato il suo id
     * @param id
     */
    public void removeGameOfLifeId(String id){
        memoryGame.removeGameOfLifeId(id);
        writeData();
    }

    /**
     * metodo che permette di scrivere i dati
     */
    @Override
    public void writeData() {
        data.write(memoryGame);
    }

    @Override
    public int getColumns() {
        InterfaceBoard board = InterfaceBoard.newBoard();
        return board.getColumns();
    }

    @Override
    public int getRows() {
        InterfaceBoard board = InterfaceBoard.newBoard();
        return board.getRows();
    }

    /**
     * metodo per rimuovere qualsiasi dato
     */
    @Override
    public void removeAll() {
        memoryGame = InterfaceMemoryGame.newMemoryGame();
        writeData();
    }

}
