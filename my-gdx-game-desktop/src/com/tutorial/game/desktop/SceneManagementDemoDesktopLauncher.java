package com.tutorial.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tutorial.SceneManagementDemo;

public class SceneManagementDemoDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SceneManagementDemo";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new SceneManagementDemo(), config);
	}
}
