package it.unicam.cs.pa.jlife105381.View.Console;

import it.unicam.cs.pa.jlife105381.Controller.InterfaceGameOfLifeController;
import it.unicam.cs.pa.jlife105381.Log;
import it.unicam.cs.pa.jlife105381.Model.*;
import it.unicam.cs.pa.jlife105381.View.InterfaceView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * classe della interfaccia grafica a console
 */
public class ConsoleView implements InterfaceView {
    /**
     * gameOfLifeController ha il compito di gestrire il gioco
     */
    private final InterfaceGameOfLifeController gameOfLifeController;

    /**
     * log ha il compito di memorizzare gli errori generati
     */
    private final Log log;

    /**
     * reader ha il compito di aprire un canale per catturare l'immisione dei dati
     */
    private final BufferedReader reader;

    /**
     * stopRequested ha il compito di gestire il Thread
     */
    private boolean stopRequested = false;

    public ConsoleView(Log log, InterfaceGameOfLifeController gameOfLifeController) {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.gameOfLifeController = gameOfLifeController;
        this.log = log;
    }

    /**
     * metodo principale che viene richiamata da App
     */
    @Override
    public void start() {
        hello();
        System.out.println(gameOfLifeController.getListGameOfLife().size() + " : account presenti");
        while (true) {
            menu();
            String app = getInput("quale azione vuoi svolgere : ");
            switch (app) {
                case "1":
                    insertGame();
                    break;
                case "2":
                    loadGame();
                    break;
                case "3":
                    updateGame();
                    break;
                case "4":
                    removeGame();
                    break;
                case "0":
                    System.out.println("arrivederci");
                    return;
                default:
                    System.out.println("\nparametro non valido\n");
                    break;
            }
        }
    }

    /**
     * stampa visiva delle opzioni eseguibili
     */
    private void menu() {
        System.out.println("1) nuova partita ");
        if (!gameOfLifeController.isEmpty()) {
            System.out.println("2) carica partita salvata ");
            System.out.println("3) modifica partita ");
            System.out.println("4) rimuovi partita ");
        }
        System.out.println("0) exit");
    }

    /**
     * stampa del titolo
     */
    private void hello() {
        System.out.println("******************************");
        System.out.println("*        Game Of Life        *");
        System.out.println("******************************");
    }

    /**
     * metodo utilizzato per stampare una richiesta ed acquisire una risposta
     * andando ad eliminare la possibilita di ottenere un carattere vuoto che andrebbe a far
     * terminare il programma
     * @param message
     * @return
     */
    private String getInput(String message) {
        if (!message.equals(""))
            System.out.print(message);
        System.out.print("\n>");
        String app = "";
        System.out.flush();
        try {
            app = reader.readLine();
        } catch (IOException e) {
            log.logger.warning(e.getMessage());
            System.out.println("Errore");
        }
        return app;
    }

    /**
     * metodo per ottenere i dati utili per l'inserimento di una nuova partita
     */
    @Override
    public void insertGame() {
        String id = checkIdGame();
        int sleep = checkSleep();
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife(id, board, sleep);
        changeValueOfBoard(gameOfLifeController.gameExist(id));
    }

    /**
     * metodo per gestire eventuali errori dovuti
     * da un valore errato per il parametro Id
     * @return
     */
    private String checkIdGame() {
        while (true) {
            try {
                String app = getInput("nome della partita : ");
                return gameOfLifeController.checkId(app);
            } catch (IllegalArgumentException e) {
                System.out.println("\nnome gia presente\n");
                log.logger.severe(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("\nparametro non valido\n");
                log.logger.severe(e.getMessage());
            }
        }
    }

    /**
     * metodo per gestire eventuali errori dovuti
     * da un valore errato per il parametro Sleep
     * @return
     */
    private int checkSleep() {
        while (true) {
            try {
                String app = getInput("tempo di riposo tra ogni ciclo di vita in secondi : [compreso tra 1 e 60]");
                return gameOfLifeController.checkSleep(app);
            } catch (NumberFormatException e) {
                System.out.println("\nparametro non valido\n");
                log.logger.severe(e.getMessage());
            }catch (ArithmeticException e){
                System.out.println("\nil parametro deve essere compreso tra 1 e 60\n");
                log.logger.severe(e.getMessage());
            }
        }
    }

    /**
     * metodo per la stampa a video di una Board da una data partita
     * @param gameOfLife
     */
    private void printBoard(InterfaceGameOfLife gameOfLife) {
        System.out.println("  |1|2|3|4|5|6|7|8|9|10");
        for (int i = 0; i < gameOfLife.getBoard().getRows(); i++) {
            if (i == 9)
                System.out.print(i + 1);
            else
                System.out.print((i + 1) + " ");
            for (int j = 0; j < gameOfLife.getBoard().getColumns(); j++) {
                if (gameOfLife.getBoard().isAlive(i,j))
                    System.out.print("|#");
                else
                    System.out.print("| ");
            }
            System.out.println();
        }
        System.out.println("ciclo di vita numero : " + gameOfLife.getLifeCycle());
    }

    /**
     * metodo grafico per il cambiamento di un dato di un Board di una data parita
     * @param gameOfLife
     */
    private void changeValueOfBoard(InterfaceGameOfLife gameOfLife) {
        while (true) {
            printBoard(gameOfLife);
            String choice = getInput("[premere invio per modificare , digita exit per uscire]");
            if (choice.equals("exit"))
                break;
            int columns = checkColumnsOrRows("inserisci colonna del valore da modificare : ");
            int rows = checkColumnsOrRows("inserisci riga del valore da modificare : ");
            gameOfLifeController.changeValueBoard(rows, columns, gameOfLife);
            System.out.println("valore modificato");
        }
    }

    /**
     * metodo per gestiore eventuali errori dovuto a parametri non validi
     * nel campo Column/Row
     * @param question
     * @return
     */
    private int checkColumnsOrRows(String question) {
        while (true) {
            try {
                String app = getInput(question);
                return gameOfLifeController.checkColumnOrRow(app);
            } catch (NumberFormatException e) {
                System.out.println("\nparametro colonna/riga non valido\n");
                log.logger.severe(e.getMessage());
            }
        }
    }

    /**
     * metodo per l'avvio di una partita tramite l'utilizzo di thread
     * in modo da avere una continua stampa dei dati fino a quando non si riceve
     * un paramentro in input
     */
    @Override
    public void loadGame() {
        printListOfGame();
        int indice = choiceGame("inserisce il codice della partita da avviare : ");
        Thread thread = new Thread(() -> {
            while (!isStopRequested()) {
                printBoard(gameOfLifeController.getListGameOfLife().get(indice));
                try {
                    Thread.sleep(gameOfLifeController.getListGameOfLife().get(indice).getSleep() * 1000);
                    if (!isStopRequested())
                        gameOfLifeController.loadGame(indice);
                } catch (InterruptedException e) {
                    log.logger.severe(e.getMessage());
                }
            }
            requestRestart();
        });
        thread.start();
        while (true) {
            try {
                if (reader.readLine().equals("")) {
                    requestStop();
                    break;
                }
            } catch (IOException e) {
                log.logger.severe(e.getMessage());
            }
        }
    }

    /**
     * metodo utilizzato per controllare che un
     * @param question
     * @return
     */
    private int choiceGame(String question) {
        while (true) {
            try {
                String app = getInput(question);
                return gameOfLifeController.checkIndexOfGame(app);
            } catch (Exception e) {
                System.out.println("\nparametro non valido\n");
                log.logger.severe(e.getMessage());
            }
        }
    }

    /**
     * metodo per la stampa a video dei atributi di tutte le partite\
     */
    private void printListOfGame() {
        int i = 1;
        for (Object game : gameOfLifeController.listGameOfLifeToString()) {
            String[] app = game.toString().split("/");
            System.out.println(i + ")");
            System.out.println(" id = " + app[0]);
            System.out.println(" pausa tra ogni ciclo di vita = " + app[1] + " sec");
            System.out.println(" cicli di vita = " + app[2]);
            i++;
        }
    }

    /**
     * metodo per aggiornare la scelta della partita da aggiornare
     */
    @Override
    public void updateGame() {
        printListOfGame();
        int choice = choiceGame("inserisci il codice del gioco da modificare");
        changeValueOfBoard(gameOfLifeController.getListGameOfLife().get(choice));
        gameOfLifeController.writeData();
    }

    /**
     * metodo per la scelta della partita da rimuovere
     */
    @Override
    public void removeGame() {
        printListOfGame();
        int choice = choiceGame("inserisci il codice del gioco da eliminare");
        gameOfLifeController.removeGameOfLifeIndex(choice);
        System.out.println("partita rimossa con successo");
    }

    /**
     * metodo richiamata da App per averte una corretta chiusura dei canali utilizzati
     */
    @Override
    public void stop() {
        try {
            reader.close();
            gameOfLifeController.writeData();
        } catch (IOException e) {
            log.logger.severe(e.getMessage());
        }
    }

    /**
     * metodo richiamato per l'interruzione del thread
     */
    private synchronized void requestStop() {
        this.stopRequested = true;
    }

    /**
     * metodo richiamato per resettare il thread
     */
    private synchronized void requestRestart() {
        this.stopRequested = false;
    }

    /**
     * metodo utilizzato per controllare se si deve uscire dal thread o no
     * @return
     */
    private synchronized boolean isStopRequested() {
        return this.stopRequested;
    }
}
