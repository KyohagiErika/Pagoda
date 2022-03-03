package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class SearchHotel extends PagodaAppMenu {
    public SearchHotel(PagodaApp application) {
        super(application, "Search Hotel", new Option[]{
                new Option("Search Hotel Information",(obj)->{
                    application.clrscr();
                    new Search(application).display();
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return  null;
                }),

                new Option("Vote Hotel ",(obj)->{
                    application.clrscr();
                    new FeedBack(application).display();
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return  null;
                }),

                new Option("Feedback Hotel ",(obj)->{
                    application.clrscr();
                    new FeedBack(application).display();
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return  null;
                }),

                new Option("Subscribe Hotel ",(obj)->{
                    application.clrscr();
                    new SubcribeHotel(application).display();
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return  null;
                }),

                new Option("Book Room ",(obj)->{
                    application.clrscr();
                    new BookRoom(application).display();
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return  null;
                }),
        });
    }
}
