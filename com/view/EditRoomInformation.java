package com.view;

import com.model.RoomManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.processor.applicaion.Option;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;
import com.viewmodel.application.PagodaValidater;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static com.viewmodel.application.PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE;

public class EditRoomInformation extends ConsolePage {
    public EditRoomInformation(PagodaApp application) {
        super(application, "Edit Room Information here");
    }
    public void display(RoomManager room, String hotelID) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();


        try {
            ArrayList<HashMap<String, String>> viewRoomList = room.viewRoom(new HashMap<String, String>() {{
                put("hotelID", hotelID);
            }});
            if(viewRoomList.size()!=0) {
                System.out.println();
                System.out.println("Enter the room you want to edit: ");
                Option[] ops = new Option[viewRoomList.size()+1];
                for(int i=0; i< viewRoomList.size();i++) {
                    HashMap<String, String> viewRoom = viewRoomList.get(i);
                    ops[i] = new Option("Room ID: "+viewRoom.get("roomID")+", Room Name: "+viewRoom.get("name"),(obj_) -> {
                        application.clrscr();
                        System.out.println("Room Information:");
                        System.out.println();
                        System.out.println("Room ID: "+viewRoom.get("roomID"));
                        System.out.println("Room Name: "+viewRoom.get("name"));
                        System.out.println("Room Size: "+viewRoom.get("roomSize"));
                        System.out.println("Price Per Night: "+viewRoom.get("pricePerNight"));
                        System.out.println("Room Capacity: "+viewRoom.get("capacity"));
                        System.out.println("Room Image: "+viewRoom.get("image"));

                        application.pause();
                        System.out.println();
                        String inputMsg = "Enter the choice you want to edit: ";
                        System.out.println("1.Room Name");
                        System.out.println("2.Room Size");
                        System.out.println("3.Price Per Night");
                        System.out.println("4.Capacity");
                        System.out.println("5.Image");
                        System.out.println("6.All");
                        System.out.println("7.Back");
                        Scanner reader = new Scanner(System.in);
                        int optionIndex;
                        do {
                            optionIndex = 0;
                            if (inputMsg.compareTo("") != 0) {
                                System.out.print(inputMsg + ": ");
                                System.out.println();
                            }
                            if (reader.hasNextInt()) {
                                optionIndex = reader.nextInt();
                            }
                            reader.nextLine();
                        } while (!(optionIndex > 0 && optionIndex <= 7));
                        try {
                            switch (optionIndex) {
                                case 1:
                                    room.editRecord(new HashMap<>() {{
                                        put("roomID", viewRoom.get("roomID"));
                                        put("hotelID", hotelID);
                                    }}, new HashMap<>() {{
                                        put("name", textReader.input("Room Name"));
                                    }});
                                    break;
                                case 2:
                                    room.editRecord(new HashMap<>() {{
                                        put("roomID", viewRoom.get("roomID"));
                                        put("hotelID", hotelID);
                                    }}, new HashMap<>() {{
                                        put("roomSize", textReader.input("Room Size(m^2)", (inputData) -> {
                                            return PagodaValidater.validate((String) inputData, PagodaValidater.ROOM_SIZE_PATTERN);
                                        }));
                                    }});
                                    break;
                                case 3:
                                    room.editRecord(new HashMap<>() {{
                                        put("roomID", viewRoom.get("roomID"));
                                        put("hotelID", hotelID);
                                    }}, new HashMap<>() {{
                                        put("pricePerNight", textReader.input("Price Per Night(VND)", (inputData) -> {
                                            return PagodaValidater.validate((String) inputData, PagodaValidater.PRICE_PER_NIGHT_PATTERN);
                                        }));
                                    }});
                                    break;
                                case 4:
                                    room.editRecord(new HashMap<>() {{
                                        put("roomID", viewRoom.get("roomID"));
                                        put("hotelID", hotelID);
                                    }}, new HashMap<>() {{
                                        put("capacity", textReader.input("Capacity", (inputData) -> {
                                            return PagodaValidater.validate((String) inputData, PagodaValidater.CAPACITY_PATTERN);
                                        }));
                                    }});
                                    break;
                                case 5:
                                    room.editRecord(new HashMap<>() {{
                                        put("roomID", viewRoom.get("roomID"));
                                        put("hotelID", hotelID);
                                    }}, new HashMap<>() {{
                                        put("image", textReader.input("Hotel Image(Link)", (inputData) -> {
                                            return PagodaValidater.validate((String) inputData, PagodaValidater.URL_PATTERN);
                                        }));
                                    }});
                                    break;
                                case 6:
                                    room.editRecord(new HashMap<>() {{
                                        put("roomID", viewRoom.get("roomID"));
                                        put("hotelID", hotelID);
                                    }}, new HashMap<>() {{
                                        put("name", textReader.input("Room Name"));
                                        put("roomSize", textReader.input("Room Size(m^2)", (inputData) -> {
                                            return PagodaValidater.validate((String) inputData, PagodaValidater.ROOM_SIZE_PATTERN);
                                        }));
                                        put("pricePerNight", textReader.input("Price Per Night(VND)", (inputData) -> {
                                            return PagodaValidater.validate((String) inputData, PagodaValidater.PRICE_PER_NIGHT_PATTERN);
                                        }));
                                        put("capacity", textReader.input("Capacity", (inputData) -> {
                                            return PagodaValidater.validate((String) inputData, PagodaValidater.CAPACITY_PATTERN);
                                        }));
                                        put("image", textReader.input("Hotel Image(Link)", (inputData) -> {
                                            return PagodaValidater.validate((String) inputData, PagodaValidater.URL_PATTERN);
                                        }));
                                    }});
                                    break;
                                case 7:
                                    application.clrscr();
                                    RoomInformation roomInformation = new RoomInformation((PagodaApp) application);
                                    roomInformation.display();
                                    roomInformation.forwardUser(PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                                    break;
                            }
                            System.out.println();
                            System.out.println("Edit Room Information successfully!");
                        } catch (SQLException e) {
                            System.out.println();
                            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                            application.writeLog(e);
                        } catch (LambdaIncompatibleTypeException e) {
                            System.out.println();
                            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                            application.writeLog(e);
                        }
                        application.pause();
                        return null;
                    });
                }
                ops[ops.length-1] = new Option("Back",(obj_)-> {
                    application.clrscr();
                    return null;
                });
                application.clrscr();
                PagodaAppMenu roomMenu = new PagodaAppMenu((PagodaApp) application,"Room List",ops);
                roomMenu.display();
                roomMenu.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);

            } else {
                System.out.println("There is no room in this hotel!");
                application.pause();
                application.clrscr();
            }
        } catch (SQLException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            application.pause();
        }
    }
}
