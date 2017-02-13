package com.digitalmango.wildman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.digitalmango.wildman.MainGame;

/**
 * Created by Carlos Zamora on 13/02/2017.
 */

public class FX {

    public Sprite sprite;
    private MainGame mainGame;

    //Logic
    float count = 0;
    int timer = 0;
    boolean activate;

    public FX(MainGame mainGame){
        this.mainGame = mainGame;
        sprite = new Sprite();
        sprite.setBounds(0,0,MainGame.SCREEN_WIDTH,MainGame.SCREEN_HEIGHT);
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
