package services;

import lombok.Getter;
import world.Organism;
import world.constants.Constants;
import world.map.Cell;
import world.map.WorldMap;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GameStarter {

    private final WorldMap worldMap;

    @Getter
    private final AtomicInteger simulationSteps = new AtomicInteger(0);

    private final ScheduledExecutorService mainExecutorService = Executors.newScheduledThreadPool(4);

    public GameStarter(WorldMap worldMap) {
        this.worldMap = worldMap;
    }


    public void init() {
        mainExecutorService.scheduleWithFixedDelay(new TaskManager(worldMap, mainExecutorService), Constants.INIT_DELAY, Constants.DELAY, TimeUnit.MILLISECONDS);
    }
}
