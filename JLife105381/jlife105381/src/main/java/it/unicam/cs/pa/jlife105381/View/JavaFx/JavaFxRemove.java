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

public class JavaFxRemove extends Parent {

    private static InterfaceGameOfLifeController gameOfLifeController;

    private final Log log;
    /**
     * choiceBox utilizzata per scegliere l'id della partita da rimuovere
     */
    private static ChoiceBox<String> choiceBoxId;
    /**
     * bottone utilizzato per la rimozione della partita
     */
    private Button btnRemoveGame;

    private  Label lblLifeCycle;

    private  Label lblSleep;
    /**
     * label che ci avverte se la partita e' stata rimossa
     */
    private  Label lblRemove;

    private  DrawBoard drawBoard;

    private Button btnLoadGame;

    /**
     * metodo costruttore di JavaFxRemove
     * inizializzo tutti gli oggetti e le varibili
     * @param logger
     * @param interfaceGameOfLifeController
     */
    public JavaFxRemove(Log logger , InterfaceGameOfLifeController interfaceGameOfLifeController) {
        gameOfLifeController = interfaceGameOfLifeController;
        log = logger;
        init();
        pos();
        initChoiceBox();
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
        lblRemove = new Label("partita rimossa");
        lblRemove.setVisible(false);
        lblRemove.setTextFill(Color.RED);
        drawBoard = new DrawBoard(log,gameOfLifeController);
        drawBoard.initizialize();
        btnRemoveGame = new Button("remove");
        btnRemoveGame.setVisible(false);
        btnLoadGame = new Button("load");
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
        hBox.getChildren().addAll(choiceBoxId, btnLoadGame, btnRemoveGame, lblRemove);
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(drawBoard.getCanvas());
        vBox.getChildren().add(lblLifeCycle);
        vBox.getChildren().add(lblSleep);
        this.getChildren().addAll(vBox);
    }

    /**
     * inizializzo gli id delle partite presenti nella choiceBox
     */
    private void initChoiceBox(){
        for (int i = 0; i < gameOfLifeController.getListGameOfLife().size(); i++) {
            choiceBoxId.getItems().add(gameOfLifeController.getListGameOfLife().get(i).getId());
        }
    }

    /**
     * creo gli eventi di cui ho bisogna
     */
    private void createEvent(){
        eventLoad();
        eventChoiceBox();
        eventRemove();
    }

    /**
     * creo un evento per quando premo il pulsante per rimuovere una partita
     */
    private void eventRemove(){
        btnRemoveGame.setOnAction(event -> {
            removeGame();
            lblRemove.setVisible(true);
        });
    }

    /**
     * creo un evento per quando premo il pulsante per caricare una partita
     */
    private void eventLoad(){
        btnLoadGame.setOnAction(event -> {
            if (!(choiceBoxId.getValue() == null)) {
                lblRemove.setVisible(false);
                lblLifeCycle.setVisible(true);
                lblSleep.setVisible(true);
                btnRemoveGame.setVisible(true);
                loadBoard();
            }
        });
    }

    /**
     * creo un evento per quando apro la choiceBox
     */
    private void eventChoiceBox(){
        choiceBoxId.setOnMouseClicked(event -> {
            btnRemoveGame.setVisible(false);
            drawBoard.initizialize();
            drawBoard.draw();
            lblRemove.setVisible(false);
        });
    }

    /**
     * metodo per caricare a video la board selezionata nella choiceBox
     */
    private void loadBoard() {
        drawBoard.setBoard(choiceBoxId.getValue());
        lblLifeCycle.setText("ciclo di vita numero : " + gameOfLifeController.gameExist(choiceBoxId.getValue()).getLifeCycle());
        lblSleep.setText("tempo tra ogni ciclo di vita  : " + gameOfLifeController.gameExist(choiceBoxId.getValue()).getSleep() + " secondi");
        drawBoard.draw();
    }

    /**
     * metodo per la rimozione della partita selezionata
     */
    private void removeGame() {
        gameOfLifeController.removeGameOfLifeId(choiceBoxId.getValue());
        drawBoard.initizialize();
        choiceBoxId.getItems().clear();
        initChoiceBox();
        drawBoard.draw();
        btnRemoveGame.setVisible(false);
        lblLifeCycle.setVisible(false);
        lblSleep.setVisible(false);
    }
}

