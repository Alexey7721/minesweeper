package com.company;

import com.company.controller.Controller;
import com.company.model.Model;
import com.company.views.interfaces.IView;
import com.company.views.swing.SwingView;

public class Main {

    public static void main(String[] args) {

        Model model = new Model();
        IView view = new SwingView();

//        IView view = new ViewConsole();

        Controller controller = new Controller(view, model);
        controller.start();

        int i = 6;
        short a = (short) i;

        Short a1 = a;
        int i1 = a;
    }
}
