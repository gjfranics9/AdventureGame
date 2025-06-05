package pokemon;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class PokemonDataLoader {

    public static PokemonData loadPokemonData(int id) throws IOException {
        String path = "/pokemon/pokemonData/" + id + ".json";
        InputStream is = PokemonDataLoader.class.getResourceAsStream(path);

        if (is == null) {
            throw new IOException("Pokémon data file not found: " + path);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(is, PokemonData.class);
    }
}
