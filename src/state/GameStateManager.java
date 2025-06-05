package state;

import main.GamePanel;

import java.awt.*;
import java.util.Objects;

public class GameStateManager {

    public GameState currentState;
    public OverworldState overworldState;
    public BattleState battleState;

    public GameStateManager(GamePanel gp) {
        this.overworldState = new OverworldState(gp);
        this.battleState = new BattleState(gp);
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
    }

    public void update() {
        if (currentState != null) {
            currentState.update();
        }
    }

    public void setupState(){
        currentState.setupState();
    }

    public void draw(Graphics2D g2) {
        if (currentState != null) {
            currentState.draw(g2);
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }
}
