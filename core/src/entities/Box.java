package entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.MyGdxGame.PIXELS_TO_METERS;

public class Box {
    private Body body;

    Box(World world, Sprite sprite, boolean isDynamicBody, boolean rectangle) {
        BodyDef bodyDef = new BodyDef();
        if(isDynamicBody){
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        }else{
            bodyDef.type = BodyDef.BodyType.StaticBody;
        }
        bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / PIXELS_TO_METERS,
                (sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);
        body = world.createBody(bodyDef);
        body.setUserData(sprite);
        if (rectangle) {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox((sprite.getWidth() / 2) / PIXELS_TO_METERS, (sprite.getHeight() / 2) / PIXELS_TO_METERS);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            shape.dispose();
        } else {
            CircleShape shape = new CircleShape();
            shape.setRadius((sprite.getWidth() / 2) / PIXELS_TO_METERS);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            shape.dispose();
        }
    }
    Body getBody() {
        return body;
    }
}
