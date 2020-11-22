package it.unicam.cs.pa.jlife105381.View;

/**
 * interfaccia che viene estesa alle diverse interfacce grafiche
 * ha il compito di implementare i metodi esenziali per il funzionamente dell'interfaccia grafica
 */
public interface InterfaceView {

    /**
     * metodo per l'avvio della interfaccia scelta
     */
    void start();

    /**
     * metodo per chiudwere i processi aperti
     */
    void stop();

    /**
     * metodo per il controllo dei paramentri e per l'inserimento
     */
    void insertGame();

    /**
     * metodo per il controllo dei parametri e per il caricamento di una partita
     */
    void loadGame();
    /**
     * metodo per il controllo dei parametri e per la modifica di una partita
     */
    void updateGame();
    /**
     * metodo per il controllo dei parametri e per la rimozione di una partita
     */
    void removeGame();

}
