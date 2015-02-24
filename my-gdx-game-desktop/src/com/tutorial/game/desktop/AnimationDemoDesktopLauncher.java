package com.tutorial.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tutorial.AnimationDemo;

public class AnimationDemoDesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "AnimationDemo";
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new AnimationDemo(), config);

	}
}
