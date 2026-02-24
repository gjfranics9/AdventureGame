package main;

import game.entity.Entity;
import game.main.CollisionChecker;

public class StubCollisionChecker extends CollisionChecker {
    public boolean simulateCollision = false;

    public StubCollisionChecker() {
        super(null);
    }

    @Override
    public void checkTile(Entity entity) {
        entity.collisionOn = simulateCollision;
    }
}
