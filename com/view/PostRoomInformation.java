package com.view;

import com.model.RoomManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaValidater;

import java.sql.SQLException;
import java.util.HashMap;

public class PostRoomInformation extends ConsolePage {
    public PostRoomInformation(PagodaApp application) { super(application,"Post Room Information"); }

    public void display(RoomManager room) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>)application.getSession().get("currentUser");
        try {
            room.toDatabase(new HashMap<>() {{
                put("roomID",textReader.input("Room ID(contains 3-10 digits) ",(inputData) -> {
                    return PagodaValidater.validate((String)inputData,PagodaValidater.ROOM_ID_PATTERN);}));
                put("name",textReader.input("Room Name"));
                put("roomSize",textReader.input("Room Size(m^2)",(inputData) -> {
                    return PagodaValidater.validate((String)inputData,PagodaValidater.ROOM_SIZE_PATTERN);}));
                put("pricePerNight",textReader.input("Price Per Night(VND)",(inputData) -> {
                    return PagodaValidater.validate((String)inputData,PagodaValidater.PRICE_PER_NIGHT_PATTERN);}));
                put("capacity",textReader.input("Capacity",(inputData) -> {
                    return PagodaValidater.validate((String)inputData,PagodaValidater.CAPACITY_PATTERN);}));
                put("image",textReader.input("Hotel Image(Link)",(inputData) -> {
                    return PagodaValidater.validate((String)inputData,PagodaValidater.URL_PATTERN);}));
                put("hotelID",currentUser.get("hotelID"));
            }});
            System.out.println();
            System.out.println("Post Room Information successfully!");
        } catch (SQLException e) {
            System.out.println("Error... Duplicate Room ID");
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            application.writeLog(e);
        } catch (LambdaIncompatibleTypeException e) {
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            application.writeLog(e);
        }
        application.pause();
    }
}
