package resources.configs.util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    public static int getRandom(int from, int to){
        return ThreadLocalRandom.current()
                                .nextInt(from, to + 1);
    }

    public static boolean getRandom(int chance){
        int roll = ThreadLocalRandom.current()
                                      .nextInt(0, 100);

        return roll <= chance;
    }
}
