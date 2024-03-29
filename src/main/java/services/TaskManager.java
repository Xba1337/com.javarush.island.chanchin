package services;

import lombok.Getter;
import util.MapViewer;
import world.Organism;
import world.constants.Constants;
import world.map.Cell;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.atomic.AtomicInteger;

public class TaskManager implements Runnable {


    private final MapViewer mapViewer;

    private final Cell[][] cells;

    @Getter
    private final AtomicInteger simulationSteps = new AtomicInteger(0);

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);


    public TaskManager(Cell[][] cells) {

        mapViewer = new MapViewer();
        this.cells = cells;
    }

    public void run() {
        simulationSteps.getAndIncrement();
        if (simulationSteps.get() >= Constants.MAX_STEPS_OF_SIMULATION) {
            System.exit(0);
        }
        Queue<Set<Organism>> queue = new ConcurrentLinkedQueue();

        for (Cell[] axisX :
                cells) {
            for (Cell cell :
                    axisX) {
                cell.getLock()
                    .lock();

                try {
                    Set<Organism> organisms = Collections.synchronizedSet(new HashSet<>());

                    organisms.addAll(cell.getContainedPlants());
                    organisms.addAll(cell.getContainedAnimals());
                    queue.add(organisms);
                    startCellProcessing(cell, organisms);
                } finally {
                    cell.getLock()
                        .unlock();
                }
            }
        }
        startView(queue);
    }

    public void startCellProcessing(Cell cell, Set<Organism> organisms) {
        cell.getLock()
            .lock();
        try {
            for (Organism organism : organisms) {
                executorService.submit(new OrganismTask(cell, organism));
            }
        } finally {
            cell.getLock()
                .unlock();
        }
    }

    public void startView(Queue<Set<Organism>> queue) {
        mapViewer.view(queue, simulationSteps.get());
        queue.clear();
    }
}
