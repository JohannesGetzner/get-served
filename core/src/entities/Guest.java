package entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import handlers.Assets;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Guest {
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> angryAnimation;
    private Animation<TextureRegion> orderAnimation;
    private Animation<TextureRegion> activeAnimation;
    private String currentAnimation;
    private int lastAnimationTime = 0;
    private int despawnTime = 0;
    private boolean isServed = false;
    private Table table;
    private String type;
    private Dish dish;
    private Sprite bubble;
    private long spawnTime;
    private long orderTime;
    private float patience;
    private float dynamicPatience;
    private float maxPatience;
    private int happiness;
    private long wealth;
    private Foodtype order;
    private float[] position = new float[2];
    private Sound spawnsound;
    private Sound refusesound;
    private Sound paysound;

    public Guest(long spawnTime, long patience, long wealth, String type) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 9 + 1);
        if (randomNum != 5 && randomNum != 8) {
            refusesound = Assets.manager.get(Assets.NOTFORME1, Sound.class);
            int randomVoice = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            int randomLine = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            switch (randomVoice) {
                case 1:
                    switch (randomLine) {
                        case 1:
                            spawnsound = Assets.manager.get(Assets.HI1, Sound.class);
                            refusesound = Assets.manager.get(Assets.NO1, Sound.class);
                            break;
                        case 2:
                            spawnsound = Assets.manager.get(Assets.YO1, Sound.class);
                            refusesound = Assets.manager.get(Assets.NOTFORME1, Sound.class);
                            break;
                        case 3:
                            spawnsound = Assets.manager.get(Assets.GREETINGS1, Sound.class);
                            refusesound = Assets.manager.get(Assets.NOTFORME1, Sound.class);
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    switch (randomLine) {
                        case 1:
                            spawnsound = Assets.manager.get(Assets.HELLO2, Sound.class);
                            refusesound = Assets.manager.get(Assets.NO2, Sound.class);
                            break;
                        case 2:
                            spawnsound = Assets.manager.get(Assets.HEY2, Sound.class);
                            refusesound = Assets.manager.get(Assets.NOTFORME2, Sound.class);
                            break;
                        case 3:
                            spawnsound = Assets.manager.get(Assets.WHATSUP2, Sound.class);
                            refusesound = Assets.manager.get(Assets.NOTFORME2, Sound.class);
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    switch (randomLine) {
                        case 1:
                            spawnsound = Assets.manager.get(Assets.HELLO3, Sound.class);
                            refusesound = Assets.manager.get(Assets.NO3, Sound.class);
                            break;
                        case 2:
                            spawnsound = Assets.manager.get(Assets.HOWDY3, Sound.class);
                            refusesound = Assets.manager.get(Assets.NOTFORME3, Sound.class);
                            break;
                        case 3:
                            spawnsound = Assets.manager.get(Assets.GREETINGS3, Sound.class);
                            refusesound = Assets.manager.get(Assets.NOTFORME3, Sound.class);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        } else {
            int randomVoice = ThreadLocalRandom.current().nextInt(1, 2 + 1);
            int randomLine = ThreadLocalRandom.current().nextInt(1, 2 + 1);
            switch (randomVoice) {
                case 1:
                    switch (randomLine) {
                        case 1:
                            spawnsound = Assets.manager.get(Assets.HEYA4, Sound.class);
                            refusesound = Assets.manager.get(Assets.NO4, Sound.class);
                            break;
                        case 2:
                            spawnsound = Assets.manager.get(Assets.HEY4, Sound.class);
                            refusesound = Assets.manager.get(Assets.NOTFORME4, Sound.class);
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    switch (randomLine) {
                        case 1:
                            spawnsound = Assets.manager.get(Assets.GREETINGS5, Sound.class);
                            refusesound = Assets.manager.get(Assets.NO5, Sound.class);
                            break;
                        case 2:
                            spawnsound = Assets.manager.get(Assets.YO5, Sound.class);
                            refusesound = Assets.manager.get(Assets.NOTFORME5, Sound.class);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        TextureAtlas textureAtlasIdle = null;
        TextureAtlas textureAtlasAngry = null;
        TextureAtlas textureAtlasOrdering = null;
        if (type.equals("normal")) {
            switch (randomNum) {
                case 1:
                    textureAtlasIdle = Assets.manager.get(Assets.GUEST1, TextureAtlas.class);
                    textureAtlasAngry = Assets.manager.get(Assets.GUEST1_ANGRY, TextureAtlas.class);
                    textureAtlasOrdering = Assets.manager.get(Assets.GUEST1_ORDERING, TextureAtlas.class);
                    break;
                case 2:
                    textureAtlasIdle = Assets.manager.get(Assets.GUEST2, TextureAtlas.class);
                    textureAtlasAngry = Assets.manager.get(Assets.GUEST2_ANGRY, TextureAtlas.class);
                    textureAtlasOrdering = Assets.manager.get(Assets.GUEST2_ORDERING, TextureAtlas.class);
                    break;
                case 3:
                    textureAtlasIdle = Assets.manager.get(Assets.GUEST3, TextureAtlas.class);
                    textureAtlasAngry = Assets.manager.get(Assets.GUEST3_ANGRY, TextureAtlas.class);
                    textureAtlasOrdering = Assets.manager.get(Assets.GUEST3_ORDERING, TextureAtlas.class);
                    break;
                case 4:
                    textureAtlasIdle = Assets.manager.get(Assets.GUEST4, TextureAtlas.class);
                    textureAtlasAngry = Assets.manager.get(Assets.GUEST4_ANGRY, TextureAtlas.class);
                    textureAtlasOrdering = Assets.manager.get(Assets.GUEST4_ORDERING, TextureAtlas.class);
                    break;
                case 5:
                    textureAtlasIdle = Assets.manager.get(Assets.GUEST5, TextureAtlas.class);
                    textureAtlasAngry = Assets.manager.get(Assets.GUEST5_ANGRY, TextureAtlas.class);
                    textureAtlasOrdering = Assets.manager.get(Assets.GUEST5_ORDERING, TextureAtlas.class);
                    break;
                case 6:
                    textureAtlasIdle = Assets.manager.get(Assets.GUEST6, TextureAtlas.class);
                    textureAtlasAngry = Assets.manager.get(Assets.GUEST6_ANGRY, TextureAtlas.class);
                    textureAtlasOrdering = Assets.manager.get(Assets.GUEST6_ORDERING, TextureAtlas.class);
                    break;
                case 7:
                    textureAtlasIdle = Assets.manager.get(Assets.GUEST7, TextureAtlas.class);
                    textureAtlasAngry = Assets.manager.get(Assets.GUEST7_ANGRY, TextureAtlas.class);
                    textureAtlasOrdering = Assets.manager.get(Assets.GUEST7_ORDERING, TextureAtlas.class);
                    break;
                case 8:
                    textureAtlasIdle = Assets.manager.get(Assets.GUEST8, TextureAtlas.class);
                    textureAtlasAngry = Assets.manager.get(Assets.GUEST8_ANGRY, TextureAtlas.class);
                    textureAtlasOrdering = Assets.manager.get(Assets.GUEST8_ORDERING, TextureAtlas.class);
                    break;
                case 9:
                    textureAtlasIdle = Assets.manager.get(Assets.GUEST9, TextureAtlas.class);
                    textureAtlasAngry = Assets.manager.get(Assets.GUEST9_ANGRY, TextureAtlas.class);
                    textureAtlasOrdering = Assets.manager.get(Assets.GUEST9_ORDERING, TextureAtlas.class);
                    break;
                default:
                    break;
            }
        } else if (type.equals("king")) {
            textureAtlasIdle = Assets.manager.get(Assets.KING, TextureAtlas.class);
            textureAtlasAngry = Assets.manager.get(Assets.KING_KICK, TextureAtlas.class);
            textureAtlasOrdering = Assets.manager.get(Assets.KING_JUMP, TextureAtlas.class);
            spawnsound = Assets.manager.get(Assets.FANFARE, Sound.class);
            refusesound = Assets.manager.get(Assets.NO3, Sound.class);
        } else if (type.equals("skeleton")) {
            textureAtlasIdle = Assets.manager.get(Assets.SKELETON_IDLE, TextureAtlas.class);
            textureAtlasAngry = Assets.manager.get(Assets.SKELETON_KICKING, TextureAtlas.class);
            textureAtlasOrdering = Assets.manager.get(Assets.SKELETON_JUMPING, TextureAtlas.class);
            spawnsound = Assets.manager.get(Assets.EVILLAUGH, Sound.class);
            refusesound = Assets.manager.get(Assets.NO3, Sound.class);
        }
        if (type.equals("skeleton")) {
        	paysound = null;
        	order = null;
        	dish = new Dish();
        }
        else {
        	paysound = Assets.manager.get(Assets.PAY, Sound.class);
        	order = Foodtype.getRandomFoodType();
        	dish = new Dish(order);
        }
        bubble = new Sprite(Assets.manager.get(Assets.BUBBLE, Texture.class));
        bubble.setSize(8, 8);
        idleAnimation = new Animation<TextureRegion>(0.045f, textureAtlasIdle.getRegions(), Animation.PlayMode.LOOP);
        angryAnimation = new Animation<TextureRegion>(0.05f, textureAtlasAngry.getRegions(), Animation.PlayMode.LOOP);
        orderAnimation = new Animation<TextureRegion>(0.1f, textureAtlasOrdering.getRegions(), Animation.PlayMode.LOOP);
        this.activeAnimation = orderAnimation;
        this.currentAnimation = "ordering";
        this.lastAnimationTime = (int) spawnTime;
        this.spawnTime = spawnTime;
        this.orderTime = spawnTime + 1;
        this.type = type;
        randomNum = ThreadLocalRandom.current().nextInt((int) (0.2 * patience) * (-1), (int) (0.2 * patience) + 1);
        this.patience = patience + randomNum;
        this.maxPatience = patience + randomNum;
        randomNum = ThreadLocalRandom.current().nextInt((int) (0.2 * wealth) * (-1), (int) (0.2 * wealth) + 1);
        this.wealth = wealth + randomNum;
        happiness = 3;
        paysound = Assets.manager.get(Assets.PAY, Sound.class);
    }

    public void receivedWrongDish(int time) {
        patience = patience - ((dynamicPatience / 100) * 25);
        setOrderTime(time);
    }

    public int getTip() {
        float tip = (wealth * (dynamicPatience / maxPatience));
        return (int) Math.ceil(tip);
    }

    public void calculatePatience(long timeElapsed) {
        dynamicPatience = (patience - timeElapsed * 3);
        if (dynamicPatience < 0) {
            dynamicPatience = 0;
        }
    }

    public void setPosition(float positionX, float positionY) {
        position[0] = positionX;
        position[1] = positionY;
        float[] position = {(float) (positionX + dish.getSprite().getWidth() + 2.3), (float) (positionY + 4)};
        dish.setPosition(position);
        bubble.setPosition((float) (positionX + dish.getSprite().getWidth() + 0.3), (float) (positionY + 1.4));
    }

    public void playSpawnsound() {
        long soundId = spawnsound.play();
        if (type.equals("king"))
            spawnsound.setPitch(soundId, 1.2f);
        else if(type.equals("skeleton"))
        	spawnsound.setVolume(soundId, 1.6f);
        else
            spawnsound.setVolume(soundId, 0.3f);
    }

    public void playRefuseSound() {
        long soundId = refusesound.play();
        refusesound.setVolume(soundId, 0.6f);
    }

    public void playPaysound() {
    	if(!type.equals("skeleton")) {
    	long soundId = paysound.play();
    	paysound.setVolume(soundId, 0.7f);
    	}
    }

    public long getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(long spawnTime) {
        this.spawnTime = spawnTime;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public float getPatience() {
        return dynamicPatience;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public float getMaxPatience() {
        return maxPatience;
    }

    public Foodtype getOrder() {
        return order;
    }

    public Sprite getBubble() {
        return bubble;
    }

    public Animation<TextureRegion> getIdleAnimation() {
        return idleAnimation;
    }

    public float[] getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Animation<TextureRegion> getActiveAnimation() {
        return activeAnimation;
    }

    public void setActiveAnimation(String animation, int time) {
        switch (animation) {
            case "idle":
                this.activeAnimation = this.idleAnimation;
                this.currentAnimation = "idle";
                this.lastAnimationTime = time;
                break;
            case "ordering":
                this.activeAnimation = this.orderAnimation;
                this.currentAnimation = "ordering";
                this.lastAnimationTime = time;
                break;
            case "angry":
                this.activeAnimation = this.angryAnimation;
                this.currentAnimation = "angry";
                this.lastAnimationTime = time;
        }

    }

    public String getCurrentAnimation() {
        return currentAnimation;
    }

    public int getLastAnimationTime() {
        return lastAnimationTime;
    }

    public int getDespawnTime() {
        return despawnTime;
    }

    public void setDespawnTime(int despawnTime) {
        this.despawnTime = despawnTime;
    }

    public boolean isServed() {
        return isServed;
    }

    public void setServed(boolean served) {
        isServed = served;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "spawnTime=" + spawnTime +
                ", patience=" + patience +
                ", wealth=" + wealth +
                ", order=" + order +
                ", position=" + Arrays.toString(position) +
                '}';
    }

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}
}
