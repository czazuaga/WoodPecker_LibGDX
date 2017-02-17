package com.digitalmango.troubleintheforest.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Carlos Zamora on 13/02/2017.
 */

public class Flor {

    //Animations
    public enum State {IDLE, HAPPY;}
    public State currentState;
    public State previousState;

    public float stateTimer;

    Animation happyAnimation, idleAnimation;
    Sprite sprite;
    TextureRegion currentFrame;

    public Flor(com.digitalmango.troubleintheforest.MainGame mainGame){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 5; i++){
                frames.add(new TextureRegion(mainGame.getAssetAtlas().findRegion("flower_happy" + i), 0 , 0, 48, 48));
        }
        happyAnimation = new Animation(0.15f,frames);
        frames.clear();


        for(int i = 1; i < 9; i++){
            frames.add(new TextureRegion(mainGame.getAssetAtlas().findRegion("flower_sleeping" + i), 0 , 0, 48, 48));
        }
        idleAnimation = new Animation(0.15f,frames);
        frames.clear();


        sprite = new Sprite();
        sprite.setBounds(97, 71, 48, 48);

    }

    public void update(float delta, Batch batch){
        currentFrame = getFrame(delta);
        sprite.setRegion(currentFrame);
        sprite.draw(batch);
    }

    public TextureRegion getFrame(float delta) {
        currentState = getState();

        TextureRegion textureRegion;
        switch (currentState) {

            case HAPPY:
                textureRegion = (TextureRegion) happyAnimation.getKeyFrame(stateTimer, true);
                break;

            case IDLE:
                textureRegion = (TextureRegion) idleAnimation.getKeyFrame(stateTimer, true);
                break;

            default:
                textureRegion = (TextureRegion) idleAnimation.getKeyFrame(stateTimer, true);

                break;
        }


        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;

        return textureRegion;
    }

    public State getState() {

        if(com.digitalmango.troubleintheforest.MainGame.EN_PARTIDA){
                return State.HAPPY;
        }

        return State.IDLE;

    }

}
