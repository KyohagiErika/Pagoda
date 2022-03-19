package com.pagoda;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsoleInput;
import com.processor.applicaion.Serialization;
import com.processor.exception.LambdaIncompatibleTypeException;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaValidater;
import com.viewmodel.exception.PagodaAppException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            PagodaApp application = new PagodaApp();
            application.start();
        } catch (PagodaAppException | IOException e) {
            System.out.println(e.getMessage());
            new Scanner(System.in).nextLine();
            System.exit(0);
        }
    }
}
