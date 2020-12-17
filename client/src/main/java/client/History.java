package client;

import javafx.scene.shape.Path;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class History {

    private static PrintWriter out;

    private static String getHistoryFilenameByLogin(String login){
        return "history/msg_history_" + login + ".txt";
    }

    public static void start(String login){
        try {
            out = new PrintWriter(new FileOutputStream(getHistoryFilenameByLogin(login),true),true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop(){
        if (out != null){
            out.close();
        }
    }

    public static void writeLine(String msg){
        out.println(msg);
    }

    public static String getLast100LinesOfChat(String login){
        if(!Files.exists(Paths.get(getHistoryFilenameByLogin(login)))){
            return "";
        }
        StringBuilder msgHistory = new StringBuilder();

        try {
            List<String> historyLines = Files.readAllLines(Paths.get(getHistoryFilenameByLogin(login)));
            int startLine = 0;
            if (historyLines.size() > 100){
                startLine = historyLines.size() - 100;
            }

            for (int i = startLine; i < historyLines.size(); i++) {
                msgHistory.append(historyLines.get(i)).append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msgHistory.toString();
    }
}
