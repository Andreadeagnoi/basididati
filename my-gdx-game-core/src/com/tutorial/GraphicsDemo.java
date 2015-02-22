package com.tutorial;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GraphicsDemo implements ApplicationListener{

	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;

	// The texture represents the underlying OpenGL texture. One important thing
	// to keep in mind with Texture ( and other similar classes ) is they
	// implement the Disposable interface. This means when you are done with it,
	// you have to call the dispose() method, or you will leak memory!
	// A Sprite holds the geometry and colour data of a texture, this means the
	// positional data ( such as it’s X and Y location ) are stored in the
	// Sprite.
	// We construct our texture by passing it’s path in.We then construct the
	// Sprite by passing in our newly created texture.
	@Override
	public void create() {
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/aereo.png"));
		sprite = new Sprite(texture);

	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		// The image is drawn relative to the origin. In the case of LibGDX
		// (0,0) is the bottom left corner of the screen.
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
}
