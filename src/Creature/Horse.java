package Creature;

import Ecosystem.Ecosystem;

import java.util.ArrayList;
import java.util.Random;

public class Horse extends Creature {

    private static int horseAmount = 0;

    public Horse(int habitatRow, int habitatColumn) {
        super(habitatRow, habitatColumn);
        level = 2;
        life = 5;
        name = "H";
        horseAmount++;
    }

    public boolean move(ArrayList<Creature> validMoveRangeOptions) {
        round++;
        life--;
        name = "H";
        while (!validMoveRangeOptions.isEmpty()) {
            Random random = new Random();
            int option = random.nextInt(validMoveRangeOptions.size());
            Creature creature = validMoveRangeOptions.get(option);
            validMoveRangeOptions.remove(option);
            if (random.nextBoolean()) {
                habitatRow = creature.habitatRow;
                habitatColumn = creature.habitatColumn;
                return true;
            }
        }
        return false;
    }

    public boolean eat(Creature creature) {
        if (creature instanceof Grass) {
            life++;
            return true;
        }
        return false;
    }

    public Creature breed(int habitatRow, int habitatColumn) {
        Horse horse = new Horse(habitatRow, habitatColumn);
        horse.name = "h";
        return horse;
    }

    public boolean isDead() {
        return life == 0;
    }

    public static void setHorseAmount(int horseAmount) {
        Horse.horseAmount = horseAmount;
    }

    public static int getHorseAmount() {
        return horseAmount;
    }

    public String toString() {
        return name;
    }
}
