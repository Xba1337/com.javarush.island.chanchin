package world.animals;


import world.animals.herbivorous.Caterpillar;
import world.constants.Constants;
import world.map.Cell;

import java.util.Map;
import java.util.Set;

public abstract class Predator extends Animal {

    private double currentStomachVolume;
    protected Class<? extends Predator> animal;

    public Predator() {
        this.animal = getClass();
        this.currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(animal)[3];
    }

    @Override
    public void eat(Cell cell) {
        Set<Map.Entry<Class<? extends Animal>, Animal>> entrySetOfAnimals = cell.getContainedAnimals()
                                                                                .entrySet();
        for (Map.Entry<Class<? extends Animal>, Animal> predatorPairs :
                entrySetOfAnimals) {
            for (Map.Entry<Class<? extends Animal>, Animal> victimPairs :
                    entrySetOfAnimals){
                if (!(predatorPairs.getValue() == null)){

                }
            }


        }

    }

    @Override
    public void multiply() {

    }

    @Override
    public void move() {

    }

    @Override
    public void die() {

    }

}
