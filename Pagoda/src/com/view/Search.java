package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class Search extends ConsolePage {

    public Search(PagodaApp application) {
        super(application, "SearchHotel");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Searching your Hotel");
        application.pause();
    }
}
