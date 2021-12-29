package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.game.MyGdxGame;
import entities.*;
import handlers.Assets;
import handlers.GsContactListener;
import handlers.LevelHandler;

import static com.mygdx.game.MyGdxGame.*;

public class GameScreen implements Screen {
	public MyGdxGame game;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private LevelHandler levelHandler;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer renderer;
	private GsContactListener contactListener;
	private float elapsedTime = 0;
	private BitmapFont moneyFont;
	private GlyphLayout layoutMoney;
	private GlyphLayout layoutTip;
	private int tip;
	private int lastTipTime;
	private Sprite coin;
	private Sound music;
	private long musicId;

	public GameScreen(MyGdxGame game, SpriteBatch batch, ShapeRenderer shapeRenderer, String levelname) {
		this.game = game;
		this.batch = batch;
		this.shapeRenderer = shapeRenderer;

		initializeFonts();
		levelHandler = new LevelHandler(levelname);
		levelHandler.initializeLevel();
		contactListener = new GsContactListener();
		levelHandler.getWorld().setContactListener(contactListener);

		tiledMap = new TmxMapLoader().load("Map/map.tmx");
		MapProperties properties = tiledMap.getProperties();
		int tileWidth = properties.get("tilewidth", Integer.class);
		int tileHeight = properties.get("tileheight", Integer.class);
		int mapWidthInTiles = properties.get("width", Integer.class);
		int mapHeightInTiles = properties.get("height", Integer.class);
		int mapWidthInPixels = mapWidthInTiles * tileWidth;
		int mapHeightInPixels = mapHeightInTiles * tileHeight;
		renderer = new OrthogonalTiledMapRenderer(tiledMap);

		camera = new OrthographicCamera(1024.f, 576.f);
		camera.position.x = mapWidthInPixels * .5f;
		camera.position.y = mapHeightInPixels * .5f;

		debugRenderer = new Box2DDebugRenderer();

		if ((game.getLevelCount() == 1) || (game.getLevelCount() == 3) || (game.getLevelCount() == 5))
			music = Assets.manager.get(Assets.MUSIC1, Sound.class);
		else
			music = Assets.manager.get(Assets.MUSIC2, Sound.class);
		musicId = music.play();
		music.setPitch(musicId, 1f);
		music.setVolume(musicId, 0.33f);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		renderer.setView(camera);
		renderer.render();
		levelHandler.getWorld().step(1f / 60f, 6, 2);
		levelHandler.updateLevel();
		levelHandler.getWaiter().move(0.9f);
		Matrix4 debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);
		batch.begin();
		elapsedTime += Gdx.graphics.getDeltaTime();
		drawWaiter();
		drawTables();
		drawGuests();
		drawCounters();
		drawDishes();
		drawOrders();
		drawDishProgress();
		showMoney();
		drawLevel();
		drawCooks();
		if ((lastTipTime + 2) >= levelHandler.getTime())
			showTip();
		batch.end();
		if (false)
			debugRenderer.render(levelHandler.getWorld(), debugMatrix);
		reactToCollision();
		serveDishChecker();

		if (levelHandler.getTime() == 21)
			music.setPitch(musicId, 1.05f);
		else if (levelHandler.getTime() == 38)
			music.setPitch(musicId, 1.1f);

		/**
		 * levelOver = 2 -> ran out of money
		 * levelOver = 3 -> died to skeleton
		 * levelOver = 1 -> beat level
		 */

		if (levelHandler.getLevelOver() == 2) {
			game.showEndScreen(2, 0);
		}else if (levelHandler.getLevelOver() == 3) {
			game.showEndScreen(3, 0);
		} else if (levelHandler.getLevelOver() == 1) {
			game.increaseLevel();
			game.showEndScreen(1, (int) levelHandler.getLevel().getMoney());
		}
	}

	@Override
	public void dispose() {
		music.stop();
		levelHandler.getWorld().dispose();
		debugRenderer.dispose();
	}

	private void drawLevel() {
		batch.draw(levelHandler.getLevelDisplay(), (float) 11.2, 14,
				(float) (levelHandler.getLevelDisplay().getWidth() * 0.015),
				(float) (levelHandler.getLevelDisplay().getHeight() * 0.015));
	}

    private void drawCooks() {
        for (Cook c : levelHandler.getCooks()) {
            float positionX = 0;
            TextureRegion currentFrame = c.getIdleAnimation().getKeyFrame(elapsedTime);
            if (c.getRotation() == 0) {
                positionX = c.getPosition()[0] - 6;
                if (currentFrame.isFlipX()) {
                    currentFrame.flip(true, false);
                }
            } else {
                positionX = c.getPosition()[0] + 5;
                if (!currentFrame.isFlipX()) {
                    currentFrame.flip(true, false);
                }
            }
            batch.draw(currentFrame, positionX, c.getPosition()[1], WORLD_WIDTH / 28, WORLD_HEIGHT / 14);
        }
    }

	private void initializeFonts() {
		moneyFont = Assets.manager.get(Assets.MONEYFONT, BitmapFont.class);
		moneyFont.getData().setScale(0.08f);
		layoutMoney = new GlyphLayout();
		tip = 0;
		lastTipTime = -3;
		layoutTip = new GlyphLayout();
		coin = new Sprite(Assets.manager.get(Assets.COIN, Texture.class));
		coin.setSize(7, 7);
		coin.setPosition(WORLD_WIDTH - coin.getWidth() - 4, WORLD_HEIGHT - coin.getHeight() - 3);
		Gdx.app.log("INFO: ", "Fonts initialized");
	}

	private void drawWaiter() {
		TextureRegion currentFrame;

		if (!Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)
				&& !Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			currentFrame = levelHandler.getWaiter().getIdleAnimation().getKeyFrame(elapsedTime);
		} else {
			currentFrame = levelHandler.getWaiter().getRunningAnimation().getKeyFrame(elapsedTime);
		}

		if (levelHandler.getWaiter().getOrientation().equals("right")) {
			if (currentFrame.isFlipX()) {
				currentFrame.flip(true, false);
			}
		}
		if (levelHandler.getWaiter().getOrientation().equals("left")) {
			if (!currentFrame.isFlipX()) {
				currentFrame.flip(true, false);
			}
		}
		batch.draw(currentFrame,
				(levelHandler.getWaiter().getBody().getPosition().x * PIXELS_TO_METERS)
						- levelHandler.getWaiter().getSprite().getWidth() / 2,
				(levelHandler.getWaiter().getBody().getPosition().y * PIXELS_TO_METERS)
						- levelHandler.getWaiter().getSprite().getHeight() / 2,
				5.5f, 6.5f);
	}

	private void drawGuests() {
		TextureRegion currentFrame;
		for (Guest g : levelHandler.getGuestHandler().getActiveGuests()) {
			currentFrame = g.getActiveAnimation().getKeyFrame(elapsedTime, false);
			batch.draw(currentFrame, g.getPosition()[0], g.getPosition()[1], 5, 6);
			drawPatience(g);
		}
	}

	private void drawPatience(Guest guest) {
		batch.end();
		shapeRenderer.begin(ShapeType.Filled);
		float patience = guest.getPatience();
		shapeRenderer.setColor(Color.GREEN);
		if (patience <= 0.4 * guest.getMaxPatience()) {
			shapeRenderer.setColor(Color.RED);
		} else if (patience <= 0.75 * guest.getMaxPatience()) {
			shapeRenderer.setColor(Color.YELLOW);
		}
		shapeRenderer.rect(guest.getPosition()[0] - 2, guest.getPosition()[1], 1,
				((float) guest.getPatience() / guest.getMaxPatience()) * 5);
		shapeRenderer.end();
		batch.begin();
	}

	private void drawTables() {
		for (Table t : levelHandler.getGameField().getTables()) {
			t.getTableSprite().draw(batch);
			t.getChairSprite().draw(batch);
		}
	}

	private void drawCounters() {
		for (Counter c : levelHandler.getGameField().getCounters()) {
			c.getSprite().draw(batch);
		}
	}

	private void drawDishes() {
		if (levelHandler.getDishHandler().getActiveDishes() != null) {
			for (Dish d : levelHandler.getDishHandler().getActiveDishes()) {
				d.getSprite().draw(batch);
			}
		}
	}

	private void drawOrders() {
		for (Guest g : levelHandler.getGuestHandler().getActiveGuests()) {
			if (((g.getOrderTime() + 1) >= levelHandler.getTime()) && (!g.isServed())) {
				g.getBubble().draw(batch);
				g.getDish().getSprite().draw(batch);
			}
		}
	}

	private void drawDishProgress() {
		batch.end();
		shapeRenderer.begin(ShapeType.Filled);
		for (Counter c : levelHandler.getGameField().getCounters()) {
			switch (c.getRotation()) {
			case 0:
				shapeRenderer.setColor(Color.LIGHT_GRAY);
				shapeRenderer.rect(c.getPosition()[0] - c.getSprite().getWidth(),
						c.getPosition()[1] + (c.getSprite().getHeight() - c.getNextDish().getSprite().getHeight() - 5),
						c.getNextDish().getSprite().getWidth(), 1);

				shapeRenderer.setColor(Color.BLUE);
				shapeRenderer.rect(c.getPosition()[0] - c.getSprite().getWidth(),
						c.getPosition()[1] + (c.getSprite().getHeight() - c.getNextDish().getSprite().getHeight() - 5),
						(c.getNextDish().getSprite().getWidth())
								* ((levelHandler.getTime() - c.getLastDishTime()) / (6 / c.getCookSpeed())),
						1);
				break;
			case 1:
				shapeRenderer.setColor(Color.LIGHT_GRAY);
				shapeRenderer.rect(c.getPosition()[0] + c.getSprite().getWidth() + 2,
						c.getPosition()[1] + (c.getSprite().getHeight() - c.getNextDish().getSprite().getHeight() - 5),
						c.getNextDish().getSprite().getWidth(), 1);

				shapeRenderer.setColor(Color.BLUE);
				shapeRenderer.rect(c.getPosition()[0] + c.getSprite().getWidth() + 2,
						c.getPosition()[1] + (c.getSprite().getHeight() - c.getNextDish().getSprite().getHeight() - 5),
						(c.getNextDish().getSprite().getWidth())
								* ((levelHandler.getTime() - c.getLastDishTime()) / (6 / c.getCookSpeed())),
						1);
				break;
			case 2:
				shapeRenderer.setColor(Color.LIGHT_GRAY);
				shapeRenderer.rect(
						c.getPosition()[0] + c.getSprite().getWidth() / 2 - c.getNextDish().getSprite().getWidth() / 2,
						c.getPosition()[1] + c.getSprite().getHeight() + 1, c.getNextDish().getSprite().getWidth(), 1);

				shapeRenderer.setColor(Color.BLUE);
				shapeRenderer.rect(
						c.getPosition()[0] + c.getSprite().getWidth() / 2 - c.getNextDish().getSprite().getWidth() / 2,
						c.getPosition()[1] + c.getSprite().getHeight() + 1, c.getNextDish().getSprite().getWidth()
								* ((levelHandler.getTime() - c.getLastDishTime()) / (6 / c.getCookSpeed())),
						1);
				break;
			case 3:
				shapeRenderer.setColor(Color.LIGHT_GRAY);
				shapeRenderer.rect(
						c.getPosition()[0] + c.getSprite().getWidth() / 2 - c.getNextDish().getSprite().getWidth() / 2,
						c.getPosition()[1] - 2, c.getNextDish().getSprite().getWidth(), 1);

				shapeRenderer.setColor(Color.BLUE);
				shapeRenderer.rect(
						c.getPosition()[0] + c.getSprite().getWidth() / 2 - c.getNextDish().getSprite().getWidth() / 2,
						c.getPosition()[1] - 2, c.getNextDish().getSprite().getWidth()
								* ((levelHandler.getTime() - c.getLastDishTime()) / (6 / c.getCookSpeed())),
						1);
				break;
			}
		}
		shapeRenderer.end();
		batch.begin();

	}

	private void showMoney() {
		String moneyText = "" + levelHandler.getLevel().getMoney();
		layoutMoney.setText(moneyFont, moneyText);
		moneyFont.draw(batch, layoutMoney, WORLD_WIDTH - layoutMoney.width - 12, WORLD_HEIGHT - 4);
		coin.draw(batch);
	}

	private void showTip() {
		if (tip != 0) {
			String tipText = "+ " + tip;
			layoutTip.setText(moneyFont, tipText);
			moneyFont.draw(batch, layoutTip, WORLD_WIDTH - layoutTip.width - 12, WORLD_HEIGHT - layoutMoney.height - 6);
		}
	}

	private void reactToCollision() {
		if (contactListener.getContact() != null) {
			Contact contact = contactListener.getContact();
			Fixture fixtureA = contact.getFixtureA();
			Fixture fixtureB = contact.getFixtureB();
			Table contactTable;
			Counter contactCounter;
			if ((fixtureA.getUserData() instanceof Counter) || (fixtureB.getUserData() instanceof Counter)) {
				if ((fixtureA.getUserData() instanceof Waiter) || (fixtureB.getUserData() instanceof Waiter)) {
					if (fixtureA.getUserData() instanceof Counter)
						contactCounter = (Counter) fixtureA.getUserData();
					else
						contactCounter = (Counter) fixtureB.getUserData();
					if (contactCounter.getDish() != null && levelHandler.getWaiter().getDish() == null) {
						levelHandler.getWaiter().setDish(contactCounter.getDish());
						levelHandler.getWaiter().getDish().setSpriteSize(3f, 3f);
						contactCounter.removeDish();
					}

				}
			}
		}
	}

	private void serveDishChecker() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			Table closestTable = levelHandler.getWaiter().getClosestTable();
			if (closestTable == null) {
				Gdx.app.log("INFO: ", "Tried to serve a dish, but no table was close enough!");
			} else {
				if (levelHandler.getWaiter().getDish() != null && closestTable.getGuest() != null) {
					Dish dish = levelHandler.getWaiter().getDish();
					Waiter waiter = levelHandler.getWaiter();
					Guest guest = closestTable.getGuest();
					serveGuest(closestTable, dish, waiter, guest);
				}
			}
		}
	}

	private void serveGuest(Table contactTable, Dish dish, Waiter waiter, Guest guest) {
		if ((guest.getType().equals("skeleton")) || (guest.getOrder() == (dish.type))) {
			if (!guest.isServed()) {
				dish.setPosition(
						new float[] { contactTable.getPosition()[0] + contactTable.getTableSprite().getWidth() / 4,
								contactTable.getPosition()[1] + contactTable.getTableSprite().getHeight() / 2 });
				guest.setOrderTime(guest.getOrderTime() - 10);
				waiter.removeDish();
				guest.setDish(dish);
				tip = guest.getTip();
				lastTipTime = levelHandler.getTime();
				levelHandler.getLevel().setMoney(levelHandler.getLevel().getMoney() + guest.getTip());
				guest.setDespawnTime(levelHandler.getTime() + 2);
				guest.setServed(true);
			    guest.playPaysound();
				Gdx.app.log("INFO: ", "Delivered correct Dish to Guest");
			}
		} else {
			guest.receivedWrongDish(levelHandler.getTime());
			waiter.removeDish();
			levelHandler.getDishHandler().removeActiveDish(dish);
			guest.setActiveAnimation("angry", levelHandler.getTime());
			guest.playRefuseSound();
			Gdx.app.log("INFO: ", "Delivered wrong dish to Guest");
		}
	}

	@Override
	public void show() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}
}
