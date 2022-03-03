package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class SeeHotelInformation extends ConsolePage {

    /**
     * Create a console page.
     *
     * @param application The application to which the page belongs.
     */
    public SeeHotelInformation(PagodaApp application) {
        super(application, "See Hotel Information");
    }

    public void display() {
        application.clrscr();
        super.display();
        // code
        System.out.println("See Hotel Information!!!!!!!");
        application.pause();
    }
}
