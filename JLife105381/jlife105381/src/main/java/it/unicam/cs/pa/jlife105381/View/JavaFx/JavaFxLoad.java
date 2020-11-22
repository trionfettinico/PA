package it.unicam.cs.pa.jlife105381.View.JavaFx;

import it.unicam.cs.pa.jlife105381.Controller.DrawBoard;
import it.unicam.cs.pa.jlife105381.Controller.InterfaceGameOfLifeController;
import it.unicam.cs.pa.jlife105381.Log;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.event.ChangeListener;


public class JavaFxLoad extends Parent {

    private static InterfaceGameOfLifeController gameOfLifeController;

    private static Log log;

    /**
     * variabile creata per contrallare se un thread e' in esecuzione
     */
    private boolean run = false;

    /**
     * variabile creata per gestire l'ingresso ad una parte di codice
     * ad un thread
     */
    private boolean request = false;

    /**
     * utilizzato per selezionare l'id della partita da utilizzare
     */
    private static ChoiceBox<String> choiceBoxId;

    /**
     * bottone utilizzato per eseguire un solo step nella partita
     */
    private Button btnNextStep;
    /**
     * bottono utilizzato per avviare la partita
     */
    private Button btnStartGame;

    private Button btnLoadGame;

    /**
     * label utilizzata per visualizzare il numero del ciclo di vita della partita
     */
    private Label lblLifeCycle;

    private Label lblSleep;
    /**
     * label utilizzata per visualizzare eventuali messaggi durante la partita
     */
    private  Label lblInfo;

    private DrawBoard drawBoard;

    /**
     * metodo costruttore di JavaFxLoad
     * inizializzo tutti gli oggetti e le variabili
     * @param logger
     * @param interfaceGameOfLifeController
     */
    public JavaFxLoad(Log logger, InterfaceGameOfLifeController interfaceGameOfLifeController){

        log = logger;
        gameOfLifeController = interfaceGameOfLifeController;
        choiceBoxId = new ChoiceBox<>();
        init();
        initChoiceBox();
        pos();
        createEvent();
        /**
         * richiamo il metodo di drawBoard per disegnare la matrice (vuota)
         */
        drawBoard.draw();
    }

    /**
     * inizializzazione delle varibili
     */
    private void init(){
        lblInfo = new Label("premi invio");
        lblInfo.setTextFill(Color.RED);
        lblInfo.setVisible(false);
        lblLifeCycle = new Label();
        lblLifeCycle.setVisible(false);
        lblSleep = new Label();
        lblSleep.setVisible(false);
        btnStartGame = new Button("start");
        btnStartGame.setVisible(false);
        btnNextStep = new Button("next step");
        btnNextStep.setVisible(false);
        btnLoadGame = new Button("load");
        drawBoard = new DrawBoard(log,gameOfLifeController);
        drawBoard.initizialize();
    }

    /**
     * inizializzo gli id delle partite nella choiceBox
     */
    private void initChoiceBox(){
        for (int i = 0; i < gameOfLifeController.getListGameOfLife().size(); i++)
            choiceBoxId.getItems().add(gameOfLifeController.getListGameOfLife().get(i).getId());
    }

    /**
     * posiziono i vari elementi in vBox e hBox per metterli in ordine
     * nell'interfaccia grafica
     */
    private void pos(){
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5,15,0,15));
        vBox.setSpacing(10);
        hBox.setPadding(new Insets(5,0,0,0));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(choiceBoxId, btnLoadGame, btnNextStep,btnStartGame,lblInfo);
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(drawBoard.getCanvas());
        vBox.getChildren().add(lblLifeCycle);
        vBox.getChildren().add(lblSleep);
        this.getChildren().addAll(vBox);
    }

    /**
     * creo nuovi eventi
     */
    private void createEvent(){
        loadEvent();
        stepEvent();
        startEvent();
        choiceBoxEvent();
    }

    /**
     * creo l'evento per il caricamento di una partita
     */
    private void loadEvent(){
        btnLoadGame.setOnAction(event -> {
            if(!(choiceBoxId.getValue()==null)){
                lblInfo.setVisible(false);
                lblLifeCycle.setVisible(true);
                lblSleep.setVisible(true);
                btnStartGame.setVisible(true);
                btnNextStep.setVisible(true);
                loadBoard();
            }
        });
    }

    /**
     * creo l'evento per eseguire uno step della partita
     */
    private void stepEvent(){
        btnNextStep.setOnAction(event -> {
            if(!(choiceBoxId.getValue()==null)){
                nextStep();
            }
        });
    }

    /**
     * creo l'evento per l'avvio della partita
     */
    private void startEvent(){
        btnStartGame.setOnAction(event -> {
            if(!isRun()){
                btnStartGame.setText("stop");
                btnLoadGame.setVisible(false);
                btnNextStep.setVisible(false);
                Thread tread = new Thread(this::startGame);
                tread.start();
                run = false;
            }else {
                btnNextStep.setVisible(true);
                btnLoadGame.setVisible(true);
                requestStop();
                btnStartGame.setText("start");
                lblInfo.setVisible(false);
            }
        });
    }

    /**
     * creo un evento per l'apertura della choiceBox
     */
    private void choiceBoxEvent(){
        choiceBoxId.setOnMouseClicked(event -> {
            lblInfo.setText("premi load");
            lblInfo.setVisible(true);
            btnNextStep.setVisible(false);
            btnStartGame.setVisible(false);
            drawBoard.initizialize();
            drawBoard.draw();
        });
    }

    /**
     * metodo per richiamato per l'avvio di una partita
     * che crea un thread che rimane in esecuzione fino a
     * quando la variabile request non viene modificata
     */
    public void startGame() {
        run = true;
        while (!isStopRequested()){
            try {
                nextStep();
                Thread.sleep(gameOfLifeController.gameExist(choiceBoxId.getValue()).getSleep()*1000);
            } catch (Exception e) {
                requestStop();
                log.logger.severe(e.getMessage());
            }
        }
        requestRestart();
        run = false;
    }

    /**
     * metodo richiamato per la stampa a video della board
     * Platform.runLater -> viene utilizzato per il fatto che un secondo thread non puo cambiare direttamente l'interfaccia grafica
     */
    private void loadBoard(){
        drawBoard.setBoard(choiceBoxId.getValue());
        drawBoard.draw();
        Platform.runLater(
                () -> {
                    lblLifeCycle.setText("ciclo di vita numero : "+gameOfLifeController.gameExist(choiceBoxId.getValue()).getLifeCycle());
                    lblSleep.setText("tempo tra ogni ciclo di vita  : "+gameOfLifeController.gameExist(choiceBoxId.getValue()).getSleep()+" secondi");
                    lblInfo.setText("partita avviata");
                    if(run)
                        lblInfo.setVisible(true);
                });
    }

    /**
     * comando per l'esecuzione di uno step della partita
     */
    private void nextStep() {
        gameOfLifeController.loadGame(gameOfLifeController.getListGameOfLife().indexOf(gameOfLifeController.gameExist(choiceBoxId.getValue())));
        loadBoard();
    }

    /**
     * metodo richiamato per bloaccare il thread
     */
    private synchronized void requestStop() {
        this.request = true;
    }

    /**
     * metodo richiamato per permettere di rieseguire di nuovo un thread
     */
    private synchronized void requestRestart() {
        this.request = false;
    }

    /**
     * metodo utilizzato per controllare se un'altro thread e' in esecuzione
     * @return
     */
    private synchronized boolean isRun(){
        return this.run;
    }

    /**
     * metodo utilizzato per contrallore lo stato della varibile utilizzata per eseguire il thread
     * @return
     */
    private synchronized boolean isStopRequested() {
        return this.request;
    }

}
