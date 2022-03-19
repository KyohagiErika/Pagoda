package com.view;

import com.model.Evaluation;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class HotelSeeFeedback extends ConsolePage {
    /**
     * Create a console page.
     *
     * @param application The application to which the page belongs.
     */
    public HotelSeeFeedback(ConsoleApplication application) {
        super(application, "Feedback");
    }

    public void display(Evaluation evaluation) {
        application.clrscr();
        super.display();
        System.out.printf("| %20s | %20s | %20s | %20s |\n","Guest name","Day","Vote","Feedback");
        try {
            for (HashMap<String, String> item : evaluation.viewEvaluationOfGuest((HashMap<String, String>) application.getSession().get("currentUser"))) {
                System.out.printf("| %20s | %20s | %20s | %20s |\n",item.get("guestUsername"),DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.parse(item.get("evaluationDay"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))),
                        item.get("vote"),item.get("feedback"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
        }
        application.pause();
        application.clrscr();
        new Hotel((PagodaApp) application) {{
            display();
            forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
        }};
    }
}
