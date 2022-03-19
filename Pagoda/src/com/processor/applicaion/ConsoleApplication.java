package com.processor.applicaion;

import java.io.IOException;

/**
 * Class for create a console application.
 */
public abstract class ConsoleApplication {
    public static final String DEFAULT_PAUSE_MESSAGE = "Press enter to continue...";

    private String applicationName;
    private int majorVersion;
    private int minorVersion;
    private int patch;

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
     * @param pauseMsg Message to display to user.
     */
    public void pause(String pauseMsg) {
        try {
            new ProcessBuilder("cmd","/c","pause").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
            this.pause(DEFAULT_PAUSE_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Clear the console screen
     */
    public void clrscr() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
            this.pause(DEFAULT_PAUSE_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * @param applicationName The application's name.
     * @param majorVersion The application's major version.
     * @param minorVersion The application's minor version.
     * @param patch The application's patch.
     */
    public ConsoleApplication(String applicationName, int majorVersion, int minorVersion, int patch) {
        this.applicationName = applicationName;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patch = patch;
    }
}
