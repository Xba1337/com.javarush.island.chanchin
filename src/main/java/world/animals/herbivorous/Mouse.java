package world.animals.herbivorous;


import util.Randomizer;
import world.Organism;
import world.animals.Animal;
import world.animals.Herbivorous;
import world.constants.Constants;
import world.map.Cell;
import world.plants.Grass;

import java.util.Iterator;
import java.util.Map;


public class Mouse extends Herbivorous {

    public Mouse() {
        super();
        this.animal = Mouse.class;
    }

    @Override
    public boolean eat(Cell cell) {
        cell.getLock()
            .lock();

        Animal herbivorous = this;

        boolean isAte = false;

        try {
            Iterator<Grass> grassIterator = cell.getContainedPlants()
                                                .iterator();
            Iterator<Animal> victimIterator = cell.getContainedAnimals()
                                                  .iterator();

            if (Randomizer.getRandom()) {
                while (grassIterator.hasNext() && ! isAte) {
                    Grass victim = grassIterator.next();
                    if (victim != null) {
                        double additionalWeight = Constants.BASE_FOR_PLANTS.get(victim.getClass())[0];
                        if ((currentStomachVolume + additionalWeight) > Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3]) {
                            currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3];
                            isAte = true;
                        } else {
                            currentStomachVolume = currentStomachVolume + additionalWeight;
                        }
                        grassIterator.remove();
                    }
                }
            } else {
                while (victimIterator.hasNext() && ! isAte) {
                    Animal victim = victimIterator.next();
                    if (victim instanceof Herbivorous && victim instanceof Caterpillar && victim != null) {
                        Herbivorous caterpillar = (Herbivorous) victim;
                        Map<Class<? extends Organism>, Integer> mapOfChances = Constants.CONTAINER_OF_CHANCES.get(herbivorous.getClass());
                        int chancesToEat = mapOfChances.get(caterpillar.getClass());
                        if (Randomizer.getRandom(chancesToEat)) {
                            double additionalWeight = Constants.BASE_FOR_ANIMALS.get(victim.getClass())[0];
                            if ((chancesToEat + additionalWeight) > Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3]) {
                                currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3];
                                isAte = true;
                            } else {
                                currentStomachVolume = currentStomachVolume + additionalWeight;
                            }
                            victimIterator.remove();
                            isAte = true;
                        }

                    }
                }
            }
        } finally {
            cell.getLock()
                .unlock();
        }
        return isAte;
    }
}
