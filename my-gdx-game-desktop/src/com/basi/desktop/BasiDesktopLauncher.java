package com.basi.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.basi.SadogashimaEditor;

public class BasiDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Sadogashima Editor";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new SadogashimaEditor(), config);
	}
}
