package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class EditHotelInformation extends ConsolePage {
    public EditHotelInformation(PagodaApp application) {
        super(application, "Edit Hotel Information here");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Edit Hotel Information here");
        application.pause();
    }
}
