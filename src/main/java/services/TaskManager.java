package services;

import lombok.Getter;
import resources.configs.util.MapViewer;
import world.Organism;
import world.animals.herbivorous.Deer;
import world.constants.Constants;
import world.map.Cell;
import world.map.WorldMap;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskManager implements Runnable {

    private final WorldMap worldMap;

    MapViewer mapViewer;


    @Getter
    private final AtomicInteger simulationSteps = new AtomicInteger(0);

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    private final ScheduledExecutorService mainExecutorService;

    public TaskManager(WorldMap worldMap, ScheduledExecutorService mainExecutorService) {
        this.worldMap = worldMap;
        this.mainExecutorService = mainExecutorService;
        mapViewer = new MapViewer(worldMap);
    }

    public void run() {
        startCellProcessing();
        if (simulationSteps.get() >= Constants.MAX_STEPS_OF_SIMULATION) {
            mainExecutorService.shutdown();
            executorService.shutdown();
        }

    }

    public void startCellProcessing() {
        Cell[][] cells = worldMap.getCells();
        simulationSteps.incrementAndGet();

        for (Cell[] axisX :
                cells) {
            for (Cell cell :
                    axisX) {
                cell.getLock()
                    .lock();
                try {
                    Set<Organism> organismSet = cell.init();
                    worldMap.print();
                    executorService.submit(new OrganismTask(organismSet, cell));
                } finally {
                    cell.getLock()
                        .unlock();
                }
            }
        }
    }
}
