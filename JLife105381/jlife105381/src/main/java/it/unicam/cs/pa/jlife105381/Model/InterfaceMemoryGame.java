package it.unicam.cs.pa.jlife105381.Model;

import java.util.ArrayList;

/**
 * interfaccia che viene estesa alla classe che ha il compito di contenere tutte le partite
 * e ne identifica i metodi utilizzati
 */
public interface InterfaceMemoryGame {

    /**
     * metodo che ritorna un array contenete tutte le partite memorizzate
     * @return
     */
    ArrayList<InterfaceGameOfLife> getListGameOfLife();

    /**
     * metodo per l'inserimento di una una nuova partita nella lista delle partite
     * @param gameOfLife
     */
    void addGameOfLife(InterfaceGameOfLife gameOfLife);

    /**
     * metodo per la rimozione di una partita dato il suo indice
     * @param index
     */
    void removeGameOfLifeIndex(int index);

    /**
     * metodo per la rimozione di una partita dato il sui id
     * @param id
     */
    void removeGameOfLifeId(String id);


        /**
         * metodo che ritorna un Array di tutte le partite come stringhe
         * @return
         */
    ArrayList<String> listToString();

    static InterfaceMemoryGame newMemoryGame(){
        return new MemoryGame();
    }

}
