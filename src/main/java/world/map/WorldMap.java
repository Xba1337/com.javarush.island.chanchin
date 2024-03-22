package world.map;

public class WorldMap {

    private final Cell[][] cells;

    public WorldMap(int x, int y){
        this.cells = new Cell[x][y];
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Cell getCell(int currentX, int currentY){
        return cells[currentX][currentY];
    }
}
