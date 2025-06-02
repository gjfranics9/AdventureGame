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
    void testLeftMovementWithoutCollision() {
        player.worldX = 7; // Not divisible by tileSize
        player.worldY = 7;
        int startX = player.worldX;

        cChecker.simulateCollision = false;
        keyH.leftPressed = true;

        player.update();

        assertAll(
                () -> assertTrue(player.moving),
                () -> assertEquals("left", player.direction),
                () -> assertTrue(player.worldX < startX),
                () -> assertFalse(player.collisionOn)
        );
    }

    @Test
    void testUpMovementWithoutCollision() {
        player.worldX = 7; // Not divisible by tileSize
        player.worldY = 7;
        int startY = player.worldY;

        cChecker.simulateCollision = false;
        keyH.upPressed = true;

        player.update();

        assertAll(
                () -> assertTrue(player.moving),
                () -> assertEquals("up", player.direction),
                () -> assertTrue(player.worldY < startY),
                () -> assertFalse(player.collisionOn)
        );
    }

    @Test
    void testDownMovementWithoutCollision() {
        player.worldX = 7; // Not divisible by tileSize
        player.worldY = 7;
        int startY = player.worldY;

        cChecker.simulateCollision = false;
        keyH.downPressed = true;

        player.update();

        assertAll(
                () -> assertTrue(player.moving),
                () -> assertEquals("down", player.direction),
                () -> assertTrue(player.worldY > startY),
                () -> assertFalse(player.collisionOn)
        );
    }

    @Test
    void testNoRightMovementWhenCollision() {
        keyH.rightPressed = true;
        cChecker.simulateCollision = true;

        int startX = player.worldX;
        player.moving = false;

        player.update();

        assertEquals("right", player.direction);
        assertEquals(startX, player.worldX);
        assertTrue(player.moving);
    }

    @Test
    void testNoLeftMovementWhenCollision() {
        keyH.leftPressed = true;
        cChecker.simulateCollision = true;

        int startX = player.worldX;
        player.moving = false;

        player.update();

        assertEquals("left", player.direction);
        assertEquals(startX, player.worldX);
        assertTrue(player.moving);
    }

    @Test
    void testNoUpMovementWhenCollision() {
        keyH.upPressed = true;
        cChecker.simulateCollision = true;

        int startY = player.worldY;
        player.moving = false;

        player.update();

        assertEquals("up", player.direction);
        assertEquals(startY, player.worldY);
        assertTrue(player.moving);
    }

    @Test
    void testNoDownMovementWhenCollision() {
        keyH.downPressed = true;
        cChecker.simulateCollision = true;

        int startY = player.worldY;
        player.moving = false;

        player.update();

        assertEquals("down", player.direction);
        assertEquals(startY, player.worldY);
        assertTrue(player.moving);
    }

    @Test
    void testSprintingIncreasesMovementSpeed() {
        int initialX = player.worldX;

        keyH.rightPressed = true;
        keyH.spacePressed = true;
        cChecker.simulateCollision = false;
        player.moving = false;

        player.update();
        int sprintX = player.worldX;

        player.worldX = initialX;
        keyH.spacePressed = false;
        player.moving = false;

        player.update();
        int normalX = player.worldX;

        assertTrue(sprintX - initialX > normalX - initialX,
                "Player should move further when sprinting");
    }
}
