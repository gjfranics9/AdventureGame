package Map;

import main.StubGamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import game.Map.MapManager;

class MapManagerTest {

    private MapManager mapManager;

    @BeforeEach
    void setUp() {
        StubGamePanel mockGamePanel = new StubGamePanel();
        mapManager = new MapManager(mockGamePanel.stubOverworldState);
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
        assertEquals("maps/OldCherryTown.txt", mapManager.maps.get(0));
        assertEquals("maps/testMap.txt", mapManager.maps.get(98));
    }
}
