package com.tutorial;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class SceneManagementDemo implements ApplicationListener{

	private Stage stage;
	private Group group;
	
	@Override
	public void create() {
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
		final TextureRegion jetTexture = new TextureRegion(new Texture("data/jet.png"));
		final TextureRegion flameTexture = new TextureRegion(new Texture("data/flame.png"));
		
		final Actor jet = new Actor() {
			public void draw(Batch batch, float alpha){
				batch.draw(jetTexture, getX(), getY(), getOriginX(), getOriginY(), 
						getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
				
			}
		};
		jet.setBounds(jet.getX(), jet.getY(), jetTexture.getRegionWidth(), jetTexture.getRegionHeight());

        final Actor flame = new Actor(){
            public void draw(Batch batch, float alpha){
                batch.draw(flameTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                        getScaleX(), getScaleY(), getRotation());
            }
        };
        flame.setBounds(0, 0, flameTexture.getRegionWidth(), flameTexture.getRegionHeight());
        flame.setPosition(jet.getWidth()-25, 25);
		// we create a new Group object, this is the secret sauce behind Scene2D
		// grouping. Then instead of adding our two Actors to the Scene, we
		// instead add them to the Group, which is then added to the Scene. So
		// that we can actually see something happening in this example, we
		// apply a moveTo and rotateBy Action to our group.
        group = new Group();
        group.addActor(jet);
        group.addActor(flame);
        
        group.addAction(parallel(moveTo(200,0,5),rotateBy(90,5)));
        
        stage.addActor(group);
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
