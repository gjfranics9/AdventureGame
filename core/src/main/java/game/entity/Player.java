package game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.main.KeyHandler;
import game.state.OverworldState;

import java.awt.*;

public class Player extends Entity{

    OverworldState overworldState;
    public KeyHandler keyH;
    private int keyTimer;
    public final int screenX;
    public final int screenY;
    public Texture image;

    public Player(OverworldState overworldState, KeyHandler keyH){

        this.overworldState = overworldState;
        this.keyH = keyH;
        this.keyTimer = 0;

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
        playerDown1 = new Texture("player/playerDown1.png");
        playerDown2 = new Texture("player/playerDown2.png");
        playerDownIDLE = new Texture("player/playerIDLE.png");
        playerBack1 = new Texture("player/playerBack1.png");
        playerBack2 = new Texture("player/playerBack2.png");
        playerBackIDLE = new Texture("player/playerBackIDLE.png");
        playerLeft1 = new Texture("player/playerLeft1.png");
        playerLeft2 = new Texture("player/playerLeft2.png");
        playerLeftIDLE = new Texture("player/playerLeftIDLE.png");
        playerRight1 = new Texture("player/playerRight1.png");
        playerRight2 = new Texture("player/playerRight2.png");
        playerRightIDLE = new Texture("player/playerRightIDLE.png");
    }

    public boolean checkIfKeyHeld(){
        if(keyH.upPressed || keyH.leftPressed || keyH.rightPressed || keyH.downPressed){
            keyTimer += 1;
        }
        else{
            keyTimer = 0;
        }
        boolean keyHeld;
        keyHeld = keyTimer >= 8;
        return keyHeld;
    }
    public void checkWhichDirectionPlayerFacing(int tileSize){
        if (!moving && worldX % tileSize == 0 && worldY % tileSize == 0) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }
        }
    }

    public void update() {
        sprinting = keyH.spacePressed;
        int tileSize = overworldState.gp.tileSize;
        int actualSpeed = sprinting ? 6 : 4;

        checkWhichDirectionPlayerFacing(tileSize);
        if(checkIfKeyHeld()){
            moving = true;
        }


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
                        else worldY = Math.max(worldY + actualSpeed, (worldY / tileSize) * tileSize);
                    }
                    case "down" -> {
                        if (remainderY == 0) moving = false;
                        else worldY = Math.min(worldY - actualSpeed, ((worldY / tileSize) + 1) * tileSize);
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
                    case "up" -> worldY += actualSpeed;
                    case "down" -> worldY -= actualSpeed;
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
    public void render(SpriteBatch batch) {
        selectDrawnImage();
        batch.draw(image, screenX, screenY, overworldState.gp.tileSize, overworldState.gp.tileSize);
    }
}
