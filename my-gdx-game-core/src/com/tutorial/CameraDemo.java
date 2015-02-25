package com.tutorial;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

//the original tutorial had examples with gestures, but sticazzi
public class CameraDemo implements ApplicationListener {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;

	@Override
	public void create() {
		// The values passed to the constructor are the resolution of the
		// camera, the width and height. In this particular case I chose to use
		// pixels for my resolution, as I wanted to have the rendering at
		// 1280x720 pixels. You however do not have to… if you are using physics
		// and want to use real world units for example, you could have gone
		// with meters, or whatever you want. The key thing is that your aspect
		// ratio is correct.
		camera = new OrthographicCamera(1280, 720);
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/Toronto2048wide.jpg"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(texture);
		sprite.setOrigin(0, 0);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Of course, the entire point of the Camera classes is so you don’t
		// have to worry about this stuff, so if you find it confusing, don’t
		// sweat it, LibGDX takes care of the math for you.
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
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
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

}
