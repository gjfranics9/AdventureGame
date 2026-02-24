package game.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.state.OverworldState;

public class ObjectHandler {

    public OverworldState overworldState;
    public SuperObject[] obj;
    public ObjectHandler(OverworldState overworldState){

        this.obj = new SuperObject[10];
        this.overworldState = overworldState;
    }

    public void render(SpriteBatch batch){
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.render(batch, overworldState);
            }
        }
    }
}
