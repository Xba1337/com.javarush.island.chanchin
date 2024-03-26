package world.map;

import world.Organism;
import world.animals.Animal;
import world.animals.herbivorous.*;
import world.animals.predators.*;
import world.constants.Constants;
import world.plants.Grass;

import java.util.Set;

public class WorldMap {

    private static Cell[][] cells;

    public WorldMap(int x, int y) {
        cells = new Cell[x][y];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public static Cell getCell(int currentX, int currentY) {
        return cells[currentX][currentY];
    }


    public void print() {
        for (Cell[] c:
             getCells()) {
            for (Cell cell:
                 c) {
                System.out.println("[]");
            }
        }

    }

    public String animalDefinition(Class<? extends Animal> animalType, Cell cell) {
        int counter = 0;
        String result = ".";
        for (Class<? extends Animal> type :
                cell.getContainedAnimals()
                    .keySet()) {
            if (animalType == type) {
                counter++;
            }
        }
        if (counter > 0) {
            result = Constants.STRINGS_FOR_ANIMALS.get(animalType)[1];
        }
        return result;
    }

    public String plantDefinition(Class<? extends Grass> plant, Cell cell) {
        int counter = 0;
        String result = ".";
        for (Class<? extends Grass> type :
                cell.getContainedPlants()
                    .keySet()) {
            if (plant == type) {
                counter++;
            }
        }
        if (counter > 0) {
            result = Constants.STRINGS_FOR_PLANTS.get(plant)[1];
        }
        return result;
    }
}
