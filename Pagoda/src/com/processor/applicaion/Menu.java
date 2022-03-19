package com.processor.applicaion;

/**
 * This class helps you to manipulate your application's menu.
 */
public abstract class Menu {
    protected String title;
    protected Option[] options;

    /**
     * Display the menu.
     */
    public abstract void display();

    /**
     * @param title The menu's title.
     * @param options Array of options.
     */
    public Menu(String title, Option[] options) {
        this.title = title;
        this.options = options;
    }
}
