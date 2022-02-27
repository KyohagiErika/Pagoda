package com.view;

import com.processor.applicaion.ConsolePage;
import com.viewmodel.application.PagodaApp;

public class CustomerInformation extends ConsolePage {
    public CustomerInformation(PagodaApp application) {
        super(application, "Customer Information");
    }

    @Override
    public void display() {
        application.clrscr();
        super.display();
        System.out.println("Customer Information");
        application.pause();
    }
}
