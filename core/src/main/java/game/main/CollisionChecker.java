package game.main;

import game.entity.Entity;
import game.state.OverworldState;

public class CollisionChecker {

    OverworldState overworldState;

    public CollisionChecker(OverworldState overworldState) {
        this.overworldState = overworldState;
    }

    public void checkTile(Entity e) {
        int ts = overworldState.gp.tileSize;

        int left   = e.worldX + e.solidArea.x;
        int bottom = e.worldY + e.solidArea.y;
        int right  = left + e.solidArea.width  - 1;
        int top    = bottom + e.solidArea.height - 1;

        int leftCol   = left / ts;
        int rightCol  = right / ts;
        int bottomRow = bottom / ts;
        int topRow    = top / ts;

        e.collisionOn = false;

        try {
            switch (e.direction) {
                case "up" -> {
                    int nextTopRow = (top + e.speed) / ts; // Y-up: up increases Y
                    int t1 = overworldState.currentTileMap[leftCol][nextTopRow];
                    int t2 = overworldState.currentTileMap[rightCol][nextTopRow];
                    if (overworldState.tileM.tile[t1].collision || overworldState.tileM.tile[t2].collision) e.collisionOn = true;
                }
                case "down" -> {
                    int nextBottomRow = (bottom - e.speed) / ts; // down decreases Y
                    int t1 = overworldState.currentTileMap[leftCol][nextBottomRow];
                    int t2 = overworldState.currentTileMap[rightCol][nextBottomRow];
                    if (overworldState.tileM.tile[t1].collision || overworldState.tileM.tile[t2].collision) e.collisionOn = true;
                }
                case "left" -> {
                    int nextLeftCol = (left - e.speed) / ts;
                    int t1 = overworldState.currentTileMap[nextLeftCol][topRow];
                    int t2 = overworldState.currentTileMap[nextLeftCol][bottomRow];
                    if (overworldState.tileM.tile[t1].collision || overworldState.tileM.tile[t2].collision) e.collisionOn = true;
                }
                case "right" -> {
                    int nextRightCol = (right + e.speed) / ts;
                    int t1 = overworldState.currentTileMap[nextRightCol][topRow];
                    int t2 = overworldState.currentTileMap[nextRightCol][bottomRow];
                    if (overworldState.tileM.tile[t1].collision || overworldState.tileM.tile[t2].collision) e.collisionOn = true;
                }
            }
        } catch (Exception ex) {
            e.collisionOn = true;
        }
    }
}
