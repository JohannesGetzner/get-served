package entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import handlers.Assets;

import static com.mygdx.game.MyGdxGame.WORLD_HEIGHT;
import static com.mygdx.game.MyGdxGame.WORLD_WIDTH;

public class Cook {
    private Animation<TextureRegion> idleAnimation;
    private float[] position;
    private Sprite sprite;
    private Box box;
    private int rotation;

    public Cook(World world, float[] position, int rotation) {
        this.rotation = rotation;
        this.position = position;
        System.out.println(rotation);
        TextureAtlas textureAtlas = Assets.manager.get(Assets.COOK, TextureAtlas.class);
        idleAnimation = new Animation<TextureRegion>(0.045f, textureAtlas.getRegions(), Animation.PlayMode.LOOP);
        sprite = new Sprite(textureAtlas.findRegion("Idle"));
        sprite.setSize(WORLD_WIDTH / 30, WORLD_HEIGHT / 15);
        sprite.setPosition(position[0], position[1]);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box = new Box(world, sprite, false, true);
        box.getBody().getFixtureList().first().setUserData(this);
    }

    public Animation<TextureRegion> getIdleAnimation() {
        return idleAnimation;
    }

    public float[] getPosition() {
        return position;
    }

    public int getRotation() {
        return rotation;
    }
}
