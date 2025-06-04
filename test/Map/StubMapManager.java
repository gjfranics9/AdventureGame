package Map;

import state.OverworldState;

public class StubMapManager extends MapManager {
    public StubMapManager(OverworldState os) {
        super(os);
        this.currentTileMap = new int[os.gp.maxWorldCol][os.gp.maxWorldRow];
        setCurrentMap(0); // Optional if you rely on this elsewhere
    }
}
