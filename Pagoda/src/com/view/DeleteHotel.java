package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class DeleteHotel extends ConsolePage {
    public DeleteHotel(PagodaApp application) {
        super(application, "Delete Hotel");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Delete Your Hotel");
        application.pause();
    }
}
