package com.view;

import com.model.GuestManager;
import com.model.HotelManager;
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
                    HotelManager hotel = new HotelManager(application.getConnection());
                    ArrayList<HashMap<String, String>> records = null;
                    try {
                        records = hotel.fromDatabase(new HashMap<String, String>() {{
                            put("username",textReader.input("Username"));
                            put("pass",passReader.input("Password"));
                        }});
                        if(records.size()==1) {
                            application.getSession().put("currentUser",records.get(0));
                            System.out.println("Login successfully!");
                            application.pause();
                            application.clrscr();
                            Hotel hotelPage = new Hotel(application);
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
                    ConsoleInput textReader = new ConsoleInput();
                    ConsoleInput passReader = new ConsoleInput(ConsoleInputMode.PASSWORD);
                    GuestManager guest = new GuestManager(application.getConnection());
                    ArrayList<HashMap<String, String>> records = null;
                    try {
                        records = guest.fromDatabase(new HashMap<String, String>() {{
                            put("username",textReader.input("Username"));
                            put("pass",passReader.input("Password"));
                        }});
                        if(records.size()==1) {
                            application.getSession().put("currentUser",records.get(0));
                            System.out.println("Login successfully!");
                            application.pause();
                            application.clrscr();
                            Guest guestPage = new Guest(application);
                            guestPage.display();
                            guestPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
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
