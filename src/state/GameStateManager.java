package state;

import main.GamePanel;

import java.awt.*;

public class GameStateManager {

    public GameState currentState;
    public OverworldState overworldState;

    public GameStateManager(GamePanel gp) {
        this.overworldState = new OverworldState(gp);
    }
    public void setState(GameState newState) {
        this.currentState = newState;
    }

    public void update() {
        if (currentState != null) {
            currentState.update();
        }
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
