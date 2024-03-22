package world.map;

import resources.configs.util.Randomizer;
import world.animals.Animal;
import world.animals.herbivorous.Boar;
import world.animals.predators.Wolf;
import world.constants.Constants;
import world.plants.Grass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class WorldCreator {

    private final WorldMap worldMap;

    private int worldSizeX;
    private int worldSizeY;

    public WorldCreator(int worldSizeX, int worldSizeY) {
        this.worldSizeX = worldSizeX;
        this.worldSizeY = worldSizeY;
        this.worldMap = new WorldMap(worldSizeX, worldSizeY);
        generateCells();


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
                               .add(generatePlant());
                }

            }
        }
    }

    private Animal generateOrganisms(Class<? extends Animal> type) {
        Animal animal = null;

        int randomNumOfAnimals = Randomizer.getRandom(0, (int) Constants.BASE_FOR_ANIMALS.get(type)[1]);
        for (int i = 0; i < randomNumOfAnimals; i++) {
            try {
                Constructor<? extends Animal> animalConstructor = type.getDeclaredConstructor();
                animal = animalConstructor.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        return animal;
    }

    private Grass generatePlant() {
        Grass plant = null;
        int randomNumberOfAnimals = Randomizer.getRandom(0, (int) Constants.BASE_FOR_PLANTS.get(Grass.class)[1]);
        for (int i = 0; i < randomNumberOfAnimals; i++) {
            String paramName = Constants.STRINGS_FOR_PLANTS.get(Grass.class)[0];
            String paramIcon = Constants.STRINGS_FOR_PLANTS.get(Grass.class)[1];
            double paramWeight = Constants.BASE_FOR_PLANTS.get(Grass.class)[0];
            double paramMaxNumberInCell = Constants.BASE_FOR_PLANTS.get(Grass.class)[1];
            plant = new Grass(paramName, paramIcon, paramWeight, paramMaxNumberInCell);
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
