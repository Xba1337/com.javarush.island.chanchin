package world.animals;


import lombok.SneakyThrows;
import util.Randomizer;
import world.Organism;
import world.animals.herbivorous.Caterpillar;
import world.constants.Constants;
import world.map.Cell;
import world.map.WorldMap;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Predator extends Animal implements Cloneable {


    protected Class<? extends Predator> animal;

    public Predator() {
        super();
        this.animal = getClass();

    }

    @Override
    public boolean eat(Cell cell) {
        cell.getLock()
            .lock();

        Animal predator = this;

        boolean isAte = false;

        try {
            Iterator<Animal> victimIterator = cell.getContainedAnimals()
                                                  .iterator();

            while (victimIterator.hasNext() && ! isAte) {
                Animal victim = victimIterator.next();
                if (victim instanceof Herbivorous && ! (victim instanceof Caterpillar) && victim != null) {
                    Herbivorous herbivorous = (Herbivorous) victim;
                    Map<Class<? extends Organism>, Integer> mapOfChances = Constants.CONTAINER_OF_CHANCES.get(predator.getClass());
                    int chancesToEat = mapOfChances.get(herbivorous.getClass());
                    if (Randomizer.getRandom(chancesToEat)) {
                        double additionalWeight = Constants.BASE_FOR_ANIMALS.get(victim.getClass())[0];
                        if ((currentStomachVolume + additionalWeight) > Constants.BASE_FOR_ANIMALS.get(predator.getClass())[3]) {
                            currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(predator.getClass())[3];
                        } else {
                            currentStomachVolume = currentStomachVolume + additionalWeight;
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

    @SneakyThrows
    @Override
    public void multiply(Cell cell) {
        cell.getLock()
            .lock();

        Animal predator = this;

        try {
            Iterator<Animal> coupleFinder = cell.getContainedAnimals()
                                                .iterator();

            boolean isMultiplied = false;
            while (coupleFinder.hasNext() && ! isMultiplied) {
                Animal couple = coupleFinder.next();
                if (couple.getClass() == predator.getClass() && ! couple.equals(predator)) {
                    if (checkNumberOfAnimals(predator, cell)) {
                        if (Randomizer.getRandom()) {
                            Constructor<? extends Animal> animalConstructor = predator.getClass()
                                                                                      .getDeclaredConstructor();
                            Animal newAnimal = animalConstructor.newInstance();
                            cell.getContainedAnimals()
                                .add(newAnimal);
                            isMultiplied = true;
                        }
                    }
                }
            }
        } finally {
            cell.getLock()
                .unlock();
        }

    }

    @Override
    public void move(Cell cell) {
        cell.getLock()
            .lock();

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

        try {
            if (checkTheWay(predator, WorldMap.getCell(posX, posY))) {
                WorldMap.getCell(posX, posY).
                        getContainedAnimals().
                        add(predator.clone());
                die(predator, cell);
            }
        } finally {
            cell.getLock()
                .unlock();
        }
    }

    @Override
    public boolean healthCheck(Cell cell) {
        cell.getLock()
            .lock();

        Animal predator = this;

        boolean dead = false;


        try {
            currentStomachVolume = currentStomachVolume - Constants.BASE_FOR_ANIMALS.get(predator.getClass())[3] / 20;
            if (currentStomachVolume <= 0) {
                die(predator, cell);
                dead = true;
            }
        } finally {
            cell.getLock()
                .unlock();
        }
        return dead;
    }

    public void die(Animal animal, Cell cell) {
        cell.getLock()
            .lock();

        try {
            Iterator<Animal> animalsFromCell = cell.getContainedAnimals()
                                                   .iterator();
            boolean success = false;

            while (animalsFromCell.hasNext() && ! success) {
                Animal remove = animalsFromCell.next();
                if (remove.equals(animal)) {
                    animalsFromCell.remove();
                    success = true;
                }
            }
        } finally {
            cell.getLock()
                .unlock();
        }
    }


    public boolean checkTheWay(Animal animal, Cell cell) {
        cell.getLock()
            .lock();

        int animalCounter = 0;

        try {
            Iterator<Animal> listInNewPosition = cell.getContainedAnimals()
                                                     .iterator();

            while (listInNewPosition.hasNext()) {
                Animal movingAnimal = listInNewPosition.next();
                if (movingAnimal.getClass() == animal.getClass()) {
                    animalCounter++;
                }
            }
            return animalCounter < Constants.BASE_FOR_ANIMALS.get(animal.getClass())[1];
        } finally {
            cell.getLock()
                .unlock();
        }


    }

    public boolean checkNumberOfAnimals(Animal animal, Cell cell) {
        cell.getLock()
            .lock();

        int animalCounter = 0;

        try {
            Set<Animal> setOfAnimals = cell.getContainedAnimals();

            for (Animal animals :
                    setOfAnimals) {
                if (animals.getClass() == animal.getClass()) {
                    animalCounter++;
                }
            }
        } finally {
            cell.getLock()
                .unlock();
        }
        return animalCounter < Constants.BASE_FOR_ANIMALS.get(animal.getClass())[1];

    }
}