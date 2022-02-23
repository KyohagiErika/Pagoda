package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.viewmodel.application.PagodaApp;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaAppMenu;

public class Register extends PagodaAppMenu {
    public Register(PagodaApp application) {
        super(application,"Register", new Option[] {
                new Option("Hotel", (obj) -> {
                    System.out.println("Forward Hotel");
                    return null;
                }),

                new Option("Guest", (obj) -> {
                    System.out.println("Forward Guest");
                    return null;
                }),

                new Option("Back", (obj) -> {
                    application.clrscr();
                    Home home = new Home(application);
                    home.display();
                    home.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                })
        });
    }
}
