package game.pokemon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class PokemonDataLoader {

    private static final Json json = new Json();

    public static PokemonData loadPokemonData(int id) {

        String path = "pokemonData/" + id + ".json";

        FileHandle file = Gdx.files.internal(path);

        if (!file.exists()) {
            throw new RuntimeException("Pokémon data file not found: " + path);
        }

        return json.fromJson(PokemonData.class, file);
    }
}
