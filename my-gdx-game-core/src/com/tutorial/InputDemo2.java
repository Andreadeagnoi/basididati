package com.tutorial;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//how to manage keyboard and mouse input with an event driven approach
public class InputDemo2 implements ApplicationListener, InputProcessor{

	private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private float posX, posY;
    
	@Override
	public void create() {
		 float w = Gdx.graphics.getWidth();
	        float h = Gdx.graphics.getHeight();
	        batch = new SpriteBatch();
	        
	        texture = new Texture(Gdx.files.internal("data/aereo.png"));
	        sprite = new Sprite(texture);
	        posX = w/2 - sprite.getWidth()/2;
	        posY = h/2 - sprite.getHeight()/2;
	        sprite.setPosition(posX,posY);
	        
	        Gdx.input.setInputProcessor(this);
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sprite.setPosition(posX,posY);
        batch.begin();
        sprite.draw(batch);
        batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	// Another thing you might notice is that you have to hit the key multiple
	// times to move. This is because a key press generates only a single event
	// ( as does it’s release ). If you want to have the old behavior that
	// holding down the key will move the character continously, you will need
	// to implement the logic yourself. Once again, this can simply be done by
	// setting a flag in your code on keyDown and toggle it when the keyUp event
	// is called.
	@Override
	public boolean keyDown(int keycode) {
		float moveAmount = 1.0f;
		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT))
            moveAmount = 10.0f;
        
        if(keycode == Keys.LEFT)
            posX-=moveAmount;
        if(keycode == Keys.RIGHT)
            posX+=moveAmount;
        return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    //To drag a sprite, set a flag on touch down, and move it in mouseMoved
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Buttons.LEFT){
			posX = screenX - sprite.getWidth()/2;
			posY = Gdx.graphics.getHeight() - screenY - sprite.getHeight()/2;
		}
		if(button == Buttons.RIGHT){
			posX = Gdx.graphics.getWidth()/2 - sprite.getWidth()/2;
			posY = Gdx.graphics.getHeight()/2 - sprite.getHeight()/2;
		}
		return false;
	}

	

}
