package game.main;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class KeyHandler implements InputProcessor {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean spacePressed;
    public boolean BPressed;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W, Input.Keys.UP -> upPressed = true;
            case Input.Keys.S, Input.Keys.DOWN -> downPressed = true;
            case Input.Keys.A, Input.Keys.LEFT -> leftPressed = true;
            case Input.Keys.D, Input.Keys.RIGHT -> rightPressed = true;
            case Input.Keys.SPACE -> spacePressed = true;
            case Input.Keys.B -> BPressed = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W, Input.Keys.UP -> upPressed = false;
            case Input.Keys.S, Input.Keys.DOWN -> downPressed = false;
            case Input.Keys.A, Input.Keys.LEFT -> leftPressed = false;
            case Input.Keys.D, Input.Keys.RIGHT -> rightPressed = false;
            case Input.Keys.SPACE -> spacePressed = false;
        }
        return true;
    }

    // Unused InputProcessor methods
    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }
}
