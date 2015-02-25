package com.tutorial.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tutorial.AudioDemo;

public class AudioDemoDesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "AudioDemo";
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new AudioDemo(), config);

	}
}
