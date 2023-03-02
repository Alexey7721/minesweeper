package com.company.controller;

import com.company.views.interfaces.IView;
import com.company.model.Model;
import com.company.views.interfaces.OpenListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private IView view;
    private Model model;


    public Controller(IView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void start(){


        view.printField(model.getField(), model.getStates());
        view.setCountFlag(model.getFlagCount());

        view.setOpenListener(new OpenListener() {
            @Override
            public void open(int x, int y) {
                model.move(x, y);
                view.printField(model.getField(), model.getStates());

                if(!model.getRunning()) {
                    view.winner(!model.getExploded());
                }
            }
        });

        view.setRestartListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.restart();
                view.printField(model.getField(), model.getStates());
                view.setCountFlag(model.getFlagCount());
            }
        });

        view.setExitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.exit();
            }
        });


        view.setFlagListener(new OpenListener() {
            @Override
            public void open(int x, int y) {
                model.setFlag(x, y);
                view.printField(model.getField(), model.getStates());
                view.setCountFlag(model.getFlagCount());
            }
        });


    }


}
