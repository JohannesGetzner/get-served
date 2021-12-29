package entities;

import java.util.Random;

public enum Foodtype {
    BEER,
    PIZZA,
    CHICKEN,
    POMMES,
	FISH;

    public static Foodtype getRandomFoodType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
