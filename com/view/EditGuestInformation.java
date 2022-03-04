package com.view;

import com.model.GuestManager;
import com.model.HotelManager;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

import java.sql.SQLException;
import java.util.HashMap;

public class EditGuestInformation extends ConsolePage {

    public EditGuestInformation(PagodaApp application) {
        super(application, "Edit Guest Information");
    }

    @Override
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

            try {
                    int i = Integer.parseInt(textReader.input("What do you want to change ?  "));
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
                            put("phoneNumber", textReader.input("Phone Number"));
                        }});
                    }
                    if (i == 4) {
                        guest.editEmail(currentUser, new HashMap<String, String>(){{
                            put("email", textReader.input("Email"));
                        }});
                    }
                    if (i == 5) {
                        guest.editCccd(currentUser, new HashMap<String, String>(){{
                            put("cccd", textReader.input("National ID"));
                        }});
                    }
                System.out.println();
                System.out.println("Edit Guest Information successfully!");

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println();
                System.out.println("Some error occurred!");
                application.writeLog(e);
            }

            application.pause();

    }
}
