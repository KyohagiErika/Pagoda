package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class Hotel extends PagodaAppMenu {
    public Hotel(PagodaApp application) {
        super(application, "Hotel", new Option[]{
                new Option("Hotel Information", (obj) -> {
                    application.clrscr();
                    HotelInformation hotelInformation = new HotelInformation(application);
                    hotelInformation.display();
                    hotelInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Room Information", (obj) -> {
                    application.clrscr();
                    RoomInformation roomInformation = new RoomInformation(application);
                    roomInformation.display();
                    roomInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Customer Information", (obj) -> {
                    application.clrscr();
                    System.out.println("Forwarding Customer Information");
                    application.pause();
                    return null;
                }),

                new Option("Support Centre", (obj) -> {
                    application.clrscr();
                    System.out.println("Forwarding Support Centre");
                    application.pause();
                    return null;
                }),

                new Option("About Pagoda App", (obj) -> {
                    application.clrscr();
                    System.out.println("Forwarding About Pagoda App");
                    application.pause();
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
