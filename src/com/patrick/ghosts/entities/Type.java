package com.patrick.ghosts.entities;

/**
 * Type safe references of Entities.
 * 
 * @author lenovo
 *
 */
public class Type {
	
	public enum EntityType { DUMMY, ENVIRONMENT, PROP };
	
	public enum DummyType { PLAYER, GHOST };
	
	public enum EnvironmentType { TILE, TRAP_DOOR, STAIRS, CHEST, LIGHT, CHUNK };
	
	public enum PropType { WHIP, SWORD, HAMMER, THROWING_KNIFE, KEY };

}
