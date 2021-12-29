package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGdxGame;

import entities.Guest;
import handlers.Assets;

import static com.mygdx.game.MyGdxGame.WORLD_HEIGHT;
import static com.mygdx.game.MyGdxGame.WORLD_WIDTH;

public class MenuScreen implements Screen{
	
	private MyGdxGame game;
	private SpriteBatch batch;
	private Sprite nextactive;
	private Sprite nextpassive;
	private Sprite exitactive;
	private Sprite exitpassive;
	private Sprite bonuslevel;
	private Guest king;
	private Guest skeleton;
	private Sprite kingInfo;
	private Sprite skeletonInfo;
	private boolean nextorexit;
	private float elapsedTime = 0;
	
	public MenuScreen(MyGdxGame game, SpriteBatch batch) {
		this.game = game;
		this.batch = batch;
		nextorexit = true;
        nextactive = new Sprite(Assets.manager.get(Assets.NEXTACTIVE, Texture.class));
		nextactive.setSize(WORLD_WIDTH / 3, WORLD_HEIGHT / 8);
		nextactive.setPosition(WORLD_WIDTH / 2 - (nextactive.getWidth() / 2), WORLD_HEIGHT / 2 - (nextactive.getHeight() / 2) + 15);
        exitactive = new Sprite(Assets.manager.get(Assets.EXITACTIVE, Texture.class));
		exitactive.setSize(WORLD_WIDTH / 7, WORLD_HEIGHT / 8);
		exitactive.setPosition(WORLD_WIDTH / 2 - (exitactive.getWidth() / 2), WORLD_HEIGHT / 2 - (exitactive.getHeight() / 2) - 15);
        nextpassive = new Sprite(Assets.manager.get(Assets.NEXTPASSIVE, Texture.class));
		nextpassive.setSize(WORLD_WIDTH / 3, WORLD_HEIGHT / 8);
		nextpassive.setPosition(WORLD_WIDTH / 2 - (nextpassive.getWidth() / 2), WORLD_HEIGHT / 2 - (nextpassive.getHeight() / 2) + 15);
        exitpassive = new Sprite(Assets.manager.get(Assets.EXITPASSIVE, Texture.class));
		exitpassive.setSize(WORLD_WIDTH / 7, WORLD_HEIGHT / 8);
		exitpassive.setPosition(WORLD_WIDTH / 2 - (exitpassive.getWidth() / 2), WORLD_HEIGHT / 2 - (exitpassive.getHeight() / 2) - 15);
		bonuslevel = new Sprite(Assets.manager.get(Assets.BONUSINSTRUCTIONS, Texture.class));
		bonuslevel.setSize(10, 2.5f);
		bonuslevel.setPosition(2, 2);
		king = new Guest(0, 0, 0, "king");
		king.setPosition(18, WORLD_HEIGHT / 2 + 4);
		king.setActiveAnimation("idle", 0);
		skeleton = new Guest(0, 0, 0, "skeleton");
		skeleton.setPosition(WORLD_WIDTH - 33, WORLD_HEIGHT / 2 + 4);
		skeleton.setActiveAnimation("idle", 0);
		kingInfo = new Sprite(Assets.manager.get(Assets.KINGINFO, Texture.class));
		kingInfo.setSize(30, 19);
		kingInfo.setPosition(10, WORLD_HEIGHT / 2 - 24);
		skeletonInfo = new Sprite(Assets.manager.get(Assets.SKELETONINFO, Texture.class));
		skeletonInfo.setSize(33, 19);
		skeletonInfo.setPosition(WORLD_WIDTH - 42, WORLD_HEIGHT / 2 - 24);
				
	}
	
	@Override
	public void show() {
		nextorexit = true;
	}

	@Override
	public void render(float delta) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		TextureRegion currentFrame;
		if((game.getLevelCount() == 2) || (game.getLevelCount() > 3)) {
			currentFrame = king.getActiveAnimation().getKeyFrame(elapsedTime, false);
			batch.draw(currentFrame, king.getPosition()[0], king.getPosition()[1], 15, 18);
			kingInfo.draw(batch);
		}
		if(game.getLevelCount() > 2) {
			currentFrame = skeleton.getActiveAnimation().getKeyFrame(elapsedTime, false);
			batch.draw(currentFrame, skeleton.getPosition()[0], skeleton.getPosition()[1], 15, 18);
			skeletonInfo.draw(batch);
		}
		if(nextorexit) {
			nextactive.draw(batch);
			exitpassive.draw(batch);
		}else {
			nextpassive.draw(batch);
			exitactive.draw(batch);
		}
		if((game.getLevelCount() == 5) && (game.isFinished())) {
			bonuslevel.draw(batch);
		}
		batch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			if(nextorexit)
				game.startLevel();
			else
				Gdx.app.exit();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			if(!nextorexit)
				nextorexit = true;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			if(nextorexit)
				nextorexit = false;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.X)) {
			if((game.getLevelCount() == 5) && (game.isFinished())) {
				game.setBonuslevel();
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
		
	}

}
