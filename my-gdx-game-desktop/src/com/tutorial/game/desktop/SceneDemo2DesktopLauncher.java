package com.tutorial.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tutorial.SceneDemo2;

public class SceneDemo2DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SceneDemo2";
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new SceneDemo2(), config);
	}
}
