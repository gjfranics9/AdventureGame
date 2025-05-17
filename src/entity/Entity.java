package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX,worldY;
    public int speed;

    public BufferedImage playerBack1, playerBack2, playerBackIDLE, playerDown1, playerDown2, playerDownIDLE, playerLeft1, playerLeft2, playerLeftIDLE, playerRight1, playerRight2, playerRightIDLE;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public boolean moving;

    public int movementFraction = 10;

    public Rectangle solidArea;
    public boolean collisionOn = false;
}
