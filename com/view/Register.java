package com.view;

import com.model.GuestManager;
import com.model.HotelManager;
import com.model.RoomManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.Serialization;
import com.processor.enumeration.ConsoleInputMode;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaAppMenu;
import com.viewmodel.application.PagodaValidater;

import java.io.IOException;
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
                    HotelManager hotel = new HotelManager(application.getConnection());
                    RoomManager room = new RoomManager(application.getConnection());
                    try {
                        hotel.toDatabase(new HashMap<>() {{
                            put("username",textReader.input("Username(contains a-z,A-Z,0-9,'-'and'_' at least 10 characters)",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.USERNAME_PATTERN);}));
                            put("pass",Serialization.encode(passReader.input("Password(contains at least 10 characters,one lowercase, one uppercase, one number and one special character)",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.PASSWORD_PATTERN);})));
                            put("lastName",textReader.input("Last Name"));
                            put("firstName",textReader.input("First Name"));
                            put("phoneNumber",textReader.input("Phone Number",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.PHONE_NUMBER_PATTERN);}));
                            put("email",textReader.input("Email",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.EMAIL_PATTERN);}));
                            put("cccd",textReader.input("National ID(CCCD)",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.CCCD_PATTERN);}));
                            put("hotelID",HotelManager.HotelIDGenerator());
                            put("name",textReader.input("Hotel Name"));
                            put("description",textReader.input("Hotel Description"));
                            put("starRank",textReader.input("Star Rank(1-5)",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.STAR_RANK_PATTERN);}));
                            put("image", textReader.input("Hotel Image(Link)",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.URL_PATTERN );}));
                            put("location",textReader.input("Hotel Location"));
                            put("regDay", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        }});
                        System.out.println();
                        System.out.println("Register successfully!");
                        application.pause();
                        application.clrscr();
                        Login login = new Login(application);
                        login.display();
                        login.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    } catch (SQLException e) {
                        System.out.println();
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.writeLog(e);
                        application.pause();
                        application.clrscr();
                        Home home = new Home(application);
                        home.display();
                        home.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    } catch (IOException e) {
                        System.out.println();
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
                    ConsoleInput textReader = new ConsoleInput();
                    ConsoleInput passReader = new ConsoleInput(ConsoleInputMode.PASSWORD);
                    GuestManager guest = new GuestManager(application.getConnection());
                    try {
                        guest.toDatabase(new HashMap<>() {{
                            put("username",textReader.input("Username(contains a-z,A-Z,0-9,'-'and'_' at least 10 characters)",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.USERNAME_PATTERN);}));
                            put("pass",Serialization.encode(passReader.input("Password(contains at least 10 characters,one lowercase, one uppercase, one number and one special character)",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.PASSWORD_PATTERN);})));
                            put("lastName",textReader.input("Last Name"));
                            put("firstName",textReader.input("First Name"));
                            put("phoneNumber",textReader.input("Phone Number",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.PHONE_NUMBER_PATTERN);}));
                            put("email",textReader.input("Email",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.EMAIL_PATTERN);}));
                            put("cccd",textReader.input("National ID(CCCD)",(inputData) -> {
                                return PagodaValidater.validate((String)inputData,PagodaValidater.CCCD_PATTERN);}));
                            put("regDay", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        }});
                        System.out.println();
                        System.out.println("Register successfully!");
                        application.pause();
                        application.clrscr();
                        Login login = new Login(application);
                        login.display();
                        login.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    } catch (SQLException e) {
                        System.out.println();
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.writeLog(e);
                        application.pause();
                        application.clrscr();
                        Home home = new Home(application);
                        home.display();
                        home.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    } catch (IOException e) {
                        System.out.println();
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
