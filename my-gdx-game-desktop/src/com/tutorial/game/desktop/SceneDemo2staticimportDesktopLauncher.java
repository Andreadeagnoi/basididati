package com.tutorial.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tutorial.SceneDemo2staticimport;

public class SceneDemo2staticimportDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SceneDemo2staticimport";
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new SceneDemo2staticimport(), config);
	}
}
