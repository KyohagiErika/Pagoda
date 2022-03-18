package com.view;

import com.model.Evaluation;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;
import com.viewmodel.application.PagodaValidater;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class FeedBack extends ConsolePage {

    public FeedBack(PagodaApp application) {
        super(application, "FeedBack");
    }

    public void display(Evaluation evaluation) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        try {
            evaluation.toDatabase(new HashMap<String,String>(){{
                put("guestUsername", currentUser.get("username"));
                put("hotelID",textReader.input("hotelID"));
                put("evaluationDay", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                put("vote",textReader.input("Vote",(inputData)-> {
                    return PagodaValidater.validate((String) inputData,PagodaValidater.VOTE_PATTERN);
                }));
                put("feedback",textReader.input("Feedback"));
            }});
            
            System.out.println("Feedback successfully!");
        } catch (SQLException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        } catch (LambdaIncompatibleTypeException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        }
        application.pause();
    }
}
