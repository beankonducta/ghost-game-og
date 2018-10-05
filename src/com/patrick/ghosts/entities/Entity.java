package com.patrick.ghosts.entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.patrick.ghosts.entities.Type.EntityType;
import com.patrick.ghosts.scenes.effects.Effect;
import com.patrick.ghosts.util.Settings;
import com.patrick.ghosts.util.SpriteTools;

public abstract class Entity {

	private float x;

	private float y;

	private int width;

	private int height;

	private Rectangle bounds;

	private String type;

	private EntityType entityType;

	private boolean visible;

	private boolean animatable;

	private boolean animate;

	private boolean collidable;

	private boolean collided;

	private boolean destroyable;

	private boolean destroyed;

	private boolean interactable;

	private boolean interacting;

	private boolean flagged;

	private int currentAnimationFrame;

	private int loadCount;

	private float moveSpeed;

	private float imageOpacity;

	private BufferedImage[] sprites;
	
	private Entity[] entities;

	private List<Effect> effects;

	// need to add an accessible shape for the collision

	/**
	 * Default constructor..
	 * 
	 */
	public Entity() {
		attemptFetchSprites();
		effects = new ArrayList<Effect>();
		imageOpacity = 1.0f;
	}

	/**
	 * Constructor with coordinate parameters.
	 * 
	 * @param x
	 * @param y
	 */
	public Entity(float x, float y) {
		setX(x);
		setY(y);
		attemptFetchSprites();
		effects = new ArrayList<Effect>();
		imageOpacity = 1.0f;
	}

	/**
	 * Constructor with coordinate parameters & visibility.
	 * 
	 * @param x
	 * @param y
	 * @param visible
	 */
	public Entity(float x, float y, boolean visible) {
		setX(x);
		setY(y);
		setVisible(visible);
		attemptFetchSprites();
		effects = new ArrayList<Effect>();
		imageOpacity = 1.0f;
	}

	/**
	 * Constructor which forces a spritesheet to be loaded.
	 * 
	 * @param x
	 * @param y
	 * @param visible
	 * @param sprites
	 */
	public Entity(float x, float y, boolean visible, File sprites) {
		setX(x);
		setY(y);
		setVisible(visible);
		forceFetchSprites(sprites);
		effects = new ArrayList<Effect>();
		imageOpacity = 1.0f;
	}

	/**
	 * Draw the Entity. Should draw with sub pixel precision
	 * since we're using float values & AffineTransform.
	 * 
	 * @param g2d
	 */
	public void draw(Graphics2D g2d) {
		if(visible) {
			if(!isDestroyed()) {
				if(sprites != null) {
					if(sprites.length > 0) {
						AffineTransform trans = new AffineTransform();
						trans.translate(getX(), getY());
						trans.scale(Settings.IMAGE_SCALE, Settings.IMAGE_SCALE);
						AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getImageOpacity());
						g2d.setComposite(ac);
						g2d.drawImage(sprites[currentAnimationFrame], trans, null);
					}
				}
			}
		}
		if(Settings.DEBUG) {
			g2d.setColor(Color.GREEN);
			g2d.drawRect(this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height);
		}
		if(isCollide()) {
			g2d.setColor(new Color(0, 0, 0, 0.25f));
			g2d.fillRect(this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height);
		}
	}

	/**
	 * General update method.
	 * 
	 */
	public void update() {
		loopAnimation();
		if(sprites == null || sprites.length < 1) {
			if(loadCount < 3) {
				attemptFetchSprites();
				loadCount++;
			}
		}
	}

	/**
	 * If the animation is running and sprites exist, loop
	 * the animation.
	 * 
	 */
	protected void loopAnimation() {
		if(sprites != null) {
			if(sprites.length > 0) {
				if(animatable) {
					if(animate) {
						if(currentAnimationFrame < sprites.length-1) {
							currentAnimationFrame ++;
						} else if(currentAnimationFrame == sprites.length-1) {
							currentAnimationFrame = 0;
						}
					}
				}
			}
		}
	}

	/**
	 * Attempts to grab sprites from a specified folder
	 * using pre-defined naming convention.
	 * 
	 * @return
	 */
	public boolean attemptFetchSprites() {
		boolean rtrn = true;
		if(entityType != null) {
			try {
				File sprite = new File("lib/sprites/"+getEntityType().name().toLowerCase()+"/"+getType()+"_sheet.png");
				sprites = SpriteTools.getSpritesFromSheet(sprite, Settings.TILE_SIZE, Settings.TILE_SIZE);
			} catch(Exception e) {
				rtrn = false;
			}
		}
		return rtrn;
	}

	/**
	 * Forces use of a sprite set outside the pre-defined
	 * naming convention.
	 * 
	 * @param file
	 * @return
	 */
	public boolean forceFetchSprites(File sprite) {
		sprites = SpriteTools.getSpritesFromSheet(sprite, Settings.TILE_SIZE, Settings.TILE_SIZE);
		return false;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getCenterX() {
		return getX() + (getWidth()/2);
	}

	public float getCenterY() {
		return getY() + (getHeight()/2);
	}

	public int getTileXLocation() {
		return (int) (getX()/Settings.TILE_SIZE_DRAW);
	}

	public int getTileYLocation() {
		return (int) (getY()/Settings.TILE_SIZE_DRAW);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public boolean isAnimatable() {
		return animatable;
	}

	public void setAnimatable(boolean animatable) {
		this.animatable = animatable;
	}

	public boolean isAnimate() {
		if(isAnimatable())
			return animate;
		else
			return false;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	public boolean isCollide() {
		if(isCollidable())
			return collided;
		else
			return false;
	}

	public void setCollide(boolean collided) {
		this.collided = collided;
	}

	public boolean isDestroyable() {
		return destroyable;
	}

	public void setDestroyable(boolean destroyable) {
		this.destroyable = destroyable;
	}

	public boolean isDestroyed() {
		if(isDestroyable())
			return destroyed;
		else
			return false;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean isInteractable() {
		return interactable;
	}

	public void setInteractable(boolean interactable) {
		this.interactable = interactable;
	}

	public boolean isInteracting() {
		if(isInteractable())
			return interacting;
		else
			return false;
	}

	public void setInteracting(boolean interacting) {
		this.interacting = interacting;
	}

	public int getCurrentAnimationFrame() {
		return currentAnimationFrame;
	}

	@Override
	public String toString() {
		return "Entity [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", type=" + type
				+ ", entityType=" + entityType + ", currentAnimationFrame=" + currentAnimationFrame + ", imageOpacity="
				+ imageOpacity + ", sprites=" + Arrays.toString(sprites) + "]";
	}

	public void setCurrentAnimationFrame(int currentAnimationFrame) {
		this.currentAnimationFrame = currentAnimationFrame;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type.toLowerCase();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	public EntityType getEntityType() {
		return entityType;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public float getImageOpacity() {
		if(imageOpacity > 1.0)
			setImageOpacity(1.0f);
		return imageOpacity;
	}

	public void setImageOpacity(float imageOpacity) {
		this.imageOpacity = imageOpacity;
	}

	public Effect[] getEffects() {
		return effects.toArray(new Effect[effects.size()]);
	}

	public void addEffect(Effect effect) {
		effects.add(effect);
	}

	public void removeEffect(Effect effect) {
		if(effects.contains(effect)) {
			effects.remove(effect);
		}
	}

	public Rectangle getBounds() {
		if(this.bounds == null) {
			this.bounds = new Rectangle((int)this.getX(), (int)this.getY(), this.getWidth(), this.getHeight());
			return bounds;
		}
		this.bounds.setBounds((int)this.getX(), (int)this.getY(), this.getWidth(), this.getHeight());
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Entity[] getEntities() {
		return entities;
	}

	public void setEntities(Entity[] entities) {
		this.entities = entities;
	}
}