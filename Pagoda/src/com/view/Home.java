package com.view;

import com.viewmodel.ConsoleMenu;
import com.viewmodel.Option;

public class Home extends ConsoleMenu {
    public Home() {
        super("Welcome to Pagoda App!",new Option[] {
            new Option("Admin", () -> {
                System.out.println("Forwarding Admin");
            }),

            new Option("Login", () -> {
                System.out.println("Forwarding Login");
            }),

            new Option("Register", () -> {
                System.out.println("Forwarding Register");
            })
        });
    }
}
