package world.animals;

import lombok.Getter;
import lombok.ToString;
import world.Organism;
import world.constants.Constants;
import world.map.Cell;


@ToString
public abstract class Animal extends Organism implements Cloneable{

    @Getter
    private double currentStomachVolume;
    protected Class<? extends Animal> animal;

    public Animal() {
        this.animal = getClass();
        this.currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(animal)[3];
    }

    public abstract boolean eat(Cell cell);

    public abstract void multiply(Cell cell);
    public abstract void move(Cell cell);
    public abstract boolean healthCheck(Cell cell);
    public abstract void die(Animal animal, Cell cell);

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
