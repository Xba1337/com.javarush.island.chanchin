package resources.configs.util;

import lombok.SneakyThrows;
import world.animals.Animal;
import world.constants.Constants;
import world.map.Cell;
import world.map.WorldMap;
import world.plants.Grass;

import java.lang.reflect.Constructor;



public class WorldCreator {

    private int worldSizeX;
    private int worldSizeY;

    public final WorldMap worldMap;

    public WorldCreator(int worldSizeX, int worldSizeY) {
        this.worldSizeX = worldSizeX;
        this.worldSizeY = worldSizeY;
        worldMap = new WorldMap(worldSizeX, worldSizeY);
        generateCells();
    }

    public void generateCells() {
        Cell[][] cells = worldMap.getCells();
        for (int y = 0; y < Constants.WORLD_SIZE_Y; y++) {
            for (int x = 0; x < Constants.WORLD_SIZE_X; x++) {
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
