package com.view;

import com.model.HotelManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaValidater;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import static com.viewmodel.application.PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE;


public class EditHotelInformation extends ConsolePage {
    public EditHotelInformation(PagodaApp application) {
        super(application, "Edit Hotel Information here");
    }
    public void display(HotelManager hotel) {
        application.clrscr();
        display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        String inputMsg = "Enter the choice to edit: ";
        System.out.println("1.Last Name");
        System.out.println("2.First Name");
        System.out.println("3.Phone Number");
        System.out.println("4.Email");
        System.out.println("5.National ID");
        System.out.println("6.Hotel Name");
        System.out.println("7.Hotel Description");
        System.out.println("8.Star Rank");
        System.out.println("9.Hotel Image");
        System.out.println("10.Hotel Location");
        System.out.println("11.All");
        System.out.println("12.Back");
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
        } while (!(optionIndex > 0 && optionIndex <= 12));
        try {
            switch (optionIndex) {
                case 1:
                    hotel.editRecord(currentUser,new HashMap<String, String>(){{
                        put("lastName",textReader.input("Last Name"));
                    }});
                    break;
                case 2:
                    hotel.editRecord(currentUser,new HashMap<String, String>(){{
                        put("firstName",textReader.input("First Name"));
                    }});
                   break;
                case 3:
                    hotel.editRecord(currentUser,new HashMap<String, String>(){{
                        put("phoneNumber",textReader.input("Phone Number",(inputData) -> {
                            return PagodaValidater.validate((String)inputData,PagodaValidater.PHONE_NUMBER_PATTERN);}));
                    }});
                   break;
                 case 4:
                     hotel.editRecord(currentUser,new HashMap<String, String>(){{
                         put("email",textReader.input("Email",(inputData) -> {
                             return PagodaValidater.validate((String)inputData,PagodaValidater.EMAIL_PATTERN);}));
                     }});
                    break;
                case 5:
                    hotel.editRecord(currentUser,new HashMap<String, String>(){{
                        put("cccd",textReader.input("National ID(CCCD)",(inputData) -> {
                            return PagodaValidater.validate((String)inputData,PagodaValidater.CCCD_PATTERN);}));
                    }});
                     break;
                case 6:
                    hotel.editRecord(currentUser,new HashMap<String, String>(){{
                        put("name",textReader.input("Hotel Name"));
                    }});
                    break;
                case 7:
                    hotel.editRecord(currentUser,new HashMap<String, String>(){{
                        put("description",textReader.input("Hotel Description"));
                    }});
                    break;
                case 8:
                    hotel.editRecord(currentUser,new HashMap<String, String>(){{
                        put("starRank",textReader.input("Star Rank(1-5)",(inputData) -> {
                            return PagodaValidater.validate((String)inputData,PagodaValidater.STAR_RANK_PATTERN);}));
                    }});
                    break;
                case 9:
                    hotel.editRecord(currentUser,new HashMap<String, String>(){{
                        put("image", textReader.input("Hotel Image(Link)",(inputData) -> {
                            return PagodaValidater.validate((String)inputData,PagodaValidater.URL_PATTERN );}));
                    }});
                    break;
                case 10:
                    hotel.editRecord(currentUser,new HashMap<String, String>(){{
                        put("location",textReader.input("Hotel Location"));
                    }});
                    break;
                case 11:
                    hotel.editRecord(currentUser,new HashMap<String, String>(){{
                        put("lastName",textReader.input("Last Name"));
                        put("firstName",textReader.input("First Name"));
                        put("phoneNumber",textReader.input("Phone Number",(inputData) -> {
                            return PagodaValidater.validate((String)inputData,PagodaValidater.PHONE_NUMBER_PATTERN);}));
                        put("email",textReader.input("Email",(inputData) -> {
                            return PagodaValidater.validate((String)inputData,PagodaValidater.EMAIL_PATTERN);}));
                        put("cccd",textReader.input("National ID(CCCD)",(inputData) -> {
                            return PagodaValidater.validate((String)inputData,PagodaValidater.CCCD_PATTERN);}));
                        put("name",textReader.input("Hotel Name"));
                        put("description",textReader.input("Hotel Description"));
                        put("starRank",textReader.input("Star Rank(1-5)",(inputData) -> {
                            return PagodaValidater.validate((String)inputData,PagodaValidater.STAR_RANK_PATTERN);}));
                        put("image", textReader.input("Hotel Image(Link)",(inputData) -> {
                            return PagodaValidater.validate((String)inputData,PagodaValidater.URL_PATTERN );}));
                        put("location",textReader.input("Hotel Location"));
                    }});
                    break;
                case 12:
                        application.clrscr();
                        HotelInformation hotelInformation = new HotelInformation((PagodaApp) application);
                        hotelInformation.display();
                        hotelInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        break;
             }
            System.out.println();
            System.out.println("Edit Hotel Information successfully!");

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
    }

}
