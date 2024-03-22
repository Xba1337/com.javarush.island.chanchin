package world.plants;


import world.Eatable;

public class Grass implements Eatable {

    private final String name;

    private final String icon;

    private final double weight;

    private final double maxNumberInCell;

    private int currentHeight;

    public Grass(String name, String icon, double weight, double maxNumberInCell) {
        this.name = name;
        this.icon = icon;
        this.weight = weight;
        this.maxNumberInCell = maxNumberInCell;
        this.currentHeight = 50;
    }

    public void multiply() {

    }

}
