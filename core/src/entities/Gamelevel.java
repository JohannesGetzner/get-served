package entities;

import java.util.ArrayList;
import java.util.List;

public class Gamelevel {
    private List<Gridposition> gridpositionList;
    private List<Guest> guests;
    private String name;
    private long money;
    private ArrayList<Foodtype> permittedDishes;

    /**
     * this method get called every second and decrements the money of the game
     */
    public void decrementMoney() {
        if (money >= 1) {
            money = money - 2;
            if(money < 0){
                money = 0;
            }
        } else if (money < 0) {
            money = 0;
        }
    }

    /*
     * -----------------
     * Getter and Setter
     */

    public List<Gridposition> getGridpositionList() {
        return gridpositionList;
    }

    public void setGridpositionList(List<Gridposition> gridpositionList) {
        this.gridpositionList = gridpositionList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public long getMoney() {
		return money;
	}

    public void setMoney(long money) {
		this.money = money;
	}

    public ArrayList<Foodtype> getPermittedDishes() {
        return permittedDishes;
    }

    public void setPermittedDishes(ArrayList<Foodtype> permittedDishes) {
        this.permittedDishes = permittedDishes;
    }
}

