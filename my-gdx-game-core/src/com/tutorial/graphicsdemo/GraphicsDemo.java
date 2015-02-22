package com.tutorial.graphicsdemo;

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
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/aereo.png"));
		sprite = new Sprite(texture);
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
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
