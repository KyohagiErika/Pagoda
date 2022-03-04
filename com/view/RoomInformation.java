package com.view;

import com.model.RoomManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class RoomInformation extends PagodaAppMenu {
    public RoomInformation(PagodaApp application) {
        super(application, "Room Information", new Option[]{
                new Option("See Room Information", (obj) -> {
                    application.clrscr();
                    RoomManager room = new RoomManager(application.getConnection());
                    new SeeRoomInformation(application).display(room);
                    application.clrscr();
                    RoomInformation roomInformation = new RoomInformation(application);
                    roomInformation.display();
                    roomInformation.forwardUser(PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Post Room Information", (obj) -> {
                    application.clrscr();
                    RoomManager room = new RoomManager(application.getConnection());
                    new PostRoomInformation(application).display(room);
                    application.clrscr();
                    RoomInformation roomInformation = new RoomInformation(application);
                    roomInformation.display();
                    roomInformation.forwardUser(PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Edit Room Information", (obj) -> {
                    application.clrscr();
                    RoomManager room = new RoomManager(application.getConnection());
                    new EditRoomInformation(application).display(room);
                    application.clrscr();
                    RoomInformation roomInformation = new RoomInformation(application);
                    roomInformation.display();
                    roomInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Delete Room", (obj) -> {
                    application.clrscr();
                    RoomManager room = new RoomManager(application.getConnection());
                    new DeleteRoom(application).display(room);
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
