package it.unicam.cs.pa.jlife105381;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * classe che crea un logger per memorizare gli eventi che portano ad
 * un errore nel programma.
 */
public class Log {

    public Logger logger;
    private final String fileLogger = "JLifeLogger.txt";
    private FileHandler fh;

    /**
     * nel caso che il file "JLifeLogger.txt" non e' presente se ne crea uno nuovo
     */
    public Log() {
        try {
            File f = new File(fileLogger);
            if(!f.exists())
                f.createNewFile();
            fh = new FileHandler(fileLogger,true);
            logger = Logger.getLogger("JLifeLogger");
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
