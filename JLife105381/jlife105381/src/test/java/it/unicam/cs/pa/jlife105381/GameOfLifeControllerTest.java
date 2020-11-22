package it.unicam.cs.pa.jlife105381;

import it.unicam.cs.pa.jlife105381.Controller.Data.DataSerialize;
import it.unicam.cs.pa.jlife105381.Controller.GameOfLifeController;
import it.unicam.cs.pa.jlife105381.Controller.InterfaceGameOfLifeController;
import it.unicam.cs.pa.jlife105381.Model.DeadOrAlive;
import it.unicam.cs.pa.jlife105381.Model.GameOfLife;
import it.unicam.cs.pa.jlife105381.Model.InterfaceBoard;
import it.unicam.cs.pa.jlife105381.Model.InterfaceGameOfLife;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class GameOfLifeControllerTest {

    private static String path = "TestGameOfLife.txt";
    private static Log log;
    private static DataSerialize data;
    private static InterfaceGameOfLifeController gameOfLifeController;

    @BeforeAll
    static void prova() {
        log = new Log();
        data = new DataSerialize(log, path);
        gameOfLifeController = new GameOfLifeController(data);
    }

    @BeforeEach
    void initialize(){
        gameOfLifeController.removeAll();
    }

    @Test
    void testIsEmpty(){
        assertTrue(gameOfLifeController.isEmpty());
    }

    @Test
    void testAddGameOfLife(){
        assertTrue(gameOfLifeController.isEmpty());
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife("prova",board,1);
        assertFalse(gameOfLifeController.isEmpty());
    }

    @Test
    void testGetListGameOfLife(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife("prova",board,1);
        ArrayList<InterfaceGameOfLife> listGameOfLife = new ArrayList<>();
        listGameOfLife.add(new GameOfLife("prova",board,1));
        assertTrue(gameOfLifeController.getListGameOfLife().size()==1);
        assertTrue(gameOfLifeController.getListGameOfLife().get(0).toString().equals("prova/1/0"));
    }

    @Test
    void testCheckId(){
        assertTrue(gameOfLifeController.checkId("prova").equals("prova"));
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife("prova",board,1);
        assertThrows(NullPointerException.class, () -> gameOfLifeController.checkId(""));
        assertThrows(IllegalArgumentException.class, () -> gameOfLifeController.checkId("prova"));
    }

    @Test
    void testCheckSleep(){
        assertTrue(gameOfLifeController.checkSleep("2")==2);
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife("prova",board,1);
        assertThrows(NumberFormatException.class, () -> gameOfLifeController.checkSleep("a"));
        assertThrows(ArithmeticException.class, () -> gameOfLifeController.checkSleep("123"));
    }

    @Test
    void testCheckColumnsOrRows(){
        assertTrue(gameOfLifeController.checkColumnOrRow("1")==0);
        assertThrows(IllegalArgumentException.class, () -> gameOfLifeController.checkColumnOrRow("a"));
        assertThrows(IllegalArgumentException.class, () -> gameOfLifeController.checkColumnOrRow(""));
        assertThrows(IllegalArgumentException.class, () -> gameOfLifeController.checkColumnOrRow("11"));
    }

    @Test
    void testCheckIndexOfGame(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife("prova",board,1);
        assertTrue(gameOfLifeController.checkIndexOfGame("1")==0);
        assertThrows(NumberFormatException.class, () -> gameOfLifeController.checkColumnOrRow("a"));
        assertThrows(NumberFormatException.class, () -> gameOfLifeController.checkColumnOrRow(""));
        assertThrows(NumberFormatException.class, () -> gameOfLifeController.checkColumnOrRow("11"));
    }

    @Test
    void testChangeValueBoard(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife("prova",board,1);
        gameOfLifeController.changeValueBoard(1,1,gameOfLifeController.gameExist("prova"));
        assertTrue(gameOfLifeController.getListGameOfLife().get(0).getBoard().getValue(1,1)== DeadOrAlive.ALIVE);
    }

    @Test
    void testListGameOfLifeToString(){
        ArrayList<String> listToString = new ArrayList<>();
        InterfaceBoard board = InterfaceBoard.newBoard();
        listToString.add("prova/1/0");
        listToString.add("prova2/2/0");
        gameOfLifeController.addGameOfLife("prova",board,1);
        gameOfLifeController.addGameOfLife("prova2",board,2);
        assertTrue(gameOfLifeController.listGameOfLifeToString().equals(listToString));
    }

    @Test
    void testGameExist(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife("prova",board,1);
        assertTrue(gameOfLifeController.getListGameOfLife().get(0).equals(gameOfLifeController.gameExist("prova")));
        assertEquals(gameOfLifeController.gameExist("prova2"),null);
    }

    /**
     * questo test eseguo sia il metodo loadGame che nextStep della Interfaccia GameOfLifeController
     */
    @Test
    void testLoadGame(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        board.changeValue(0,0);
        board.changeValue(1,0);
        board.changeValue(0,1);
        InterfaceBoard board2 = InterfaceBoard.newBoard();
        board2.changeValue(0,0);
        board2.changeValue(1,0);
        board2.changeValue(0,1);
        board2.changeValue(1,1);
        gameOfLifeController.addGameOfLife("prova",board,1);
        gameOfLifeController.loadGame(0);
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                assertEquals(gameOfLifeController.getListGameOfLife().get(0).getBoard().getValue(i,j),board2.getValue(i,j));
            }
        }
    }

    @Test
    void testRemoveGameOfLifeIndex(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife("prova",board,1);
        assertTrue(gameOfLifeController.getListGameOfLife().size()==1);
        gameOfLifeController.removeGameOfLifeIndex(0);
        assertTrue(gameOfLifeController.getListGameOfLife().size()==0);
    }

    @Test
    void testRemoveGameOfLifeId(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife("prova",board,1);
        assertTrue(gameOfLifeController.getListGameOfLife().size()==1);
        gameOfLifeController.removeGameOfLifeId("prova");
        assertTrue(gameOfLifeController.getListGameOfLife().size()==0);
    }

    @Test
    void testRemoveAll(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        gameOfLifeController.addGameOfLife("prova",board,1);
        gameOfLifeController.addGameOfLife("prova2",board,1);
        assertTrue(gameOfLifeController.getListGameOfLife().size()==2);
        gameOfLifeController.removeAll();
        assertTrue(gameOfLifeController.getListGameOfLife().size()==0);
    }
}
