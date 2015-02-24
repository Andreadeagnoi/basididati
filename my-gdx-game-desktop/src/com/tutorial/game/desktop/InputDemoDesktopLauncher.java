package com.tutorial.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tutorial.InputDemo;

public class InputDemoDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "InputDemo";
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new InputDemo(), config);
	}
}
