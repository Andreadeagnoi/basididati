package com.tutorial.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tutorial.SceneDemo2otheractions;

public class SceneDemo2otheractionsDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SceneDemo2otheractions";
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new SceneDemo2otheractions(), config);
	}
}
