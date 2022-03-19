package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class GuestInformation extends PagodaAppMenu {
    public GuestInformation(PagodaApp application) {
        super(application, "Guest Information", new Option[]{
                new Option("See Guest Information", (obj) -> {
                    application.clrscr();
//                    SeeGuestInformation seeGuestInformation = new SeeGuestInformation(application);
//                    seeGuestInformation.display();
                    new SeeGuestInformation(application).display();
                    application.clrscr();
                    GuestInformation guestInformation = new GuestInformation(application);
                    guestInformation.display();
                    guestInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Edit Guest Information", (obj) -> {
                    application.clrscr();
                   new EditGuestInformation(application).display();
                    application.clrscr();
                    GuestInformation guestInformation = new GuestInformation(application);
                    guestInformation.display();
                    guestInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Delete Account", (obj) -> {
                    application.clrscr();
                    new DeleteAccount(application).display();
                    application.clrscr();
                    Home home = new Home(application);
                    home.display();
                    home.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Back", (obj) -> {
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                })
        });
    }
}
