package object;

import main.GamePanel;
import state.OverworldState;

import java.awt.*;

public class ObjectHandler {

    public OverworldState overworldState;
    public SuperObject[] obj;
    public ObjectHandler(OverworldState overworldState){

        this.obj = new SuperObject[10];
        this.overworldState = overworldState;
    }

    public void draw(Graphics2D g2){
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2, overworldState);
            }
        }
    }
}
