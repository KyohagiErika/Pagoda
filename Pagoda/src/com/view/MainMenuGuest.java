package com.view;

import com.viewmodel.ConsoleMenu;
import com.viewmodel.Option;

public class MainMenuGuest extends ConsoleMenu {
    public MainMenuGuest() {
        super("Main Menu Guest", new Option[]{
                new Option("Account", () -> {
                    System.out.println("Forwarding Account Information");
                }),

                new Option("Search", () -> {
                    System.out.println("Forwarding Hotel Information");
                }),

                new Option("Explore", () -> {
                    System.out.println("Forwarding Explore");
                }),

                new Option("Support Centre", () -> {
                    System.out.println("Forwarding Support Centre");
                }),

                new Option("About Pagoda", () -> {
                    System.out.println("Forwarding About Pagoda");
                })
        });
    }
}
