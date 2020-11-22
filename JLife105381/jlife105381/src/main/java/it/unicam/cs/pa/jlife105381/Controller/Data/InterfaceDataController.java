package it.unicam.cs.pa.jlife105381.Controller.Data;


import it.unicam.cs.pa.jlife105381.Model.InterfaceMemoryGame;

import java.util.ArrayList;

/**
 * interfaccia estesa dalle classi che hanno il compito di memorizzare/leggere dati
 */
public interface InterfaceDataController {
    /**
     * metodo per la scrittura dei dati
     * @param data
     * @return
     */
    boolean write(InterfaceMemoryGame data);

    /**
     * metodo per la lettura dei dati
     * @return
     */
    InterfaceMemoryGame read();

}
