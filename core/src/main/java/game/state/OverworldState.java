package game.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.Map.MapManager;
import game.entity.Player;
import game.main.AssetSetter;
import game.object.ObjectHandler;
import game.tile.TileManager;
import game.main.CollisionChecker;
import game.main.Game;

import java.util.*;

public class OverworldState implements GameState {

    public final Game gp;
    public CollisionChecker cChecker;
    private final AssetSetter assetSetter;
    public final Player player;
    public MapManager mm;
    public final TileManager tileM;
    public final ObjectHandler objectHandler;
    public int[][] currentTileMap;

    public OverworldState(Game gp) {

        this.gp = gp;
        this.cChecker = new CollisionChecker(this);
        this.assetSetter = new AssetSetter(this);
        this.mm = new MapManager(this);
        this.tileM = new TileManager(this);
        this.objectHandler = new ObjectHandler(this);
        this.player = new Player(this, gp.keyH);
        gp.player = player;
    }


    @Override
    public void update() {
        player.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        tileM.render(batch);
        player.render(batch);
        objectHandler.render(batch);
    }

    @Override
    public void setupState(){
        mm.setCurrentMap(0);
        mm.loadTileMap(mm.getCurrentMap(), gp.maxWorldCol, gp.maxWorldRow);
        currentTileMap = mm.getCurrentTileMap();
        Map<String, List<int[]>> current_items = mm.retrieveCurrentMapItems();
        assetSetter.setObjectsFromMap(current_items);
    }
}
