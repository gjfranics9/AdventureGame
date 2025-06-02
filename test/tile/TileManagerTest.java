package tile;

import Map.MapManager;
import main.StubGamePanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TileManagerTest {

    private TileManager tileManager;
    @BeforeEach
    void setUp() {
        StubGamePanel mockGamePanel = new StubGamePanel();
        tileManager = new TileManager(mockGamePanel);
    }

    @Test
    void testTileInitialization() {
        tileManager.getTileImage();

        assertNotNull(tileManager.tile[0]);
        assertTrue(tileManager.tile[0].collision, "Tile 0 should have collision");

        assertNotNull(tileManager.tile[1]);
        assertFalse(tileManager.tile[1].collision, "Tile 1 should not have collision");

        assertNotNull(tileManager.tile[5]);
        assertTrue(tileManager.tile[5].collision, "Tile 5 should have collision");
    }
}
