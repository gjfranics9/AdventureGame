package pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PokemonInstanceTest {

    public PokemonInstance chimchar;
    @BeforeEach
    void setup() throws IOException {
        int chimcharId = 390;
        long personalityValue = 0x12345678L;

        chimchar = new PokemonInstance(chimcharId, personalityValue);
    }

    @Test
    void testChimcharDeterministicCreation() {

        assertEquals("Chimchar", chimchar.species);
        assertEquals(390, chimchar.id);
        assertEquals("Fire", chimchar.type[0]);


    }

    @Test
    void testChimcharDeterministicIVs() {
        int[] expectedIvs = {
                (int) ((chimchar.personalityValue) & 0x1F),
                (int) ((chimchar.personalityValue >> 5) & 0x1F),
                (int) ((chimchar.personalityValue >> 10) & 0x1F),
                (int) ((chimchar.personalityValue >> 15) & 0x1F),
                (int) ((chimchar.personalityValue >> 20) & 0x1F),
                (int) ((chimchar.personalityValue >> 25) & 0x1F)
        };
        assertArrayEquals(expectedIvs, chimchar.ivs);
    }

    @Test
    void testChimcharNature() {
        String[] natures = {"Hardy","Lonely","Brave","Adamant","Naughty","Bold","Docile","Relaxed","Impish","Lax","Timid","Hasty","Serious","Jolly","Naive","Modest","Mild","Quiet","Bashful","Rash","Calm","Gentle","Sassy","Careful","Quirky"};
        String expectedNature = natures[(int)(chimchar.personalityValue % 25)];
        assertEquals(expectedNature, chimchar.nature);
    }
}