package services;

import world.Organism;
import world.animals.Animal;
import world.map.Cell;
import world.plants.Grass;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class OrganismTask implements Runnable{


    private final Set<Organism> organismSet;

    private final Cell cell;

    public OrganismTask(Set<Organism> organismSet, Cell cell){
        this.organismSet = organismSet;
        this.cell = cell;
    }

    @Override
    public void run() {
        for (Organism organism:
             organismSet) {
            if (organism instanceof Animal){
                Animal animal = (Animal) organism;
                if (animal.healthCheck(cell)){
                    return;
                }
                if (animal.eat(cell)){
                    animal.multiply(cell);
                } else {
                    animal.move(cell);
                }
            } else {
                Grass plant = (Grass) organism;
                plant.multiply(cell);
            }
        }
    }
}
