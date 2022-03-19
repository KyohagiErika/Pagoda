package com.view;

import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class SubcribeHotel extends ConsolePage {

    public SubcribeHotel(PagodaApp application) {
        super(application, "Subcribe Hotel");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Subcribe Hotel");
        application.pause();
    }
}
