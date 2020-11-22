package it.unicam.cs.pa.jlife105381;

import it.unicam.cs.pa.jlife105381.Model.Board;
import it.unicam.cs.pa.jlife105381.Model.DeadOrAlive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {
    private Board board;

    @BeforeEach
    void initialize(){
        board = new Board();
    }

    @Test
    void createBoard(){
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                assertTrue(board.getValue(i,j).equals(DeadOrAlive.DEAD));
            }
        }
    }

    @Test()
    void testChangeValue(){
        assertTrue(board.getValue(2,3).equals(DeadOrAlive.DEAD));
        board.changeValue(2,3);
        assertTrue(board.getValue(2,3).equals(DeadOrAlive.ALIVE));
        board.changeValue(2,3);
        assertTrue(board.getValue(2,3).equals(DeadOrAlive.DEAD));
    }

    @Test
    void testCountAliveNeighbours(){
        assertEquals(board.countAliveNeighbours(0,0),0);
        board.changeValue(1,1);
        assertEquals(board.countAliveNeighbours(0,0),1);
        board.changeValue(1,0);
        assertEquals(board.countAliveNeighbours(0,0),2);
        board.changeValue(1,0);
        assertEquals(board.countAliveNeighbours(0,0),1);
    }

    @Test
    void testIsAlive(){
        assertEquals(board.exist(-1,-1),0);
        assertEquals(board.exist(11,12),0);
        assertEquals(board.exist(1,1),0);
        board.changeValue(1,1);
        assertEquals(board.exist(1,1),1);
    }

}
