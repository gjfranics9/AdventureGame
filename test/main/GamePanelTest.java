package main;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
class GamePanelTest {
    @Test
    void testPaintComponentDoesNotThrow() {
        GamePanel panel = new StubGamePanel();
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();


        assertDoesNotThrow(() -> panel.paintComponent(g2d));

        g2d.dispose();
    }
}
