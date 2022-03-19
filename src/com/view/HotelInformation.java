package com.view;

import com.model.GuestManager;
import com.model.HotelManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class HotelInformation extends PagodaAppMenu {
    public HotelInformation(PagodaApp application) {
        super(application, "Hotel Information", new Option[]{
                new Option("See Hotel Information", (obj) -> {
                    application.clrscr();
                    HotelManager hotel = new HotelManager(application.getConnection());
                    SeeHotelInformation seeHotelInformation = new SeeHotelInformation(application);
                    seeHotelInformation.display(hotel);
                    application.clrscr();
                    HotelInformation hotelInformation = new HotelInformation(application);
                    hotelInformation.display();
                    hotelInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Edit Hotel Information", (obj) -> {
                    application.clrscr();
                    HotelManager hotel = new HotelManager(application.getConnection());
                    EditHotelInformation editHotelInformation = new EditHotelInformation(application);
                    editHotelInformation.display(hotel);
                    application.clrscr();
                    HotelInformation hotelInformation = new HotelInformation(application);
                    hotelInformation.display();
                    hotelInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),
                new Option("Edit Hotel Password", (obj) -> {
                    application.clrscr();
                    HotelManager hotel = new HotelManager(application.getConnection());
                    new changePassword(application).display(hotel);
                    application.clrscr();
                    HotelInformation hotelInformation = new HotelInformation(application);
                    hotelInformation.display();
                    hotelInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),
                new Option("Delete Hotel", (obj) -> {
                    application.clrscr();
                    HotelManager hotel = new HotelManager(application.getConnection());
                    new DeleteHotel(application).display(hotel);
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
