package com.tutorial.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tutorial.AnimationDemo2;

public class AnimationDemo2DesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "AnimationDemo2";
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new AnimationDemo2(), config);

	}
}
