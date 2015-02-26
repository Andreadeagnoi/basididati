package com.tutorial.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tutorial.SceneManagementDemo2;

public class SceneManagementDemo2DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SceneManagementDemo2";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new SceneManagementDemo2(), config);
	}
}
