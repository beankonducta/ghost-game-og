package com.patrick.ghosts.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteTools {

	private static int frameCount;

	/**
	 * Splits up a single BufferedImage into individual BufferedImages based on an array of tiles
	 * assigned by the tileWidth and tileHeight.
	 * 
	 * @param file
	 * @param tileWidth
	 * @param tileHeight
	 * @return
	 */
	public static BufferedImage[] getSpritesFromSheet(File file, int tileWidth, int tileHeight) {
		BufferedImage[] sprites = null;
		try {
			BufferedImage sheet = ImageIO.read(file);
			sprites = new BufferedImage[(sheet.getWidth()/tileWidth)+(sheet.getHeight()/tileHeight)];
			for(int i = 0; i < sheet.getHeight()/tileHeight; i++) {
				for(int j = 0; j < sheet.getWidth()/tileWidth; j++) {
					sprites[frameCount] = BufferedImageTools.toCompatibleImage(sheet.getSubimage(j*tileWidth, i*tileHeight, tileWidth, tileHeight));
					frameCount ++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		frameCount = 0;
		return sprites;
	}

	public static BufferedImage castLight(BufferedImage img, float entityX, float entityY, float lightX, float lightY, float lightRadius) {
		for(int i = 0; i < img.getWidth(); i++) {
			for(int j = 0; j < img.getHeight(); j++) {
				int argb = img.getRGB(i, j); //always returns TYPE_INT_ARGB
				int alpha = (argb >> 24) & 0xff;  //isolate alpha
				alpha *= MathTools.calculateLightLevel(entityX, entityY, lightX, lightY, lightRadius);
				alpha &= 0xff;      //keeps alpha in 0-255 range
				argb &= 0x00ffffff; //remove old alpha info
				argb |= (alpha << 24);  //add new alpha info
				img.setRGB(i, j, argb);
			}
		}
		return img;
	}

	public static BufferedImage map(BufferedImage[] sprites) {
		return null;
	}
}