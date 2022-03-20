package com.view;

import com.model.BookRoomManager;
import com.model.RoomManager;
import com.model.SubscriptionManager;
import com.processor.applicaion.*;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;
import com.viewmodel.application.PagodaValidater;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class BookRoom extends ConsolePage {

    public BookRoom(PagodaApp application) {
        super(application, "Book Room");
    }

    public void display(SubscriptionManager subscription, BookRoomManager bookRoom, RoomManager room, String hotelID, String roomID) {
        //application.clrscr();
        //super.display();
        System.out.println();
        System.out.println("Book Room : ");
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime checkIn, checkOut;
        try {
            do {
//DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.parse(item.get("evaluationDay"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                String stringCheckIn = new ConsoleInput().input("Check In (dd-MM-yyyy HH)",(inputData) -> {
                    return PagodaValidater.validate((String)inputData,PagodaValidater.DATE_PATTERN);}) + ":00:00";
                String stringCheckOut = new ConsoleInput().input("Check Out (dd-MM-yyyy HH)",(inputData) -> {
                    return PagodaValidater.validate((String)inputData,PagodaValidater.DATE_PATTERN);}) + ":00:00";

                checkIn = LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.parse(stringCheckIn, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))),dateTimeFormatter);
                checkOut = LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.parse(stringCheckOut, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))),dateTimeFormatter);

            } while (!bookRoom.validateCheckInCheckOutRoom(hotelID,roomID,checkIn,checkOut));
            LocalDateTime finalCheckIn = checkIn;
            LocalDateTime finalCheckOut = checkOut;

               // int pricePerNight =bookRoom.seePricePerNightOfRoom(hotelID,roomID);

            int pricePerNight = Integer.parseInt(room.fromDatabase(new HashMap<>(){{
                put("hotelID",hotelID);
                put("roomID", roomID);
            }}).get(0).get("pricePerNight"));

            Duration duration = Duration.between(finalCheckIn, finalCheckOut);

                System.out.println(duration.toMinutes());
                System.out.println(pricePerNight);

            System.out.printf("Your Total Money is %d VND.\n",duration.toMinutes()*pricePerNight/(24*60));

            System.out.println("Do you want to continue ?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            String inputMsg = "Input your choice";
            Scanner reader = new Scanner(System.in);
            int optionIndex;
            do {
                optionIndex = 0;
                if (inputMsg.compareTo("") != 0) {
                    System.out.print(inputMsg + ": ");
                }
                if (reader.hasNextInt()) {
                    optionIndex = reader.nextInt();
                }
                reader.nextLine();
            } while (!(optionIndex > 0 && optionIndex <= 2));
            if(optionIndex == 1) {
                String paymentMethod = new ConsoleInput().input("Payment Method(CASH/CREDIT)",(inputData) -> {
                    return PagodaValidater.validate((String)inputData,PagodaValidater.PAYMENT_METHOD_PATTERN);});
                String note = new ConsoleInput().input("Note");
                bookRoom.toDatabase(new HashMap<String,String>(){{
                    put("bookRoomID", Serialization.getRandomIDString(10));
                    put("guestUsername",currentUser.get("username"));
                    put("roomID",roomID);
                    put("hotelID",hotelID);
                    put("bookDay", LocalDateTime.now().format(dateTimeFormatter));
                    put("checkIn", finalCheckIn.format(dateTimeFormatter));
                    put("checkOut", finalCheckOut.format(dateTimeFormatter));
                    put("paymentMethod",paymentMethod);
                    put("noteOfGuests",note);
                }});
                System.out.println();
                System.out.println("Book Room Successfully!");
                if (subscription.fromDatabase(new HashMap<>() {{
                    put("guestUsername",currentUser.get("username"));
                    put("hotelId",hotelID);
                }}).size() == 0) {
                    System.out.println("Do you want to subscribe the hotel?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    if (Integer.parseInt(new ConsoleInput().input("Input your choice",(inputData) -> Validater.validate(inputData.toString(),"[1-2]"))) == 1) {
                        subscription.toDatabase(new HashMap<>() {{
                            put("guestUsername",currentUser.get("username"));
                            put("hotelId",hotelID);
                        }});
                        System.out.println("Subscribe hotel successfully!");
                    }
                }
                application.pause();
            }
        } catch (SQLException | LambdaIncompatibleTypeException e) {
            System.out.println(e.getMessage());
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            System.out.println();
            System.out.println("Invalid Date or Wrong Format");
            System.out.println(e.getMessage());
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        }
        application.clrscr();
        Guest guestPage = new Guest((PagodaApp) application);
        guestPage.display();
        guestPage.forwardUser(PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
    }
}
