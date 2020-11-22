package it.unicam.cs.pa.jlife105381.Controller;

import it.unicam.cs.pa.jlife105381.Model.*;
import java.util.ArrayList;

/**
 * interfaccia che viene implementata alla classe che ha il compito di gestire Game Of Life
 */
public interface InterfaceGameOfLifeController {

    /**
     * metodo per controllare se la lista delle partite e' vuota
     * @return
     */
    boolean isEmpty();

    /**
     * metodo per inserire una nuova partita
     * @param id
     * @param board
     * @param sleep
     */
    void addGameOfLife(String id , InterfaceBoard board , int sleep );

    /**
     * metodo per ottenere la lista delle partite
     * @return
     */
    ArrayList<InterfaceGameOfLife> getListGameOfLife();

    /**
     * metodo per controllare il valore del paramentro id ed eventuali errori
     * @param id
     * @return
     */
    String checkId(String id);

    /**
     * metodo per controllare il valore del parametro sleep ed eventuali errori
     * @param sleep
     * @return
     */
    int checkSleep(String sleep);

    /**
     * metodo per controllare se una data colonna o riga appartiene alla matrice
     * @param columnsOrRows
     * @return
     */
    int checkColumnOrRow(String columnsOrRows);

    /**
     * metodo per controllare l'indice di una partite ed eventuali errori
     * @param index
     * @return
     */
    int checkIndexOfGame(String index);

    /**
     * metodo per modificare il valore di una matrice
     * @param rows
     * @param columns
     * @param gameOfLife
     */
    void changeValueBoard(int rows, int columns,InterfaceGameOfLife gameOfLife);

    /**
     * metodo per ottenere una lista in string di tutter le partite
     * @return
     */
    ArrayList<String> listGameOfLifeToString();

    /**
     * metodo per controllare se l'id di una partita e ne ritorna l'eventuale partita
     * @param id
     * @return
     */
    InterfaceGameOfLife gameExist(String id);

    /**
     * metodo per caricare la partita selizionata
     * @param indice
     */
    void loadGame(int indice);

    /**
     * metodo per eseguire uno step della partita selezionata
     * @param gameOfLife
     */
    void nextStep(InterfaceGameOfLife gameOfLife);

    /**
     * metodo per rimuovere una partita dato il suo indice
     * @param index
     */
    void removeGameOfLifeIndex(int index);

    /**
     * metodo per rimuovere una partita dato il suo id
     * @param id
     */
    void removeGameOfLifeId(String id);

    /**
     * metodo per scrivere i dati salvati
     */
    void writeData();

    /**
     * metodo per ottenre la dimensione delle colonne della board
     * @return
     */
    int getColumns();
    /**
     * metodo per ottenre la dimensione delle righe della board
     * @return
     */
    int getRows();

    /**
     * metodo per rimuovere qualsiasi dato salvato
     */
    void removeAll();
}
