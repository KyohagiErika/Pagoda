package com.processor.applicaion;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class for create a console application.
 */
public abstract class ConsoleApplication {
    public static final String DEFAULT_ERROR_MESSAGE = "Some errors occurred, please check the error file for more info.";

    private String applicationName;
    private int majorVersion;
    private int minorVersion;
    private int patch;
    private HashMap<String, String> session;
    private FileOutputStream errorLogStream;

    /**
     * @return The application's name.
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @return Version of the application.
     */
    public String getVersion() {
        return majorVersion+"."+minorVersion+"."+patch;
    }

    /**
     * @return Session of the application.
     */
    public HashMap<String, String> getSession() {
        return session;
    }

    /**
     * Starting method for application.
     */
    public abstract void start();

    /**
     * Close the application.
     */
    public void close() {
        System.exit(0);
    }

    /**
     * Pause the application.
     */
    public void pause() {
        try {
            new ProcessBuilder("cmd","/c","pause").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            System.out.println(DEFAULT_ERROR_MESSAGE);
            writeLog(e);
            System.exit(0);
        }
    }

    /**
     * Clear the console screen
     */
    public void clrscr() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            writeLog(e);
            System.out.println(DEFAULT_ERROR_MESSAGE);
            this.pause();
            System.exit(0);
        }
    }

    /**
     * Write an error log to the error stream.
     * @param e The exception to be written.
     */
    public void writeLog(Exception e) {
        e.printStackTrace(new PrintWriter(errorLogStream));
    }

    /**
     * @param applicationName The application's name.
     * @param majorVersion The application's major version.
     * @param minorVersion The application's minor version.
     * @param patch The application's patch.
     * @param errorLogPath The application's path to the log file.
     * @throws IOException Throw when the application can't generate the error log file.
     */
    public ConsoleApplication(String applicationName,String errorLogPath, int majorVersion, int minorVersion, int patch) throws IOException {
        this.applicationName = applicationName;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patch = patch;
        while (true) {
            try {
                errorLogStream = new FileOutputStream(errorLogPath);
                return;
            } catch (FileNotFoundException e) {
                File errorFile = new File(errorLogPath);
                errorFile.createNewFile();
            }
        }

    }
}
