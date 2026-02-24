package game.pokemon;

import com.badlogic.gdx.utils.ObjectMap;
import game.pokemon.moves.MoveList;

public class PokemonData {
    public int id;
    public String name;
    public ObjectMap<String, Double> baseStats;
    public String[] abilities;
    public String hiddenAbility;
    public int baseFriendship;
    public ObjectMap<String, Double> genderRatio;
    public String[] type;
    public int baseExperience;
    public Evolution evolution;
    public String sprite;
    public String backSprite;
    public String icon;
    public int catchRate;
    public String[] eggGroups;
    public int hatchSteps;
    public double height;
    public double weight;
    public MoveList moves;
    public String description;
}
