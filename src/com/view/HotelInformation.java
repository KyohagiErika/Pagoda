package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class HotelInformation extends PagodaAppMenu {
    public HotelInformation(PagodaApp application) {
        super(application, "Hotel Information", new Option[]{
                new Option("See Hotel Information", (obj) -> {
                    SeeHotelInformation seeHotelInformation = new SeeHotelInformation(application);
                    seeHotelInformation.display();
                    application.clrscr();
                    HotelInformation hotelInformation = new HotelInformation((PagodaApp) application);
                    hotelInformation.display();
                    hotelInformation.forwardUser(PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Edit Hotel Information", (obj) -> {
                    application.clrscr();
                    // Edit hotel info here
                    application.pause();
                    application.clrscr();
                    HotelInformation hotelInformation = new HotelInformation(application);
                    hotelInformation.display();
                    hotelInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Delete Hotel", (obj) -> {
                    application.clrscr();
                    System.out.println("Delete Hotel");
                    application.pause();
                    application.clrscr();
                    Home home = new Home(application);
                    home.display();
                    home.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
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
