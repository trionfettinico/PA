package it.unicam.cs.pa.jlife105381.Controller.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.pa.jlife105381.Log;
import it.unicam.cs.pa.jlife105381.Model.GameOfLife;
import it.unicam.cs.pa.jlife105381.Model.InterfaceGameOfLife;
import it.unicam.cs.pa.jlife105381.Model.InterfaceMemoryGame;
import it.unicam.cs.pa.jlife105381.Model.MemoryGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * classe che ha il compito di gestire la memorizzazione dei dati tramite json
 */
public class DataJson extends DataManager implements InterfaceDataController {


    Gson gson = new Gson();

    public DataJson(Log log, String path) {
        super(log, path);
    }

    /**
     * metodo per la scrittura di un dato che implementa l'interfaccia InterfaceMemoryGame in json
     * @param data dato che implementa l'interfaccia InterfaceMemoryGame
     * @return
     */
    @Override
    public boolean write(InterfaceMemoryGame data) {
        try {
            String json = gson.toJson(data);
            FileWriter output = new FileWriter(getFile());
            output.write(json);
            output.close();
        } catch (Exception e) {
            log.logger.severe(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * metodo per la lettura di un dato del tipo MemoryGame
     * @return il dato trasformato da json in oggetto (InterfaceMemoryGame)
     */
    @Override
    public InterfaceMemoryGame read() {
        InterfaceMemoryGame data = null;
        this.gson = new GsonBuilder().registerTypeAdapter(MemoryGame.class, new MemoryGameJSON()).create();
        if (file.length() == 0)
            return null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            data = gson.fromJson(br, MemoryGame.class);
            br.close();
        } catch (Exception e) {
            log.logger.severe(e.getMessage());
        }
        return data;
    }
}
