package entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import handlers.Assets;

import java.util.Arrays;

import static com.mygdx.game.MyGdxGame.WORLD_HEIGHT;
import static com.mygdx.game.MyGdxGame.WORLD_WIDTH;

public class Counter {
	private Sprite sprite;
	private int rotation;
	private float[] positions = new float[2];
    private float[] cookingPosition = new float[2];
    private float[] dishCounterPos;
	private Dish dish;
	private Dish nextDish;
	private float lastDishTime;
	private int cookSpeed;
	private Sound bell;

	public Counter(World world, float positionX, float positionY, int rotation) {
		this.positions[0] = positionX;
		this.positions[1] = positionY;
		this.rotation = rotation;
        if (rotation != 3 && rotation != 2) {
            sprite = new Sprite(Assets.manager.get(Assets.COUNTER_TURNED, Texture.class));
        } else {
            sprite = new Sprite(Assets.manager.get(Assets.COUNTER, Texture.class));
        }
		if(rotation == 0 || rotation == 1) {
			sprite.setSize(7.5f, 11);
		}else {
			sprite.setSize(11, 7.5f);
		}
		sprite.setPosition(positionX, positionY);
        Box box = new Box(world, sprite, false, true);
		box.getBody().getFixtureList().first().setUserData(this);
		dish = new Dish(Foodtype.getRandomFoodType());
		float[] dpos = { positions[0] + (sprite.getWidth() - dish.getSprite().getWidth()) / 2,
                (float) (positions[1] + (sprite.getHeight() - dish.getSprite().getHeight()) / 2 + 1.5)};
        dishCounterPos = dpos;
		if (rotation == 0) {
			float[] displpos = { positions[0] - sprite.getWidth(),
					positions[1] + (sprite.getHeight() - dish.getSprite().getHeight()) / 2 };
            cookingPosition = displpos;
		} else if (rotation == 1) {
			float[] displpos = { positions[0] + sprite.getWidth() + 2,
					positions[1] + (sprite.getHeight() - dish.getSprite().getHeight()) / 2 };
            cookingPosition = displpos;
		} else if (rotation == 2) {
			float[] displpos = { positions[0] + sprite.getWidth() / 2 - dish.getSprite().getWidth() / 2,
					positions[1] + sprite.getHeight() + 3};
            cookingPosition = displpos;
		} else if (rotation == 3) {
			float[] displpos = { positions[0] + sprite.getWidth() / 2 - dish.getSprite().getWidth() / 2,
					positions[1] - dish.getSprite().getHeight() - 3};
            cookingPosition = displpos;
		}
		
		dish = null;
		lastDishTime = 0;
		cookSpeed = 1;
		bell = Assets.manager.get(Assets.BELL, Sound.class);
	}

	public void playBell() {
        long bellId = bell.play();
        bell.setPitch(bellId, 2f);
        bell.setVolume(bellId, 0.6f);
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public float[] getPosition() {
		return positions;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
		//playBell();
	}

	public float getLastDishTime() {
		return lastDishTime;
	}

	public void setLastDishTime(float lastDishTime) {
		this.lastDishTime = lastDishTime;
	}

	public void removeDish() {
		dish = null;
	}

	@Override
	public String toString() {
		return "Counter{" + ", positions=" + Arrays.toString(positions) + ", dish=" + dish + '}';
	}

	public Dish getNextDish() {
		return nextDish;
	}

	public void setNextDish(Dish nextDish) {
		this.nextDish = nextDish;
	}

	public int getCookSpeed() {
		return cookSpeed;
	}

	public void setCookSpeed(int cookSpeed) {
		this.cookSpeed = cookSpeed;
	}

    public float[] getDishCounterPos() {
        return dishCounterPos;
    }

    public float[] getCookingPosition() {
        return cookingPosition;
	}

	public int getRotation() {
		return rotation;
	}

	public Sound getBell() {
		return bell;
	}
}
