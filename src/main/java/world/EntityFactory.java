package world;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import world.animals.herbivorous.*;
import world.animals.predators.*;
import world.plants.Grass;
import lombok.Getter;



import java.io.File;
import java.io.IOException;
import java.util.HashSet;


public class EntityFactory {
 /*   @Getter
    private static final Set<Class<? extends Eatable>> TYPES;
    @Getter
//    private static final Map<Class<? extends Organism>, Organism> BASE;
    private static final Set<Eatable> BASE;

    static {
 //       BASE = new HashMap<>();
        BASE = new HashSet<>();
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
        TYPES.add(Grass.class);

        try {
            init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*public static Organism getBaseObj(Class<? extends Organism> organism){
        Organism result = null;
        Set<Map.Entry<Class<? extends Organism>, Organism>> entrySet = BASE.entrySet();
        for (Map.Entry<Class<? extends Organism>, Organism> pair:
             entrySet) {
            if (organism.equals(pair.getKey())){
                result = pair.getValue();
            }
        }
        return result;
    }*/



    /*private static void init() throws Exception {
        for (Class<? extends Eatable> organismType : TYPES) {
            Eatable eatable = createBase(organismType);
 //           BASE.put(organismType, organism);
            BASE.add(eatable);
        }
    }

    private static Eatable createBase(Class<? extends Eatable> type) throws IOException {
        if (! (type.isAnnotationPresent(Config.class))) {
            throw new IllegalArgumentException(String.format("Класс %s не содержит @Config аннотации", type.getSimpleName()));
        }

        Config annoConfig = type.getAnnotation(Config.class);

        File file = new File(annoConfig.fileName());
        Eatable eatableType = createObject(file, type);

        return eatableType;
    }

    private static Eatable createObject(File file, Class<? extends Eatable> type) throws IOException {
        ObjectMapper yamlMapper = new YAMLMapper();
        System.out.println(yamlMapper.readValue(file, type));
        Eatable eatable;
        try {
            eatable = yamlMapper.readValue(file, type);
        } catch (Exception e) {
            System.out.printf("Отсутвует конфиг файл %s для класса %s", file, type);
            throw new RuntimeException();                                                   // НАДО ОПИСАТЬ ИСКЛЮЧЕНИЕ!!!!!!!!!!!!!!!!!!
        }
        System.out.println(eatable);
        return eatable;
    } */

}
