package com.tutorial.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tutorial.CameraDemo;

public class CameraDemoDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "CameraDemo";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new CameraDemo(), config);
	}
}
