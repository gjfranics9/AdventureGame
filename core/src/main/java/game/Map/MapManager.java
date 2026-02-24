package game.Map;

import game.state.OverworldState;

import java.io.*;
import java.util.*;

public class MapManager {

    public OverworldState overworldState;
    public Map<Integer, String> maps;
    public Map<Integer, String> mapItems;
    private int currentMap;
    public int[][] currentTileMap;

    public MapManager(OverworldState overworldState) {
        this.overworldState = overworldState;
        this.maps = new HashMap<>();
        this.mapItems = new HashMap<>();
        this.currentMap = 0;
        assembleAllMaps();
        assembleAllMapsItems();
    }

    public void assembleAllMaps() {
        this.maps.put(0, "maps/OldCherryTown.txt");
        this.maps.put(1, "maps/randomMapForTesting.txt");
        this.maps.put(98, "maps/testMap.txt");
    }

    public void assembleAllMapsItems(){
        this.mapItems.put(0, "mapItems/OldCherryTownItems.txt");
    }

    public void setCurrentMap(int mapID) {
        currentMap = mapID;
    }

    public String getCurrentMap() {
        return maps.get(currentMap);
    }

    public Map<String, List<int[]>> retrieveCurrentMapItems() {
        String filename = mapItems.get(currentMap);
        Map<String, List<int[]>> itemMap = new HashMap<>();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
            if (is == null) {
                throw new FileNotFoundException("Could not find resource: " + filename);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length != 3) continue;

                String key = parts[0].toLowerCase();
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                itemMap.putIfAbsent(key, new ArrayList<>());
                itemMap.get(key).add(new int[]{x, y});
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return itemMap;
    }

    public void loadTileMap(String fileDirectory, int maxCols, int maxRows) {
        currentTileMap = new int[maxCols][maxRows];

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileDirectory);
            if (is == null) {
                throw new FileNotFoundException("Map file not found: " + fileDirectory);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            while (row < maxRows) {
                String line = br.readLine();
                if (line == null) break;

                String[] numbers = line.split(" ");
                for (int col = 0; col < Math.min(numbers.length, maxCols); col++) {
                    currentTileMap[col][row] = Integer.parseInt(numbers[col]);
                }

                row++;
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load tile map: " + fileDirectory, e);
        }
        currentTileMap = flipMapY(currentTileMap);
    }

    static int[][] flipMapY(int[][] src) {
        int cols = src.length;
        int rows = src[0].length;
        int[][] out = new int[cols][rows];

        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                out[c][rows - 1 - r] = src[c][r];
            }
        }
        return out;
    }
    public int[][] getCurrentTileMap(){
        return currentTileMap;
    }
}
