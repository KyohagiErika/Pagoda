package com.processor.applicaion;

import com.model.*;

/**
 * This class helps you to create page for console app.
 */
public class ConsolePage {
    protected ConsoleApplication application;
    protected String title;

    /**
     * Display the page.
     */
    public void display() {
        System.out.println(application.getApplicationName()+" "+application.getVersion());
        System.out.println(title);
    }
    public void display(HotelManager hotel) {
        System.out.println(application.getApplicationName()+" "+application.getVersion());
        System.out.println(title);
    }

    public void display(RoomManager room) {
        System.out.println(application.getApplicationName()+" "+application.getVersion());
        System.out.println(title);
    }

    public void display(BookRoom bookRoom) {
        System.out.println(application.getApplicationName()+" "+application.getVersion());
        System.out.println(title);
    }

    public void display(Evaluation evaluation) {
        System.out.println(application.getApplicationName()+" "+application.getVersion());
        System.out.println(title);
    }

    public void display(GuestManager guest) {
        System.out.println(application.getApplicationName()+" "+application.getVersion());
        System.out.println(title);
    }
    /**
     * Create a console page.
     * @param application The application to which the page belongs.
     * @param title Title of page.
     */
    public ConsolePage(ConsoleApplication application, String title) {
        this.application = application;
        this.title = title;
    }

}
