package screens;

import static com.mygdx.game.MyGdxGame.WORLD_HEIGHT;
import static com.mygdx.game.MyGdxGame.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

import handlers.Assets;

public class EndLevelScreen implements Screen{

	private MyGdxGame game;
	private SpriteBatch batch;
	private Sprite cleared;
	private Sprite failed;
	private Sprite pressEnter;
	private Sprite score;
	private Sprite skeleton;
	private Sprite nomoney;
	private Sprite skull;
	private Sprite coin;
	private int state = 0;
	private int money = 0;
	private Sound applause;
	private Sound boo;
	private BitmapFont moneyFont;
	private GlyphLayout layoutMoney;
	
	public EndLevelScreen(MyGdxGame game, SpriteBatch batch) {
		this.game = game;
		this.batch = batch;
		cleared = new Sprite(Assets.manager.get(Assets.LEVELCLEARED, Texture.class));
		failed = new Sprite(Assets.manager.get(Assets.LEVELFAILED, Texture.class));
		cleared.setSize(WORLD_WIDTH / 3 + 5, WORLD_HEIGHT / 8);
		failed.setSize(WORLD_WIDTH / 3 + 5, WORLD_HEIGHT / 8);
		cleared.setPosition(WORLD_WIDTH / 2 - cleared.getWidth() / 2, WORLD_HEIGHT / 2 - cleared.getHeight() / 2 + 16);
		failed.setPosition(WORLD_WIDTH / 2 - failed.getWidth() / 2, WORLD_HEIGHT / 2 - failed.getHeight() / 2 + 16);
		pressEnter = new Sprite(Assets.manager.get(Assets.ENTER, Texture.class));
		pressEnter.setSize(WORLD_WIDTH / 4 + 12, WORLD_HEIGHT / 16);
		pressEnter.setPosition(WORLD_WIDTH / 2 - pressEnter.getWidth() / 2, 10);
		
		score = new Sprite(Assets.manager.get(Assets.SCORE, Texture.class));
		score.setSize(WORLD_WIDTH / 8, WORLD_HEIGHT / 16 + 1);
		score.setPosition(WORLD_WIDTH / 2 - score.getWidth() / 2 - 8, WORLD_HEIGHT / 2 - 12);
		coin = new Sprite(Assets.manager.get(Assets.COIN, Texture.class));
		coin.setSize(score.getHeight(), score.getHeight());
		moneyFont = Assets.manager.get(Assets.MONEYFONT, BitmapFont.class);
		moneyFont.getData().setScale(0.08f);
		layoutMoney = new GlyphLayout();
		
		nomoney = new Sprite(Assets.manager.get(Assets.NOMONEY, Texture.class));
		nomoney.setSize(60, WORLD_HEIGHT / 16 + 1);
		nomoney.setPosition(WORLD_WIDTH / 2 - nomoney.getWidth() / 2 + coin.getWidth() / 2, WORLD_HEIGHT / 2 - 12);
		
		skull = new Sprite(Assets.manager.get(Assets.SKULL, Texture.class));
		skull.setSize(score.getHeight(), score.getHeight());
		skeleton = new Sprite(Assets.manager.get(Assets.SKELETON, Texture.class));
		skeleton.setSize(70, WORLD_HEIGHT / 16 + 1);
		skeleton.setPosition(WORLD_WIDTH / 2 - skeleton.getWidth() / 2 + skull.getWidth() / 2, WORLD_HEIGHT / 2 - 12);
		
		applause = Assets.manager.get(Assets.APPLAUSE, Sound.class);
		boo = Assets.manager.get(Assets.BOO, Sound.class);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(state == 1) {
			cleared.draw(batch);
			score.draw(batch);
			layoutMoney.setText(moneyFont, "" + money);
			if(money >= 10) {
				moneyFont.draw(batch, layoutMoney, WORLD_WIDTH / 2 - layoutMoney.width / 2 + 9, WORLD_HEIGHT / 2 - 6);
				coin.setPosition(WORLD_WIDTH / 2 + score.getWidth() / 2 + layoutMoney.width - 3, WORLD_HEIGHT / 2 - 12);
			}else {
				moneyFont.draw(batch, layoutMoney, WORLD_WIDTH / 2 - layoutMoney.width / 2 + 7, WORLD_HEIGHT / 2 - 6);
				coin.setPosition(WORLD_WIDTH / 2 + score.getWidth() / 2 + layoutMoney.width - 3, WORLD_HEIGHT / 2 - 12);
			}
			coin.draw(batch);
		}else if (state == 2) {
			failed.draw(batch);
			coin.setPosition(WORLD_WIDTH / 2 - nomoney.getWidth() / 2 - coin.getWidth() / 2 - 2, WORLD_HEIGHT / 2 - 12);
			coin.draw(batch);
			nomoney.draw(batch);
		}else {
			failed.draw(batch);
			skeleton.draw(batch);
			skull.setPosition(WORLD_WIDTH / 2 - skeleton.getWidth() / 2 - coin.getWidth() / 2 - 2, WORLD_HEIGHT / 2 - 12);
			skull.draw(batch);
		}
		pressEnter.draw(batch);
		batch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			game.showMenu();
		}	
	}
	
	public void playEndSound() {
		if(state == 1) {
			long soundId = applause.play();
			applause.setVolume(soundId, 0.7f);
			applause.setPitch(soundId, 1.1f);
		}else {
			long soundId = boo.play();
			boo.setVolume(soundId, 0.5f);
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

	public void setEndScreen(int state, int money) {
		this.state = state;
		this.money = money;
	}
	
	
}
