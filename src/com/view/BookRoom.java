package com.view;

import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class BookRoom extends ConsolePage {

    public BookRoom(PagodaApp application) {
        super(application, "Book Room");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Book Room Here");
        application.pause();
    }
}
