package com.patrick.ghosts.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.patrick.ghosts.entities.Entity;

public class BufferedImageTools {

	static int count = 0;

	public static BufferedImage toCompatibleImage(BufferedImage image)
	{
		// obtain the current system graphical settings
		GraphicsConfiguration gfxConfig = GraphicsEnvironment.
				getLocalGraphicsEnvironment().getDefaultScreenDevice().
				getDefaultConfiguration();

		/*
		 * if image is already compatible and optimized for current system 
		 * settings, simply return it
		 */
		if (image.getColorModel().equals(gfxConfig.getColorModel()))
			return image;

		// image is not optimized, so create a new image that is
		BufferedImage newImage = gfxConfig.createCompatibleImage(
				image.getWidth(), image.getHeight(), image.getTransparency());

		// get the graphics context of the new image to draw the old image on
		Graphics2D g2d = newImage.createGraphics();

		// actually draw the image and dispose of context no longer needed
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		// return the new optimized image
		return newImage; 
	}

	public static BufferedImage buildChunkSprite(Entity[] entities, int width, int height, int opacity) {
		BufferedImage fullImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		// build the full image from our map tiles here,
		// then return it.
		Graphics2D g2d = (Graphics2D) fullImage.getGraphics();
		float baseX = entities[0].getX();
		float baseY = entities[0].getY();
		for(Entity e : entities) {
			if(e != null) {
				AffineTransform trans = new AffineTransform();
				trans.translate((e.getX()-baseX) / Settings.IMAGE_SCALE, (e.getY()-baseY) / Settings.IMAGE_SCALE);
				AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, e.getImageOpacity());
				g2d.setComposite(ac);
				g2d.drawImage(e.getSprites()[e.getCurrentAnimationFrame()], trans, null);		
			}
		}
		g2d.dispose();
		return fullImage;
	}
}
