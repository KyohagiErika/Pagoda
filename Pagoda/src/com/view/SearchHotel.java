package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.Option;
import com.viewmodel.PagodaApp;
import com.viewmodel.PagodaAppMenu;

public class SearchHotel extends PagodaAppMenu {
    public SearchHotel(PagodaApp application) {
        super(application, "Search Hotel", new Option[]{
                new Option("See Hotel Information",(obj)->{
                    System.out.println("Forwarding See Hotel Information");
                    return  null;
                }),

                new Option("Vote Hotel ",(obj)->{
                    System.out.println("Forwarding Vote Hotel ");
                    return  null;
                }),

                new Option("Feedback Hotel ",(obj)->{
                    System.out.println("Forwarding Feedback Hotel ");
                    return  null;
                }),

                new Option("Subscribe Hotel ",(obj)->{
                    application.pause(ConsoleApplication.DEFAULT_PAUSE_MESSAGE);
                    return  null;
                }),

                new Option("Book Room ",(obj)->{
                    System.out.println("Forwarding Book Room");
                    return  null;
                }),
        });
    }
}
