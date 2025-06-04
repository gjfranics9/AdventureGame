package main;

import state.StubOverworldState;

public class StubGamePanel extends GamePanel {
    public int screenWidth = 640;
    public int screenHeight = 480;
    public int tileSize = 16;
    public int maxWorldCol = 50;
    public int maxWorldRow = 50;

    public StubOverworldState stubOverworldState;

    public StubGamePanel() {
        StubCollisionChecker stubCollisionChecker = new StubCollisionChecker();
        stubOverworldState = new StubOverworldState(this, stubCollisionChecker);
        stubOverworldState.setupState();
    }

}
