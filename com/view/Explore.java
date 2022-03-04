package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class Explore extends ConsolePage {
    public Explore(PagodaApp application) {
        super(application, "Explore");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Explore");
        application.pause();
    }
}
