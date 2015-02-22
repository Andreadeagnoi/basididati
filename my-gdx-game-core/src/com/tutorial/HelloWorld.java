package com.tutorial;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloWorld implements ApplicationListener {
	private SpriteBatch batch;
	private BitmapFont font;

	// In create() we allocate a new SpriteBatch,
	// BitmapFont and set the font to the colour red.
	// In OpenGL there is a fair bit of overhead in drawing … well, anything. A
	// spritebatch combines them all into a single operation to reduce the
	// amount of overhead. In a nutshell, it makes 2D rendering a great deal
	// faster. A BitmapFont is exactly what it sounds like, a 2D bitmap
	// containing all the characters. If you don’t specify a Font in the
	// constructor, you will get the default font Arial-15 included with LibGDX.
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();

	}

	// The render() method will be called each frame,
	// so if you want to, you can think of that as your event loop.
	// In the render() method we clear the screen to white by making the OpenGL
	// function call glClear() and glClearColor(). The parameters to
	// glClearColor are the red, green, blue and alpha ( transparency ) values
	// to clear the screen with. The function glClear actually clears the
	// screen. As you can see, the underlying OpenGL functionality is exposed in
	// Gdx.gl, although generally you wont work at that level very often.
	// Next we start our sprite batch by calling begin(), then render our text
	// to the batch using the font.draw method. The parameters to draw() are the
	// batch to draw to, the text to draw and the x and y coordinates to draw
	// the text at.
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		font.draw(batch, "Hello World", 200, 200);
		batch.end();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void resume() {

	}

}
