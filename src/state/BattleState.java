package state;

import main.GamePanel;
import state.GameState;

import java.awt.Graphics2D;

public class BattleState implements GameState {

    private final GamePanel gp;

    public BattleState(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void update() {
        // TODO: Battle logic
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawString("Battle mode!", 100, 100);
    }

    @Override
    public void setupState() {

    }
}
