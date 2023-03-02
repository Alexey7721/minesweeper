package com.company.views.console;

import com.company.controller.state.State;
import com.company.views.interfaces.IView;
import com.company.views.interfaces.OpenListener;

import java.awt.event.ActionListener;

public class ViewConsole implements IView {

    public void printField(int[][] field, State[][] states) {
        System.out.print("   ");
        for (int j = 0; j < field[0].length; j++) {
            System.out.printf("%2d", j);
            if(j == 9) System.out.print(" ");
        }
        String bomb = "*";
        System.out.println();
        for (int i = 0; i < field.length; i++) {
//            System.out.println(i);
            System.out.printf("%2d |", i);
            for (int j = 0; j < field[0].length; j++) {
                String s = " ";
                switch (states[j][i]){
                    case CLOSE:
                        s = "#";
                        break;
                    case OPEN:
                        s = field[j][i] == 0 ? " " : String.valueOf(field[j][i]);
                        break;
                    case BOMB_ACTIVATE:
                    case BOMB:
                        s = bomb;
                        break;
                    case FLAG:
                        s = "?";
                        break;
                }



                System.out.print(s + "|");

            }
            System.out.println();
        }


    }

    @Override
    public void setOpenListener(OpenListener listener) {

    }

    @Override
    public void winner(boolean w) {

    }

    @Override
    public void setRestartListener(ActionListener listener) {

    }
    public void setExitListener(ActionListener listener) {

    }
    public void setFlagListener(OpenListener listener) {

    }

    @Override
    public void setCountFlag(int value) {

    }

}
