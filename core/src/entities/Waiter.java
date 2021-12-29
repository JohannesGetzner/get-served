
package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import handlers.Assets;
import interfaces.Moveable;

import java.util.List;

import static com.mygdx.game.MyGdxGame.WORLD_HEIGHT;
import static com.mygdx.game.MyGdxGame.WORLD_WIDTH;

public class Waiter implements Moveable {
    private Animation<TextureRegion> runningAnimation;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> dyingAnimation;
    private boolean isAllowedToMove = true;
    private Sprite sprite;
    private Box box;
    private Dish dish;
    private String orientation = "right";
    private List<Table> tables;

    /**
     * constructor for the waiter
     *
     * @param world     the physics world for the waiter to be created in
     * @param positionX the initial position of the waiter on the X axis
     * @param positionY the initial position of the waiter on the Y axis
     */
    public Waiter(World world, float positionX, float positionY) {
        TextureAtlas textureAtlas = Assets.manager.get(Assets.WAITER_WALKING, TextureAtlas.class);
        runningAnimation = new Animation<TextureRegion>(0.015f, textureAtlas.getRegions(), Animation.PlayMode.LOOP);
        textureAtlas = Assets.manager.get(Assets.WAITER_IDLE, TextureAtlas.class);
        sprite = new Sprite(textureAtlas.findRegion("Idle"));
        sprite.setSize(WORLD_WIDTH / 32, WORLD_HEIGHT / 16);
        sprite.setPosition(positionX, positionY);
        idleAnimation = new Animation<TextureRegion>(0.045f, textureAtlas.getRegions(), Animation.PlayMode.LOOP);
        textureAtlas = Assets.manager.get(Assets.WAITER_DYING, TextureAtlas.class);
        dyingAnimation = new Animation<TextureRegion>(0.3f, textureAtlas.getRegions(), Animation.PlayMode.NORMAL);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box = new Box(world, sprite, true, false);
        box.getBody().getFixtureList().first().setUserData(this);
        dish = null;
    }

    public void move(float speed) {
        if (isAllowedToMove) {
            float velX = 0, velY = 0;
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                velY = speed;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                orientation = "right";
                velX = speed;
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                velY = (speed * -1);
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                orientation = "left";
                velX = (speed * -1);
            }
            box.getBody().setLinearVelocity(velX, velY);
        } else {
            box.getBody().setLinearVelocity(0, 0);
        }
    }

    public Table getClosestTable(){
        Vector2 currentWaiterPos = box.getBody().getPosition();
        float smallestDist = 99;
        float tempDist = 0;
        Table closestTable = new Table();
        for (Table t:tables
             ) {
            tempDist = currentWaiterPos.dst(t.getBox().getBody().getPosition());
            if(tempDist < smallestDist){
                smallestDist = tempDist;
                closestTable = t;
            }
        }
        if(smallestDist >= 0.19){
            return null;
        }else{
            return closestTable;
        }
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "box=" + box +
                ", dish=" + dish +
                '}';
    }

    /*
     * -----------------
     * Getter and Setter
     */

    public Sprite getSprite() {
        return sprite;
    }

    public Body getBody() {
        return box.getBody();
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void removeDish() {
        dish = null;
    }

    public Animation<TextureRegion> getRunningAnimation() {
        return runningAnimation;
    }

    public String getOrientation() {
        return orientation;
    }

    public Animation<TextureRegion> getIdleAnimation() {
        return idleAnimation;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public void setAllowedToMove(boolean allowedToMove) {
        isAllowedToMove = allowedToMove;
    }

    public void activateDyingAnimation() {
        this.idleAnimation = dyingAnimation;
        this.runningAnimation = dyingAnimation;
    }
}
