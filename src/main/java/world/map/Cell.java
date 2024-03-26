package world.map;

import world.Organism;
import world.animals.Animal;
import world.plants.Grass;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {

    private int x;

    private int y;

    private final Map<Class<? extends Animal>, Animal> containedAnimals = new HashMap<>();
    private final Map<Class<? extends Grass>, Grass> containedPlants = new HashMap<>();

    private final Lock lock = new ReentrantLock(true);


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Set<Organism> init(){
        lock.lock();
        Set<Organism> organisms = new HashSet<>();
        organisms.addAll(containedAnimals.values());
        organisms.addAll(containedPlants.values());

        return organisms;
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


    public Lock getLock() {
        return lock;
    }
}
