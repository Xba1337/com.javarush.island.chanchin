package world.plants;


import resources.configs.util.Randomizer;
import world.Eatable;
import world.animals.Animal;
import world.constants.Constants;
import world.map.Cell;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Set;

public class Grass implements Eatable {

    private final double weight;

    private int currentHeight;

    public Grass() {
        this.weight = Constants.BASE_FOR_PLANTS.get(Grass.class)[0];
        this.currentHeight = Randomizer.getDeciRandom(0, 10);
    }

    public void multiply(Cell cell) {
        Grass grass = this;
        currentHeight = currentHeight + 10;
        if (currentHeight >= 100){
            if(checkNumberOfPlants(grass, cell)){
                currentHeight = 1;
                Grass plant = new Grass();
                cell.getContainedPlants()
                    .put(Grass.class, plant);
                System.out.println("трава " + plant + " выросла высотой " + plant.currentHeight);
            }
        }
    }

    public boolean checkNumberOfPlants(Grass grass, Cell cell){
        Set<Map.Entry<Class<? extends Grass>, Grass>> mapOfPlants = cell.getContainedPlants().
                                                                           entrySet();
        int plantCounter = 0;
        for (Map.Entry<Class<? extends Grass>, Grass> animalEntry :
                mapOfPlants) {
            if (animalEntry.getKey() == grass.getClass()) {
                plantCounter++;
            }
        }

        return plantCounter < Constants.BASE_FOR_PLANTS.get(grass.getClass())[1];
    }

}
