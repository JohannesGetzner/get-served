package entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.MyGdxGame.*;

public class Walls {
	Body body;
	
	public Walls(World world) {
		BodyDef bdef = new BodyDef();
		bdef.type = BodyDef.BodyType.StaticBody;
		body = world.createBody(bdef);
		ChainShape shape = new ChainShape();
		Vector2[] verts = new Vector2[5];
        verts[0] = new Vector2((1) / PIXELS_TO_METERS, (1) / PIXELS_TO_METERS);
        verts[1] = new Vector2((WORLD_WIDTH - 20) / PIXELS_TO_METERS, (1) / PIXELS_TO_METERS);
        verts[2] = new Vector2((WORLD_WIDTH - 20) / PIXELS_TO_METERS, (WORLD_HEIGHT - 15) / PIXELS_TO_METERS);
        verts[3] = new Vector2((1) / PIXELS_TO_METERS, (WORLD_HEIGHT - 15) / PIXELS_TO_METERS);
        verts[4] = new Vector2((1) / PIXELS_TO_METERS, (1) / PIXELS_TO_METERS);
		shape.createChain(verts);
		body.createFixture(shape, 1.0f);
		shape.dispose();
	}
}
