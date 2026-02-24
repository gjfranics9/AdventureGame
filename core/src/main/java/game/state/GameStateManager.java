package game.state;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.main.Game;
import game.main.KeyHandler;

import java.util.Objects;

public class GameStateManager {

    public GameState currentState;
    private final OverworldState overworldState;
    private final BattleState battleState;
    private final KeyHandler keyH;

    public GameStateManager(Game gp) {
        this.overworldState = new OverworldState(gp);
        this.battleState = new BattleState(gp);
        this.keyH = gp.keyH;
    }
    public void setState(String state) {
        GameState newState;
        if(Objects.equals(state, "Overworld")) {
            newState = overworldState;
        }
        else if ((Objects.equals(state, "Battle"))) {
            newState = battleState;
        }
        else{
            newState = null;
        }
        this.currentState = newState;
        this.currentState.setupState();
    }

    public void update() {
        if (currentState != null) {
            currentState.update();
        }
        if(keyH.BPressed){
            setState("Battle");
        }
    }

    public void setupState(){
        currentState.setupState();
    }

    public void render(SpriteBatch batch) {
        if (currentState != null) {
            currentState.render(batch);
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }
}
