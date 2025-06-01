package main;
import entity.Entity;
//comment

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX - entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY - entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int checkedTile;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                checkedTile = gp.mm.currentTileMap[entityLeftCol][entityTopRow];
                if(gp.tileM.tile[checkedTile].collision){entity.collisionOn = true;}
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY) / gp.tileSize;
                checkedTile = gp.mm.currentTileMap[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[checkedTile].collision){entity.collisionOn = true;}
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                checkedTile = gp.mm.currentTileMap[entityLeftCol][entityTopRow];
                if(gp.tileM.tile[checkedTile].collision){entity.collisionOn = true;}
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX) / gp.tileSize;
                checkedTile = gp.mm.currentTileMap[entityRightCol][entityTopRow];
                if(gp.tileM.tile[checkedTile].collision){entity.collisionOn = true;}
            }
        }
    }
}
