package com.view;

import com.model.*;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Guest extends PagodaAppMenu {
    public Guest(PagodaApp application) {
        super(application, "Guest", new Option[]{
                new Option("Guest Information", (obj) -> {
                    application.clrscr();
                    GuestInformation guestInformation = new GuestInformation(application);
                    guestInformation.display();
                    guestInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Search Hotel", (obj) -> {
                    application.clrscr();
                    SearchHotel searchHotel = new SearchHotel(application);
                    searchHotel.display();
                    searchHotel.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("See BookRoom History", (obj) -> {
                    application.clrscr();
                    try {
                        HashMap<String, String> currentUser = (HashMap<String, String>)application.getSession().get("currentUser");
                        ArrayList<HashMap<String, String>> returnBookRoomList = new BookRoomManager(application.getConnection()).seeBookRoomsOfGuest(new HashMap<>() {{
                            put("guestUsername",currentUser.get("username"));
                        }});
                        if (returnBookRoomList.size() != 0) {
                            System.out.println("Your BookRoom History:");
                            System.out.printf("%12s | %10s | %12s | %20s | %20s | %20s | %20s | %15s | %30s", "BookRoom ID", "Room ID", "Hotel ID", "Book Day", "Check In", "Check Out","Total Money", "Payment Method", "Note Of Guest");
                            System.out.println();
                            for (HashMap<String, String> returnBookRoom : returnBookRoomList) {
                                int pricePerNight = new BookRoomManager(application.getConnection()).seePricePerNightOfRoom(returnBookRoom.get("hotelID"),returnBookRoom.get("roomID"));
                                Duration duration = Duration.between(LocalDateTime.parse(returnBookRoom.get("checkIn"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                        LocalDateTime.parse(returnBookRoom.get("checkOut"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                                System.out.printf("%12s | %10s | %12s | %20s | %20s | %20s | %20d | %15s | %30s", returnBookRoom.get("bookroomID"),
                                        returnBookRoom.get("roomID"), returnBookRoom.get("hotelID"), returnBookRoom.get("bookDay"),
                                        returnBookRoom.get("checkIn"), returnBookRoom.get("checkOut"),duration.toHours()*pricePerNight/24,
                                        returnBookRoom.get("paymentMethod"), returnBookRoom.get("noteOfGuests"));
                                System.out.println();
                            }
                            application.pause();
                        } else {
                            System.out.println("You haven't booked any rooms!");
                            application.pause();
                        }
                    } catch (SQLException e) {
                        application.writeLog(e);
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.pause();
                    }
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("Unsubscribe hotel",(obj) -> {
                    application.clrscr();
                    try {
                        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
                        ArrayList<HashMap<String, String>> returnHotelList = new SubscriptionManager(application.getConnection()).fromDatabase(new HashMap<>() {{
                            put("guestUsername",currentUser.get("username"));
                        }});
                        if (returnHotelList.size() != 0) {
                            Option[] ops = new Option[returnHotelList.size()+1];
                            for (int i = 0; i < returnHotelList.size(); i++) {
                                HashMap<String, String> returnHotel = returnHotelList.get(i);
                                ops[i] = new Option("Hotel Name: "+returnHotel.get("name")+", Location: "+returnHotel.get("location"), (obj_) -> {
                                    application.clrscr();
                                    try {
                                        new SubscriptionManager(application.getConnection()).deleteRecord(new HashMap<>() {{
                                            put("guestUsername",currentUser.get("username"));
                                            put("hotelId",returnHotel.get("hotelID"));
                                        }});
                                        System.out.println("Unsubscribed hotel!");
                                    } catch (SQLException e) {
                                        application.writeLog(e);
                                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                                    }
                                    application.pause();
                                    application.clrscr();
                                    new Guest(application) {{
                                        display();
                                        forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                                    }};
                                    return null;
                                });
                            }
                            ops[ops.length-1] = new Option("Back", (obj_) -> {
                                application.clrscr();
                                new Guest(application) {{
                                    display();
                                    forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                                }};
                                return null;
                            });
                            application.clrscr();
                            PagodaAppMenu hotelMenu = new PagodaAppMenu(application, "Hotel List",ops);
                            hotelMenu.display();
                            hotelMenu.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        } else {
                            System.out.println("You haven't subscribed to any hotel yet!");
                            application.pause();
                            application.clrscr();
                            new Guest(application) {{
                                display();
                                forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                            }};
                        }
                    } catch (SQLException e) {
                        application.writeLog(e);
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.pause();
                        new Guest(application) {{
                            display();
                            forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        }};
                    }
                    return null;
                }),

                new Option("Feedback", (obj) -> {
                    application.clrscr();
                    new FeedBackMenu(application) {{
                        display();
                        forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    }};
                    return null;
                }),

                new Option("Support Centre", (obj) -> {
                    application.clrscr();
                    new SupportCentre(application).display();
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                }),

                new Option("About Pagoda App", (obj) -> {
                    application.clrscr();
                    new AboutPagodaApp(application).display();
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
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
