package it.unicam.cs.pa.jlife105381.View.JavaFx;

import it.unicam.cs.pa.jlife105381.Controller.InterfaceGameOfLifeController;
import it.unicam.cs.pa.jlife105381.Log;
import it.unicam.cs.pa.jlife105381.View.InterfaceView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * classe principale che richiama il menu della gui
 * realizzato con l'ausilio di scene builder
 */
public class JavaFxView extends Application implements InterfaceView {

    /**
     * bottone utilizzato per richiamare l'inserimento di una nuova partita
      */
    @FXML
    private Button btnInsertNewGame;
    /**
     * bottone utilizzato per richiamare l'avvio di una partita
     */
    @FXML
    private Button btnLoadGame;
    /**
     * bottone utilizzato per richiamare la modifica di una partita
     */
    @FXML
    private Button btnUpdateGame;
    /**
     * bottone utilizzato per richiamare la rimozione di una partita
     */
    @FXML
    private Button btnRemoveGame;

    private static InterfaceGameOfLifeController gameOfLifeController;

    private static Log log;

    /**
     * metodo costruttore di JavaFxView
     *
     * @param logger
     * @param interfaceGameOfLifeController
     */
    public JavaFxView(Log logger, InterfaceGameOfLifeController interfaceGameOfLifeController) {
        gameOfLifeController = interfaceGameOfLifeController;
        log = logger;
    }

    /**
     * metodo costruttore vuoto richoesto per l'avvio della GUI
     */
    public JavaFxView() {
    }

    /**
     * metodo richoamata tramite l'interfaccia dalla classe App
     * che permette di caricare la GUI
     */
    @Override
    public void start() {
        launch();
    }

    /**
     * metodo per caricare la GUI tramite file FXML
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGameOfLife.fxml"));
            loader.setController(new JavaFxView(log, gameOfLifeController));
            primaryStage.setResizable(false);
            primaryStage.setTitle("JLife");
            primaryStage.setScene(new Scene(loader.load(), 417, 288));
            primaryStage.show();
        } catch (IOException e) {
            log.logger.severe(e.getMessage());
        }

    }

    /**
     * metodo richiamata al termine del programma per salvare i dati
     */
    @Override
    public void stop() {
        gameOfLifeController.writeData();
    }

    /**
     * creazione della GUI per il caricamento di una nuovo partita
     */
    @FXML
    @Override
    public void insertGame() {
        Stage stage = new Stage();
        stage.setTitle("new Game");
        stage.initModality(Modality.APPLICATION_MODAL);
        JavaFxInsert javaFxInsert = new JavaFxInsert(log,gameOfLifeController);
        stage.setScene(new Scene(javaFxInsert, 435, 580));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * creazione della GUI per l'avvio di una nuova partita
     */
    @FXML
    @Override
    public void loadGame() {
        Stage stage = new Stage();
        stage.setTitle("load Game");
        stage.initModality(Modality.APPLICATION_MODAL);
        JavaFxLoad javaFxLoad = new JavaFxLoad(log, gameOfLifeController);
        stage.setScene(new Scene(javaFxLoad, 440, 505));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * creazione della GUI per la modifica di una partita
     */
    @FXML
    @Override
    public void updateGame() {
        Stage stage = new Stage();
        stage.setTitle("update Game");
        stage.initModality(Modality.APPLICATION_MODAL);
        JavaFxUpdate javaFxUpdate = new JavaFxUpdate(log, gameOfLifeController);
        stage.setScene(new Scene(javaFxUpdate, 440, 505));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * creazione della GUI per la rimozione di una partita
     */
    @FXML
    @Override
    public void removeGame() {
        Stage stage = new Stage();
        stage.setTitle("remove Game");
        stage.initModality(Modality.APPLICATION_MODAL);
        JavaFxRemove javaFxRemove = new JavaFxRemove(log,gameOfLifeController);
        stage.setScene(new Scene(javaFxRemove, 440, 505));
        stage.setResizable(false);
        stage.show();
    }

}
