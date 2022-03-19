package com.view;

import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class SeeRoomInformation extends ConsolePage {
    public SeeRoomInformation(PagodaApp application) {
        super(application, "See Room Information");
    }

    public void display() {
        application.clrscr();
        super.display();
        System.out.println("See Room Information Here");
        application.pause();
    }
}
