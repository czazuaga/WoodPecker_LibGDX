package com.digitalmango.wildman.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.digitalmango.wildman.MainGame;

/**
 * Created by Carlos Zamora on 12/02/2017.
 */

public class DefinicionPajaro {

    Animation attackAnimation, idleAnimation;
    Sprite sprite;
    TextureRegion currentFrame;

    public DefinicionPajaro(MainGame mainGame){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 5; i++){
            if(i!= 3){
                frames.add(new TextureRegion(mainGame.getAssetAtlas().findRegion("player_attack" + i), 0 , 0, 96, 96));
            }
        }
        attackAnimation = new Animation(0.03f,frames);
        frames.clear();


        for(int i = 1; i < 3; i++){
            frames.add(new TextureRegion(mainGame.getAssetAtlas().findRegion("player_idle" + i), 0 , 0, 96, 96));
        }
        idleAnimation = new Animation(0.7f,frames);
        frames.clear();

        sprite = new Sprite();


    }

}
