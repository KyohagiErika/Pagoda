package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.PagodaApp;
import com.viewmodel.PagodaAppMenu;

public class Guest extends PagodaAppMenu {
    public Guest(PagodaApp application) {
        super(application, "Guest", new Option[]{
                new Option("Guest Information", (obj) -> {
                    application.clrscr();
                    System.out.println("Forward Guest Information");
                    GuestInformation guestInformation = new GuestInformation(application);
                    guestInformation.display();
                    guestInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Search Hotel", (obj) -> {
                    application.clrscr();
                    System.out.println("Forward Search Hotel");
                    SearchHotel searchHotel = new SearchHotel(application);
                    searchHotel.display();
                    searchHotel.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Explore", (obj) -> {
                    application.clrscr();
                    System.out.println("Forward Explore");
                    return null;
                }),

                new Option("Support Centre", (obj) -> {
                    application.clrscr();
                    System.out.println("Forward Support Centre");
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
                    return null;
                }),

                new Option("About Pagoda App", (obj) -> {
                    application.clrscr();
                    System.out.println("Forward About Pagoda App");
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
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
