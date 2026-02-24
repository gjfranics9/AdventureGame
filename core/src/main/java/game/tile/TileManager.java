package game.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.Map.MapManager;
import game.state.OverworldState;

public class TileManager {

    OverworldState overworldState;
    MapManager mm;
    public Tile[] tile;
    public TileManager(OverworldState overworldState){

        this.overworldState = overworldState;
        this.mm = overworldState.mm;
        tile = new Tile[256];

        getTileImage();
    }



    public void getTileImage(){

        tile[0] = new Tile();
        tile[0].image = new Texture("tiles/treeTile.png");
        tile[0].collision = true;

        tile[1] = new Tile();
        tile[1].image = new Texture("tiles/grassTile.png");

        tile[2] = new Tile();
        tile[2].image = new Texture("tiles/untextured.png");
        tile[2].collision = true;

        tile[3] = new Tile();
        tile[3].image = new Texture("tiles/doorTile.png");

        tile[4] = new Tile();
        tile[4].image = new Texture("tiles/pathTile.png");

        tile[5] = new Tile();
        tile[5].image = new Texture("tiles/waterTile.png");
        tile[5].collision = true;


    }

    public void render(SpriteBatch batch) {
        int[][] tileMap = overworldState.currentTileMap;
        if (tileMap == null) return;

        int mapCols = tileMap.length;
        int mapRows = tileMap[0].length;

        for (int row = 0; row < mapRows; row++) {
            for (int col = 0; col < mapCols; col++) {
                int tileNum = tileMap[col][row];

                int worldX = col * overworldState.gp.tileSize;
                int worldY = row * overworldState.gp.tileSize;

                batch.draw(tile[tileNum].image, worldX, worldY,
                    overworldState.gp.tileSize, overworldState.gp.tileSize);
            }
        }
    }
}
