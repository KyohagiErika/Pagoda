package com.view;

import com.model.HotelManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Search extends ConsolePage {

    public Search(PagodaApp application) {
        super(application, "SearchHotel");
    }

    @Override
    public void display( HotelManager hotel) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> records = null;
        try {
             records= hotel.searchHotel(new HashMap<String,String>() {{
                 put("location",textReader.input("location"));
                 put("star_rank",textReader.input("star_rank"));
            }}).get(0);
            System.out.println();
            System.out.println("Result: ");
            System.out.println("hotelID: " + records.get("hotelID"));
            System.out.println("name: " + records.get("name"));
            System.out.println("description: " + records.get("description"));
            System.out.println("starRank: " + records.get("starRank"));
            System.out.println("image: " + records.get("image"));
            System.out.println("location: " + records.get("location"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        application.pause();
    }
}
