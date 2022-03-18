package com.view;

import com.model.GuestManager;
import com.model.HotelManager;
import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.ConsolePage;
import com.processor.applicaion.Serialization;
import com.processor.enumeration.ConsoleInputMode;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaValidater;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class changePassword extends ConsolePage {
    public changePassword(PagodaApp application) {
        super(application, "Change Your Password Here ");
    }

    public void display(GuestManager guest) {
        application.clrscr();
        super.display();
        ConsoleInput passReader = new ConsoleInput(ConsoleInputMode.PASSWORD);
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        try {
            while (passReader.input("Enter your password").compareTo((String) Serialization.decode(currentUser.get("pass"))) != 0) {
                System.out.println("Wrong Password!");
            }
            String newPass = Serialization.encode(passReader.input("New Password(contains at least 10 characters,one lowercase, one uppercase, one number and one special character)", (inputData) -> {
                return PagodaValidater.validate((String) inputData, PagodaValidater.PASSWORD_PATTERN);
            }));
            while (newPass.compareTo( currentUser.get("pass"))==0) {
                System.out.println("New password must be different from old password!");
                newPass = Serialization.encode(passReader.input("New Password(contains at least 10 characters,one lowercase, one uppercase, one number and one special character)", (inputData) -> {
                    return PagodaValidater.validate((String) inputData, PagodaValidater.PASSWORD_PATTERN);
                }));
            }

            while (Serialization.encode(passReader.input("Re-enter your new password")).compareTo(newPass) != 0) {
                System.out.println("Wrong Re-enter Password!");
            }

            String finalNewPass = newPass;
            guest.changePass(currentUser, new HashMap<String, String>() {{
                put("pass", finalNewPass);
            }});
            System.out.println();
            System.out.println("Edit your password successfully!");

        } catch (SQLException e) {
            System.out.println();
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            application.writeLog(e);
        } catch (LambdaIncompatibleTypeException e) {
            System.out.println();
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            application.writeLog(e);
        } catch (ClassNotFoundException e) {
            System.out.println();
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            application.writeLog(e);
        } catch (IOException e) {
            System.out.println();
            System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
            application.writeLog(e);
        }
        application.pause();
    }

    public void display(HotelManager hotel) {
        application.clrscr();
        super.display();
        ConsoleInput passReader = new ConsoleInput(ConsoleInputMode.PASSWORD);
        HashMap<String, String> currentUser = (HashMap<String, String>) application.getSession().get("currentUser");
        try {
            while (passReader.input("Enter your password").compareTo((String) Serialization.decode(currentUser.get("pass"))) != 0) {
                System.out.println("Wrong Password!");
            }
            String newPass = Serialization.encode(passReader.input("New Password(contains at least 10 characters,one lowercase, one uppercase, one number and one special character)", (inputData) -> {
                return PagodaValidater.validate((String) inputData, PagodaValidater.PASSWORD_PATTERN);
            }));
            while (newPass.compareTo( currentUser.get("pass"))==0) {
                System.out.println("New password must be different from old password!");
                newPass = Serialization.encode(passReader.input("New Password(contains at least 10 characters,one lowercase, one uppercase, one number and one special character)", (inputData) -> {
                    return PagodaValidater.validate((String) inputData, PagodaValidater.PASSWORD_PATTERN);
                }));
            }

            while (Serialization.encode(passReader.input("Re-enter your new password")).compareTo(newPass) != 0) {
                System.out.println("Wrong Re-enter Password!");
            }

            String finalNewPass = newPass;
            hotel.changePass(currentUser, new HashMap<String, String>() {{
                put("pass", finalNewPass);
            }});
            System.out.println();
            System.out.println("Edit your password successfully!");
            } catch (SQLException e) {
                System.out.println();
                System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                application.writeLog(e);
            } catch (LambdaIncompatibleTypeException e) {
                System.out.println();
                System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                application.writeLog(e);
            } catch (IOException e) {
                System.out.println();
                System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                application.writeLog(e);
            } catch (ClassNotFoundException e) {
                System.out.println();
                System.out.println(ConsoleApplication.DEFAULT_ERROR_MESSAGE);
                application.writeLog(e);
            }
            application.pause();
    }
}