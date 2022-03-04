package com.view;

import com.model.Evaluation;
import com.model.HotelManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class SearchHotel extends PagodaAppMenu {
    public SearchHotel(PagodaApp application) {
        super(application, "Search Hotel", new Option[]{
                new Option("Search Hotel Information",(obj)->{
                    application.clrscr();
                    HotelManager hotel = new HotelManager(application.getConnection());
                    new Search(application).display(hotel);
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return  null;
                }),

                new Option("Feedback Hotel ",(obj)->{
                    application.clrscr();
                    Evaluation evaluation = new Evaluation(application.getConnection());
                    new FeedBack(application).display(evaluation);
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return  null;
                }),

                new Option(" Edit Feedback Hotel ",(obj)->{
                    application.clrscr();
                    Evaluation evaluation = new Evaluation(application.getConnection());
                    new EditFeedBack(application).display(evaluation);
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return  null;
                }),

                new Option(" Delete Feedback Hotel ",(obj)->{
                    application.clrscr();
                    Evaluation evaluation = new Evaluation(application.getConnection());
                    new FeedBack(application).display(evaluation);
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
                    com.model.BookRoom bookRoom = new com.model.BookRoom(application.getConnection());
                    new BookRoom(application).display(bookRoom);
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return  null;
                }),
        });
    }
}
