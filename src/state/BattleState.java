package state;

import main.GamePanel;
import pokemon.PokemonInstance;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BattleState implements GameState {

    private final GamePanel gp;
    private final List<PokemonInstance> party;
    private BufferedImage backgroundImage;

    public BattleState(GamePanel gp) {

        this.gp = gp;
        party = new ArrayList<>();
    }

    @Override
    public void update() {
        System.out.println(party.get(0).species);
    }

    @Override
    public void draw(Graphics2D g2) {

        g2.drawImage(backgroundImage, 0, 0, gp.tileSize * gp.maxScreenCol, gp.tileSize * gp.maxScreenRow, null);
    }

    @Override
    public void setupState() {
        party.add(gp.pokemonManager.ownedPokemon.get(0));
        loadImages();
    }

    public void loadImages(){
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("battle/battleBackground.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
