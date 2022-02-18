package com.view;

import com.viewmodel.ConsoleMenu;
import com.viewmodel.Option;

public class MainMenuHotel extends ConsoleMenu {
    public MainMenuHotel() {
        super("Main Menu Hotel", new Option[]{
                new Option("Hotel", () -> {
                    System.out.println("Forwarding Hotel Information");
                }),

                new Option("Room", () -> {
                    System.out.println("Forwarding Room Information");
                }),

                new Option("Customer", () -> {
                    System.out.println("Forwarding Customer Information");
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
