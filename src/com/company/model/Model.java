package com.company.model;

import com.company.controller.state.State;

import java.util.Random;

public class Model {
    private int height = 8;
    private int width = 8;
    private int n_bomb = 10;
    private int countOpened;
    private int countFlag = n_bomb;

    private int[][] field;
    private State[][] states;

    private boolean reset;
    private boolean exploded;
    private boolean running;

    private final Random random;

    public Model() {
        states = new State[width][height];
        field = new int[width][height];
        random = new Random();
        restart();
    }

    private void generate(int x, int y) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[j][i] = 0;
            }
        }
//        В случайном порядке размещаем бомбы на поле.

        int countBomb = 0;
        while (countBomb < n_bomb) {
            int rx = random.nextInt(width);
            int ry = random.nextInt(height);
            if (field[rx][ry] != -1 & (rx != x | ry != y)) {
                field[rx][ry] = -1;
                countBomb++;
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[j][i] != -1) {
                    field[j][i] = countBombNeighbours(j, i);
                }
            }
        }

        reset = false;
    }


    public void move(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            return;
        }

        if (reset) {
            generate(x, y);
        }

        if (states[x][y] == State.FLAG) {
            return;
        }

        if (!running) {
            return;
        }

        if (isBomb(x, y)) {
            explode(x, y);
        } else {
            open(x, y);
        }
        checkWin();

    }


    private void open(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            return;
        }

        if (states[x][y] == State.OPEN || states[x][y] == State.FLAG) {
            return;
        }

        states[x][y] = State.OPEN;
        countOpened++;

        if (field[x][y] == 0) {
            open(x - 1, y);
            open(x + 1, y);
            open(x, y - 1);
            open(x, y + 1);
            open(x - 1, y - 1);
            open(x + 1, y - 1);
            open(x - 1, y + 1);
            open(x + 1, y + 1);
        }
    }

    private void checkWin() {
        if (countOpened == height * width - n_bomb | exploded) {
            running = false;
        }
    }

    private void explode(int x, int y) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isBomb(j, i)) {
                    states[j][i] = State.BOMB;
                }
            }
        }
        states[x][y] = State.BOMB_ACTIVATE;
        exploded = true;

    }


    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 & x < width & y >= 0 & y < height;
    }


    private boolean isBomb(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            return false;
        }
        return field[x][y] == -1;
    }

    private int countBombNeighbours(int x, int y) {
        int countBomb = 0;

//        countBomb += isBomb(x + 1, y) ? 1 : 0;

        if (isBomb(x - 1, y)) countBomb++;
        if (isBomb(x + 1, y)) countBomb++;
        if (isBomb(x, y - 1)) countBomb++;
        if (isBomb(x, y + 1)) countBomb++;
        if (isBomb(x - 1, y - 1)) countBomb++;
        if (isBomb(x + 1, y - 1)) countBomb++;
        if (isBomb(x - 1, y + 1)) countBomb++;
        if (isBomb(x + 1, y + 1)) countBomb++;

        return countBomb;
    }


    public int[][] getField() {
        int[][] copyField = new int[field.length][field[0].length];
        for (int i = 0; i < copyField.length; i++) {
            for (int j = 0; j < copyField[0].length; j++) {
                copyField[j][i] = field[j][i];
            }
        }
        return copyField;
    }


    public State[][] getStates() {

        State[][] copyField = new State[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                copyField[j][i] = states[j][i];
            }
        }
        return copyField;
    }


    public boolean getExploded() {
        return exploded;
    }

    public boolean getRunning() {
        return running;
    }

    public void restart() {
        reset = true;
        exploded = false;
        running = true;
        countOpened = 0;
        countFlag = n_bomb;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                states[j][i] = State.CLOSE;
            }
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void setFlag(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            return;
        }
        if (!running) {
            return;
        }
        if (states[x][y] == State.CLOSE) {
            states[x][y] = State.FLAG;
            countFlag--;
        } else {
            if (states[x][y] == State.FLAG) {
                states[x][y] = State.CLOSE;
                countFlag++;
            }
        }
    }

    public int getFlagCount() {
        return countFlag;
    }


}
