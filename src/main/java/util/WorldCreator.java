package util;

import lombok.Getter;
import lombok.SneakyThrows;
import world.animals.Animal;
import world.constants.Constants;
import world.map.Cell;
import world.map.WorldMap;
import world.plants.Grass;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;


public class WorldCreator {
    @Getter
    private int worldSizeX;
    @Getter
    private int worldSizeY;

    public final WorldMap worldMap;

    public WorldCreator(int worldSizeX, int worldSizeY) {
        this.worldSizeX = worldSizeX;
        this.worldSizeY = worldSizeY;
        worldMap = new WorldMap(worldSizeX, worldSizeY);
        generateCells();
    }

    private void generateCells() {
        Cell[][] cells = worldMap.getCells();
        for (int x = 0; x < Constants.WORLD_SIZE_X; x++) {
            for (int y = 0; y < Constants.WORLD_SIZE_Y; y++) {
                cells[x][y] = new Cell(y, x);
            }
        }
        containCells(cells);
    }

    private void containCells(Cell[][] cells) {
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                for (Class<? extends Animal> type :
                        Constants.TYPES) {
                    cells[y][x].getContainedAnimals()
                               .addAll(generateOrganisms(type));
                    cells[y][x].getContainedPlants()
                               .addAll(generatePlant());
                }
            }
        }
    }

    @SneakyThrows
    private Set<Animal> generateOrganisms(Class<? extends Animal> type) {
        Set<Animal> animalSet = new HashSet<>();
        Animal animal = null;

        int randomNumOfAnimals = Randomizer.getRandom(0, (int) Constants.BASE_FOR_ANIMALS.get(type)[1]);
        for (int i = 0; i < randomNumOfAnimals; i++) {
            Constructor<? extends Animal> animalConstructor = type.getDeclaredConstructor();
            animal = animalConstructor.newInstance();
            animalSet.add(animal);
        }
        return animalSet;
    }

    private Set<Grass> generatePlant() {
        Set<Grass> plantSet = new HashSet<>();
        Grass plant = null;

        int randomNumberOfPlants = Randomizer.getRandom(0, (int) Constants.BASE_FOR_PLANTS.get(Grass.class)[1]);
        for (int i = 0; i < randomNumberOfPlants; i++) {
            plant = new Grass();
            plantSet.add(plant);
        }
        return plantSet;
    }
}
