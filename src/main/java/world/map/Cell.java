package world.map;

import world.animals.Animal;
import world.plants.Grass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Cell {

    private int x;

    private int y;

    private Map<Class<? extends Animal>, Animal> containedAnimals = new HashMap<>();
    private Map<Class<? extends Grass>, Grass> containedPlants = new HashMap<>();


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

    public Map<Class<? extends Grass>, Grass> getContainedPlants() {
        return containedPlants;
    }
}
