package it.unicam.cs.pa.jlife105381.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe che ha il compito di contenere tutte le partite
 */
public class MemoryGame implements InterfaceMemoryGame, Serializable {

    private ArrayList<InterfaceGameOfLife> listGameOfLife;

    public MemoryGame() {
        listGameOfLife = new ArrayList<>();
    }

    @Override
    public ArrayList<InterfaceGameOfLife> getListGameOfLife() {
        return this.listGameOfLife;
    }

    @Override
    public void addGameOfLife(InterfaceGameOfLife gameOfLife) {
        this.listGameOfLife.add(gameOfLife);
    }

    @Override
    public void removeGameOfLifeIndex(int index) {
        this.listGameOfLife.remove(index);
    }

    @Override
    public void removeGameOfLifeId(String id) {
        for (InterfaceGameOfLife game : getListGameOfLife()) {
            if(game.getId().equals(id)) {
                getListGameOfLife().remove(game);
                return;
            }
        }
    }

    /**
     * metodo per ottenere un ArrayList contenente ogni partita came stringa
     * @return
     */
    @Override
    public ArrayList<String> listToString() {
        ArrayList<String> list = new ArrayList<>();
        for (Object gameOfLifeBasic : getListGameOfLife()) {
            list.add(gameOfLifeBasic.toString());
        }
        return list;
    }

}
