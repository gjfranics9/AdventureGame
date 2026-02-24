package state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import main.StubCollisionChecker;
import main.StubGamePanel;

import java.awt.*;

public class StubOverworldState extends game.state.OverworldState {

    public StubOverworldState(StubGamePanel gp, StubCollisionChecker stubChecker) {
        super(gp);
        super.cChecker = stubChecker;
    }


    @Override
    public void render(SpriteBatch batch) {
    }

    @Override
    public void setupState(){
        mm.currentTileMap = new int[gp.maxWorldCol][gp.maxWorldRow];
    }
}
