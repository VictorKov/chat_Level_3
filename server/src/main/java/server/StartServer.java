package server;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class StartServer {
    private static final Logger logger = Logger.getLogger("");
    public static void main(String[] args) {
        try {
            Handler fileHandler = new FileHandler("log%_g.txt",20 * 1025, 10, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Server();
    }
}
