package game.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.state.OverworldState;

public class SuperObject {

    public Texture texture;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void render(SpriteBatch batch, OverworldState overworldState) {

        if(worldX+overworldState.gp.tileSize>overworldState.player.worldX&&
                worldX-overworldState.gp.tileSize<overworldState.player.worldX &&
                worldY+overworldState.gp.tileSize>overworldState.player.worldY &&
                worldY-overworldState.gp.tileSize<overworldState.player.worldY){
            batch.draw(texture, worldX, worldY, overworldState.gp.tileSize, overworldState.gp.tileSize);
        }
    }
}
