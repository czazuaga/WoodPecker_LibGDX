package com.digitalmango.troubleintheforest.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Carlos Zamora on 16/02/2017.
 */

public class TapTap {

    Animation idleAnimation;
    Sprite sprite;
    TextureRegion currentFrame;
    public boolean isAlive = false;
    public float stateTimer = 0;

    public TapTap(com.digitalmango.troubleintheforest.MainGame mainGame){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 3; i++){
            frames.add(new TextureRegion(mainGame.getAssetAtlas().findRegion("tap" + i), 0 , 0, 240, 400));
        }
        idleAnimation = new Animation(0.3f,frames);
        frames.clear();

        sprite = new Sprite();
        sprite.setBounds(0, 0, 240, 400);

    }

    public void update(float delta, Batch batch){
        stateTimer+= delta;
        currentFrame = (TextureRegion) idleAnimation.getKeyFrame(stateTimer, true);
        sprite.setRegion(currentFrame);
        sprite.draw(batch);
    }



}
