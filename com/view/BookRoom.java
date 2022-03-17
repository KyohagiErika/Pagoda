package com.view;

import com.model.BookRoomManager;
import com.model.SubscriptionManager;
import com.processor.applicaion.*;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;
import com.viewmodel.application.PagodaValidater;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class BookRoom extends ConsolePage {

    public BookRoom(PagodaApp application) {
        super(application, "Book Room");
    }

    public void display(SubscriptionManager subscription, BookRoomManager bookRoom, String hotelID, String roomID) {
        //application.clrscr();
        //super.display();
        System.out.println();
        System.out.println("Book Room : ");
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime checkIn, checkOut;
        try {
            do {
                String stringCheckIn = new ConsoleInput().input("Check In (yyyy-MM-dd HH)",(inputData) -> {
                    return PagodaValidater.validate((String)inputData,PagodaValidater.DATE_PATTERN);}) + ":00:00";
                String stringCheckOut = new ConsoleInput().input("Check Out (yyyy-MM-dd HH)",(inputData) -> {
                    return PagodaValidater.validate((String)inputData,PagodaValidater.DATE_PATTERN);}) + ":00:00";

                String[] splits1 = stringCheckIn.split(" ");
                String[] splits2 = stringCheckOut.split(" ");
                String stringCheckInDate = splits1[0];
                String stringCheckOutDate = splits2[0];

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                Date checkInDate = sdf.parse(stringCheckInDate);
                Date checkOutDate = sdf.parse(stringCheckOutDate);

                checkIn = LocalDateTime.parse(stringCheckIn,dateTimeFormatter);
                checkOut = LocalDateTime.parse(stringCheckOut,dateTimeFormatter);

            } while (!bookRoom.validateCheckInCheckOutRoom(hotelID,roomID,checkIn,checkOut));
            LocalDateTime finalCheckIn = checkIn;
            LocalDateTime finalCheckOut = checkOut;
            String paymentMethod = new ConsoleInput().input("Payment Method(CASH/CREDIT)",(inputData) -> {
                return PagodaValidater.validate((String)inputData,PagodaValidater.PAYMENT_METHOD_PATTERN);});
            String note = new ConsoleInput().input("Note");

            Duration duration = Duration.between(checkIn, checkOut);
            long totalMoney = duration.toHours()*bookRoom.seePricePerNightOfRoom(hotelID,roomID)/24;
            System.out.println("Your Total Money is " + totalMoney + "VND.");
            System.out.println("Do you want to continue ?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            ConsoleInput binarReader = new ConsoleInput();
            if(Integer.parseInt(binarReader.input("Input your choice",(inputData) -> Validater.validate(inputData.toString(),"[1-2]"))) == 1) {
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
                    put("hoteId",hotelID);
                }}).size() == 0) {
                    System.out.println("Do you want to subscribe the hotel?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    if (Integer.parseInt(binarReader.input("Input your choice",(inputData) -> Validater.validate(inputData.toString(),"[1-2]"))) == 1) {
                        subscription.toDatabase(new HashMap<>() {{
                            put("guestUsername",currentUser.get("username"));
                            put("hotelId",hotelID);
                        }});
                        System.out.println("Subscribe hotel successfully!");
                    }
                }

            }
        } catch (SQLException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            System.out.println();
            System.out.println("Invalid Date or Wrong Format");
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        } catch (LambdaIncompatibleTypeException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        } catch (ParseException e) {
            System.out.println();
            System.out.println("Invalid Date or Wrong Format");
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        }
        application.pause();
        application.clrscr();
        Guest guestPage = new Guest((PagodaApp) application);
        guestPage.display();
        guestPage.forwardUser(PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
    }
}
