package it.unicam.cs.pa.jlife105381;

import it.unicam.cs.pa.jlife105381.Model.GameOfLife;
import it.unicam.cs.pa.jlife105381.Model.InterfaceBoard;
import it.unicam.cs.pa.jlife105381.Model.InterfaceGameOfLife;
import it.unicam.cs.pa.jlife105381.Model.InterfaceMemoryGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class MemoryGameTest {

    private InterfaceMemoryGame memoryGame;

    @BeforeEach
    void initialize(){
        memoryGame = InterfaceMemoryGame.newMemoryGame();
    }

    @Test
    void testGetListGameOfLife(){
        assertTrue(memoryGame.getListGameOfLife().isEmpty());
        ArrayList<InterfaceGameOfLife> listGameOfLife = new ArrayList<>();
        InterfaceBoard board = InterfaceBoard.newBoard();
        InterfaceGameOfLife gameOfLife = new GameOfLife("prova",board,1);
        memoryGame.addGameOfLife(gameOfLife);
        listGameOfLife.add(gameOfLife);
        assertTrue(memoryGame.getListGameOfLife().equals(listGameOfLife));
        memoryGame.removeGameOfLifeIndex(0);
    }

    @Test
    void testAddGameOfLife(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        InterfaceGameOfLife gameOfLife = new GameOfLife("prova",board,1);
        memoryGame.addGameOfLife(gameOfLife);
        assertTrue(memoryGame.getListGameOfLife().size()==1);
        memoryGame.removeGameOfLifeIndex(0);
    }

    @Test
    void TestRemoveGameOfLifeId(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        InterfaceGameOfLife gameOfLife = new GameOfLife("prova",board,1);
        memoryGame.addGameOfLife(gameOfLife);
        assertTrue(memoryGame.getListGameOfLife().size()==1);
        memoryGame.removeGameOfLifeId("prova");
        assertTrue(memoryGame.getListGameOfLife().size()==0);
    }

    @Test
    void TestRemoveGameOfLifeIndex(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        InterfaceGameOfLife gameOfLife = new GameOfLife("prova",board,1);
        memoryGame.addGameOfLife(gameOfLife);
        assertTrue(memoryGame.getListGameOfLife().size()==1);
        memoryGame.removeGameOfLifeIndex(0);
        assertTrue(memoryGame.getListGameOfLife().size()==0);
    }

    @Test
    void testListToString(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        InterfaceGameOfLife gameOfLife = new GameOfLife("prova",board,1);
        InterfaceGameOfLife gameOfLife2 = new GameOfLife("prova2",board,2,1);

        memoryGame.addGameOfLife(gameOfLife);
        memoryGame.addGameOfLife(gameOfLife2);

        ArrayList<String> listString = new ArrayList<>();
        listString.add("prova/1/0");
        listString.add("prova2/2/1");

        assertTrue(listString.equals(memoryGame.listToString()));
        memoryGame.removeGameOfLifeIndex(0);
        memoryGame.removeGameOfLifeIndex(0);
    }
}
