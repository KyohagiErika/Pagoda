package com.view;

import com.model.Evaluation;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class EditFeedBack extends ConsolePage {
    public EditFeedBack(PagodaApp application) {
        super(application, "Edit Your Feedback Here ");
    }

    public void display(Evaluation evaluation) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        try {
            evaluation.editRecord(currentUser, new HashMap<String,String>(){{
                put("vote", textReader.input("vote"));
                put("Feedback", textReader.input("feedback"));
                put("guestUsername", currentUser.get("username"));
                put("hotelID", textReader.input("hotelID"));
                put("evaluationDay", textReader.input("Evaluation Day"));
            }});
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println();
            System.out.println("Some error occurred!");
            application.writeLog(e);
        }
        application.pause();

    }
}
