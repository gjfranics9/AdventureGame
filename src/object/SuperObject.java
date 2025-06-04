package object;

import main.GamePanel;
import state.OverworldState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, OverworldState overworldState) {

        int screenX = worldX - overworldState.player.worldX + overworldState.player.screenX;
        int screenY = worldY - overworldState.player.worldY + overworldState.player.screenY;

        if(worldX+overworldState.gp.tileSize>overworldState.player.worldX-overworldState.player.screenX &&
                worldX-overworldState.gp.tileSize<overworldState.player.worldX+overworldState.player.screenX &&
                worldY+overworldState.gp.tileSize>overworldState.player.worldY-overworldState.player.screenY &&
                worldY-overworldState.gp.tileSize<overworldState.player.worldY+overworldState.player.screenY){
            g2.drawImage(image, screenX, screenY, overworldState.gp.tileSize, overworldState.gp.tileSize, null);
        }
    }
}
