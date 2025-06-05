package pokemon;

import java.io.IOException;

public class PokemonManager {

    public PokemonInstance[] ownedPokemon;
    public PokemonManager(){
        ownedPokemon = new PokemonInstance[1024];
    }

    public void generateNewPokemon(int ID) throws IOException {
        long pID = Integer.toUnsignedLong(new java.util.Random().nextInt());
        PokemonInstance pokemon = new PokemonInstance(ID, pID);
    }
}
