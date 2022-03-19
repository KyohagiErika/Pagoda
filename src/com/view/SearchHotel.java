package com.view;

import com.model.Evaluation;
import com.model.HotelManager;
import com.model.RoomManager;
import com.model.SubscriptionManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchHotel extends PagodaAppMenu {
    public SearchHotel(PagodaApp application) {
        super(application, "Search Hotel", new Option[]{
                new Option("Search by name",(obj) -> {
                    application.clrscr();
                    try {
                        ArrayList<HashMap<String, String>> returnHotelList = new HotelManager(application.getConnection()).searchHotel(new HashMap<>() {{
                            put("name",new ConsoleInput().input("Hotel name"));
                        }});
                        if (returnHotelList.size() != 0) {
                            Option[] ops = new Option[returnHotelList.size()+1];
                            for (int i = 0; i < returnHotelList.size(); i++) {
                                HashMap<String, String> returnHotel = returnHotelList.get(i);
                                ops[i] = new Option("Hotel Name: "+returnHotel.get("name")+", Location: "+returnHotel.get("location"), (obj_) -> {
                                    application.clrscr();
                                    System.out.println("Hotel information:");
                                    System.out.println();
                                    System.out.println("Hotel ID: "+returnHotel.get("hotelID"));
                                    System.out.println("Hotel Name: "+returnHotel.get("name"));
                                    System.out.println("Hotel Description: "+returnHotel.get("description"));
                                    System.out.println("Star Rank: "+returnHotel.get("starRank"));
                                    System.out.println("Hotel Image: "+returnHotel.get("image"));
                                    System.out.println("Hotel Location: "+returnHotel.get("location"));

                                    application.pause();
                                    System.out.println();
                                    new SeeRoomInformation(application).display(new RoomManager(application.getConnection()),returnHotel.get("hotelID"));
                                    return null;
                                });
                            }
                            ops[ops.length-1] = new Option("Back", (obj_) -> {
                                application.clrscr();
                                SearchHotel searchHotelPage = new SearchHotel(application);
                                searchHotelPage.display();
                                searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                                return null;
                            });
                            application.clrscr();
                            PagodaAppMenu hotelMenu = new PagodaAppMenu(application, "Hotel List",ops);
                            hotelMenu.display();
                            hotelMenu.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        } else {
                            System.out.println("Can't find the hotel you're searching for!");
                            application.pause();
                            application.clrscr();
                            SearchHotel searchHotelPage = new SearchHotel(application);
                            searchHotelPage.display();
                            searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.pause();
                    }
                    return null;
                }),

                new Option("Search by location",(obj) -> {
                    application.clrscr();
                    try {
                        ArrayList<HashMap<String, String>> returnHotelList = new HotelManager(application.getConnection()).searchHotel(new HashMap<>() {{
                            put("location",new ConsoleInput().input("Hotel location"));
                        }});
                        if (returnHotelList.size() != 0) {
                            Option[] ops = new Option[returnHotelList.size()+1];
                            for (int i = 0; i < returnHotelList.size(); i++) {
                                HashMap<String, String> returnHotel = returnHotelList.get(i);
                                ops[i] = new Option("Hotel Name: "+returnHotel.get("name")+", Location: "+returnHotel.get("location"), (obj_) -> {
                                    application.clrscr();
                                    System.out.println("Hotel information:");
                                    System.out.println();
                                    System.out.println("Hotel ID: "+returnHotel.get("hotelID"));
                                    System.out.println("Hotel Name: "+returnHotel.get("name"));
                                    System.out.println("Hotel Description: "+returnHotel.get("description"));
                                    System.out.println("Star Rank: "+returnHotel.get("starRank"));
                                    System.out.println("Hotel Image: "+returnHotel.get("image"));
                                    System.out.println("Hotel Location: "+returnHotel.get("location"));

                                    application.pause();
                                    System.out.println();
                                    new SeeRoomInformation(application).display(new RoomManager(application.getConnection()),returnHotel.get("hotelID"));
                                    return null;
                                });
                            }
                            ops[ops.length-1] = new Option("Back", (obj_) -> {
                                application.clrscr();
                                SearchHotel searchHotelPage = new SearchHotel(application);
                                searchHotelPage.display();
                                searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                                return null;
                            });
                            application.clrscr();
                            PagodaAppMenu hotelMenu = new PagodaAppMenu(application, "Hotel List",ops);
                            hotelMenu.display();
                            hotelMenu.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        } else {
                            System.out.println("Can't find the hotel you're searching for!");
                            application.pause();
                            application.clrscr();
                            SearchHotel searchHotelPage = new SearchHotel(application);
                            searchHotelPage.display();
                            searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.pause();
                    }
                    return null;
                }),

                new Option("Browse all hotel",(obj) -> {
                    application.clrscr();
                    try {
                        ArrayList<HashMap<String, String>> returnHotelList = new HotelManager(application.getConnection()).browseAllHotels();
                        if (returnHotelList.size() != 0) {
                            Option[] ops = new Option[returnHotelList.size()+1];
                            for (int i = 0; i < returnHotelList.size(); i++) {
                                HashMap<String, String> returnHotel = returnHotelList.get(i);
                                ops[i] = new Option("Hotel Name: "+returnHotel.get("name")+", Location: "+returnHotel.get("location"), (obj_) -> {
                                    application.clrscr();
                                    System.out.println("Hotel information:");
                                    System.out.println();
                                    System.out.println("Hotel ID: "+returnHotel.get("hotelID"));
                                    System.out.println("Hotel Name: "+returnHotel.get("name"));
                                    System.out.println("Hotel Description: "+returnHotel.get("description"));
                                    System.out.println("Star Rank: "+returnHotel.get("starRank"));
                                    System.out.println("Hotel Image: "+returnHotel.get("image"));
                                    System.out.println("Hotel Location: "+returnHotel.get("location"));

                                    application.pause();
                                    System.out.println();
                                    new SeeRoomInformation(application).display(new RoomManager(application.getConnection()),returnHotel.get("hotelID"));
                                    return null;
                                });
                            }
                            ops[ops.length-1] = new Option("Back", (obj_) -> {
                                application.clrscr();
                                SearchHotel searchHotelPage = new SearchHotel(application);
                                searchHotelPage.display();
                                searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                                return null;
                            });
                            application.clrscr();
                            PagodaAppMenu hotelMenu = new PagodaAppMenu(application, "Hotel List",ops);
                            hotelMenu.display();
                            hotelMenu.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        } else {
                            System.out.println("There is no hotel!");
                            application.pause();
                            application.clrscr();
                            SearchHotel searchHotelPage = new SearchHotel(application);
                            searchHotelPage.display();
                            searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.pause();
                    }
                    return null;
                }),

                new Option("Good Hotel (Average Vote >=7)",(obj) -> {
                    application.clrscr();
                    try {
                        ArrayList<HashMap<String, String>> returnHotelList = new Evaluation(application.getConnection()).viewHotelsWithAverageVoteBiggerThanOrEqual7();
                        if (returnHotelList.size() != 0) {
                            Option[] ops = new Option[returnHotelList.size()+1];
                            for (int i = 0; i < returnHotelList.size(); i++) {
                                HashMap<String, String> returnHotel = returnHotelList.get(i);
                                ops[i] = new Option("Hotel Name: "+returnHotel.get("name")+", Average Vote: "+returnHotel.get("averageVote"), (obj_) -> {
                                    application.clrscr();
                                    System.out.println("Hotel information:");
                                    System.out.println();
                                    System.out.println("Hotel ID: "+returnHotel.get("hotelID"));
                                    System.out.println("Hotel Name: "+returnHotel.get("name"));
                                    System.out.println("Hotel Location: "+returnHotel.get("location"));
                                    System.out.println("Hotel Image: "+returnHotel.get("image"));
                                    System.out.println("Average Vote: "+returnHotel.get("averageVote"));

                                    application.pause();
                                    System.out.println();
                                    new SeeRoomInformation(application).display(new RoomManager(application.getConnection()),returnHotel.get("hotelID"));
                                    return null;
                                });
                            }
                            ops[ops.length-1] = new Option("Back", (obj_) -> {
                                application.clrscr();
                                SearchHotel searchHotelPage = new SearchHotel(application);
                                searchHotelPage.display();
                                searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                                return null;
                            });
                            application.clrscr();
                            PagodaAppMenu hotelMenu = new PagodaAppMenu(application, "Hotel List",ops);
                            hotelMenu.display();
                            hotelMenu.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        } else {
                            System.out.println("There is no hotel having average vote above or equal 7!");
                            application.pause();
                            application.clrscr();
                            SearchHotel searchHotelPage = new SearchHotel(application);
                            searchHotelPage.display();
                            searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.pause();
                    }
                    return null;
                }),

                new Option("Luxury Hotel (Star Rank >= 4)",(obj) -> {
                    application.clrscr();
                    try {
                        ArrayList<HashMap<String, String>> returnHotelList = new HotelManager(application.getConnection()).viewHotelsWithStarRankBiggerThanOrEqual4();
                        if (returnHotelList.size() != 0) {
                            Option[] ops = new Option[returnHotelList.size()+1];
                            for (int i = 0; i < returnHotelList.size(); i++) {
                                HashMap<String, String> returnHotel = returnHotelList.get(i);
                                ops[i] = new Option("Hotel Name: "+returnHotel.get("name")+", Star Rank: "+returnHotel.get("starRank"), (obj_) -> {
                                    application.clrscr();
                                    System.out.println("Hotel information:");
                                    System.out.println();
                                    System.out.println("Hotel ID: "+returnHotel.get("hotelID"));
                                    System.out.println("Hotel Name: "+returnHotel.get("name"));
                                    System.out.println("Hotel Description: "+returnHotel.get("description"));
                                    System.out.println("Star Rank: "+returnHotel.get("starRank"));
                                    System.out.println("Hotel Image: "+returnHotel.get("image"));
                                    System.out.println("Hotel Location: "+returnHotel.get("location"));

                                    application.pause();
                                    System.out.println();
                                    new SeeRoomInformation(application).display(new RoomManager(application.getConnection()),returnHotel.get("hotelID"));
                                    return null;
                                });
                            }
                            ops[ops.length-1] = new Option("Back", (obj_) -> {
                                application.clrscr();
                                SearchHotel searchHotelPage = new SearchHotel(application);
                                searchHotelPage.display();
                                searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                                return null;
                            });
                            application.clrscr();
                            PagodaAppMenu hotelMenu = new PagodaAppMenu(application, "Hotel List",ops);
                            hotelMenu.display();
                            hotelMenu.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        } else {
                            System.out.println("There is no hotel having star rank above or equal 4!");
                            application.pause();
                            application.clrscr();
                            SearchHotel searchHotelPage = new SearchHotel(application);
                            searchHotelPage.display();
                            searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.pause();
                    }
                    return null;
                }),

                new Option("Subscribed Hotel", (obj) -> {
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
                                    System.out.println("Hotel information:");
                                    System.out.println();
                                    System.out.println("Hotel ID: "+returnHotel.get("hotelID"));
                                    System.out.println("Hotel Name: "+returnHotel.get("name"));
                                    System.out.println("Hotel Description: "+returnHotel.get("description"));
                                    System.out.println("Star Rank: "+returnHotel.get("starRank"));
                                    System.out.println("Hotel Image: "+returnHotel.get("image"));
                                    System.out.println("Hotel Location: "+returnHotel.get("location"));

                                    application.pause();
                                    System.out.println();
                                    new SeeRoomInformation(application).display(new RoomManager(application.getConnection()),returnHotel.get("hotelID"));
                                    return null;
                                });
                            }
                            ops[ops.length-1] = new Option("Back", (obj_) -> {
                                application.clrscr();
                                SearchHotel searchHotelPage = new SearchHotel(application);
                                searchHotelPage.display();
                                searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
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
                            SearchHotel searchHotelPage = new SearchHotel(application);
                            searchHotelPage.display();
                            searchHotelPage.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        application.pause();
                    }
                    return null;
                }),

                new Option("Back", (obj) -> {
                    application.clrscr();
                    Guest guest = new Guest(application);
                    guest.display();
                    guest.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                    return null;
                })
        });
    }
}
