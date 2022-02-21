package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.PagodaApp;
import com.viewmodel.PagodaAppMenu;

public class RoomInformation extends PagodaAppMenu {
    public RoomInformation(PagodaApp application) {
        super(application, "Room Information", new Option[]{
                new Option("See Room Information", (obj) -> {
                    application.clrscr();
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
                    System.out.println("See Room Information");
                    return null;
                }),

                new Option("Edit Room Information", (obj) -> {
                    application.clrscr();
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
                    System.out.println("Edit Room Information");
                    return null;
                }),

                new Option("Delete Room Information", (obj) -> {
                    application.clrscr();
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
                    System.out.println("Delete Room Information");
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
