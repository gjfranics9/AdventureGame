package main;

import entity.Entity;

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
