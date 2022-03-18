package com.view;

import com.model.GuestManager;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import static com.viewmodel.application.PagodaAppMenu.PAGODA_MENU_DEFAULT_INPUT_MESSAGE;

public class DeleteAccount extends ConsolePage {
    public DeleteAccount(PagodaApp application) {
        super(application, "Delete Account");
    }

    public void display(GuestManager guest) {
        application.clrscr();
        super.display();
        HashMap<String, String> currentUser= (HashMap<String, String>)application.getSession().get("currentUser");
        System.out.println("Deleting Your Guest Account : .....");
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
        if(optionIndex == 2) {
            application.clrscr();
            GuestInformation guestInformation = new GuestInformation((PagodaApp) application);
            guestInformation.display();
            guestInformation.forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
        } else {
            try {
                guest.deleteRecord(currentUser);
                System.out.println();
                System.out.println("Delete Guest successfully!");
            } catch (SQLException e) {
                System.out.println();
                application.writeLog(e);
                application.pause();
            }
        }
    }
}
