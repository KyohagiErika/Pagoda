package com.view;

import com.model.GuestManager;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.HashMap;

public class DeleteAccount extends ConsolePage {
    public DeleteAccount(PagodaApp application) {
        super(application, "Delete Account");
    }

    @Override
    public void display(GuestManager guest) {
        application.clrscr();
        super.display();
        HashMap<String, String> currentUser= (HashMap<String, String>)application.getSession().get("currentUser");
        try {
            guest.deleteRecord(new HashMap<String, String>(){{
                put("username",currentUser.get("username"));
            }});
            System.out.println("Delete Guest successfully!");
        } catch (SQLException e) {
            System.out.println();
            System.out.println("Some error occurred!");
            application.writeLog(e);
        }
        application.pause();
    }
}
