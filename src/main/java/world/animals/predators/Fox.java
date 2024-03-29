package world.animals.predators;


import util.Randomizer;
import world.Organism;
import world.animals.Animal;
import world.animals.Herbivorous;
import world.animals.Predator;
import world.constants.Constants;
import world.map.Cell;

import java.util.Iterator;
import java.util.Map;


public class Fox extends Predator {

    public Fox() {
        super();
        this.animal = Fox.class;
    }

    @Override
    public boolean eat(Cell cell) {
        cell.getLock()
            .lock();

        Animal predator = this;

        boolean isAte = false;

        try {
            Iterator<Animal> victimIterator = cell.getContainedAnimals()
                                                  .iterator();
            while (victimIterator.hasNext() && ! isAte) {
                Animal victim = victimIterator.next();
                if (victim instanceof Herbivorous && victim != null) {
                    Herbivorous herbivorous = (Herbivorous) victim;
                    Map<Class<? extends Organism>, Integer> mapOfChances = Constants.CONTAINER_OF_CHANCES.get(predator.getClass());
                    int chancesToEat = mapOfChances.get(herbivorous.getClass());
                    if (Randomizer.getRandom(chancesToEat)) {
                        double additionalWeight = Constants.BASE_FOR_ANIMALS.get(victim.getClass())[0];
                        if ((currentStomachVolume + additionalWeight) > Constants.BASE_FOR_ANIMALS.get(predator.getClass())[3]) {
                            currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(predator.getClass())[3];
                        } else {
                            currentStomachVolume = currentStomachVolume + additionalWeight;
                        }
                        victimIterator.remove();
                        isAte = true;
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
