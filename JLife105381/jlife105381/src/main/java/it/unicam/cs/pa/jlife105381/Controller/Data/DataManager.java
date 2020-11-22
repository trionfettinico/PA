package it.unicam.cs.pa.jlife105381.Controller.Data;

import it.unicam.cs.pa.jlife105381.Log;

import java.io.File;

/**
 * classe alla base di DataJson e DataSerialize
 * che permette di andar a creare/aprire un file
 */
public abstract class DataManager {

    protected Log log;

    protected File file;

    public DataManager(Log log , String path) {
        this.log = log;
        this.file = null;
        this.file = new File(path);
        if(!file.exists())
            try {
                this.file.createNewFile();
            } catch (Exception e) {
                log.logger.severe(e.getMessage());
            }
    }

    protected File getFile(){
        return this.file;
    }

}
