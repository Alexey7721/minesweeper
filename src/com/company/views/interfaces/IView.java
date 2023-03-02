package com.company.views.interfaces;

import com.company.controller.state.State;

import java.awt.event.ActionListener;

public interface IView {
    void printField(int[][] field, State[][] states);
    void setOpenListener(OpenListener listener);
    void winner(boolean w);
    void setRestartListener(ActionListener listener);
    void setExitListener(ActionListener listener);
    void setFlagListener(OpenListener listener);
    void setCountFlag(int value);
}
