package com.view;

import com.model.Evaluation;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class SeeFeedback extends ConsolePage {
    public SeeFeedback(PagodaApp application) {
        super(application, "See Your Feedback here");
    }

    public void display(Evaluation evaluation) {
        application.clrscr();
        super.display();
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %20s |\n","#","Hotel ID","Hotel Name","Date","Vote","Feedback");
        try {
            ArrayList<HashMap<String, String>> feedbackList = evaluation.viewEvaluationsOfAGuest(new HashMap<>() {{
                put("guestUsername",((HashMap<String, String>) (application.getSession()).get("currentUser")).get("username"));
            }});
            if (feedbackList.size() == 0) {
                System.out.println("You haven't feedback anything yet!");
            } else {
                for (int i = 0; i < feedbackList.size();i++) {
                    System.out.printf("| %10d | %20s | %20s | %20s | %20s | %20s |\n",i+1, feedbackList.get(i).get("hotelId"),feedbackList.get(i).get("name"), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.parse(feedbackList.get(i).get("evaluationDay"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))),
                        feedbackList.get(i).get("vote"),feedbackList.get(i).get("feedback"));
                }
            }
        } catch (SQLException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        }
        application.pause();
    }
}
