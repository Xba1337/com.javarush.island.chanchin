package world.map;

import lombok.Getter;
import world.animals.Animal;
import world.plants.Grass;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Cell {

    private int x;
    private int y;
    private final Set<Animal> containedAnimals = Collections.synchronizedSet(new HashSet<>());
    private final Set<Grass> containedPlants = Collections.synchronizedSet(new HashSet<>());
    private final Lock lock = new ReentrantLock(true);

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
