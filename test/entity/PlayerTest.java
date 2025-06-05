package entity;

import main.StubCollisionChecker;
import main.StubGamePanel;
import main.StubKeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import state.StubOverworldState;

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
        gp.stubOverworldState = new StubOverworldState(gp, cChecker);
        gp.stubOverworldState.setupState();

        player = new Player(gp.stubOverworldState,keyH);
        player.worldX = 0;
        player.worldY = 0;
    }


    @Test
    void testRightMovementWithoutCollision() {
        int startX = player.worldX;

        cChecker.simulateCollision = false;
        player.keyH.rightPressed = true;

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
        int startX = player.worldX;

        cChecker.simulateCollision = false;
        player.keyH.leftPressed = true;

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
        int startY = player.worldY;

        cChecker.simulateCollision = false;
        player.keyH.upPressed = true;

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
        int startY = player.worldY;

        cChecker.simulateCollision = false;
        player.keyH.downPressed = true;

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
        player.keyH.rightPressed = true;
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
        player.keyH.leftPressed = true;
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
        player.keyH.upPressed = true;
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
        player.keyH.downPressed = true;
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

        player.keyH.rightPressed = true;
        player.keyH.spacePressed = true;
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
