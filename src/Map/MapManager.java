package Map;

import main.GamePanel;

import java.io.*;
import java.util.*;

public class MapManager {

    public GamePanel gp;
    public String[][] maps;
    public int currentMap;
    public int[][] currentTileMap;

    public MapManager(GamePanel gp) {
        this.gp = gp;
        this.maps = new String[99][2];
        this.currentMap = 0;
        assembleAllMaps();
    }

    public void assembleAllMaps() {
        this.maps[0][0] = "maps/OldCherryTown.txt";
        this.maps[0][1] = "maps/OldCherryTownItems.txt";
        this.maps[98][0] = "maps/testMap.txt";
    }

    public void setCurrentMap(int mapID) {
        currentMap = mapID;
    }

    public String getCurrentMap() {
        return maps[currentMap][0];
    }

    public Map<String, List<int[]>> retrieveCurrentMapItems() {
        String filename = maps[currentMap][1];
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
    }

}
