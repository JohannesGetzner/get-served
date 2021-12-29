package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) (LwjglApplicationConfiguration.getDesktopDisplayMode().width);
		config.height = (int) (LwjglApplicationConfiguration.getDesktopDisplayMode().height);
		config.title = "Get Served";
		new LwjglApplication(new MyGdxGame(), config);
	}
}
