package com.libgdx.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class generateString {
	public static final String txtToString(String path) {
		FileHandle file = Gdx.files.internal(path);
		String text = file.readString();
		return text;
	}
	
}
