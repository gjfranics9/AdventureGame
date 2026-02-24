package game.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.pokemon.PokemonManager;
import game.state.GameStateManager;

public class Game extends ApplicationAdapter {

    public final int originalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    private SpriteBatch batch;

    public KeyHandler keyH;
    public GameStateManager stateManager;
    public PokemonManager pokemonManager;
    OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();

        keyH = new KeyHandler();
        Gdx.input.setInputProcessor(keyH);

        stateManager = new GameStateManager(this);
        pokemonManager = new PokemonManager();

        stateManager.setState("Overworld");
        stateManager.setupState();
        pokemonManager.generateNewPokemon(25);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        camera.update();
    }

    public void render() {

        // UPDATE
        stateManager.update();

        // CLEAR SCREEN
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // DRAW
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        stateManager.render(batch);
        batch.end();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
