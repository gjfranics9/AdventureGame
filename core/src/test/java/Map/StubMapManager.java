package Map;

import game.state.OverworldState;
import game.Map.MapManager;

public class StubMapManager extends MapManager {
    public StubMapManager(OverworldState os) {
        super(os);
        this.currentTileMap = new int[os.gp.maxWorldCol][os.gp.maxWorldRow];
        setCurrentMap(0); // Optional if you rely on this elsewhere
    }
}
