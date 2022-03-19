package com.processor.applicaion;

/**
 * You can create a menu that works on console application.
 */
public abstract class ConsoleMenu extends Menu {
    protected ConsoleApplication application;

    public void display() {
        System.out.println(application.getApplicationName()+" "+application.getVersion());
        System.out.println(title);
        int i = 1;
        for (Option option : options) {
            System.out.println((i++)+". "+option.getTitle());
        }
    }

    /**
     * Get user's input and forward the corresponding option.
     * @param inputMsg The display message to notice to user to input.
     */
    public abstract void forwardUser(String inputMsg);

    /**
     * @param application The application to which the menu belongs.
     * @param title Title of the menu.
     * @param options The menu's options.
     */
    public ConsoleMenu(ConsoleApplication application, String title, Option[] options) {
        super(title,options);
        this.application = application;
    }
}
