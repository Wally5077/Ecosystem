package Creature;

import java.util.ArrayList;

public class Creature {

    int round = 0;
    int level;
    int life;
    int habitatRow;
    int habitatColumn;
    String name;

    public Creature(int habitatRow, int habitatColumn) {
        this.habitatRow = habitatRow;
        this.habitatColumn = habitatColumn;
        level = 0;
        life = 0;
        name = ".";
    }

    public boolean move(ArrayList<Creature> moveRange) {
        return false;
    }

    public boolean eat(Creature creature) {
        return false;
    }

    public boolean isDead() {
        return false;
    }

    public Creature breed(int habitatRow, int habitatColumn) {
        return this;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    public int getHabitatRow() {
        return habitatRow;
    }

    public int getHabitatColumn() {
        return habitatColumn;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return name;
    }
}
