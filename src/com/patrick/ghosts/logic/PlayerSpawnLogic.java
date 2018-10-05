package com.patrick.ghosts.logic;

import com.patrick.ghosts.entities.dummies.Dummy;
import com.patrick.ghosts.entities.dummies.Player;
import com.patrick.ghosts.util.Settings;

public class PlayerSpawnLogic {
	
	public PlayerSpawnLogic() {
		
	}
	
	public Dummy spawnNewPlayer(int difficulty) {
		return new Player(Settings.WINDOW_WIDTH/2, Settings.WINDOW_HEIGHT/2, Settings.TILE_SIZE_DRAW, Settings.TILE_SIZE_DRAW, true);
	}
}
