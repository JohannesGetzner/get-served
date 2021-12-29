package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import handlers.Assets;

public class Dish {
    private Sprite sprite;
    public Foodtype type;
    private float[] position;

    public Dish(Foodtype type){
        this.type = type;
        switch (type) {
            case PIZZA:
                sprite = new Sprite(Assets.manager.get(Assets.PIZZA, Texture.class));
                break;
            case BEER:
                sprite = new Sprite(Assets.manager.get(Assets.BEER, Texture.class));
                break;
            case CHICKEN:
                sprite = new Sprite(Assets.manager.get(Assets.CHICKEN, Texture.class));
                break;
            case POMMES:
                sprite = new Sprite(Assets.manager.get(Assets.POMMES, Texture.class));
                break;
            case FISH:
                sprite = new Sprite(Assets.manager.get(Assets.FISH, Texture.class));
                break;
            default: break;
        }
        sprite.setSize(4, 4);
    }
    public Dish() {
    	this.type = null;
    	sprite = new Sprite(Assets.manager.get(Assets.SKULL, Texture.class));
    	sprite.setSize(4, 4);
    }
    public void setPosition(float[] positions){
        position = positions;
        this.sprite.setPosition(position[0],position[1]);
    }
    public Sprite getSprite(){
        return sprite;
    }

    public void setSpriteSize(float width, float height) {
        sprite.setSize(width, height);
    }
    @Override
    public String toString() {
        return "Dish{" +
                "type=" + type +
                '}';
    }
}
