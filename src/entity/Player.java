package entity;

import main.KeyHandler;
import state.OverworldState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    OverworldState overworldState;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public BufferedImage image;

    public Player(OverworldState overworldState, KeyHandler keyH){

        this.overworldState = overworldState;
        this.keyH = keyH;

        screenX = overworldState.gp.screenWidth/2;
        screenY = overworldState.gp.screenHeight/2;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = (overworldState.gp.tileSize);
        solidArea.height = (overworldState.gp.tileSize);

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues(){

        moving = false;
        worldX = overworldState.gp.tileSize * overworldState.gp.maxWorldCol/2;
        worldY = overworldState.gp.tileSize * overworldState.gp.maxWorldRow/2;
        speed = 4;
        direction = "down";
        sprinting = false;
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

    public void checkWhichDirectionPlayerFacing(int tileSize){
        if (!moving && worldX % tileSize == 0 && worldY % tileSize == 0) {
            if (keyH.upPressed) {
                direction = "up";
                moving = true;
            } else if (keyH.downPressed) {
                direction = "down";
                moving = true;
            } else if (keyH.leftPressed) {
                direction = "left";
                moving = true;
            } else if (keyH.rightPressed) {
                direction = "right";
                moving = true;
            }
        }
    }

    public void update() {
        sprinting = keyH.spacePressed;
        int tileSize = overworldState.gp.tileSize;
        int actualSpeed = sprinting ? 6 : 4;

        checkWhichDirectionPlayerFacing(tileSize);


        if (moving) {
            collisionOn = false;
            overworldState.cChecker.checkTile(this);

            boolean continueMoving = switch (direction) {
                case "up" -> keyH.upPressed;
                case "down" -> keyH.downPressed;
                case "left" -> keyH.leftPressed;
                case "right" -> keyH.rightPressed;
                default -> false;
            };

            if (!continueMoving) {
                int remainderX = worldX % tileSize;
                int remainderY = worldY % tileSize;
                switch (direction) {
                    case "up" -> {
                        if (remainderY == 0) moving = false;
                        else worldY = Math.max(worldY - actualSpeed, (worldY / tileSize) * tileSize);
                    }
                    case "down" -> {
                        if (remainderY == 0) moving = false;
                        else worldY = Math.min(worldY + actualSpeed, ((worldY / tileSize) + 1) * tileSize);
                    }
                    case "left" -> {
                        if (remainderX == 0) moving = false;
                        else worldX = Math.max(worldX - actualSpeed, (worldX / tileSize) * tileSize);
                    }
                    case "right" -> {
                        if (remainderX == 0) moving = false;
                        else worldX = Math.min(worldX + actualSpeed, ((worldX / tileSize) + 1) * tileSize);
                    }
                }
            } else if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= actualSpeed;
                    case "down" -> worldY += actualSpeed;
                    case "left" -> worldX -= actualSpeed;
                    case "right" -> worldX += actualSpeed;
                }
            }


            if (worldX % tileSize != 0 && (direction.equals("left") || direction.equals("right"))) {
                if (!continueMoving && (worldX % tileSize < actualSpeed || tileSize - (worldX % tileSize) < actualSpeed)) {
                    worldX = Math.round(worldX / (float) tileSize) * tileSize;
                }
            }
            if (worldY % tileSize != 0 && (direction.equals("up") || direction.equals("down"))) {
                if (!continueMoving && (worldY % tileSize < actualSpeed || tileSize - (worldY % tileSize) < actualSpeed)) {
                    worldY = Math.round(worldY / (float) tileSize) * tileSize;
                }
            }
        }

        spriteCounter++;
        if (spriteCounter > 6) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    public void selectDrawnImage(){
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
    }
    public void draw(Graphics2D g2){
        selectDrawnImage();
        g2.drawImage(image, screenX, screenY, overworldState.gp.tileSize, overworldState.gp.tileSize, null);
    }
}
