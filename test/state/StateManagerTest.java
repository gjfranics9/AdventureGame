package state;

import main.GamePanel;
import main.StubGamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StateManagerTest {

    GameStateManager stateManager;
    GamePanel gp;

    @BeforeEach
    void setup(){

        this.gp = new StubGamePanel();
        this.stateManager = new GameStateManager(gp);
    }

    @Test
    void testSwitchToOverworldState(){

        GameState overworld = stateManager.overworldState;
        stateManager.setState("Overworld");
        assertEquals(overworld, stateManager.getCurrentState());
    }

    @Test
    void testSwitchToBattleState(){
        GameState battle = stateManager.battleState;
        stateManager.setState("Battle");
        assertEquals(battle, stateManager.getCurrentState());
    }
}
