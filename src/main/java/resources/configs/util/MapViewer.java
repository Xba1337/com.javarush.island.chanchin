package resources.configs.util;

import world.animals.Animal;
import world.animals.herbivorous.*;
import world.constants.Constants;
import world.map.Cell;
import world.map.WorldMap;

public class MapViewer {

    private final WorldMap worldMap;

    public MapViewer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void view(WorldMap worldMap){
        Cell[][] cells = worldMap.getCells();
        for (Cell[] row:
             cells) {
            for (Cell cell:
                 row) {

                System.out.println(cell.getContainedAnimals().values());

            }
        }

    }

    public int animalDefinition(Class<? extends Animal> animalType, Cell cell) {
        int counter = 0;

        for (Class<? extends Animal> type :
                cell.getContainedAnimals()
                    .keySet()) {
            if (animalType == type) {
                counter++;
            }
        }

        return counter;
    }


}

/*System.out.println("╔" + "═".repeat(7) + "╗");
        System.out.println("‖" + animalDefinition(Boar.class, cell) + animalDefinition(Bull.class, cell)
        + animalDefinition(Caterpillar.class, cell) + animalDefinition(Deer.class, cell) + "‖");
        System.out.println("‖" + animalDefinition(Duck.class, cell) + animalDefinition(Goat.class, cell)
        + animalDefinition(Hare.class, cell) + animalDefinition(Horse.class, cells[x][y]) + " " + " " + "‖");
        System.out.println("‖" + animalDefinition(Mouse.class, cell) + animalDefinition(Sheep.class, cell)
        + animalDefinition(Bear.class, cell) + animalDefinition(Eagle.class, cells[x][y]) + " " + " " + "‖");
        System.out.println("‖" + animalDefinition(Fox.class, cell) + animalDefinition(Snake.class, cell)
        + animalDefinition(Wolf.class, cell) + " " + " " + "‖");
        System.out.println("╚" + "═".repeat(7) + "╝"); */