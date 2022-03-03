package com.view;

import com.model.Hotel;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.Option;
import com.processor.enumeration.ConsoleInputMode;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Login extends PagodaAppMenu {
    public Login(PagodaApp application) {
        super(application, "Login", new Option[]{
                new Option("Hotel", (obj) -> {
                    application.clrscr();
                    ConsoleInput textReader = new ConsoleInput();
                    ConsoleInput passReader = new ConsoleInput(ConsoleInputMode.PASSWORD);
                    Hotel hotelManagement = new Hotel(application.getConnection());
                    try {
                        ArrayList<HashMap<String, String>> records = hotelManagement.fromDatabase(new HashMap<>() {{
                            put("username",textReader.input("Username"));
                            put("pass",passReader.input("Password"));
                        }});
                        if (records.size() == 1) {
                            application.getSession().put("currentUser",records.get(0));
                            System.out.println("Login Successfully!");
                            application.pause();
                            application.clrscr();
                            com.view.Hotel hotelPage = new com.view.Hotel(application);
                            hotelPage.display();
                            hotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        } else {
                            System.out.println("Login failed! Wrong username or password!");
                            application.pause();
                            application.clrscr();
                            Home homePage = new Home(application);
                            homePage.display();
                            homePage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        }
                    } catch (SQLException e) {
                        application.writeLog(e);
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.pause();
                        application.clrscr();
                        Home homePage = new Home(application);
                        homePage.display();
                        homePage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    }

                    return null;
                }),

                new Option("Guest", (obj) -> {
                    application.clrscr();
                    ConsoleInput readUsername = new ConsoleInput();
                    String username= readUsername.input("Username");
                    ConsoleInput readPassword = new ConsoleInput(ConsoleInputMode.PASSWORD);
                    String password = readPassword.input("Password");

                    if(username.compareTo("linhlinh2") == 0 && password.compareTo("333333") == 0) {
                        System.out.println("Login Successful");
                        application.pause();
                        application.clrscr();
                        Guest guest = new Guest(application);
                        guest.display();
                        guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    } else {
                        System.out.println("Wrong Username or Password");
                        application.pause();
                        Home home = new Home(application);
                        home.display();
                        home.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    }
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
