package com.pagoda;

import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.Serialization;
import com.processor.applicaion.Validater;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaValidater;
import com.viewmodel.exception.PagodaAppException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            PagodaApp application = new PagodaApp();
            application.start();
        } catch (PagodaAppException | IOException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            new Scanner(System.in).nextLine();
            System.exit(0);
        }
    }
}
