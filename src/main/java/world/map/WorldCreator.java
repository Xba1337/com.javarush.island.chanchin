package world.map;

import lombok.SneakyThrows;
import resources.configs.util.Randomizer;
import world.animals.Animal;
import world.animals.herbivorous.*;
import world.animals.predators.*;
import world.constants.Constants;
import world.plants.Grass;

import java.lang.reflect.Constructor;
import java.util.Iterator;


public class WorldCreator {

    private int worldSizeX;
    private int worldSizeY;

    public final WorldMap worldMap;

    public WorldCreator(int worldSizeX, int worldSizeY) {
        this.worldSizeX = worldSizeX;
        this.worldSizeY = worldSizeY;
        worldMap = new WorldMap(worldSizeX, worldSizeY);
        generateCells();
        for (int i = 0; i < getWorldSizeY(); i++) {
            for (int j = 0; j < getWorldSizeX(); j++) {
                Cell cell1 = WorldMap.getCell(j, i);

                cell1.getContainedPlants().get(Grass.class).multiply(cell1);
            }

        }


    }

    public void generateCells() {
        Cell[][] cells = worldMap.getCells();
        for (int y = 0; y < getWorldSizeY(); y++) {
            for (int x = 0; x < getWorldSizeX(); x++) {
                cells[x][y] = new Cell(x, y);
            }
        }
        containCells(cells);

    }

    public void containCells(Cell[][] cells) {
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                for (Class<? extends Animal> type :
                        Constants.TYPES) {
                    cells[x][y].getContainedAnimals()
                               .put(type, generateOrganisms(type));
                    cells[x][y].getContainedPlants()
                               .put(Grass.class, generatePlant());
                }

            }
        }
    }

    @SneakyThrows
    private Animal generateOrganisms(Class<? extends Animal> type) {
        Animal animal = null;

        int randomNumOfAnimals = Randomizer.getRandom(0, (int) Constants.BASE_FOR_ANIMALS.get(type)[1]);
        for (int i = 0; i < randomNumOfAnimals; i++) {

            Constructor<? extends Animal> animalConstructor = type.getDeclaredConstructor();
            animal = animalConstructor.newInstance();

        }

        return animal;
    }

    private Grass generatePlant() {
        Grass plant = null;
        int randomNumberOfAnimals = Randomizer.getRandom(0, (int) Constants.BASE_FOR_PLANTS.get(Grass.class)[1]);
        for (int i = 0; i < randomNumberOfAnimals; i++) {
            plant = new Grass();
        }
        return plant;
    }

    public int getWorldSizeX() {
        return worldSizeX;
    }

    public int getWorldSizeY() {
        return worldSizeY;
    }
}
