package com.tutorial;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GraphicsDemoTextureAtlas implements ApplicationListener{

	private SpriteBatch batch;
	private TextureAtlas textureAtlas;
	private Sprite sprite;
	private int currentFrame = 1;
	private String currentAtlasKey = new String("001");

	// Just like working with a texture, but instead you load a TextureAtlas.
	// Then instead of assign the texture to the sprite, you use an AtlasRegion,
	// which describes the coordinates of the individual sprite within the
	// spritesheet. You get the region by name by calling the findRegion()
	// method and passing the key. Remember this value is set by the file names
	// of the source images. The TextureAtlas needs to be dispose()’d or you
	// will leak memory.
	@Override
	public void create() {
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas(Gdx.files.internal("data/jetTiles.atlas"));
		AtlasRegion region = textureAtlas.findRegion("001");
		sprite = new Sprite(region);
		sprite.setPosition(120, 100);
		sprite.scale(2.5f);
		Timer.schedule(new Task(){
            @Override
            public void run() {
                currentFrame++;
                if(currentFrame > 6)
                    currentFrame = 1;
                
                // ATTENTION! String.format() doesnt work under GWT for god knows why...
                currentAtlasKey = String.format("%03d", currentFrame);
                sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
            }
        }
        ,0,5/30.0f);

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        textureAtlas.dispose();
	}
	
}
