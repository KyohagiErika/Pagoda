package com.view;

import com.model.Evaluation;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.HashMap;

public class EditFeedBack extends ConsolePage {
    public EditFeedBack(PagodaApp application) {
        super(application, "Edit Your Feedback Here ");
    }

    @Override
    public void display(Evaluation evaluation) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        try {
            evaluation.editRecord(currentUser, new HashMap<String,String>(){{
               // put("guestUsername", currentUser.get("username"));
               // put("hotelID", currentUser.get("hotelID"));
               // put("evaluationDay", currentUser.get("Evaluation Day"));
                put("vote", textReader.input("Vote"));
                put("Feedback", textReader.input("Feedback"));
            }});
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
