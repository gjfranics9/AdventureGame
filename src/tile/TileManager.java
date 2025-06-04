package tile;

import Map.MapManager;
import main.GamePanel;
import state.OverworldState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    OverworldState overworldState;
    MapManager mm;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(OverworldState overworldState){
        
        this.overworldState = overworldState;
        this.mm = overworldState.mm;
        tile = new Tile[256];
        mapTileNum = new int[overworldState.gp.maxWorldCol][overworldState.gp.maxWorldRow];

        
        getTileImage();
        System.out.println(mapTileNum[0][7]);
    }
    
    public void getTileImage(){
        
        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/treeTile.png")));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/grassTile.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/untextured.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/doorTile.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/pathTile.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/waterTile.png")));
            tile[5].collision = true;


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int[][] tileMap = mm.currentTileMap;

        for (int row = 0; row < overworldState.gp.maxWorldRow; row++) {
            for (int col = 0; col < overworldState.gp.maxWorldCol; col++) {
                int tileNum = tileMap[col][row];

                int worldX = col * overworldState.gp.tileSize;
                int worldY = row * overworldState.gp.tileSize;
                int screenX = worldX - overworldState.player.worldX + overworldState.player.screenX;
                int screenY = worldY - overworldState.player.worldY + overworldState.player.screenY;

                if (worldX + overworldState.gp.tileSize > overworldState.player.worldX - overworldState.player.screenX &&
                        worldX - overworldState.gp.tileSize < overworldState.player.worldX + overworldState.player.screenX &&
                        worldY + overworldState.gp.tileSize > overworldState.player.worldY - overworldState.player.screenY &&
                        worldY - overworldState.gp.tileSize < overworldState.player.worldY + overworldState.player.screenY) {

                    g2.drawImage(tile[tileNum].image, screenX, screenY, overworldState.gp.tileSize, overworldState.gp.tileSize, null);
                }
            }
        }
    }
}
