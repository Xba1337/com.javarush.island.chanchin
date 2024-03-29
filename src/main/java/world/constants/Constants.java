package world.constants;

import world.Organism;
import world.animals.Animal;
import world.animals.herbivorous.*;
import world.animals.predators.*;
import world.plants.Grass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Constants {

    public static int WORLD_SIZE_Y = 10;
    public static int WORLD_SIZE_X = 10;

    public static int MAX_STEPS_OF_SIMULATION = 300;

    public static int DELAY = 250; //ms

    public static int INIT_DELAY = 250; //ms

    public static final Map<Class<? extends Animal>, Map<Class<? extends Organism>, Integer>> CONTAINER_OF_CHANCES;
    public static final Map<Class<? extends Organism>, Integer> WOLF_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> SNAKE_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> FOX_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> BEAR_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> EAGLE_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> HORSE_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> DEER_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> HARE_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> MOUSE_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> GOAT_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> SHEEP_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> BOAR_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> BULL_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> DUCK_CHANCES_TO_EAT;

    public static final Map<Class<? extends Organism>, Integer> CATERPILLAR_CHANCES_TO_EAT;

    public static final Map<Class<? extends Animal>, String[]> STRINGS_FOR_ANIMALS;

    public static final Map<Class<? extends Grass>, String[]> STRINGS_FOR_PLANTS;

    public static final Map<Class<? extends Animal>, double[]> BASE_FOR_ANIMALS;

    public static final Map<Class<? extends Grass>, double[]> BASE_FOR_PLANTS;

    public static final Set<Class<? extends Animal>> TYPES;

    static {
        STRINGS_FOR_ANIMALS = new HashMap<>();

        STRINGS_FOR_ANIMALS.put(Boar.class, new String[]{"Boar", "\uD83D\uDC17"});
        STRINGS_FOR_ANIMALS.put(Bull.class, new String[]{"Bull", "\uD83D\uDC03"});
        STRINGS_FOR_ANIMALS.put(Caterpillar.class, new String[]{"Caterpillar", "\uD83D\uDC1B"});
        STRINGS_FOR_ANIMALS.put(Deer.class, new String[]{"Deer", "\uD83E\uDD8C"});
        STRINGS_FOR_ANIMALS.put(Duck.class, new String[]{"Duck", "\uD83E\uDD86"});
        STRINGS_FOR_ANIMALS.put(Goat.class, new String[]{"Goat", "\uD83D\uDC10"});
        STRINGS_FOR_ANIMALS.put(Hare.class, new String[]{"Hare", "\uD83D\uDC07"});
        STRINGS_FOR_ANIMALS.put(Horse.class, new String[]{"Horse", "\uD83D\uDC0E"});
        STRINGS_FOR_ANIMALS.put(Mouse.class, new String[]{"Mouse", "\uD83D\uDC01"});
        STRINGS_FOR_ANIMALS.put(Sheep.class, new String[]{"Sheep", "\uD83D\uDC11"});
        STRINGS_FOR_ANIMALS.put(Bear.class, new String[]{"Bear", "\uD83D\uDC3B"});
        STRINGS_FOR_ANIMALS.put(Eagle.class, new String[]{"Eagle", "\uD83E\uDD85"});
        STRINGS_FOR_ANIMALS.put(Fox.class, new String[]{"Fox", "\uD83E\uDD8A"});
        STRINGS_FOR_ANIMALS.put(Snake.class, new String[]{"Snake", "\uD83D\uDC0D"});
        STRINGS_FOR_ANIMALS.put(Wolf.class, new String[]{"Wolf", "\uD83D\uDC3A"});

        STRINGS_FOR_PLANTS = new HashMap<>();

        STRINGS_FOR_PLANTS.put(Grass.class, new String[]{"Grass", "\uD83C\uDF3F"});

    }

    static {
        BASE_FOR_ANIMALS = new HashMap<>();

        BASE_FOR_ANIMALS.put(Boar.class, new double[]{400, 50, 2, 50});
        BASE_FOR_ANIMALS.put(Bull.class, new double[]{700, 10, 3, 100});
        BASE_FOR_ANIMALS.put(Caterpillar.class, new double[]{0.01, 1000, 0, 0});
        BASE_FOR_ANIMALS.put(Deer.class, new double[]{300, 20, 4, 50});
        BASE_FOR_ANIMALS.put(Duck.class, new double[]{1, 200, 4, 0.15});
        BASE_FOR_ANIMALS.put(Goat.class, new double[]{60, 140, 3, 10});
        BASE_FOR_ANIMALS.put(Hare.class, new double[]{2, 150, 2, 0.45});
        BASE_FOR_ANIMALS.put(Horse.class, new double[]{400, 20, 4, 60});
        BASE_FOR_ANIMALS.put(Mouse.class, new double[]{0.05, 500, 1, 0.01});
        BASE_FOR_ANIMALS.put(Sheep.class, new double[]{70, 140, 3, 15});
        BASE_FOR_ANIMALS.put(Bear.class, new double[]{500, 5, 2, 80});
        BASE_FOR_ANIMALS.put(Eagle.class, new double[]{6, 20, 3, 1});
        BASE_FOR_ANIMALS.put(Fox.class, new double[]{8, 30, 2, 2});
        BASE_FOR_ANIMALS.put(Snake.class, new double[]{15, 30, 1, 3});
        BASE_FOR_ANIMALS.put(Wolf.class, new double[]{50, 30, 3, 8});

        BASE_FOR_PLANTS = new HashMap<>();
        BASE_FOR_PLANTS.put(Grass.class, new double[]{1, 200});
    }

    static {
        TYPES = new HashSet<>();

        TYPES.add(Boar.class);
        TYPES.add(Bull.class);
        TYPES.add(Caterpillar.class);
        TYPES.add(Deer.class);
        TYPES.add(Duck.class);
        TYPES.add(Goat.class);
        TYPES.add(Hare.class);
        TYPES.add(Horse.class);
        TYPES.add(Mouse.class);
        TYPES.add(Sheep.class);
        TYPES.add(Bear.class);
        TYPES.add(Eagle.class);
        TYPES.add(Fox.class);
        TYPES.add(Snake.class);
        TYPES.add(Wolf.class);
    }


    static {
        WOLF_CHANCES_TO_EAT = new HashMap<>();

        WOLF_CHANCES_TO_EAT.put(Snake.class, 0);
        WOLF_CHANCES_TO_EAT.put(Fox.class, 0);
        WOLF_CHANCES_TO_EAT.put(Bear.class, 0);
        WOLF_CHANCES_TO_EAT.put(Eagle.class, 0);
        WOLF_CHANCES_TO_EAT.put(Horse.class, 10);
        WOLF_CHANCES_TO_EAT.put(Deer.class, 15);
        WOLF_CHANCES_TO_EAT.put(Hare.class, 60);
        WOLF_CHANCES_TO_EAT.put(Mouse.class, 80);
        WOLF_CHANCES_TO_EAT.put(Goat.class, 60);
        WOLF_CHANCES_TO_EAT.put(Sheep.class, 70);
        WOLF_CHANCES_TO_EAT.put(Boar.class, 15);
        WOLF_CHANCES_TO_EAT.put(Bull.class, 10);
        WOLF_CHANCES_TO_EAT.put(Duck.class, 40);
        WOLF_CHANCES_TO_EAT.put(Caterpillar.class, 0);
        WOLF_CHANCES_TO_EAT.put(Grass.class, 0);
    }

    static {
        SNAKE_CHANCES_TO_EAT = new HashMap<>();

        SNAKE_CHANCES_TO_EAT.put(Wolf.class, 0);
        SNAKE_CHANCES_TO_EAT.put(Fox.class, 15);
        SNAKE_CHANCES_TO_EAT.put(Bear.class, 0);
        SNAKE_CHANCES_TO_EAT.put(Eagle.class, 0);
        SNAKE_CHANCES_TO_EAT.put(Horse.class, 0);
        SNAKE_CHANCES_TO_EAT.put(Deer.class, 0);
        SNAKE_CHANCES_TO_EAT.put(Hare.class, 20);
        SNAKE_CHANCES_TO_EAT.put(Mouse.class, 40);
        SNAKE_CHANCES_TO_EAT.put(Goat.class, 0);
        SNAKE_CHANCES_TO_EAT.put(Sheep.class, 0);
        SNAKE_CHANCES_TO_EAT.put(Boar.class, 0);
        SNAKE_CHANCES_TO_EAT.put(Bull.class, 0);
        SNAKE_CHANCES_TO_EAT.put(Duck.class, 10);
        SNAKE_CHANCES_TO_EAT.put(Caterpillar.class, 0);
        SNAKE_CHANCES_TO_EAT.put(Grass.class, 0);
    }

    static {
        FOX_CHANCES_TO_EAT = new HashMap<>();

        FOX_CHANCES_TO_EAT.put(Wolf.class, 0);
        FOX_CHANCES_TO_EAT.put(Snake.class, 0);
        FOX_CHANCES_TO_EAT.put(Bear.class, 0);
        FOX_CHANCES_TO_EAT.put(Eagle.class, 0);
        FOX_CHANCES_TO_EAT.put(Horse.class, 0);
        FOX_CHANCES_TO_EAT.put(Deer.class, 0);
        FOX_CHANCES_TO_EAT.put(Hare.class, 70);
        FOX_CHANCES_TO_EAT.put(Mouse.class, 90);
        FOX_CHANCES_TO_EAT.put(Goat.class, 0);
        FOX_CHANCES_TO_EAT.put(Sheep.class, 0);
        FOX_CHANCES_TO_EAT.put(Boar.class, 0);
        FOX_CHANCES_TO_EAT.put(Bull.class, 0);
        FOX_CHANCES_TO_EAT.put(Duck.class, 60);
        FOX_CHANCES_TO_EAT.put(Caterpillar.class, 40);
        FOX_CHANCES_TO_EAT.put(Grass.class, 0);
    }

    static {
        BEAR_CHANCES_TO_EAT = new HashMap<>();

        BEAR_CHANCES_TO_EAT.put(Wolf.class, 0);
        BEAR_CHANCES_TO_EAT.put(Snake.class, 80);
        BEAR_CHANCES_TO_EAT.put(Fox.class, 0);
        BEAR_CHANCES_TO_EAT.put(Eagle.class, 0);
        BEAR_CHANCES_TO_EAT.put(Horse.class, 40);
        BEAR_CHANCES_TO_EAT.put(Deer.class, 80);
        BEAR_CHANCES_TO_EAT.put(Hare.class, 80);
        BEAR_CHANCES_TO_EAT.put(Mouse.class, 90);
        BEAR_CHANCES_TO_EAT.put(Goat.class, 70);
        BEAR_CHANCES_TO_EAT.put(Sheep.class, 70);
        BEAR_CHANCES_TO_EAT.put(Boar.class, 50);
        BEAR_CHANCES_TO_EAT.put(Bull.class, 20);
        BEAR_CHANCES_TO_EAT.put(Duck.class, 10);
        BEAR_CHANCES_TO_EAT.put(Caterpillar.class, 0);
        BEAR_CHANCES_TO_EAT.put(Grass.class, 0);
    }

    static {
        EAGLE_CHANCES_TO_EAT = new HashMap<>();

        EAGLE_CHANCES_TO_EAT.put(Wolf.class, 0);
        EAGLE_CHANCES_TO_EAT.put(Snake.class, 0);
        EAGLE_CHANCES_TO_EAT.put(Fox.class, 10);
        EAGLE_CHANCES_TO_EAT.put(Bear.class, 0);
        EAGLE_CHANCES_TO_EAT.put(Horse.class, 0);
        EAGLE_CHANCES_TO_EAT.put(Deer.class, 0);
        EAGLE_CHANCES_TO_EAT.put(Hare.class, 90);
        EAGLE_CHANCES_TO_EAT.put(Mouse.class, 90);
        EAGLE_CHANCES_TO_EAT.put(Goat.class, 0);
        EAGLE_CHANCES_TO_EAT.put(Sheep.class, 0);
        EAGLE_CHANCES_TO_EAT.put(Boar.class, 0);
        EAGLE_CHANCES_TO_EAT.put(Bull.class, 0);
        EAGLE_CHANCES_TO_EAT.put(Duck.class, 80);
        EAGLE_CHANCES_TO_EAT.put(Caterpillar.class, 0);
        EAGLE_CHANCES_TO_EAT.put(Grass.class, 0);
    }

    static {
        HORSE_CHANCES_TO_EAT = new HashMap<>();

        HORSE_CHANCES_TO_EAT.put(Wolf.class, 0);
        HORSE_CHANCES_TO_EAT.put(Snake.class, 0);
        HORSE_CHANCES_TO_EAT.put(Fox.class, 0);
        HORSE_CHANCES_TO_EAT.put(Bear.class, 0);
        HORSE_CHANCES_TO_EAT.put(Eagle.class, 0);
        HORSE_CHANCES_TO_EAT.put(Deer.class, 0);
        HORSE_CHANCES_TO_EAT.put(Hare.class, 0);
        HORSE_CHANCES_TO_EAT.put(Mouse.class, 0);
        HORSE_CHANCES_TO_EAT.put(Goat.class, 0);
        HORSE_CHANCES_TO_EAT.put(Sheep.class, 0);
        HORSE_CHANCES_TO_EAT.put(Boar.class, 0);
        HORSE_CHANCES_TO_EAT.put(Bull.class, 0);
        HORSE_CHANCES_TO_EAT.put(Duck.class, 0);
        HORSE_CHANCES_TO_EAT.put(Caterpillar.class, 0);
        HORSE_CHANCES_TO_EAT.put(Grass.class, 100);
    }

    static {
        DEER_CHANCES_TO_EAT = new HashMap<>();

        DEER_CHANCES_TO_EAT.put(Wolf.class, 0);
        DEER_CHANCES_TO_EAT.put(Snake.class, 0);
        DEER_CHANCES_TO_EAT.put(Fox.class, 0);
        DEER_CHANCES_TO_EAT.put(Bear.class, 0);
        DEER_CHANCES_TO_EAT.put(Eagle.class, 0);
        DEER_CHANCES_TO_EAT.put(Horse.class, 0);
        DEER_CHANCES_TO_EAT.put(Hare.class, 0);
        DEER_CHANCES_TO_EAT.put(Mouse.class, 0);
        DEER_CHANCES_TO_EAT.put(Goat.class, 0);
        DEER_CHANCES_TO_EAT.put(Sheep.class, 0);
        DEER_CHANCES_TO_EAT.put(Boar.class, 0);
        DEER_CHANCES_TO_EAT.put(Bull.class, 0);
        DEER_CHANCES_TO_EAT.put(Duck.class, 0);
        DEER_CHANCES_TO_EAT.put(Caterpillar.class, 0);
        DEER_CHANCES_TO_EAT.put(Grass.class, 100);
    }

    static {
        HARE_CHANCES_TO_EAT = new HashMap<>();

        HARE_CHANCES_TO_EAT.put(Wolf.class, 0);
        HARE_CHANCES_TO_EAT.put(Snake.class, 0);
        HARE_CHANCES_TO_EAT.put(Fox.class, 0);
        HARE_CHANCES_TO_EAT.put(Bear.class, 0);
        HARE_CHANCES_TO_EAT.put(Eagle.class, 0);
        HARE_CHANCES_TO_EAT.put(Horse.class, 0);
        HARE_CHANCES_TO_EAT.put(Deer.class, 0);
        HARE_CHANCES_TO_EAT.put(Mouse.class, 0);
        HARE_CHANCES_TO_EAT.put(Goat.class, 0);
        HARE_CHANCES_TO_EAT.put(Sheep.class, 0);
        HARE_CHANCES_TO_EAT.put(Boar.class, 0);
        HARE_CHANCES_TO_EAT.put(Bull.class, 0);
        HARE_CHANCES_TO_EAT.put(Duck.class, 0);
        HARE_CHANCES_TO_EAT.put(Caterpillar.class, 0);
        HARE_CHANCES_TO_EAT.put(Grass.class, 100);
    }

    static {
        MOUSE_CHANCES_TO_EAT = new HashMap<>();

        MOUSE_CHANCES_TO_EAT.put(Wolf.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Snake.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Fox.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Bear.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Eagle.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Horse.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Deer.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Hare.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Goat.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Sheep.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Boar.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Bull.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Duck.class, 0);
        MOUSE_CHANCES_TO_EAT.put(Caterpillar.class, 90);
        MOUSE_CHANCES_TO_EAT.put(Grass.class, 100);
    }

    static {
        GOAT_CHANCES_TO_EAT = new HashMap<>();

        GOAT_CHANCES_TO_EAT.put(Wolf.class, 0);
        GOAT_CHANCES_TO_EAT.put(Snake.class, 0);
        GOAT_CHANCES_TO_EAT.put(Fox.class, 0);
        GOAT_CHANCES_TO_EAT.put(Bear.class, 0);
        GOAT_CHANCES_TO_EAT.put(Eagle.class, 0);
        GOAT_CHANCES_TO_EAT.put(Horse.class, 0);
        GOAT_CHANCES_TO_EAT.put(Deer.class, 0);
        GOAT_CHANCES_TO_EAT.put(Hare.class, 0);
        GOAT_CHANCES_TO_EAT.put(Mouse.class, 0);
        GOAT_CHANCES_TO_EAT.put(Sheep.class, 0);
        GOAT_CHANCES_TO_EAT.put(Boar.class, 0);
        GOAT_CHANCES_TO_EAT.put(Bull.class, 0);
        GOAT_CHANCES_TO_EAT.put(Duck.class, 0);
        GOAT_CHANCES_TO_EAT.put(Caterpillar.class, 0);
        GOAT_CHANCES_TO_EAT.put(Grass.class, 100);
    }

    static {
        SHEEP_CHANCES_TO_EAT = new HashMap<>();

        SHEEP_CHANCES_TO_EAT.put(Wolf.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Snake.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Fox.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Bear.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Eagle.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Horse.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Deer.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Hare.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Mouse.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Goat.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Boar.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Bull.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Duck.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Caterpillar.class, 0);
        SHEEP_CHANCES_TO_EAT.put(Grass.class, 100);
    }

    static {
        BOAR_CHANCES_TO_EAT = new HashMap<>();

        BOAR_CHANCES_TO_EAT.put(Wolf.class, 0);
        BOAR_CHANCES_TO_EAT.put(Snake.class, 0);
        BOAR_CHANCES_TO_EAT.put(Fox.class, 0);
        BOAR_CHANCES_TO_EAT.put(Bear.class, 0);
        BOAR_CHANCES_TO_EAT.put(Eagle.class, 0);
        BOAR_CHANCES_TO_EAT.put(Horse.class, 0);
        BOAR_CHANCES_TO_EAT.put(Deer.class, 0);
        BOAR_CHANCES_TO_EAT.put(Hare.class, 0);
        BOAR_CHANCES_TO_EAT.put(Mouse.class, 50);
        BOAR_CHANCES_TO_EAT.put(Goat.class, 0);
        BOAR_CHANCES_TO_EAT.put(Sheep.class, 0);
        BOAR_CHANCES_TO_EAT.put(Bull.class, 0);
        BOAR_CHANCES_TO_EAT.put(Duck.class, 0);
        BOAR_CHANCES_TO_EAT.put(Caterpillar.class, 90);
        BOAR_CHANCES_TO_EAT.put(Grass.class, 100);
    }

    static {
        BULL_CHANCES_TO_EAT = new HashMap<>();

        BULL_CHANCES_TO_EAT.put(Wolf.class, 0);
        BULL_CHANCES_TO_EAT.put(Snake.class, 0);
        BULL_CHANCES_TO_EAT.put(Fox.class, 0);
        BULL_CHANCES_TO_EAT.put(Bear.class, 0);
        BULL_CHANCES_TO_EAT.put(Eagle.class, 0);
        BULL_CHANCES_TO_EAT.put(Horse.class, 0);
        BULL_CHANCES_TO_EAT.put(Deer.class, 0);
        BULL_CHANCES_TO_EAT.put(Hare.class, 0);
        BULL_CHANCES_TO_EAT.put(Mouse.class, 0);
        BULL_CHANCES_TO_EAT.put(Goat.class, 0);
        BULL_CHANCES_TO_EAT.put(Sheep.class, 0);
        BULL_CHANCES_TO_EAT.put(Boar.class, 0);
        BULL_CHANCES_TO_EAT.put(Duck.class, 0);
        BULL_CHANCES_TO_EAT.put(Caterpillar.class, 90);
        BULL_CHANCES_TO_EAT.put(Grass.class, 100);
    }

    static {
        DUCK_CHANCES_TO_EAT = new HashMap<>();

        DUCK_CHANCES_TO_EAT.put(Wolf.class, 0);
        DUCK_CHANCES_TO_EAT.put(Snake.class, 0);
        DUCK_CHANCES_TO_EAT.put(Fox.class, 0);
        DUCK_CHANCES_TO_EAT.put(Bear.class, 0);
        DUCK_CHANCES_TO_EAT.put(Eagle.class, 0);
        DUCK_CHANCES_TO_EAT.put(Horse.class, 0);
        DUCK_CHANCES_TO_EAT.put(Deer.class, 0);
        DUCK_CHANCES_TO_EAT.put(Hare.class, 0);
        DUCK_CHANCES_TO_EAT.put(Mouse.class, 0);
        DUCK_CHANCES_TO_EAT.put(Goat.class, 0);
        DUCK_CHANCES_TO_EAT.put(Sheep.class, 0);
        DUCK_CHANCES_TO_EAT.put(Boar.class, 0);
        DUCK_CHANCES_TO_EAT.put(Bull.class, 0);
        DUCK_CHANCES_TO_EAT.put(Caterpillar.class, 90);
        DUCK_CHANCES_TO_EAT.put(Grass.class, 100);
    }

    static {
        CATERPILLAR_CHANCES_TO_EAT = new HashMap<>();

        CATERPILLAR_CHANCES_TO_EAT.put(Wolf.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Snake.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Fox.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Bear.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Eagle.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Horse.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Deer.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Hare.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Mouse.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Goat.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Sheep.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Boar.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Bull.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Duck.class, 0);
        CATERPILLAR_CHANCES_TO_EAT.put(Grass.class, 100);
    }

    static {
        CONTAINER_OF_CHANCES = new HashMap<>();

        CONTAINER_OF_CHANCES.put(Wolf.class, WOLF_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Snake.class, SNAKE_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Fox.class, FOX_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Bear.class, BEAR_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Eagle.class, EAGLE_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Deer.class, DEER_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Hare.class, HARE_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Mouse.class, MOUSE_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Sheep.class, SHEEP_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Boar.class, BOAR_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Bull.class, BULL_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Duck.class, DUCK_CHANCES_TO_EAT);
        CONTAINER_OF_CHANCES.put(Caterpillar.class, CATERPILLAR_CHANCES_TO_EAT);
    }

}
