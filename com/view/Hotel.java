package com.view;

import com.model.BookRoomManager;
import com.model.Evaluation;
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
                    BookRoomManager bookRoom = new BookRoomManager(application.getConnection());
                    new CustomerInformation(application).display(bookRoom);

                    application.clrscr();
                    Hotel hotel = new Hotel(application);
                    hotel.display();
                    hotel.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("See Feedback",(obj) -> {
                    new HotelSeeFeedback(application).display(new Evaluation(application.getConnection()));
                    return null;
                }),

                new Option("Support Centre", (obj) -> {
                    application.clrscr();
                    new SupportCentre(application).display();
                    application.clrscr();
                    Hotel hotel = new Hotel(application);
                    hotel.display();
                    hotel.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("About Pagoda App", (obj) -> {
                    application.clrscr();
                    new AboutPagodaApp(application).display();
                    application.clrscr();
                    Hotel hotel = new Hotel(application);
                    hotel.display();
                    hotel.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
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
