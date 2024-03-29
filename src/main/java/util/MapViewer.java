package util;

import world.Organism;
import world.animals.herbivorous.*;
import world.animals.predators.*;
import world.constants.Constants;
import world.plants.Grass;

import java.util.Queue;
import java.util.Set;

public class MapViewer {

    public MapViewer() {
    }

    public void view(Queue<Set<Organism>> queue, int day) {
        System.out.println("День на острове: №" + day);
        System.out.println("Количество организмов на острове: ");
        System.out.print("{");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Boar.class)[1] + organismCounter(Boar.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Bull.class)[1] + organismCounter(Bull.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Caterpillar.class)[1] + organismCounter(Caterpillar.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Duck.class)[1] + organismCounter(Duck.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Deer.class)[1] + organismCounter(Deer.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Goat.class)[1] + organismCounter(Goat.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Hare.class)[1] + organismCounter(Hare.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Horse.class)[1] + organismCounter(Horse.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Mouse.class)[1] + organismCounter(Mouse.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Sheep.class)[1] + organismCounter(Sheep.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Bear.class)[1] + organismCounter(Bear.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Eagle.class)[1] + organismCounter(Eagle.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Fox.class)[1] + organismCounter(Fox.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Snake.class)[1] + organismCounter(Snake.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_ANIMALS.get(Wolf.class)[1] + organismCounter(Wolf.class, queue) + " ");
        System.out.print(Constants.STRINGS_FOR_PLANTS.get(Grass.class)[1] + organismCounter(Grass.class, queue) + "}");
        System.out.println();
    }

    public int organismCounter(Class<? extends Organism> organismType, Queue<Set<Organism>> queue) {
        int result = 0;

        for (Set<Organism> organisms :
                queue) {
            for (Organism organism :
                    organisms) {

                if (organism.getClass() == organismType) {
                    result++;
                }

            }
        }
        return result;
    }
}
