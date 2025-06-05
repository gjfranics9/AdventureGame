package pokemon;

import pokemon.abilities.Ability;
import pokemon.abilities.AbilityFactory;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

public class PokemonInstance {

    public String species;
    public int id;
    public int[] ivs, evs;
    public String nature;
    public BaseMove[] moves;
    public int level, totalExp;
    public int attackStat, defenseStat, spAttackStat, spDefenseStat, hpStat, speedStat;
    public Ability ability;
    public BufferedImage frontSpriteSheet;
    public BufferedImage backSpriteSheet;
    public BufferedImage icon;
    public String gender;
    public int friendship;
    public String[] type;
    public long personalityValue;

    public PokemonInstance(int ID, long personalityValue) throws IOException {
        PokemonData data = PokemonDataLoader.loadPokemonData(ID);
        this.personalityValue = personalityValue;

        this.id = data.id;
        this.species = data.name;

        this.ivs = new int[6];
        this.evs = new int[6];
        this.ivs = getIVsFromPersonality(personalityValue);

        this.gender = getGender(personalityValue, getGenderThreshold(data.genderRatio));

        this.type = data.type;

        for (int i = 0; i < 6; i++) {
            this.evs[i] = 0;
        }


        String[] natures = {"Hardy", "Lonely", "Brave", "Adamant", "Naughty",
                "Bold", "Docile", "Relaxed", "Impish", "Lax",
                "Timid", "Hasty", "Serious", "Jolly", "Naive",
                "Modest", "Mild", "Quiet", "Bashful", "Rash",
                "Calm", "Gentle", "Sassy", "Careful", "Quirky"};
        int natureIndex = (int)(personalityValue % 25);
        this.nature = natures[natureIndex];

        this.level = 5;
        this.hpStat = calculateStat((data.baseStats.get("hp")), this.ivs[0], this.evs[0], this.level, true);
        this.attackStat = calculateStat(data.baseStats.get("attack"), this.ivs[1], this.evs[1], this.level, false);
        this.defenseStat = calculateStat(data.baseStats.get("defense"), this.ivs[2], this.evs[2], this.level, false);
        this.spAttackStat = calculateStat(data.baseStats.get("specialAttack"), this.ivs[3], this.evs[3], this.level, false);
        this.spDefenseStat = calculateStat(data.baseStats.get("specialDefense"), this.ivs[4], this.evs[4], this.level, false);
        this.speedStat = calculateStat(data.baseStats.get("speed"), this.ivs[5], this.evs[5], this.level, false);

        this.ability = AbilityFactory.getAbility(data.abilities[0]);

        this.friendship = data.baseFriendship;

        this.moves = new BaseMove[4];


        this.frontSpriteSheet = null;
        this.backSpriteSheet = null;
        this.icon = null;

        this.totalExp = 0;
    }

    private int calculateStat(Double base, int iv, int ev, int level, boolean isHP) {
        if (isHP) {
            return (int) ((((2 * base + iv + (ev / 4)) * level) / 100) + level + 10);
        } else {
            return (int) ((((2 * base + iv + (ev / 4)) * level) / 100) + 5);
        }
    }

    private int[] getIVsFromPersonality(long p) {
        int[] ivs = new int[6];
        ivs[0] = (int) ((p)  & 0x1F);
        ivs[1] = (int) ((p >> 5)  & 0x1F);
        ivs[2] = (int) ((p >> 10) & 0x1F);
        ivs[3] = (int) ((p >> 15) & 0x1F);
        ivs[4] = (int) ((p >> 20) & 0x1F);
        ivs[5] = (int) ((p >> 25) & 0x1F);
        return ivs;
    }

    public static String getGender(long personalityValue, int genderThreshold) {
        if (genderThreshold == -1) return "Genderless";
        int val = (int)(personalityValue & 0xFF);
        return val < genderThreshold ? "Female" : "Male";
    }

    public static int getGenderThreshold(Map<String, Double> genderRatio) {
        if (!genderRatio.containsKey("female") || genderRatio.get("female") == 0.0)
            return 0;
        if (!genderRatio.containsKey("male") || genderRatio.get("male") == 0.0)
            return 256;
        return (int)Math.round(genderRatio.get("female") / 100 * 256);
    }


}
