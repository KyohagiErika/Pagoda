package com.view;

import com.model.Hotel;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.enumeration.ConsoleInputMode;
import com.viewmodel.application.PagodaApp;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaAppMenu;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Register extends PagodaAppMenu {
    public Register(PagodaApp application) {
        super(application,"Register", new Option[] {
                new Option("Hotel", (obj) -> {
                    ConsoleInput textReader = new ConsoleInput();
                    ConsoleInput passReader = new ConsoleInput(ConsoleInputMode.PASSWORD);
                    Hotel hotel = new Hotel(application.getConnection());
                    try {
                        hotel.toDatabase(new HashMap<>() {{
                            put("username",textReader.input("Username"));
                            put("pass",passReader.input("Password"));
                            put("lastName",textReader.input("Last Name"));
                            put("firstName",textReader.input("First Name"));
                            put("phoneNumber", textReader.input("Phone Number"));
                            put("email",textReader.input("Email"));
                            put("cccd",textReader.input("National ID"));
                            put("hotelID", Hotel.HotelIDGenerator());
                            put("name",textReader.input("Hotel Name"));
                            put("description",textReader.input("Hotel Description"));
                            put("starRank",textReader.input("Star Rank"));
                            put("image",textReader.input("Image Link"));
                            put("location",textReader.input("Hotel Location"));
                            put("regDay", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        }});
                        System.out.println("Register successfully!");
                        application.pause();
                        application.clrscr();
                        Login login = new Login(application);
                        login.display();
                        login.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    } catch (SQLException e) {
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.writeLog(e);
                        application.pause();
                        application.clrscr();
                        Home home = new Home(application);
                        home.display();
                        home.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    }
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
