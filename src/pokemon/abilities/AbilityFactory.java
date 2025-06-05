package pokemon.abilities;

public class AbilityFactory {
    public static Ability getAbility(String abilityName) {
        return switch (abilityName.toLowerCase()) {
            case "blaze" -> new Blaze();
            case "overgrow" -> new Overgrow();
            // Add other abilities here
            default -> null; // or a default ability
        };
    }
}
