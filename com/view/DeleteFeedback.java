package com.view;

import com.model.Evaluation;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.processor.applicaion.Option;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DeleteFeedback extends ConsolePage {

    public DeleteFeedback(PagodaApp application) {
        super(application, "Delete Your Feedback");
    }

    public void display(Evaluation evaluation) {
        application.clrscr();
        super.display();
        try {
            ArrayList<HashMap<String, String>> feedbackList = evaluation.viewEvaluationsOfAGuest(new HashMap<>() {{
                put("guestUsername",((HashMap<String, String>) (application.getSession()).get("currentUser")).get("username"));
            }});
            Option[] ops = new Option[feedbackList.size()+1];
            for (int i = 0; i < feedbackList.size(); i++) {
                int finalI = i;
                ops[i] = new Option(feedbackList.get(i).get("name") + ", " + feedbackList.get(i).get("evaluationDay"), (obj) -> {
                    HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
                    System.out.println("Deleting Your FeedBack : .....");
                    System.out.println("Are you sure ?");
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
                        try {
                            evaluation.deleteRecord(new HashMap<String, String>() {{
                                put("guestUsername", currentUser.get("username"));
                                put("hotelID", feedbackList.get(finalI).get("hotelId"));
                                put("evaluationDay", feedbackList.get(finalI).get("evaluationDay"));
                            }});
                        } catch (SQLException e) {
                            application.writeLog(e);
                            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                        }
                        System.out.println();
                        System.out.println("Delete FeedBack successfully!");
                    }
                    return null;
                });
            }
            ops[feedbackList.size()] = new Option("Back",(obj -> null));
            application.clrscr();
            new PagodaAppMenu((PagodaApp) application,"",ops) {{
                display();
                forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
            }};
        } catch (SQLException e) {
            application.writeLog(e);
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        }
        application.pause();
    }
}
