package com.patrick.ghosts.util;

import java.util.Random;

public class MathTools {
	
	public static int randomInt(int max) {
		Random random = new Random();
		return random.nextInt(max);
	}
	
	public static int randomInt(int min, int max) {
		Random random = new Random();
		int rtrn = 0;
		if(min < 1) {
			min = 1;
		}
		while(rtrn < min) {
			rtrn = random.nextInt(max);
		}
		return rtrn;
	}
	
	public static float calculateLightLevel(float entityX, float entityY, float lightX, float lightY, float lightRadius) {
		float distX = Math.abs(entityX - lightX);
		float distY = Math.abs(entityY - lightY);
		float calcX = (float) ((lightRadius - distX)*Math.PI);
		float calcY = (float) ((lightRadius - distY)*Math.PI);
		float calc = (float) ((float) Math.sin(calcX + calcY));
		float rtrn = 1.0f + (calc);
		if(rtrn <= 0 )
			rtrn = 0;
		if(rtrn >= 1)
			rtrn = 1;
		System.out.println(rtrn/3);
		return rtrn;
	}
}