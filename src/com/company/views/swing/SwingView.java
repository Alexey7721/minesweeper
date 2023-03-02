package com.company.views.swing;

import com.company.controller.state.State;
import com.company.views.interfaces.IView;
import com.company.views.interfaces.OpenListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwingView implements IView {
    private JButton[][] button;
    private int M = 8;
    private int N = 8;
    private JLabel lbWinner;
    private JLabel n_Flag;
    private JButton btnRestart;
    private JButton btnExit;
    JFrame frame;


    public SwingView() {
        frame = new JFrame("Сапёр");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(515, 600));
        frame.add(panel);
        frame.pack();

        JPanel panel1 = new JPanel(null);
        panel1.setPreferredSize(new Dimension(500, 500));
        panel1.setBounds(10, 10, 500, 500);
        frame.add(panel.add(panel1));
        panel.add(panel1);

        int width = panel1.getWidth() / N;
        int height = panel1.getHeight() / M;
        button = new JButton[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int x = width * j;
                int y = height * i;
                button[j][i] = new JButton("");
                button[j][i].setActionCommand(j + ":" + i);
                button[j][i].setBounds(x, y, width, height);
                panel1.add(button[j][i]);

            }
        }

        lbWinner = new JLabel();
        lbWinner.setBounds(225, 515, 100, 30);
        panel.add(lbWinner);
        lbWinner.setVisible(true);
        lbWinner.setText("");

        n_Flag = new JLabel();
        n_Flag.setBounds(245, 545, 60, 30);
        panel.add(n_Flag);
        n_Flag.setVisible(true);

        btnRestart = new JButton("restart");
        btnRestart.setBounds(10, 515, 120, 60);
        panel.add(btnRestart);
        btnRestart.setVisible(true);
        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbWinner.setText("");
            }
        });


        btnExit = new JButton("exit");
        btnExit.setBounds(385, 515, 120, 60);
        panel.add(btnExit);
        btnExit.setVisible(true);


        frame.setVisible(true);
    }

    public void printField(int[][] field, State[][] states) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                switch (states[j][i]) {
                    case CLOSE:
                        button[j][i].setText("");
                        button[j][i].setEnabled(true);
                        break;
                    case OPEN:
                        button[j][i].setText(field[j][i] == 0 ? " " : String.valueOf(field[j][i]));
                        button[j][i].setEnabled(false);
                        break;
                    case BOMB_ACTIVATE:
                        button[j][i].setText("!*!");
                        break;
                    case BOMB:
                        button[j][i].setText("*");
                        break;
                    case FLAG:
                        button[j][i].setText("?");
                        break;
                }
            }
        }
    }


    @Override
    public void setOpenListener(OpenListener listener) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                char cx = actionCommand.charAt(0);
                char cy = actionCommand.charAt(2);
                int x = cx - '0';
                int y = cy - '0';

                listener.open(x, y);
            }
        };

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                button[j][i].addActionListener(actionListener);
            }
        }


    }

    public void winner(boolean w) {
        if (w) {
            lbWinner.setText("Выиграли");
        } else {
            lbWinner.setText("Проиграли");
        }
    }

    public void setRestartListener(ActionListener listener) {
        btnRestart.addActionListener(listener);
    }

    public void setExitListener(ActionListener listener) {
        btnExit.addActionListener(listener);
    }


    public void setFlagListener(OpenListener listener) {
        MouseListener mouseListener = new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 3) {
                    JButton button = (JButton) e.getSource();
                    String actionCommand = button.getActionCommand();
                    char cx = actionCommand.charAt(0);
                    char cy = actionCommand.charAt(2);
                    int x = cx - '0';
                    int y = cy - '0';

                    listener.open(x, y);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };


        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                button[j][i].addMouseListener(mouseListener);
            }
        }


    }
    public void setCountFlag(int value) {
        n_Flag.setText(String.valueOf(value));
    }



}
