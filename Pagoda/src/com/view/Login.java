package com.view;

import com.viewmodel.ConsoleMenu;
import com.viewmodel.Option;

public class Login extends ConsoleMenu {
    public Login() {
        super("Login", new Option[]{
                new Option("Hotel", () -> {
                    System.out.println("Forwarding Hotel");
                }),

                new Option("Guest", () -> {
                    System.out.println("Forwarding Guest");
                })
        });
    }
}
