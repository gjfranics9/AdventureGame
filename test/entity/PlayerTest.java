package entity;

import main.StubCollisionChecker;
import main.StubGamePanel;
import main.StubKeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private StubKeyHandler keyH;
    private StubCollisionChecker cChecker;

    @BeforeEach
    void setup() {
        keyH = new StubKeyHandler();
        cChecker = new StubCollisionChecker();

        StubGamePanel gp = new StubGamePanel();
        gp.keyH = keyH;
        gp.cChecker = cChecker;

        player = new Player(gp);
    }


    @Test
    void testRightMovementWithoutCollision() {
        player.worldX = 7; // Not divisible by tileSize
        player.worldY = 7;
        int startX = player.worldX;

        cChecker.simulateCollision = false;
        keyH.rightPressed = true;

        player.update();

        assertAll(
                () -> assertTrue(player.moving),
                () -> assertEquals("right", player.direction),
                () -> assertTrue(player.worldX > startX),
                () -> assertFalse(player.collisionOn)
        );
    }

    @Test
    void testNoMovementWhenCollision() {
        keyH.rightPressed = true;
        int startX = player.worldX;
        cChecker.simulateCollision = true;

        player.update();

        assertTrue(player.moving); // still set to moving
        assertEquals("right", player.direction);
        assertEquals(startX, player.worldX); // didn't move due to collision
    }
}
