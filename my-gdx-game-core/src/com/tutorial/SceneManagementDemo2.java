package com.tutorial;

import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


public class SceneManagementDemo2 implements ApplicationListener{
	// One important thing to realize is hit() is evaluated in reverse order
	// objects are created. So, the last object you add to the stage will be the
	// first one hit() if more than one object occupy the same space. The
	// determining function whether a Jet has been touched or not is hit().
	// hit() works by creating a bounding circle to fit the image within, then
	// checking to see if the mouse pointer is within the bounds of that circle.
	// When a hit occurs, we return the object hit, otherwise we return null.
	//
	// The nice thing about this system is the user doesn’t have to worry about
	// any translations applied to it’s parent or other translations that have
	// occurred. It’s also important to realize this is a pretty derived
	// example. If you removed the overriden hit() method, the default
	// implementation would actually work better. You do NOT need to provide a
	// hit() method in your derived Actor classes unless the default doesn’t fit
	// your needs. This example is merely to show how the Scene2D hit detection
	// works, and how to implement a custom detector. Should you wish to say,
	// implement pixel-perfect detection, you could do it this way. I commented
	// this example a bit more than I regularly do, to explain the bits I’ve
	// glossed over.
	class Jet extends Actor {
		private TextureRegion _texture;
		
		public Jet(TextureRegion texture){
			_texture = texture;
			setBounds(getX(),getY(),_texture.getRegionWidth(),_texture.getRegionHeight());
			
			this.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons){
					System.out.println("Touched"+ getName());
					setVisible(false);
					return true;
					
				}
			});
		}
		
		 // Implement the full form of draw() so we can handle rotation and scaling.
        public void draw(Batch batch, float alpha){
            batch.draw(_texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                    getScaleX(), getScaleY(), getRotation());
        }
        
        //This hit() instead of checking against a bounding box, checks a bounding circle.
        public Actor hit(float x, float y, boolean touchable){
        	//If this Actor is hidden or untouchable, it can't be hit
        	if(!this.isVisible()||this.getTouchable() == Touchable.disabled){
        		return null;
        	}
        	
        	//get centerpoint of bounding circle, also known as the center of the rect
        	float centerX = getWidth()/2;
        	float centerY = getHeight()/2;
        	// Square roots are bad m'kay. In "real" code, simply square both sides for much speedy fastness
            // This however is the proper, unoptimized and easiest to grok equation for a hit within a circle
            // You could of course use LibGDX's Circle class instead.
        	
        	// Calculate radius of circle
            float radius = (float) Math.sqrt(centerX * centerX +
                    centerY * centerY);

            // And distance of point from the center of the circle
            float distance = (float) Math.sqrt(((centerX - x) * (centerX - x)) 
                    + ((centerY - y) * (centerY - y)));
            
            // If the distance is less than the circle radius, it's a hit
            if(distance <= radius) return this;
            
            // Otherwise, it isn't
            return null;
        }
	}
	
	private Jet[] jets;
	private Stage stage;
	
	@Override
	public void create() {
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
		final TextureRegion jetTexture = new TextureRegion(new Texture("data/jet.png"));
		jets = new Jet[10];
		
		//create/seed our random number for positioning jets randomly
		Random random = new Random();
		
		//Create 10 Jet objects at random on screen locations
		for(int i=0; i < 10; i++){
			jets[i] = new Jet(jetTexture);
			
			//Assign the position of the jet to a random value within the screen boundaries
			jets[i].setPosition(random.nextInt(Gdx.graphics.getWidth() - (int)jets[i].getWidth())
					,  random.nextInt(Gdx.graphics.getHeight() - (int)jets[i].getHeight()));
			//set the name of the Jet to its index within the loop
			jets[i].setName(Integer.toString(i));
			
			//Add them to the stage
			stage.addActor(jets[i]);
		}
		
		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
		stage.dispose();
	}

}
