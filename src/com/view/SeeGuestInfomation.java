package com.view;

import com.processor.applicaion.ConsoleApplication;
import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;
import com.viewmodel.application.PagodaAppMenu;

public class SeeGuestInfomation extends ConsolePage {
    public SeeGuestInfomation(PagodaApp application) {
        super(application, "See Guest Infomation");
    }

    public void display() {
        super.display();
        System.out.println("See Guest Information!!!!!!!");
        application.pause();
    }
}
