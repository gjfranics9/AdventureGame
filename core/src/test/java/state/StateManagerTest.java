package state;

import game.main.Game;
import game.state.BattleState;
import game.state.GameStateManager;
import game.state.OverworldState;
import main.StubGamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StateManagerTest {

    GameStateManager stateManager;
    Game gp;

    @BeforeEach
    void setup(){

        this.gp = new StubGamePanel();
        this.stateManager = new GameStateManager(gp);
    }

    @Test
    void testSwitchToOverworldState(){
        stateManager.setState("Overworld");
        assertEquals(OverworldState.class, stateManager.getCurrentState().getClass());
    }

    @Test
    void testSwitchToBattleState(){
        stateManager.setState("Battle");
        assertEquals(BattleState.class, stateManager.getCurrentState().getClass());
    }
}
