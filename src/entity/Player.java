package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = (gp.tileSize);
        solidArea.height = (gp.tileSize);

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues(){

        moving = false;
        worldX = gp.tileSize * gp.maxWorldCol/2;
        worldY = gp.tileSize * gp.maxWorldRow/2;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            playerDown1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerDown1.png")));
            playerDown2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerDown2.png")));
            playerDownIDLE = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerIDLE.png")));
            playerBack1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerBack1.png")));
            playerBack2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerBack2.png")));
            playerBackIDLE = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerBackIDLE.png")));
            playerLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerLeft1.png")));
            playerLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerLeft2.png")));
            playerLeftIDLE = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerLeftIDLE.png")));
            playerRight1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerRight1.png")));
            playerRight2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerRight2.png")));
            playerRightIDLE = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/playerRightIDLE.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed && !moving) {
            direction = "up";
            moving = true;
        } else if (keyH.downPressed && !moving) {
            direction = "down";
            moving = true;
        } else if (keyH.leftPressed && !moving) {
            direction = "left";
            moving = true;
        } else if (keyH.rightPressed && !moving) {
            direction = "right";
            moving = true;
        } else {
                if(worldX%gp.tileSize==0 && worldY%gp.tileSize==0) {
                    moving = false;
                }
            }

        collisionOn = false;
        gp.cChecker.checkTile(this);

        //position update block
        if(!collisionOn && moving){
            switch (direction) {
                case "up" -> {
                    worldY -= gp.tileSize / movementFraction;
                }
                case "down" -> {
                    worldY += gp.tileSize / movementFraction;
                }
                case "left" -> {
                    worldX -= gp.tileSize / movementFraction;
                }
                case "right" -> {
                    worldX += gp.tileSize / movementFraction;
                }
            }
        }


        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (moving) {
                    if (spriteNum == 1) {
                        image = playerBack1;
                    }
                    if (spriteNum == 2) {
                        image = playerBack2;
                    }
                } else {
                    image = playerBackIDLE;
                }
            }
            case "down" -> {
                if (moving) {
                    if (spriteNum == 1) {
                        image = playerDown1;
                    }
                    if (spriteNum == 2) {
                        image = playerDown2;
                    }
                } else {
                    image = playerDownIDLE;
                }
            }
            case "left" -> {
                if (moving) {
                    if (spriteNum == 1) {
                        image = playerLeft1;
                    }
                    if (spriteNum == 2) {
                        image = playerLeft2;
                    }
                } else {
                    image = playerLeftIDLE;
                }
            }
            case "right" -> {
                if (moving) {
                    if (spriteNum == 1) {
                        image = playerRight1;
                    }
                    if (spriteNum == 2) {
                        image = playerRight2;
                    }
                } else {
                    image = playerRightIDLE;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
