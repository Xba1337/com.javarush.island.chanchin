package world.animals.predators;


import resources.configs.util.Randomizer;
import world.Eatable;
import world.animals.Animal;
import world.animals.Herbivorous;
import world.animals.Predator;
import world.animals.herbivorous.Boar;
import world.animals.herbivorous.Caterpillar;
import world.constants.Constants;
import world.map.Cell;

import java.util.Iterator;
import java.util.Map;


public class Fox extends Predator {

    public Fox() {
        this.animal = Fox.class;
    }

    @Override
    public void eat(Cell cell) {
        Animal predator = this;

        Iterator<Map.Entry<Class<? extends Animal>, Animal>> victimIterator = cell.getContainedAnimals()
                                                                                  .entrySet()
                                                                                  .iterator();
        boolean isAte = false;

        while (victimIterator.hasNext() && ! isAte) {
            Map.Entry<Class<? extends Animal>, Animal> victim = victimIterator.next();
            if (victim.getValue() instanceof Herbivorous && victim.getValue() != null) {
                Herbivorous herbivorous = (Herbivorous) victim.getValue();
                Map<Class<? extends Eatable>, Integer> mapOfChances = Constants.CONTAINER_OF_CHANCES.get(predator.getClass());
                int chancesToEat = mapOfChances.get(herbivorous.getClass());
                if (Randomizer.getRandom(chancesToEat)) {
                    System.out.println(predator + " съел " + herbivorous);
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
        if (isAte) {
            multiply(cell);
        }

    }
}
