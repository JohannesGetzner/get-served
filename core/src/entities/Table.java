package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import handlers.Assets;

import java.util.Arrays;

import static com.mygdx.game.MyGdxGame.WORLD_HEIGHT;
import static com.mygdx.game.MyGdxGame.WORLD_WIDTH;

public class Table {
    private Sprite tableSprite;
    private Sprite chairSprite;
    private Box box;
    private float[] positions = new float[2];
    private float[] chairPosition = new float[2];
    private Guest guest;

    public Table(World world, float positionX, float positionY) {
        this.positions[0] = positionX;
        this.positions[1] = positionY;
        this.chairPosition[0] = positionX + 2.5f;
        this.chairPosition[1] = positionY + 5f;
        tableSprite = new Sprite(Assets.manager.get(Assets.TABLE, Texture.class));
        chairSprite = new Sprite(Assets.manager.get(Assets.CHAIR, Texture.class));
        tableSprite.setSize(6, 6);
        chairSprite.setSize(3, 3);
        tableSprite.setPosition(positionX, positionY);
        chairSprite.setPosition(positionX + 3f, positionY + 6f);
        box = new Box(world, tableSprite, false, true);
        box.getBody().getFixtureList().first().setUserData(this);
        guest = null;
    }

    public Table() {
    }

    public Sprite getTableSprite() {
        return tableSprite;
    }

    public Sprite getChairSprite() {
        return chairSprite;
    }

    public float[] getChairPosition() {
        return chairPosition;
    }

    public void setChairPosition(float[] chairPosition) {
        this.chairPosition = chairPosition;
    }

    public float[] getPosition() {
        return positions;
    }

    public Guest getGuest() {
        return guest;
    }
    
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    
    public void removeGuest() {
    	this.guest = null;
    }

    public Box getBox() {
        return box;
    }

    @Override
    public String toString() {
        return "Table{" +
                "positions=" + Arrays.toString(positions) +
                ", guest=" + guest +
                '}';
    }
}
