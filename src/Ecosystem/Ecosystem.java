package Ecosystem;

import Creature.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ecosystem {

    private Creature[][] animalLand = new Creature[5][7];
    private int currentRound = 0;
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Ecosystem ecosystem = new Ecosystem();
        ecosystem.start();
    }

    private void start() {
        createCreature();
        createEcosystem();
        System.out.println("[開始] 物競天擇，適者生存");
        while (true) {
            readLine();
            currentRound++;
            System.out.println("[回合] 第 " + currentRound + " 回合 : ");
            updateMap();
        }
    }

    private void readLine() {
        if (scanner.nextLine() != null) {
            printMap();
        }
    }

    private void createCreature() {
        while (Horse.getHorseAmount() < 6) {
            int row;
            int column;
            do {
                row = random.nextInt(5);
                column = random.nextInt(7);
            } while (animalLand[row][column] != null);
            animalLand[row][column] = new Horse(row, column);
        }
    }

    private void createEcosystem() {
        for (int habitatRow = 0; habitatRow < animalLand.length; habitatRow++) {
            for (int habitatColumn = 0; habitatColumn < animalLand[habitatRow].length; habitatColumn++) {
                if (animalLand[habitatRow][habitatColumn] == null) {
                    animalLand[habitatRow][habitatColumn] = new Creature(habitatRow, habitatColumn);
                }
            }
        }
    }

    private void updateMap() {
        for (int habitatRow = 0; habitatRow < animalLand.length; habitatRow++) {
            for (int habitatColumn = 0; habitatColumn < animalLand[habitatRow].length; habitatColumn++) {
                develop(habitatRow, habitatColumn);
            }
        }
    }

    private void develop(int habitatRow, int habitatColumn) {
        Creature creature = animalLand[habitatRow][habitatColumn];
        if (creature.isDead()) {
            animalLand[habitatRow][habitatColumn] = new Creature(habitatRow, habitatColumn);
            Horse.setHorseAmount(Horse.getHorseAmount() - 1);
        }
        if ((creature.toString()).equals(".")) {
            animalLand[habitatRow][habitatColumn] = (random.nextInt(4) == 0) ? new Grass(habitatRow, habitatColumn) : creature;
        }
        if (currentRound > creature.getRound()) {
            if (creature.move(getValidMoveRange(creature))) {
                int newHabitatRow = creature.getHabitatRow();
                int newHabitatColumn = creature.getHabitatColumn();
                if (creature.eat(animalLand[newHabitatRow][newHabitatColumn])) {
                    if (random.nextBoolean()) {
                        animalLand[habitatRow][habitatColumn] = creature.breed(habitatRow, habitatColumn);
                        animalLand[habitatRow][habitatColumn].setRound(currentRound);
                    } else {
                        animalLand[habitatRow][habitatColumn] = new Creature(habitatRow, habitatColumn);
                    }
                } else {
                    animalLand[habitatRow][habitatColumn] = new Creature(habitatRow, habitatColumn);
                }
                animalLand[newHabitatRow][newHabitatColumn] = creature;
            }
        }
    }

    private ArrayList<Creature> getValidMoveRange(Creature originalCreature) {
        ArrayList<Creature> validMoveRange = new ArrayList<>(8);
        int habitatRow = originalCreature.getHabitatRow();
        int habitatColumn = originalCreature.getHabitatColumn();
        for (int validMoveRow = habitatRow - 1; validMoveRow <= (habitatRow + 1); validMoveRow++) {
            for (int validMoveColumn = habitatColumn - 1; validMoveColumn <= (habitatColumn + 1); validMoveColumn++) {
                try {
                    Creature targetCreature = animalLand[validMoveRow][validMoveColumn];
                    if (originalCreature != targetCreature) {
                        if (originalCreature.getLevel() > targetCreature.getLevel()) {
                            validMoveRange.add(targetCreature);
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
        return validMoveRange;
    }

    private void printMap() {
        System.out.print("0 ");
        for (int column = 1; column <= 7; column++) {
            System.out.print(column + " ");
        }
        System.out.println();
        for (int habitatRow = 0; habitatRow < animalLand.length; habitatRow++) {
            System.out.print(habitatRow + 1 + " ");
            for (int habitatColumn = 0; habitatColumn < animalLand[habitatRow].length; habitatColumn++) {
                Creature creature = animalLand[habitatRow][habitatColumn];
                System.out.print(creature + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
