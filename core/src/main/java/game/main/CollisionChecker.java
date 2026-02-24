package game.main;

import game.entity.Entity;
import game.state.OverworldState;

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

                case "up":
                    entityTopRow = (entityTopWorldY - entity.speed) / overworldState.gp.tileSize;
                    checkedTile = overworldState.mm.currentTileMap[entityLeftCol][entityTopRow];
                    if (overworldState.tileM.tile[checkedTile].collision) {
                        entity.collisionOn = true;
                    }
                    break;

                case "down":
                    entityBottomRow = (entityBottomWorldY) / overworldState.gp.tileSize;
                    checkedTile = overworldState.mm.currentTileMap[entityLeftCol][entityBottomRow];
                    if (overworldState.tileM.tile[checkedTile].collision) {
                        entity.collisionOn = true;
                    }
                    break;

                case "left":
                    entityLeftCol = (entityLeftWorldX - entity.speed) / overworldState.gp.tileSize;
                    checkedTile = overworldState.mm.currentTileMap[entityLeftCol][entityTopRow];
                    if (overworldState.tileM.tile[checkedTile].collision) {
                        entity.collisionOn = true;
                    }
                    break;

                case "right":
                    entityRightCol = (entityRightWorldX) / overworldState.gp.tileSize;
                    checkedTile = overworldState.mm.currentTileMap[entityRightCol][entityTopRow];
                    if (overworldState.tileM.tile[checkedTile].collision) {
                        entity.collisionOn = true;
                    }
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            entity.collisionOn = true;
        }
    }
}
