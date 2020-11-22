package it.unicam.cs.pa.jlife105381;

import it.unicam.cs.pa.jlife105381.Model.GameOfLife;
import it.unicam.cs.pa.jlife105381.Model.InterfaceBoard;
import it.unicam.cs.pa.jlife105381.Model.InterfaceGameOfLife;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GameOfLifeTest {

    @Test
    void testGetValue(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        InterfaceBoard board2 = InterfaceBoard.newBoard();
        board2.changeValue(1,2);

        InterfaceGameOfLife gameOfLife = new GameOfLife("prova",board,1);
        InterfaceGameOfLife gameOfLife2 = new GameOfLife("prova2",board,2,2);

        assertFalse(gameOfLife.equals(gameOfLife2));
        assertTrue(gameOfLife.getId().equals("prova"));
        assertTrue(gameOfLife.getLifeCycle()==0);
        assertTrue(gameOfLife.getSleep()==1);
        assertFalse(gameOfLife.getBoard().equals(board2));

        assertTrue(gameOfLife2.getId().equals("prova2"));
        assertTrue(gameOfLife2.getLifeCycle()==2);
        assertTrue(gameOfLife2.getSleep()==2);
        assertTrue(gameOfLife2.getBoard().equals(board));
    }

    @Test
    void testUpgradeLifeCycle(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        InterfaceGameOfLife gameOfLife = new GameOfLife("prova",board,1);
        assertTrue(gameOfLife.getLifeCycle()==0);
        gameOfLife.upgradeLifeCycle();
        assertTrue(gameOfLife.getLifeCycle()==1);
    }

    @Test
    void testToString(){
        InterfaceBoard board = InterfaceBoard.newBoard();
        InterfaceGameOfLife gameOfLife = new GameOfLife("prova",board,1);
        String gameOfLifeString = "prova/1/0";
        assertTrue(gameOfLife.toString().equals(gameOfLifeString));
    }
}
