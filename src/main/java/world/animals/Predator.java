package world.animals;


import lombok.SneakyThrows;
import resources.configs.util.Randomizer;
import world.Eatable;
import world.animals.herbivorous.Caterpillar;
import world.constants.Constants;
import world.map.Cell;
import world.map.WorldMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Predator extends Animal implements Cloneable {

    private double currentStomachVolume;
    protected Class<? extends Predator> animal;

    public Predator() {
        this.animal = getClass();
        this.currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(animal)[3];
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
            if (victim.getValue() instanceof Herbivorous && ! (victim.getValue() instanceof Caterpillar) && victim.getValue() != null) {
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

    @SneakyThrows
    @Override
    public void multiply(Cell cell) {
        Animal predator = this;
        Iterator<Map.Entry<Class<? extends Animal>, Animal>> coupleFinder = cell.getContainedAnimals()
                                                                                .entrySet()
                                                                                .iterator();

        boolean isMultiplied = false;
        while (coupleFinder.hasNext() && ! isMultiplied) {
            Map.Entry<Class<? extends Animal>, Animal> couple = coupleFinder.next();
            if (couple.getKey() == predator.getClass() && ! couple.getValue()
                                                                  .equals(predator)) {
                if (checkNumberOfAnimals(predator, cell)){
                    System.out.println(predator + " " + couple.getValue() + " устроили просексовку");
                    Constructor<? extends Animal> animalConstructor = predator.getClass()
                                                                              .getDeclaredConstructor();
                    Animal newAnimal = animalConstructor.newInstance();
                    cell.getContainedAnimals()
                        .put(predator.getClass(), newAnimal);
                    isMultiplied = true;
                }
            }
        }

    }

    @Override
    public void move(Cell cell) {
        Animal predator = this;
        int posX = cell.getX();
        int posY = cell.getY();
        int distanceX = Randomizer.getRandom((int) - Constants.BASE_FOR_ANIMALS.get(predator.getClass())[2],
                (int) Constants.BASE_FOR_ANIMALS.get(predator.getClass())[2]);
        int distanceY;
        if (distanceX > 0) {
            distanceY = Randomizer.getRandom((int) - Constants.BASE_FOR_ANIMALS.get(predator.getClass())[2] + distanceX,
                    (int) Constants.BASE_FOR_ANIMALS.get(predator.getClass())[2] - distanceX);
        } else
            distanceY = Randomizer.getRandom((int) - Constants.BASE_FOR_ANIMALS.get(predator.getClass())[2] - distanceX,
                    (int) Constants.BASE_FOR_ANIMALS.get(predator.getClass())[2] + distanceX);

        if ((posX + distanceX) > Constants.WORLD_SIZE_X - 1) {
            posX = Constants.WORLD_SIZE_X - 1;
        } else if ((posX + distanceX) < 0) {
            posX = 0;
        } else posX = posX + distanceX;
        if ((posY + distanceY) > Constants.WORLD_SIZE_Y - 1) {
            posY = Constants.WORLD_SIZE_Y - 1;
        } else if ((posY + distanceY) < 0) {
            posX = 0;
        } else posY = posY + distanceY;

        if (checkTheWay(predator, WorldMap.getCell(posX, posY))) {
            WorldMap.getCell(posX, posY).
                    getContainedAnimals().
                    put(predator.getClass(), predator.clone());
            die(predator, cell);
            System.out.println(predator + " появился в клетке " + WorldMap.getCell(posX, posY));
            System.out.println(predator + " исчез в клетке " + cell);
        } else move(cell);
    }

    @Override
    public void healthCheck(Cell cell) {
        Animal predator = this;

        System.out.println(currentStomachVolume);
        currentStomachVolume = currentStomachVolume - Constants.BASE_FOR_ANIMALS.get(predator.getClass())[3];
        System.out.println(predator.getCurrentStomachVolume());
        if (predator.getCurrentStomachVolume() <= 0) {
            die(predator, cell);
        }
    }

    public void die(Animal animal, Cell cell) {
        Iterator<Map.Entry<Class<? extends Animal>, Animal>> animalsFromCell = cell.getContainedAnimals()
                                                                                   .entrySet()
                                                                                   .iterator();
        boolean success = false;
        if (animal.getCurrentStomachVolume() <= 0) {
            while (animalsFromCell.hasNext() && ! success) {
                Animal removable = animalsFromCell.next()
                                                  .getValue();
                if (removable.equals(animal)) {
                    animalsFromCell.remove();
                    System.out.println(animal + " и " + removable + " померли");
                    success = true;
                }
            }
        }
    }

    @Override
    public double getCurrentStomachVolume() {
        return currentStomachVolume;
    }

    public boolean checkTheWay(Animal animal, Cell cell) {
        Map<Class<? extends Animal>, Animal> listInNewPosition = cell.getContainedAnimals();
        Set<Map.Entry<Class<? extends Animal>, Animal>> animalsInNewCell = listInNewPosition.entrySet();
        int animalCounter = 0;

        for (Map.Entry<Class<? extends Animal>, Animal> animalInNewCell :
                animalsInNewCell) {
            if (animalInNewCell.getKey() == animal.getClass()) {
                animalCounter++;
            }
        }
        return animalCounter < Constants.BASE_FOR_ANIMALS.get(animal.getClass())[1];
    }

    public boolean checkNumberOfAnimals(Animal animal, Cell cell){
        Set<Map.Entry<Class<? extends Animal>, Animal>> mapOfAnimals = cell.getContainedAnimals().
                                                                           entrySet();
        int animalCounter = 0;
        for (Map.Entry<Class<? extends Animal>, Animal> animalEntry :
                mapOfAnimals) {
            if (animalEntry.getKey() == animal.getClass()) {
                animalCounter++;
            }
        }

        return animalCounter < Constants.BASE_FOR_ANIMALS.get(animal.getClass())[1];
    }
}