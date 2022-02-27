package com.view;

import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class AboutPagodaApp extends ConsolePage {
    public AboutPagodaApp(PagodaApp application) {
        super(application, "Support Centre");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Information About Pagoda App");
        application.pause();
    }
}
