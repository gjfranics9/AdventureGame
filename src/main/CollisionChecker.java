package main;
import entity.Entity;
import state.OverworldState;
//comment

public class CollisionChecker {

    OverworldState overworldState;

    public CollisionChecker(OverworldState overworldState){
        this.overworldState = overworldState;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX - entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY - entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / overworldState.gp.tileSize;
        int entityRightCol = entityRightWorldX / overworldState.gp.tileSize;
        int entityTopRow = entityTopWorldY / overworldState.gp.tileSize;
        int entityBottomRow = entityBottomWorldY / overworldState.gp.tileSize;

        int checkedTile;


        try {
            switch (entity.direction) {
                case "up" -> {
                    entityTopRow = (entityTopWorldY - entity.speed) / overworldState.gp.tileSize;
                    checkedTile = overworldState.mm.currentTileMap[entityLeftCol][entityTopRow];
                    if (overworldState.tileM.tile[checkedTile].collision) {
                        entity.collisionOn = true;
                    }
                }
                case "down" -> {
                    entityBottomRow = (entityBottomWorldY) / overworldState.gp.tileSize;
                    checkedTile = overworldState.mm.currentTileMap[entityLeftCol][entityBottomRow];
                    if (overworldState.tileM.tile[checkedTile].collision) {
                        entity.collisionOn = true;
                    }
                }
                case "left" -> {
                    entityLeftCol = (entityLeftWorldX - entity.speed) / overworldState.gp.tileSize;
                    checkedTile = overworldState.mm.currentTileMap[entityLeftCol][entityTopRow];
                    if (overworldState.tileM.tile[checkedTile].collision) {
                        entity.collisionOn = true;
                    }
                }
                case "right" -> {
                    entityRightCol = (entityRightWorldX) / overworldState.gp.tileSize;
                    checkedTile = overworldState.mm.currentTileMap[entityRightCol][entityTopRow];
                    if (overworldState.tileM.tile[checkedTile].collision) {
                        entity.collisionOn = true;
                    }
                }
            }
        }
        catch (Exception e){
            entity.collisionOn = true;
        }
    }
}
