package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.PagodaApp;
import com.viewmodel.PagodaAppMenu;

public class GuestInformation extends PagodaAppMenu {
    public GuestInformation(PagodaApp application) {
        super(application, "Guest Information", new Option[]{
                new Option("See Guest Information", (obj) -> {
                    application.clrscr();
                    System.out.println("See Guest Information");
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
                    return null;
                }),

                new Option("Edit Guest Information", (obj) -> {
                    application.clrscr();
                    System.out.println("Edit Guest Information");
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
                    return null;
                }),

                new Option("Delete Guest Information", (obj) -> {
                    application.clrscr();
                    System.out.println("Delete Guest Information");
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
                    application.clrscr();
                    Home home = new Home(application);
                    home.display();
                    home.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Back", (obj) -> {
                    application.clrscr();
                    Home home = new Home(application);
                    home.display();
                    home.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                })
        });
    }
}
