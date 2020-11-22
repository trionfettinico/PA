package it.unicam.cs.pa.jlife105381.Controller;

import it.unicam.cs.pa.jlife105381.Log;
import it.unicam.cs.pa.jlife105381.Model.InterfaceBoard;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

/**
 * classe che ha il compito di gestire e creare una tabella della partita per la GUI
 */
public class DrawBoard {

    private final Affine affine;

    private final Canvas canvas;

    private static InterfaceBoard board;

    private static InterfaceGameOfLifeController gameOfLifeController;

    private Log log;

    public DrawBoard(Log logger, InterfaceGameOfLifeController gameOfLifeController2){
        this.canvas = new Canvas(400, 400);
        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);
        log = logger;
        gameOfLifeController = gameOfLifeController2;
        initizialize();
    }

    public Canvas getCanvas(){ return this.canvas; }

    public void initizialize(){
        board = InterfaceBoard.newBoard();
    }

    /**
     * metodo per implementare un evento se viene eseguito un click sulla board
     */
    public void setOnMousePressed(){
         this.canvas.setOnMousePressed(this::handleDraw);
    }

    /**
     * evento creato dal click sulla board
     * grazie all'utilizzo dell'oggetto affine possiamo vedere quale cella della
     * board viene premuta e possiamo quindi cambiarne il valore e colore
     * @param event
     */
    private void handleDraw(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        try {
            Point2D simCoord = this.affine.inverseTransform(mouseX, mouseY);
            int row = (int) simCoord.getX();
            int column = (int) simCoord.getY();
            board.changeValue(row,column);
            draw();
        } catch (NonInvertibleTransformException e) {
            log.logger.severe(e.getMessage());
        }
    }

    public InterfaceBoard getBoard(){
        return board;
    }

    /**
     * metodo per iniziallizzare una board dall'id della partita
     * @param id id della partita
     */
    public void setBoard(String id){
        initizialize();
        for (int i = 0; i < gameOfLifeController.getRows(); i++) {
            for (int j = 0; j < gameOfLifeController.getColumns(); j++) {
                if(gameOfLifeController.gameExist(id).getBoard().isAlive(i,j))
                    board.setAlive(i,j);
                else
                    board.setDead(i,j);
            }
        }
    }

    /**
     * metodo per la realizzazione grafica della board
     */
    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);
        g.setFill(Color.BLACK);
        for (int i = 0; i < gameOfLifeController.getRows(); i++) {
            for (int j = 0; j < gameOfLifeController.getRows(); j++) {
                if (board.isAlive(i,j)) {
                    g.fillRect(i, j, 1, 1);
                }
            }
        }
        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= gameOfLifeController.getRows(); x++) {
            g.strokeLine(x, 0, x, 10);
        }

        for (int y = 0; y <= gameOfLifeController.getColumns(); y++) {
            g.strokeLine(0, y, 10, y);
        }
    }
}
