package it.unicam.cs.pa.jlife105381.View.JavaFx;

import it.unicam.cs.pa.jlife105381.Controller.DrawBoard;
import it.unicam.cs.pa.jlife105381.Controller.InterfaceGameOfLifeController;
import it.unicam.cs.pa.jlife105381.Log;
import it.unicam.cs.pa.jlife105381.Model.InterfaceBoard;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class JavaFxInsert extends Parent {

    private static InterfaceGameOfLifeController gameOfLifeController;

    private static Log log;
    /**
     * input del id di una nuova partita
     */
    private TextField tbxId;
    /**
     * input del parametro sleep di una nuova partita
     */
    private TextField tbxSleep;

    private Label lblId;

    private Label lblSleep;

    private Label lblInfo;
    /**
     * label utilizzata per visualizzare eventuali errori
     */
    private Label lblError;
    /**
     * variabile utilizzata per scrivere a video una tabella
     */
    private DrawBoard drawBoard;
    /**
     * bottono utilizzato per l'inserimento di una partita
     */
    private Button btnInsertGame;

    /**
     * metodo costruttore di JavaFxInsert
     * inizializzo tutti gli oggetti e le variabili
     * @param logger
     * @param interfaceGameOfLifeController
     */
    public JavaFxInsert(Log logger, InterfaceGameOfLifeController interfaceGameOfLifeController) {
        log = logger;
        gameOfLifeController = interfaceGameOfLifeController;
        initVar();
        pos();
        /**
         * creo l'evento per quando il bottone insertGame viene premuto
         */
        btnInsertGame.setOnAction(event -> insertGame());
        /**
         * creo l'evento per quando una cella viene premuta
         */
        drawBoard.setOnMousePressed();
        /**
         * richiamo il metodo per disegnare la board
         */
        drawBoard.draw();
    }

    /**
     * inizializzo le varie variabili
     */
    private void initVar(){
        lblSleep = new Label("tempo di riposo");
        lblId = new Label("id nuova partita");
        lblInfo = new Label("selezionare le celle della matrice");
        lblError = new Label();
        lblError.setVisible(false);
        lblError.setTextFill(Color.RED);
        tbxId = new TextField();
        tbxSleep = new TextField();
        btnInsertGame = new Button("save");
        drawBoard = new DrawBoard(log, gameOfLifeController);
    }

    /**
     * posiziono i vari elementi in vBox e hBox per metterli in ordine
     * nell'interfaccia grafica
     */
    private void pos(){
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5, 15, 0, 15));
        vBox.setSpacing(10);
        hBox.setPadding(new Insets(5, 0, 0, 0));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(lblId, tbxId);
        vBox.getChildren().add(hBox);
        hBox = new HBox();
        hBox.setPadding(new Insets(5, 0, 0, 0));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(lblSleep, tbxSleep);
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(lblInfo);
        vBox.getChildren().add(drawBoard.getCanvas());
        vBox.getChildren().addAll(btnInsertGame, lblError);
        this.getChildren().addAll(vBox);
    }

    /**
     * metodo richiamato dal tasto btnInsertGame
     * per l'inserimento di una nuova partita
     * che gestisce i vari errori che si possono ottenere
     * mostrando un messaggio di errore
     */
    private void insertGame() {
        try {
            String id = gameOfLifeController.checkId(tbxId.getText());
            int sleep = gameOfLifeController.checkSleep(tbxSleep.getText());
            InterfaceBoard board = drawBoard.getBoard();
            gameOfLifeController.addGameOfLife(id, board, sleep);
            lblError.setText("partita inserita");
            reset();
        } catch (NullPointerException e) {
            lblError.setText("errore id vuoto");
            lblError.setVisible(true);
            log.logger.severe(e.getMessage());
        } catch (NumberFormatException e) {
            lblError.setText("tempo di riposo deve essere un valore intero");
            lblError.setVisible(true);
            log.logger.severe(e.getMessage());
        } catch (ArithmeticException e) {
            lblError.setText("tempo di riposo deve essere compreso tra 1 e 60");
            lblError.setVisible(true);
            log.logger.severe(e.getMessage());
        } catch (IllegalArgumentException e) {
            lblError.setText("id presente");
            lblError.setVisible(true);
            log.logger.severe(e.getMessage());
        }
    }

    private void reset(){
        lblError.setVisible(true);
        tbxSleep.clear();
        tbxId.clear();
        drawBoard.initizialize();
        drawBoard.draw();
    }
}
