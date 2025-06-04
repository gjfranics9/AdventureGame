package state;

import main.CollisionChecker;
import main.StubCollisionChecker;
import main.StubGamePanel;

import java.awt.*;

public class StubOverworldState extends OverworldState {

    public CollisionChecker cChecker;
    public StubOverworldState(StubGamePanel gp, StubCollisionChecker stubChecker) {
        super(gp);
        super.cChecker = stubChecker;
    }


    @Override
    public void draw(Graphics2D g2) {
    }

    @Override
    public void setupState(){
        mm.currentTileMap = new int[gp.maxWorldCol][gp.maxWorldRow];
    }
}
