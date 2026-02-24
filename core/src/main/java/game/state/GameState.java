package game.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameState {
    void update();
    void render(SpriteBatch batch);
    void setupState();
}
