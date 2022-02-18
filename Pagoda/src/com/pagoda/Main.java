package com.pagoda;

import com.view.Home;

public class Main {

    public static void main(String[] args) {
        Home h = new Home();
        h.display();
        h.forwardUser("Input your choice");
    }
}
