package world.animals;

import lombok.ToString;
import world.Eatable;
import world.constants.Constants;
import world.map.Cell;
import world.plants.Grass;

@ToString
public abstract class Animal implements Eatable {

    private double currentStomachVolume;
    private Class<? extends Animal> animal;

    public Animal() {
        this.animal = getClass();
        this.currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(animal)[3];
    }

    public abstract void eat(Cell cell);

    public abstract void multiply();
    public abstract void move();
    public abstract void die();

    public double getCurrentStomachVolume() {
        return currentStomachVolume;
    }

    public void setCurrentStomachVolume(double currentStomachVolume) {
        this.currentStomachVolume = currentStomachVolume;
    }


}
