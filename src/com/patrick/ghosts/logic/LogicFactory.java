package com.patrick.ghosts.logic;

public class LogicFactory {
	
	public static CameraMoveLogic newCameraMoveLogic() {
		return new CameraMoveLogic();
	}
	
	public static GhostMoveLogic newGhostMoveLogic() {
		return new GhostMoveLogic();
	}
	
	public static MapLogic newMapLogic() {
		return new MapLogic();
	}
	
	public static MapLogic newProceduralMapLogic() {
		return new ProceduralMapLogic();
	}
	
	public static GhostSpawnLogic newGhostSpawnLogic() {
		return new GhostSpawnLogic();
	}
	
	public static PlayerSpawnLogic newPlayerSpawnLogic() {
		return new PlayerSpawnLogic();
	}
	
	public static RightFirstPathLogic newPathLogic() {
		return new RightFirstPathLogic();
	}
	
	public static ChunkLogic newChunkLogic() {
		return new ChunkLogic();
	}
	
	public static CollisionLogic newCollisionLogic() {
		return new CollisionLogic();
	}
}