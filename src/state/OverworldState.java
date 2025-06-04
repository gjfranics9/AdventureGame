package state;

import Map.MapManager;
import entity.Player;
import main.AssetSetter;
import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;
import object.ObjectHandler;
import state.GameState;
import tile.TileManager;

import java.awt.Graphics2D;
import java.util.Map;

public class OverworldState implements GameState {

    public final GamePanel gp;
    public CollisionChecker cChecker;
    private final AssetSetter assetSetter;
    public final Player player;
    public MapManager mm;
    public final TileManager tileM;
    public final ObjectHandler objectHandler;

    public OverworldState(GamePanel gp) {

        this.gp = gp;
        this.cChecker = new CollisionChecker(this);
        this.assetSetter = new AssetSetter(this);
        this.mm = new MapManager(this);
        this.tileM = new TileManager(this);
        this.objectHandler = new ObjectHandler(this);
        this.player = new Player(this, gp.keyH);
    }


    @Override
    public void update() {
        player.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        tileM.draw(g2);
        player.draw(g2);
    }

    @Override
    public void setupState(){

        mm.loadTileMap(mm.getCurrentMap(), gp.maxWorldCol, gp.maxWorldRow);
        mm.setCurrentMap(0);
        var current_items = mm.retrieveCurrentMapItems();
        assetSetter.setObjectsFromMap(current_items);
    }
}
