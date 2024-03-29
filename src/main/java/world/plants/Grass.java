package world.plants;


import lombok.ToString;
import util.Randomizer;
import world.Organism;
import world.constants.Constants;
import world.map.Cell;

import java.util.Set;

@ToString
public class Grass extends Organism {

    private final double weight;

    private int currentHeight;

    public Grass() {
        this.weight = Constants.BASE_FOR_PLANTS.get(Grass.class)[0];
        this.currentHeight = Randomizer.getDeciRandom(0, 10);
    }

    public void multiply(Cell cell) {
        Grass grass = this;
        currentHeight = currentHeight + 10;
        if (currentHeight >= 100) {
            if (checkNumberOfPlants(grass, cell)) {
                currentHeight = 1;
                Grass plant = new Grass();
                cell.getContainedPlants()
                    .add(plant);
            }
        }
    }

    public boolean checkNumberOfPlants(Grass grass, Cell cell) {
        cell.getLock()
            .lock();
        Set<Grass> setOfPlants = cell.getContainedPlants();
        int plantCounter = 0;
        try {

            for (Grass plants :
                    setOfPlants) {
                if (plants.getClass() == grass.getClass()) {
                    plantCounter++;
                }
            }
        } finally {
            cell.getLock()
                .unlock();
        }
        return plantCounter < Constants.BASE_FOR_PLANTS.get(grass.getClass())[1];
    }

}
