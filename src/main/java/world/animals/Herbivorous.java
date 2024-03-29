package world.animals;

import lombok.SneakyThrows;
import util.Randomizer;
import world.animals.herbivorous.Caterpillar;
import world.constants.Constants;
import world.map.Cell;
import world.map.WorldMap;
import world.plants.Grass;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Set;

public class Herbivorous extends Animal {

    protected Class<? extends Herbivorous> animal;

    public Herbivorous() {
        super();
        this.animal = getClass();
    }

    @Override
    public boolean eat(Cell cell) {
        cell.getLock()
            .lock();

        Animal herbivorous = this;

        boolean isAte = false;

        try {
            Iterator<Grass> grassIterator = cell.getContainedPlants()
                                                .iterator();
            while (grassIterator.hasNext() && ! isAte) {
                Grass victim = grassIterator.next();
                if (victim != null) {
                    double additionalWeight = Constants.BASE_FOR_PLANTS.get(victim.getClass())[0];
                    if ((currentStomachVolume + additionalWeight) > Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3]) {
                        currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3];
                        isAte = true;
                    } else {
                        currentStomachVolume = currentStomachVolume + additionalWeight;
                    }
                    grassIterator.remove();
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

        Animal herbivorous = this;

        try {
            Iterator<Animal> coupleFinder = cell.getContainedAnimals()
                                                .iterator();
            boolean isMultiplied = false;
            while (coupleFinder.hasNext() && ! isMultiplied) {
                Animal couple = coupleFinder.next();
                if (couple.getClass() == herbivorous.getClass() && ! couple.equals(herbivorous)) {
                    if (checkNumberOfAnimals(herbivorous, cell)) {
                        if (Randomizer.getRandom()) {
                            Constructor<? extends Animal> animalConstructor = herbivorous.getClass()
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

        Animal herbivorous = this;

        int posX = cell.getX();
        int posY = cell.getY();
        int distanceX = Randomizer.getRandom((int) - Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[2],
                (int) Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[2]);
        int distanceY;
        if (distanceX > 0) {
            distanceY = Randomizer.getRandom((int) - Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[2] + distanceX,
                    (int) Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[2] - distanceX);
        } else
            distanceY = Randomizer.getRandom((int) - Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[2] - distanceX,
                    (int) Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[2] + distanceX);

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
            if (checkTheWay(herbivorous, WorldMap.getCell(posX, posY))) {
                WorldMap.getCell(posX, posY).
                        getContainedAnimals().
                        add(herbivorous.clone());
                die(herbivorous, cell);
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

        Animal herbivorous = this;

        boolean dead = false;

        try {
            currentStomachVolume = currentStomachVolume - Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3] / 20;
            if (currentStomachVolume <= 0 && ! (herbivorous instanceof Caterpillar)) {
                die(herbivorous, cell);
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
            Iterator<Animal> listInNewPosition = cell.getContainedAnimals().iterator();

            while (listInNewPosition.hasNext()){
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
