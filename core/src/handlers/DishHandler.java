package handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Queue;
import entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.mygdx.game.MyGdxGame.PIXELS_TO_METERS;

public class DishHandler {
    /**
     * dishQueue: contains the Dishes that will spawn next on the field
     * activeDishes: this list contains the dishes that are currently on the field at any given time
     */
    private Queue<Foodtype> dishQueue;
    private ArrayList<Dish> activeDishes;
    private List<Counter> counters;
    private Waiter waiter;
    private ArrayList<Foodtype> permittedDishes;


    public DishHandler() {
        dishQueue = new Queue<Foodtype>();
        activeDishes = new ArrayList<Dish>();
    }

    /**
     * this method initializes the DishHandler
     *
     * @param waiter   the waiter who carries the dishes
     * @param counters the counters where the dishes spawn on
     */
    public void initializeDishManager(Waiter waiter, List<Counter> counters, ArrayList<Foodtype> permittedDishes) {
        this.waiter = waiter;
        this.counters = counters;
        this.permittedDishes = permittedDishes;
    }
	/**
	 * this method initializes the DishHandler
	 *
	 * @param waiter   the waiter who carries the dishes
	 * @param counters the counters where the dishes spawn on
	 */
	public void initializeDishManager(Waiter waiter, List<Counter> counters) {
		this.waiter = waiter;
		this.counters = counters;
	}

    //TODO: REFACTOR THIS METHOD
    /**
     * this method updates the dishes location and decides on which dishes need to be spawned next
     *
     * @param time         the current time since the level started
     * @param activeGuests the guests which are currently on the field
     */
    public void updateDishes(int time, ArrayList<Guest> activeGuests) {
        if (waiter.getDish() != null) {
            waiter.getDish().getSprite().setPosition(
                    (waiter.getBody().getPosition().x * PIXELS_TO_METERS) - waiter.getSprite().getWidth() / 2,
                    (waiter.getBody().getPosition().y * PIXELS_TO_METERS) - waiter.getSprite().getHeight() / 2);
        }
        for (Counter c : counters) {
            if (c.getNextDish() == null) {
                Dish initDish;
                if (dishQueue.notEmpty()) {
                    initDish = new Dish(dishQueue.removeFirst());
                } else {
                    initDish = new Dish(Foodtype.getRandomFoodType());
                }
                c.setNextDish(initDish);
                c.setLastDishTime(time);
                activeDishes.add(initDish);
                initDish.setPosition(c.getCookingPosition());
            }

			if (c.getDish() == null)
				c.setCookSpeed(2);

			if ((time - c.getLastDishTime()) >= (float) (5 / c.getCookSpeed()) + 1) {
				Dish nextDish = null;
				if (dishQueue.isEmpty()) {
					for (Guest g : activeGuests) {
						if (!g.getType().equals("skeleton")) {
							boolean dishAvailable = false;
							for (Dish d : activeDishes) {
								if (d.type == g.getOrder()) {
									dishAvailable = true;
									break;
								}
							}
							if (!dishAvailable) {
								nextDish = new Dish(g.getOrder());
								break;
							}
						}
					}
					if (nextDish == null)
						nextDish = new Dish(Foodtype.getRandomFoodType());
				} else {
					nextDish = new Dish(dishQueue.removeFirst());
				}
				c.setLastDishTime(time);
				if (c.getDish() != null) {
					activeDishes.remove(c.getDish());
				}
				activeDishes.add(nextDish);
				nextDish.setPosition(c.getCookingPosition());
				c.getNextDish().setPosition(c.getDishCounterPos());
				c.setDish(c.getNextDish());
				c.setNextDish(nextDish);
				c.setCookSpeed(1);
			}
		}

	}

	/**
	 * this method adds a new dish to the queue
	 *
	 * @param type the type of dish to be added next
	 */
	public void addToDishQueue(Foodtype type) {
		dishQueue.addLast(type);
	}

	/**
	 * this method allows the waiter to drop dishes
	 *
	 * @param waiter the waiter object
	 */
	public void trashBinHandler(Waiter waiter) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			if (waiter.getDish() != null) {
				activeDishes.remove(waiter.getDish());
				Gdx.app.log("INFO: ", "Dish has been removed by Button Press");
			}
			waiter.removeDish();
			Gdx.app.log("INFO: ", "Waiter no longer carries a dish");
		}
	}

	/**
	 * this method removes a Dish from the active dishes on the field when served to
	 * the guest
	 *
	 * @param dish the dish to be removed
	 */
	public void removeActiveDish(Dish dish) {
		Gdx.app.log("INFO: ", "Dish " + dish + " has been served and removed");
		activeDishes.remove(dish);
	}

	/**
	 * this method initializes the queue for the dishes and ensures that for the
	 * first guest there will always be the correct dish available and spawns
	 * another random dish on other counters
	 *
	 * @param order the order of the first guest to be spawned
	 */
	public void initializeDishQueue(Foodtype order) {
		Foodtype random = Foodtype.getRandomFoodType();
		int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
		if (randomNum == 1) {
			addToDishQueue(order);
			addToDishQueue(random);
		} else {
			addToDishQueue(random);
			addToDishQueue(order);
		}
	}

	/*
	 * ----------------- Getter and Setter
	 */
	public ArrayList<Dish> getActiveDishes() {
		return activeDishes;
	}
}
