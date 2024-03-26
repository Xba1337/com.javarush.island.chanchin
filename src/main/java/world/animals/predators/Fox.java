package world.animals.predators;


import resources.configs.util.Randomizer;
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
        this.animal = Fox.class;
    }

    @Override
    public boolean eat(Cell cell) {
        cell.getLock()
            .lock();

        Animal predator = this;

        boolean isAte = false;

        try {
            Iterator<Map.Entry<Class<? extends Animal>, Animal>> victimIterator = cell.getContainedAnimals()
                                                                                      .entrySet()
                                                                                      .iterator();

            while (victimIterator.hasNext() && ! isAte) {
                Map.Entry<Class<? extends Animal>, Animal> victim = victimIterator.next();
                if (victim.getValue() instanceof Herbivorous && victim.getValue() != null) {
                    Herbivorous herbivorous = (Herbivorous) victim.getValue();
                    Map<Class<? extends Organism>, Integer> mapOfChances = Constants.CONTAINER_OF_CHANCES.get(predator.getClass());
                    int chancesToEat = mapOfChances.get(herbivorous.getClass());
                    if (Randomizer.getRandom(chancesToEat)) {
                        double additionalWeight = Constants.BASE_FOR_ANIMALS.get(victim.getKey())[0];
                        if ((predator.getCurrentStomachVolume() + additionalWeight) > Constants.BASE_FOR_ANIMALS.get(predator.getClass())[3]) {
                            predator.setCurrentStomachVolume(Constants.BASE_FOR_ANIMALS.get(predator.getClass())[3]);
                        } else {
                            predator.setCurrentStomachVolume(predator.getCurrentStomachVolume() + additionalWeight);
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
