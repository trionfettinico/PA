package it.unicam.cs.pa.jlife105381.Controller.Data;

import it.unicam.cs.pa.jlife105381.Log;
import it.unicam.cs.pa.jlife105381.Model.GameOfLife;
import it.unicam.cs.pa.jlife105381.Model.InterfaceMemoryGame;
import it.unicam.cs.pa.jlife105381.Model.MemoryGame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * classe che ha il compito di gestire la memorizzazione dei dati tramite serializzazione
 */
public class DataSerialize extends DataManager implements InterfaceDataController{

    public DataSerialize(Log log, String path) {
        super(log,path);
    }

    /**
     * metodo per la scrittura di un dato che implementa l'interfaccia InterfaceMemoryGame tramite serializzazione
     * @param data dato che implementa l'interfaccia InterfaceMemoryGame
     * @return
     */
    @Override
    public boolean write(InterfaceMemoryGame data) {
        try {
            FileOutputStream fos = new FileOutputStream(getFile());
            ObjectOutputStream output = new ObjectOutputStream(fos);
            output.writeObject(data);
            output.close();
        }catch (Exception e){
            log.logger.severe(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * metodo per la lettura di un dato del tipo MemoryGame
     * @return il dato trasformato in oggetto (InterfaceMemoryGame)
     */
    @Override
    public InterfaceMemoryGame read() {
        InterfaceMemoryGame data = null;
        if (file.length() == 0)
            return null;
        try {
            FileInputStream fis = new FileInputStream(getFile());
            ObjectInputStream input = new ObjectInputStream(fis);
            data = (InterfaceMemoryGame) input.readObject();
            input.close();
        } catch (Exception e) {
            log.logger.severe(e.getMessage());
        }
        return data;
    }

}
