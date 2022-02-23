package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.view.Home;
import com.viewmodel.PagodaApp;
import com.viewmodel.PagodaAppMenu;

public class HotelInformation extends PagodaAppMenu {
    public HotelInformation(PagodaApp application) {
        super(application, "Hotel Information", new Option[]{
                new Option("See Hotel Information", (obj) -> {
                    application.clrscr();
                    System.out.println("See Hotel Information");
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
                    return null;
                }),

                new Option("Edit Hotel Information", (obj) -> {
                    application.clrscr();
                    System.out.println("Edit Hotel Information");
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
                    return null;
                }),

                new Option("Delete Hotel Information", (obj) -> {
                    application.clrscr();
                    System.out.println("Delete Hotel Information");
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
