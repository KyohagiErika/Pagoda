package com.view;

import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class FeedBack extends ConsolePage {

    public FeedBack(PagodaApp application) {
        super(application, "FeedBack");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Give FeedBack");
        application.pause();
    }
}
