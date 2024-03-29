package services;


import world.constants.Constants;
import world.map.Cell;
import world.map.WorldMap;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameStarter {

    private final WorldMap worldMap;

    private final ScheduledExecutorService mainExecutorService = Executors.newSingleThreadScheduledExecutor();

    public GameStarter(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void init() {
        Cell[][] cells = worldMap.getCells();
        mainExecutorService.scheduleWithFixedDelay(new TaskManager(cells), Constants.INIT_DELAY, Constants.DELAY, TimeUnit.MILLISECONDS);
    }
}
