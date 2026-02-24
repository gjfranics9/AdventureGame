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

        int screenX = worldX - overworldState.player.worldX + overworldState.player.screenX;
        int screenY = worldY - overworldState.player.worldY + overworldState.player.screenY;

        if(worldX+overworldState.gp.tileSize>overworldState.player.worldX-overworldState.player.screenX &&
                worldX-overworldState.gp.tileSize<overworldState.player.worldX+overworldState.player.screenX &&
                worldY+overworldState.gp.tileSize>overworldState.player.worldY-overworldState.player.screenY &&
                worldY-overworldState.gp.tileSize<overworldState.player.worldY+overworldState.player.screenY){
            batch.draw(texture, screenX, screenY, overworldState.gp.tileSize, overworldState.gp.tileSize);
        }
    }
}
