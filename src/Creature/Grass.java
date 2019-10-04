package Creature;

public class Grass extends Creature {

    public Grass(int habitatRow, int habitatColumn) {
        super(habitatRow, habitatColumn);
        level = 1;
        life = 1;
        name = "g";
    }
}
