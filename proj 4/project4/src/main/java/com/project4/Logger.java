package com.project4;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This Logger class is used for logging events in both the console and a .log file. 
 * @author George Maurice
 */
public class Logger {
    // Default log file path
    private String logFilePath = "CharacterSimulationEvents.log";
    // BufferedWriter for writing to the log file
    private BufferedWriter bufferedWriter;

    /**
     * Standard constructor that initializes the writer and logs the start of a new simulation
     */
    public Logger() {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(logFilePath, true)); 
            bufferedWriter.write("New Simulation Run at " + java.time.LocalDateTime.now() + "----------------------------------------");
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs a message to both the console and the log file
     * @param message The message to log
     */
    public void log(String message) {
        System.out.println(message);
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to log a specific event with both the time and the event description
     * @param time The time of the event
     * @param event The description of the event
     */
    public void logEvent(int time, String event) {
        log(time + "       " + event);
    }
    
}
