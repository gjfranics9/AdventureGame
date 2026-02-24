package game.state;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.main.Game;
import game.pokemon.PokemonInstance;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BattleState implements GameState {

    private final Game gp;
    private final List<PokemonInstance> party;
    private Texture backgroundImage;

    public BattleState(Game gp) {

        this.gp = gp;
        party = new ArrayList<>();
    }

    @Override
    public void update() {
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.draw(backgroundImage, 0, 0, gp.tileSize * gp.maxScreenCol, gp.tileSize * gp.maxScreenRow);
    }

    @Override
    public void setupState() {
        loadParty();
        loadImages();
    }

    public void loadParty(){

    }
    public void loadImages(){
        backgroundImage = new Texture("battle/battleBackground.png");
    }
}
