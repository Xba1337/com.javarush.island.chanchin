package world.map;

public class WorldMap {

    private static Cell[][] cells;

    public WorldMap(int x, int y) {
        cells = new Cell[x][y];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public static Cell getCell(int currentX, int currentY) {
        return cells[currentX][currentY];
    }
}
