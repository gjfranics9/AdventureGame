package state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import main.StubGamePanel;
import game.state.BattleState;

public class StubBattleState extends BattleState {

    public StubBattleState(StubGamePanel gp) {
        super(gp);
    }


    @Override
    public void render(SpriteBatch batch) {
    }

}
