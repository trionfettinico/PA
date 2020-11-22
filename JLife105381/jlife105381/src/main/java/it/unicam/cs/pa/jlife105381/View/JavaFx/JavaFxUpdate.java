package it.unicam.cs.pa.jlife105381.View.JavaFx;

import it.unicam.cs.pa.jlife105381.Controller.DrawBoard;
import it.unicam.cs.pa.jlife105381.Controller.InterfaceGameOfLifeController;
import it.unicam.cs.pa.jlife105381.Log;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class JavaFxUpdate extends Parent {

    private static InterfaceGameOfLifeController gameOfLifeController;

    private static Log log;

    /**
     * choiceBox contenete gli id delle partite presenti
     */
    private static ChoiceBox<String> choiceBoxId;
    /**
     * bottono per la modifica di una partita
     */
    private Button btnUpdateGame;

    private Label lblLifeCycle;

    private Label lblSleep;
    /**
     * label per visualizzare la modifica di una partita
     */
    private Label lblUpdate;

    private DrawBoard drawBoard;

    private Button btnLoadGame;

    /**
     * metodo costruttore di JavaFxUpdate
     * inizializzo tutti gli oggetti e le varibili
     * @param logger
     * @param interfaceGameOfLifeController
     */
    public JavaFxUpdate(Log logger, InterfaceGameOfLifeController interfaceGameOfLifeController) {
        log = logger;
        gameOfLifeController = interfaceGameOfLifeController;
        init();
        pos();
        createEvent();
        drawBoard.draw();
    }

    /**
     * inizializzo le variabili
     */
    private void init(){
        choiceBoxId = new ChoiceBox<>();
        lblLifeCycle = new Label();
        lblLifeCycle.setVisible(false);
        lblSleep = new Label();
        lblSleep.setVisible(false);
        lblUpdate = new Label();
        lblUpdate.setVisible(false);
        lblUpdate.setTextFill(Color.RED);
        btnLoadGame = new Button("load");
        btnLoadGame.setOnAction(event -> loadGame());
        btnUpdateGame = new Button("save");
        btnUpdateGame.setVisible(false);
        btnUpdateGame.setOnAction(event -> updateGame());
        drawBoard = new DrawBoard(log,gameOfLifeController);
        drawBoard.initizialize();
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
        hBox.getChildren().addAll(choiceBoxId, btnLoadGame, btnUpdateGame, lblUpdate);
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(drawBoard.getCanvas());
        vBox.getChildren().add(lblLifeCycle);
        vBox.getChildren().add(lblSleep);
        initChoiceBox();
        this.getChildren().addAll(vBox);
    }

    private void createEvent(){
        drawBoard.setOnMousePressed();
        eventChoiceBox();
    }

    /**
     * carico gli id presenti nella choiceBox
     */
    private void initChoiceBox(){
        for (int i = 0; i < gameOfLifeController.getListGameOfLife().size(); i++) {
            choiceBoxId.getItems().add(gameOfLifeController.getListGameOfLife().get(i).getId());
        }
    }
    /**
     * creo un evento per quando clicco nella choiceBox
     */
    private void eventChoiceBox(){
        choiceBoxId.setOnMouseClicked(event -> {
            btnUpdateGame.setVisible(false);
            drawBoard.initizialize();
            drawBoard.draw();
            lblUpdate.setText("premi load");
            lblUpdate.setVisible(true);
        });
    }

    /**
     * metodo per caricare la partita selezionata
     */
    private void loadGame(){
        if (!(choiceBoxId.getValue() == null)) {
            lblUpdate.setVisible(true);
            lblUpdate.setText("seleziona le celle da modificare");
            lblLifeCycle.setVisible(true);
            btnUpdateGame.setVisible(true);
            lblSleep.setVisible(true);
            drawBoard.setBoard(choiceBoxId.getValue());
            lblLifeCycle.setText("ciclo di vita numero : " + gameOfLifeController.gameExist(choiceBoxId.getValue()).getLifeCycle());
            lblSleep.setText("tempo tra ogni ciclo di vita  : " + gameOfLifeController.gameExist(choiceBoxId.getValue()).getSleep() + " secondi");
            drawBoard.draw();
        }
    }

    /**
     * metodo per la modifica della partita salvata
     */
    private void updateGame(){
        gameOfLifeController.gameExist(choiceBoxId.getValue()).setBoard(drawBoard.getBoard());
        lblUpdate.setText("partita modificata");
        btnUpdateGame.setVisible(false);
    }
}
