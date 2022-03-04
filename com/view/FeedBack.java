package com.view;

import com.model.Evaluation;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class FeedBack extends ConsolePage {

    public FeedBack(PagodaApp application) {
        super(application, "FeedBack");
    }

    @Override
    public void display(Evaluation evaluation) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        try {
            evaluation.toDatabase(new HashMap<String,String>(){{
               // put("guestUsername", currentUser.get("username"));
               // put("hotelID",currentUser.get("hotelID"));
                put("evaluationDay",textReader.input("Evaluation Day"));
                put("vote",textReader.input("Vote"));
                put("feedback",textReader.input("Feedback"));

            }});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, String>> records = null;
        try {
             records = evaluation.fromDatabase(new HashMap<String,String>(){{
                 put("hotelID",currentUser.get("hotelID"));
            }});
             System.out.println("Your Feedback");
            System.out.println("userame: " + records.get(Integer.parseInt("guestUsername")));
            System.out.println("HotelID: " + records.get(Integer.parseInt("HotelID")));
            System.out.println("Evaluation Day: " + records.get(Integer.parseInt("evaluationDay")));
            System.out.println("Vote: " + records.get(Integer.parseInt("vote")));
            System.out.println("Feedback: " + records.get(Integer.parseInt("feedback")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        application.pause();


    }
}
