package handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;
import entities.GameField;
import entities.Guest;
import entities.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuestHandler {
	/**
	 * guestQueue: this queue contains all the guests that will be spawned during the game in the correct order
	 * activeGuests: this list contains all the guests at any time that are currently on the field
	 * guestsToRemove: this list contains all the guests at any time that need to be removed from the field
	 */
	private Queue<Guest> guestQueue;
	private ArrayList<Guest> activeGuests;
	private ArrayList<Guest> guestsToRemove;
	private GameField gameField;
	private boolean deadToSkeleton = false;


	GuestHandler() {
		guestQueue = new Queue<>();
		activeGuests = new ArrayList<>();
		guestsToRemove = new ArrayList<>();
	}

	/**
	 * This method will directly manages the guests during the game
	 *
	 * @param time        the current time since the level started
	 * @param dishHandler the object that manages the dishes; used to create the dishes when guests spawn
	 */
	void manageGuests(int time, DishHandler dishHandler) {
		if (!guestQueue.isEmpty()) {
			if (time >= guestQueue.first().getSpawnTime()) {
				if(time > guestQueue.first().getSpawnTime()){
					guestQueue.first().setSpawnTime(time);
				}
				spawnGuest(guestQueue.first(), dishHandler);
			}
		}
		for (Guest g : activeGuests) {
			updateGuest(g, time);
		}
		if (!guestsToRemove.isEmpty()) {
			for (Guest g : guestsToRemove) {
				removeActiveGuest(g, time, dishHandler);
			}
			guestsToRemove.clear();
		}
	}

	/**
	 * this method updates a guests state
	 *
	 * @param guest the guest to be updated
	 * @param time  the current time since the level started
	 */
	private void updateGuest(Guest guest, int time) {
		long timeElapsed = time - guest.getSpawnTime();
		guest.calculatePatience(timeElapsed);
		if (guest.getDespawnTime() != 0) {
			guestsToRemove.add(guest);
		}
		if (guest.getPatience() == 0) {
			if((guest.getType().equals("skeleton")) && (!guest.isServed())) {
				deadToSkeleton = true;
			}
			guestsToRemove.add(guest);
		}else if (guest.getPatience() <= 0.4 * guest.getMaxPatience() && guest.getHappiness() == 2 && !guest.isServed()) {
			guest.setHappiness(1);
			guest.setOrderTime(time);
			if(!(guest.getLastAnimationTime() + 1 >= time))
				guest.setActiveAnimation("ordering", time);
		}else if (guest.getPatience() <= 0.75 * guest.getMaxPatience() && guest.getHappiness() == 3  && !guest.isServed()) {
			guest.setHappiness(2);
			guest.setOrderTime(time);
			if(!(guest.getLastAnimationTime() + 1 >= time))
				guest.setActiveAnimation("ordering", time);
		}
		if(guest.getCurrentAnimation().equals("angry") || guest.getCurrentAnimation().equals("ordering")){
			if(guest.getLastAnimationTime() + 1 <= time){
				guest.setActiveAnimation("idle",time);
			}
		}
	}

	/**
	 * this method spawns the guest on the on the field
	 *
	 * @param guest       the guest to be spawned; all guest objects are already created prior to spawning them
	 * @param dishHandler the dishHandler to add the order of the new guest to the queue
	 */
	private void spawnGuest(Guest guest, DishHandler dishHandler) {
		if (gameField.getFreeTables().size() != 0) {
			Random rndm = new Random();
			int tableID = rndm.nextInt(gameField.getFreeTables().size());
			Table table = gameField.getFreeTables().get(tableID);
			gameField.removeFreeTable(table);
			guest.setPosition(table.getChairPosition()[0] - 0.5f, table.getChairPosition()[1] + 1);
			guest.setTable(table);
			table.setGuest(guest);
			if(!guest.getType().equals("skeleton")) {
				dishHandler.addToDishQueue(guest.getOrder());
				Gdx.app.log("INFO: ", "Added " + guest.getOrder() + " to dishqueue");
			}
			activeGuests.add(guest);
			Gdx.app.log("INFO: ", "Spawned " + guest);
			guestQueue.removeFirst();
			System.out.println(guestQueue);
			Gdx.app.log("INFO: ", "Removed Guest from guestList ");
			guest.playSpawnsound();
		}

	}

	/**
	 * this method initializes the guests for the game by putting them into the queue
	 *
	 * @param guests the list of guests
	 */
	void initializeGuests(List<Guest> guests) {
		for (Guest g : guests) {
			this.guestQueue.addLast(g);
		}
	}

	/**
	 * this method removes an active guest from the field
	 *
	 * @param guest the guest to be removed
	 */
	public void removeActiveGuest(Guest guest, int time, DishHandler dishHandler) {
		if (time >= guest.getDespawnTime()) {
			if(!gameField.getFreeTables().contains(guest.getTable()))
				gameField.addFreeTable(guest.getTable());
			dishHandler.removeActiveDish(guest.getDish());
			activeGuests.remove(guest);
			guest.getTable().removeGuest();
			Gdx.app.log("INFO: ", "Guest " + guest + " removed.");
		}
	}

	/*
	 * -------------------
	 * Getters and Setters
	 */

	public void setGameField(GameField gameField) {
		this.gameField = gameField;
	}

	public ArrayList<Guest> getActiveGuests() {
		return activeGuests;
	}

	public Queue<Guest> getGuestQueue() {
		return guestQueue;
	}
	
	public boolean IsDeadToSkeleton() {
		return deadToSkeleton;
	}


}
