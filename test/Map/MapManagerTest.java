package Map;

import main.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapManagerTest {

    private MapManager mapManager;

    @BeforeEach
    void setUp() {
        GamePanel mockGamePanel = new GamePanel(); // Replace with mock if needed
        mapManager = new MapManager(mockGamePanel);
    }

    @Test
    void testSetAndGetCurrentMap() {
        mapManager.setCurrentMap(0);
        assertEquals("maps/OldCherryTown.txt", mapManager.getCurrentMap());

        mapManager.setCurrentMap(98);
        assertEquals("maps/testMap.txt", mapManager.getCurrentMap());
    }

    @Test
    void testAssembleAllMapsSetsCorrectPaths() {
        assertEquals("maps/OldCherryTown.txt", mapManager.maps[0][0]);
        assertEquals("maps/OldCherryTownItems.txt", mapManager.maps[0][1]);
        assertEquals("maps/testMap.txt", mapManager.maps[98][0]);
    }
}
