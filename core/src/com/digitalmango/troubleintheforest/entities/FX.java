package com.digitalmango.troubleintheforest.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Carlos Zamora on 13/02/2017.
 */

public class FX {

    public Sprite sprite;
    private com.digitalmango.troubleintheforest.MainGame mainGame;

    //Logic
    float count = 0;
    int timer = 0;
    boolean activate;

    public FX(com.digitalmango.troubleintheforest.MainGame mainGame){
        this.mainGame = mainGame;
        sprite = new Sprite();
        sprite.setBounds(0,0, com.digitalmango.troubleintheforest.MainGame.SCREEN_WIDTH, com.digitalmango.troubleintheforest.MainGame.SCREEN_HEIGHT);
        sprite.setRegion(new TextureRegion(mainGame.assetLoader.getAssetManager()
                .get("atlas/game_assets.pack",TextureAtlas.class).findRegion("fx")),0,0,199,271);
    }

    public void update(float delta){
        if(activate){
            sprite.setAlpha(1f);
            count = count + 1 * delta;


            if (count > 0.1f){
                sprite.setAlpha(0.6f);
            }

            if (count > 0.15f){
                count = 0f;
                timer = 0;
                activate = false;
            }

        }else{
            sprite.setAlpha(0f);
        }
    }
}
