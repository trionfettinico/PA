package it.unicam.cs.pa.jlife105381.Controller.Data;

import com.google.gson.*;
import it.unicam.cs.pa.jlife105381.Model.*;

import java.lang.reflect.Type;

/**
 * classe che ha il compito di trasformari i dati letti in json in un oggetto
 */
public class MemoryGameJSON implements JsonDeserializer<InterfaceMemoryGame> {

    /**
     * metodo che ha il compito di convertire dal json ogni partita
     * @param json
     * @param typeOfT
     * @param context
     * @return
     * @throws JsonParseException
     */
    @Override
    public InterfaceMemoryGame deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return deserializeGameOfLife(json.getAsJsonObject().get("listGameOfLife"), context);
    }

    /**
     * metodo che ha il compito di convertire ogni attributo di una partita da json
     * @param json
     * @param context
     * @return
     */
    public InterfaceMemoryGame deserializeGameOfLife(JsonElement json, JsonDeserializationContext context) {
        InterfaceMemoryGame memoryGame = new MemoryGame();
        for (JsonElement jsonElement : json.getAsJsonArray()) {
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            String id = jsonObj.get("id").getAsString();
            int sleep = jsonObj.get("sleep").getAsInt();
            int lyfeCicle = jsonObj.get("lifeCycle").getAsInt();
            InterfaceBoard board = deserializeBoard(jsonElement, context);
            memoryGame.addGameOfLife(new GameOfLife(id, board, sleep, lyfeCicle));
        }
        return memoryGame;
    }

    /**
     * metodo per convertire una Board da json
     * @param jsonElement
     * @param context
     * @return
     */
    public InterfaceBoard deserializeBoard(JsonElement jsonElement, JsonDeserializationContext context) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return context.deserialize(jsonObject.get("board"), Board.class);
    }
}
