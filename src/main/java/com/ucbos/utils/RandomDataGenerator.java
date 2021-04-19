package com.ucbos.utils;

import java.util.Random;



public class RandomDataGenerator {
	
	
	public static int getRandomID() {
		Random rand = new Random();
		rand.ints(1000,99999);
		rand.nextInt();
		int randomNum = rand.nextInt();
		return randomNum;
	}
}
