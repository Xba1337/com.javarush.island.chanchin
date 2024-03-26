package world.animals.herbivorous;

import resources.configs.util.Randomizer;
import world.Organism;
import world.animals.Animal;
import world.animals.Herbivorous;
import world.constants.Constants;
import world.map.Cell;
import world.plants.Grass;

import java.util.Iterator;
import java.util.Map;


public class Boar extends Herbivorous {

    public Boar() {
        this.animal = Boar.class;
    }

    @Override
    public boolean eat(Cell cell) {
        cell.getLock()
            .lock();

        Animal herbivorous = this;

        boolean isAte = false;

        try {
            Iterator<Map.Entry<Class<? extends Grass>, Grass>> grassIterator = cell.getContainedPlants()
                                                                                   .entrySet().
                                                                                   iterator();
            Iterator<Map.Entry<Class<? extends Animal>, Animal>> victimIterator = cell.getContainedAnimals()
                                                                                      .entrySet()
                                                                                      .iterator();

            if (Randomizer.getRandom()) {
                while (grassIterator.hasNext() && ! isAte) {
                    Map.Entry<Class<? extends Grass>, Grass> victim = grassIterator.next();
                    if (victim.getValue() != null) {
                        double additionalWeight = Constants.BASE_FOR_PLANTS.get(victim.getKey())[0];
                        if ((herbivorous.getCurrentStomachVolume() + additionalWeight) > Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3]) {
                            herbivorous.setCurrentStomachVolume(Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3]);
                            isAte = true;
                        } else {
                            herbivorous.setCurrentStomachVolume(herbivorous.getCurrentStomachVolume() + additionalWeight);
                        }
                        grassIterator.remove();
                    }

                }
            } else {
                while (victimIterator.hasNext() && ! isAte) {
                    Map.Entry<Class<? extends Animal>, Animal> victim = victimIterator.next();
                    if (victim.getValue() instanceof Caterpillar || victim.getValue() instanceof Mouse && victim.getValue() != null) {
                        Herbivorous mouseOrCaterpillar = (Herbivorous) victim.getValue();
                        Map<Class<? extends Organism>, Integer> mapOfChances = Constants.CONTAINER_OF_CHANCES.get(herbivorous.getClass());
                        int chancesToEat = mapOfChances.get(mouseOrCaterpillar.getClass());
                        if (Randomizer.getRandom(chancesToEat)) {
                            double additionalWeight = Constants.BASE_FOR_ANIMALS.get(victim.getKey())[0];
                            if ((herbivorous.getCurrentStomachVolume() + additionalWeight) > Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3]) {
                                herbivorous.setCurrentStomachVolume(Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3]);
                            } else {
                                herbivorous.setCurrentStomachVolume(herbivorous.getCurrentStomachVolume() + additionalWeight);
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
