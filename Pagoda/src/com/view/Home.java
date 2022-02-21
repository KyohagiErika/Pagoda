package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.viewmodel.PagodaApp;
import com.processor.applicaion.Option;
import com.viewmodel.PagodaAppMenu;

public class Home extends PagodaAppMenu {
    public Home(PagodaApp application) {
        super(application,"Welcome to Pagoda App!",new Option[] {
            new Option("Admin", (obj) -> {
                System.out.println("Forwarding Admin");
                return null;
            }),

            new Option("Login", (obj) -> {
                application.clrscr();
                System.out.println("Forwarding Login");
                Login login = new Login(application);
                login.display();
                login.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                return null;
            }),

            new Option("Register", (obj) -> {
                application.clrscr();
                System.out.println("Forwarding Register");
                Register register = new Register(application);
                register.display();
                register.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                return null;
            }),

            new Option("Exit", (obj) -> {
                application.close();
                return null;
            })
        });
    }
}
