package com.view;

import com.model.HotelManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class DeleteHotel extends ConsolePage {
    public DeleteHotel(PagodaApp application) {
        super(application, "Delete Hotel");
    }

    public void display(HotelManager hotel) {
        application.clrscr();
        super.display();
        HashMap<String, String> currentUser= (HashMap<String, String>)application.getSession().get("currentUser");
        System.out.println("Deleting Your Hotel Account : .....");
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
                hotel.deleteRecord(currentUser);
                System.out.println();
                System.out.println("Delete Hotel successfully!");
            } catch (SQLException e) {
                System.out.println();
                System.out.println(e.getMessage());
            }
            application.pause();
        } else {
            application.clrscr();
            new HotelInformation((PagodaApp) application) {{
                display();
                forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
            }};
        }
    }
}
