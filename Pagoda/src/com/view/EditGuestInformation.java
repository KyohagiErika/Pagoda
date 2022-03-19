package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class EditGuestInformation extends ConsolePage {
    /**
     * Create a console page.
     *
     * @param application The application to which the page belongs.
     * @param title       Title of page.
     */
    public EditGuestInformation(PagodaApp application) {
        super(application, "Edit Guest Information");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Edit Guest Information here");
        application.pause();
    }
}
