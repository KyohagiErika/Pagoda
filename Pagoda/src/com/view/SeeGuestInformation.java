package com.view;

import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class SeeGuestInformation extends ConsolePage {
    public SeeGuestInformation(PagodaApp application) {
        super(application, "See Guest Information");
    }

    public void display() {
        application.clrscr();
        super.display();
        System.out.println("See Guest Information!!!!!!!");
        application.pause();
    }
}
