package world.animals;

import lombok.SneakyThrows;
import resources.configs.util.Randomizer;
import world.Eatable;
import world.animals.herbivorous.Caterpillar;
import world.constants.Constants;
import world.map.Cell;
import world.map.WorldMap;
import world.plants.Grass;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Herbivorous extends Animal {

    private double currentStomachVolume;
    protected Class<? extends Herbivorous> animal;

    public Herbivorous() {
        this.animal = getClass();
        this.currentStomachVolume = Constants.BASE_FOR_ANIMALS.get(animal)[3];
    }

    @Override
    public void eat(Cell cell) {
        Animal herbivorous = this;

        Iterator<Map.Entry<Class<? extends Grass>, Grass>> grassIterator = cell.getContainedPlants()
                                            .entrySet().
                                                                               iterator();
        boolean isAte = false;

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
        if (isAte) {
            multiply(cell);
        }

    }

    @SneakyThrows
    @Override
    public void multiply(Cell cell) {
        Animal herbivorous = this;
        Iterator<Map.Entry<Class<? extends Animal>, Animal>> coupleFinder = cell.getContainedAnimals()
                                                                                .entrySet()
                                                                                .iterator();

        boolean isMultiplied = false;
        while (coupleFinder.hasNext() && ! isMultiplied) {
            Map.Entry<Class<? extends Animal>, Animal> couple = coupleFinder.next();
            if (couple.getKey() == herbivorous.getClass() && ! couple.getValue()
                                                                     .equals(herbivorous)) {
                if (checkNumberOfAnimals(herbivorous, cell)){
                    System.out.println(herbivorous + " " + couple.getValue() + " устроили просексовку");
                    Constructor<? extends Animal> animalConstructor = herbivorous.getClass()
                                                                                 .getDeclaredConstructor();
                    Animal newAnimal = animalConstructor.newInstance();
                    cell.getContainedAnimals()
                        .put(herbivorous.getClass(), newAnimal);
                    isMultiplied = true;
                }
            }
        }

    }

    @Override
    public void move(Cell cell) {
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

        if (checkTheWay(herbivorous, WorldMap.getCell(posX, posY))) {
            WorldMap.getCell(posX, posY).
                    getContainedAnimals().
                    put(herbivorous.getClass(), herbivorous.clone());
            die(herbivorous, cell);
            System.out.println(herbivorous + " появился в клетке " + WorldMap.getCell(posX, posY));
            System.out.println(herbivorous + " исчез в клетке " + cell);
        } else move(cell);
    }

    @Override
    public void healthCheck(Cell cell) {
        Animal herbivorous = this;

        System.out.println(currentStomachVolume);
        currentStomachVolume = currentStomachVolume - Constants.BASE_FOR_ANIMALS.get(herbivorous.getClass())[3];
        System.out.println(herbivorous.getCurrentStomachVolume());
        if (herbivorous.getCurrentStomachVolume() <= 0 && ! (herbivorous instanceof Caterpillar)) {
            die(herbivorous, cell);
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
