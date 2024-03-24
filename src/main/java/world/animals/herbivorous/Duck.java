package world.animals.herbivorous;


import resources.configs.util.Randomizer;
import world.Eatable;
import world.animals.Animal;
import world.animals.Herbivorous;
import world.constants.Constants;
import world.map.Cell;
import world.plants.Grass;

import java.util.Iterator;
import java.util.Map;


public class Duck extends Herbivorous {

    public Duck() {
        this.animal = Duck.class;
    }

    @Override
    public void eat(Cell cell) {
        Animal herbivorous = this;

        Iterator<Map.Entry<Class<? extends Grass>, Grass>> grassIterator = cell.getContainedPlants()
                                                                               .entrySet().
                                                                               iterator();
        Iterator<Map.Entry<Class<? extends Animal>, Animal>> victimIterator = cell.getContainedAnimals()
                                                                                  .entrySet()
                                                                                  .iterator();
        boolean isAte = false;


        if (Randomizer.getRandom()) {
            while (grassIterator.hasNext() && ! isAte) {
                Map.Entry<Class<? extends Grass>, Grass> victim = grassIterator.next();
                if (victim.getValue() != null) {
                    System.out.println(herbivorous + " съел " + victim.getValue());
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
                if (victim.getValue() instanceof Herbivorous && victim.getValue() instanceof Caterpillar && victim.getValue() != null) {
                    Herbivorous caterpillar = (Herbivorous) victim.getValue();
                    Map<Class<? extends Eatable>, Integer> mapOfChances = Constants.CONTAINER_OF_CHANCES.get(herbivorous.getClass());
                    int chancesToEat = mapOfChances.get(caterpillar.getClass());
                    if (Randomizer.getRandom(chancesToEat)) {
                        System.out.println(herbivorous + " съел " + caterpillar);
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
        if (isAte) {
            multiply(cell);
        }

    }
}
