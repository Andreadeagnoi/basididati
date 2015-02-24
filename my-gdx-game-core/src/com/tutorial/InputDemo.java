package com.tutorial;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// This example will demonstrate how to manage keyboard and mouse input with
// polling. Polling = check every frame if a button was pressed.
public class InputDemo implements ApplicationListener {

	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/aereo.png"));
		sprite = new Sprite(texture);
		sprite.setPosition(w / 2 - sprite.getWidth() / 2,
				h / 2 - sprite.getHeight() / 2);

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// keyboard polling
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
				sprite.translateX(-1f);
			else
				sprite.translateX(-10.0f);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
				sprite.translateX(1f);
			else
				sprite.translateX(10.0f);
		}
		// mouse polling
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			sprite.setPosition(
					Gdx.input.getX() - sprite.getWidth() / 2,
					Gdx.graphics.getHeight() - Gdx.input.getY()
							- sprite.getHeight() / 2);
		}
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
			sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth()
					/ 2, Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);
		}
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
