package services;

import world.Organism;
import world.animals.Animal;
import world.map.Cell;
import world.plants.Grass;

public class OrganismTask implements Runnable {

    private final Cell cell;

    private final Organism organism;

    public OrganismTask(Cell cell, Organism organism) {
        this.cell = cell;
        this.organism = organism;
    }

    @Override
    public void run() {

        if (organism instanceof Animal) {
            Animal animal = (Animal) organism;
            if (animal.healthCheck(cell)) {
                return;
            } else {
                if (animal.eat(cell)) {
                    animal.multiply(cell);
                }
                animal.move(cell);
            }
        } else {
            Grass plant = (Grass) organism;
            plant.multiply(cell);
        }

    }
}
