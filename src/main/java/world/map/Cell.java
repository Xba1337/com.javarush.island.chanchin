package world.map;

import world.animals.Animal;
import world.plants.Grass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cell {

    private int x;

    private int y;

    private Map<Class<? extends Animal>,Animal> containedAnimals = new HashMap<>();
    private Set<Grass> containedPlants = new HashSet<>();


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Map<Class<? extends Animal>, Animal> getContainedAnimals() {
        return containedAnimals;
    }

    public Set<Grass> getContainedPlants() {
        return containedPlants;
    }
}
