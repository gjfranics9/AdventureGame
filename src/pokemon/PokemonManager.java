package pokemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokemonManager {

    public List<PokemonInstance> ownedPokemon;
    public PokemonManager(){
        ownedPokemon = new ArrayList<>();
    }

    public void generateNewPokemon(int ID){
        long pID = Integer.toUnsignedLong(new java.util.Random().nextInt());
        try {
            PokemonInstance pokemon = new PokemonInstance(ID, pID);
            ownedPokemon.add(pokemon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
