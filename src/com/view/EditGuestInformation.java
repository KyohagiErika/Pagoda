package com.view;

import com.model.GuestManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.processor.applicaion.Validater;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaValidater;

import java.sql.SQLException;
import java.util.HashMap;

public class EditGuestInformation extends ConsolePage {

    public EditGuestInformation(PagodaApp application) {
        super(application, "Edit Guest Information");
    }

    public void display(GuestManager guest) {
        application.clrscr();
        super.display();
        ConsoleInput textReader = new ConsoleInput();
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        HashMap<String, String> records = null;
        try {
            records = guest.fromDatabase(new HashMap<String, String>() {{
                put("username", currentUser.get("username"));
                put("pass", currentUser.get("pass"));
            }}).get(0);
            System.out.println();
            System.out.println("1. Last Name: " + records.get("lastName"));
            System.out.println("2. First Name: " + records.get("firstName"));
            System.out.println("3. Phone Number: " + records.get("phoneNumber"));
            System.out.println("4. Email: " + records.get("email"));
            System.out.println("5. National ID: " + records.get("cccd"));
            System.out.println("6. Back");

        } catch (SQLException e) {
            System.out.println();
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }

            try {
                int i = 7;
                do {
                    i = Integer.parseInt(textReader.input("Enter the choice to edit: ",(inputData) -> {
                        return Validater.validate(inputData.toString(),"[0-9]");
                    }));
                    if (i == 1) {
                        guest.editLastName(currentUser, new HashMap<String, String>() {{
                            put("lastName", textReader.input("Last Name"));
                        }});
                    }
                    if (i == 2) {
                        guest.editFirstName(currentUser, new HashMap<String, String>(){{
                            put("firstName", textReader.input("First Name"));
                        }});

                    }
                    if (i == 3) {
                        guest.editPhoneNumber(currentUser, new HashMap<String, String>(){{
                            put("phoneNumber", textReader.input("Phone Number", (inputData) -> Validater.validate(inputData.toString(), PagodaValidater.PHONE_NUMBER_PATTERN)));
                        }});
                    }
                    if (i == 4) {
                        guest.editEmail(currentUser, new HashMap<String, String>(){{
                            put("email", textReader.input("Email",(inputData) -> Validater.validate(inputData.toString(),PagodaValidater.EMAIL_PATTERN)));
                        }});
                    }
                    if (i == 5) {
                        guest.editCccd(currentUser, new HashMap<String, String>(){{
                            put("cccd", textReader.input("National ID(CCCD)",(inputData) -> Validater.validate(inputData.toString(),PagodaValidater.CCCD_PATTERN)));
                        }});
                    }
                    if (i == 6) {
                        application.clrscr();
                        new GuestInformation((PagodaApp) application) {{
                            display();
                            forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
                        }};
                    }
                } while (i > 6);
                System.out.println();
                System.out.println("Edit Guest Information successfully!");

            } catch (SQLException | LambdaIncompatibleTypeException e) {
                System.out.println(e.getMessage());
                System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            }
            application.pause();
            application.clrscr();
            new GuestInformation((PagodaApp) application) {{
                display();
                forwardUser(PAGODA_MENU_DEFAULT_INPUT_MESSAGE);
            }};
    }
}
