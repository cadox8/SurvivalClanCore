package es.projectalpha.scc.utils;

import java.util.Random;

import es.projectalpha.scc.utils.ParticleEffect.OrdinaryColor;

public class Randoms {

	private static Random ran = new Random();

	public static OrdinaryColor randomOrdinaryColor(){

		int r = ran.nextInt(256);
		int g = ran.nextInt(256);
		int b = ran.nextInt(256);

		return new OrdinaryColor(r, g, b);
	}

	public static short randomColor(){
		short c = (short) ran.nextInt(15);

		return c;
	}

	public static int randomNumber(){
		int i = ran.nextInt(6);

		return i;
	}
}
