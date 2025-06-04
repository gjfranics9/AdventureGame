package main;

import object.*;
import state.OverworldState;

import java.util.*;

public class AssetSetter {

    OverworldState overworldState;

    public AssetSetter(OverworldState overworldState) {
        this.overworldState = overworldState;
    }


    // Factory map to create objects by name
    private final Map<String, GameObjectFactory> objectFactoryMap = new HashMap<>() {{
        put("pokeball", ObjBall::new);
        // Add more as needed
    }};

    public void setObjectsFromMap(Map<String, List<int[]>> itemMap) {
        int index = 0;

        for (Map.Entry<String, List<int[]>> entry : itemMap.entrySet()) {
            String key = entry.getKey().toLowerCase();
            GameObjectFactory factory = objectFactoryMap.get(key);

            if (factory == null) {
                System.out.println("Unknown object type: " + key);
                continue;
            }

            for (int[] coords : entry.getValue()) {
                if (index >= overworldState.objectHandler.obj.length) {
                    System.out.println("gp.obj array full");
                    return;
                }

                SuperObject obj = factory.create();
                obj.worldX = coords[0] * overworldState.gp.tileSize;
                obj.worldY = coords[1] * overworldState.gp.tileSize;
                overworldState.objectHandler.obj[index++] = obj;
            }
        }
    }
}
