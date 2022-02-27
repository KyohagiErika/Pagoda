package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class RoomInformation extends PagodaAppMenu {
    public RoomInformation(PagodaApp application) {
        super(application, "Room Information", new Option[]{
                new Option("See Room Information", (obj) -> {
                    application.clrscr();
                    System.out.println("See Room Information");
                    application.pause();
                    application.clrscr();
                    RoomInformation roomInformation = new RoomInformation(application);
                    roomInformation.display();
                    roomInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Edit Room Information", (obj) -> {
                    application.clrscr();
                    // Edit room info menu here
                    application.pause();
                    application.clrscr();
                    RoomInformation roomInformation = new RoomInformation(application);
                    roomInformation.display();
                    roomInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Delete Room", (obj) -> {
                    application.clrscr();
                    System.out.println("Delete Room");
                    application.pause();
                    application.clrscr();
                    Hotel hotel = new Hotel(application);
                    hotel.display();
                    hotel.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Back", (obj) -> {
                    application.clrscr();
                    Hotel hotel = new Hotel(application);
                    hotel.display();
                    hotel.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                })
        });
    }
}
