package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class SupportCentre extends ConsolePage {
    public SupportCentre(PagodaApp application) {
        super(application, "Support Centre");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Welcome to Support Centre");
        application.pause();
    }
}
