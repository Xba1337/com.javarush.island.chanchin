package world.animals;

import lombok.ToString;
import world.Eatable;
import world.constants.Constants;
import world.map.Cell;

import java.lang.reflect.Constructor;
import java.util.Objects;


public abstract class Animal implements Eatable, Cloneable{

    private double currentStomachVolume;
    protected Class<? extends Animal> animal;

    public Animal() {
        this.animal = getClass();
        this.currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(animal)[3];
    }

    public abstract void eat(Cell cell);

    public abstract void multiply(Cell cell);
    public abstract void move(Cell cell);
    public abstract void healthCheck(Cell cell);
    public abstract void die(Animal animal, Cell cell);

    public double getCurrentStomachVolume() {
        return currentStomachVolume;
    }

    public void setCurrentStomachVolume(double currentStomachVolume) {
        this.currentStomachVolume = currentStomachVolume;
    }


    @Override
    public Animal clone() {
        try {
            Animal clone = (Animal) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
