package com.tutorial;
// So, what is scene2D? In a nutshell, it’s a 2D scene graph. So you might
// be asking “what’s a scene graph?”. Good Question! Essentially a scene
// graph is a data structure for storing the stuff in your world. So if your
// game world is composed of dozens or hundreds of sprites, those sprites
// are stored in the scene graph. In addition to holding the contents of
// your world, Scene2D provides a number of functions that it performs on
// that data. Things such as hit detection, creating hierarchies between
// game objects, routing input, creating actions for manipulating a node
// over time, etc.

// You can think of Scene2D as a higher level framework for creating a game
// built over top of the LibGDX library. Oh it also is used to provide a
// very good UI widget library… something we will discuss later.

// The object design of Scene2D is built around the metaphor of a play ( or
// at least I assume it is ). At the top of the hierarchy you have the
// stage. This is where your play (game) will take place. The Stage in turn
// contains a Viewport… think of this like, um… camera recording the play (
// or the view point of someone in the audience ). The next major
// abstraction is the Actor, which is what fills the stage with… stuff. This
// name is a bit misleading, as Actor doesn’t necessarily mean a visible
// actor on stage. Actors could also include the guy running the lighting, a
// piece of scenery on stage, etc. Basically actors are the stuff that make
// up your game. So basically, you split your game up into logical Scenes (
// be it screens, stages, levels, whatever makes sense ) composed of Actors.

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;


public class SceneDemo implements ApplicationListener{
	
	// We create an embedded Actor derived class named MyActor. MyActor simply
	// loads it’s own texture from file. The key part is the draw() method. This
	// will be called every frame by the stage containing the actor. It is here
	// you draw the actor to the stage using the provided Batch. Batch is the
	// interface that SpriteBatch we saw earlier implements and is responsible
	// for batching up drawing calls to OpenGL. In this example we simply draw
	// our Texture to the batch at the location 0,0. Your actor could just as
	// easily be programmatically generated, from a spritesheet, etc. One thing
	// I should point out here, this example is for brevity, in a real world
	// scenario you would want to manage things differently, as every MyActor
	// would leak it’s Texture when it is destroyed!
	public class MyActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("data/aereo.png"));
        float actorX = 0, actorY = 0;
        public boolean started = false;

		public MyActor() {
			// This call (setBounds) is very important! If you inherit from
			// Actor, you need to set the bounds or it will not be
			// click/touch-able!
			setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					((MyActor)event.getTarget()).started = true;
                    return true;
                }
            });
        }
        
        
        @Override
        public void draw(Batch batch, float alpha){
            batch.draw(texture,actorX,actorY);
        }
        
		// Just like with draw(), there is an act() method that is called on
		// every actor on the stage. This is where you will update your actor
		// over time. In many other frameworks, act would instead be called
		// update(). In this case we simply add 5 pixels to the X location of
		// our MyActor each frame. Of course, we only do this once the started
		// flag has been set.
        @Override
        public void act(float delta){
            if(started){
                actorX+=5;
            }
        }
    }

	private Stage stage;

	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
        
        MyActor myActor = new MyActor();
        myActor.setTouchable(Touchable.enabled);	
        stage.addActor(myActor);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// we now call stage.act() passing in the elapsed time since the
		// previous frame. This is is what causes the various actors to have
		// their act() function called.
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
