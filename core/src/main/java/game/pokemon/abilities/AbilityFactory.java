package game.pokemon.abilities;

public class AbilityFactory {

    public static Ability getAbility(String abilityName) {

        if (abilityName == null) {
            return null;
        }

        switch (abilityName.toLowerCase()) {

            case "blaze":
                return new Blaze();

            case "overgrow":
                return new Overgrow();

            default:
                return null;
        }
    }
}
