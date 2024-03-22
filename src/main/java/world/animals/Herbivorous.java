package world.animals;

import world.constants.Constants;
import world.map.Cell;

public class Herbivorous extends Animal{

    private double currentStomachVolume;
    protected Class<? extends Herbivorous> animal;
    @Override
    public void eat(Cell cell) {

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

    public Herbivorous() {
        this.animal = getClass();
        this.currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(animal)[3];
    }
}
