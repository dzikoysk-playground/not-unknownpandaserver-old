package net.dzikoysk.server.logging;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private final PrintStream original;

    public Logger() {
        this.original = System.out;
        System.setOut(new PrintStream(new CustomOutput(this)));
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void severe(String message) {
        log(Level.SEVERE, message);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void log(Level level, String message) {
        log(level, message, false);
    }

    public void log(Level level, String message, boolean oneline) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if (oneline) {
            this.original.print("[" + sdf.format(new Date()) + "][" + level.toString() + "] " + message);
        }
        else {
            this.original.println("[" + sdf.format(new Date()) + "][" + level.toString() + "] " + message);
        }
    }

}
