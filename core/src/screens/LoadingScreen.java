package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;
import handlers.Assets;

import static com.mygdx.game.MyGdxGame.WORLD_HEIGHT;
import static com.mygdx.game.MyGdxGame.WORLD_WIDTH;

public class LoadingScreen implements Screen {
	private MyGdxGame game;
	private SpriteBatch batch;
	private boolean finishLoading;
	private boolean showLogo;
	private Sprite loading;
	private Sprite enter;
	private Sprite discard;
	private Sprite serve;
	private Sprite sKey;
	private Sprite dKey;
	private Sprite move;
	private Sprite arrows;
	private Sprite dontlose;
	private Sprite coin;
	private Sprite logo;

	public LoadingScreen(MyGdxGame game, SpriteBatch batch) {
		this.game = game;
		this.batch = batch;
		finishLoading = false;
		showLogo = true;
		loading = new Sprite(Assets.manager.get(Assets.LOADING, Texture.class));
		loading.setSize(64, WORLD_HEIGHT / 10);
		loading.setPosition(WORLD_WIDTH / 2 - (loading.getWidth() / 2), 5);
		enter = new Sprite(Assets.manager.get(Assets.ENTER, Texture.class));
		enter.setSize(64, WORLD_HEIGHT / 12);
		enter.setPosition(WORLD_WIDTH / 2 - (enter.getWidth() / 2), 5);
		arrows = new Sprite(Assets.manager.get(Assets.ARROWS, Texture.class));
		arrows.setSize(15, 10);
		arrows.setPosition(33, WORLD_HEIGHT - 20);
		move = new Sprite(Assets.manager.get(Assets.MOVE, Texture.class));
		move.setSize(23, 5);
		move.setPosition(44 + arrows.getWidth(), WORLD_HEIGHT - 20);
		sKey = new Sprite(Assets.manager.get(Assets.SKEY, Texture.class));
		sKey.setSize(7, 7);
		sKey.setPosition(37, WORLD_HEIGHT - 35);
		serve = new Sprite(Assets.manager.get(Assets.SERVE, Texture.class));
		serve.setSize(45, 6);
		serve.setPosition(43 + arrows.getWidth(), WORLD_HEIGHT - 35);
		dKey = new Sprite(Assets.manager.get(Assets.DKEY, Texture.class));
		dKey.setSize(7, 7);
		dKey.setPosition(37, WORLD_HEIGHT - 50);
		discard = new Sprite(Assets.manager.get(Assets.DISCARD, Texture.class));
		discard.setSize(50, 6);
		discard.setPosition(43 + arrows.getWidth(), WORLD_HEIGHT - 50);
		dontlose = new Sprite(Assets.manager.get(Assets.DONTLOSE, Texture.class));
		dontlose.setSize(88, 6);
		dontlose.setPosition(43 + arrows.getWidth(), WORLD_HEIGHT - 65);
		coin = new Sprite(Assets.manager.get(Assets.COIN, Texture.class));
		coin.setSize(7, 7);
		coin.setPosition(37, WORLD_HEIGHT - 65);
		logo = new Sprite(Assets.manager.get(Assets.LOGO, Texture.class));
		logo.setSize(60, 65);
		logo.setPosition(WORLD_WIDTH / 2 - logo.getWidth() / 2, WORLD_HEIGHT / 2 - logo.getHeight() / 2 + 5);
		queueAssets();

		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				showLogo = false;
			}
		}, 6, 6);
	}

	@Override
	public void show() {

	}

	private void update(float delta) {
		if (Assets.manager.update()) {
			game.initializeEndLevelScreen();
			finishLoading = true;
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(delta);
		batch.begin();
		if (showLogo) {
			logo.draw(batch);
			loading.draw(batch);
			batch.end();
		} else {
			if (!finishLoading)
				loading.draw(batch);
			else
				enter.draw(batch);
			discard.draw(batch);
			serve.draw(batch);
			arrows.draw(batch);
			sKey.draw(batch);
			dKey.draw(batch);
			move.draw(batch);
			coin.draw(batch);
			dontlose.draw(batch);
			batch.end();

			if (finishLoading) {
				if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
					 Timer.instance().clear();  
					game.showMenu();
				}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	private void queueAssets() {
		Assets.load();
	}

}
