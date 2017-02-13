package com.digitalmango.wildman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.digitalmango.wildman.MainGame;

/**
 * Created by Carlos Zamora on 13/02/2017.
 */

public class Barra {
    public Sprite sprite;
    private MainGame mainGame;

    //Logic
    float count = 8;
    int timer = 0;

    public Barra(MainGame mainGame){
        this.mainGame = mainGame;
        sprite = new Sprite();
        sprite.setBounds(57,20,126,16);
        sprite.setRegion(new TextureRegion(mainGame.assetLoader.getAssetManager()
                .get("atlas/game_assets.pack",TextureAtlas.class).findRegion("bar1")),0,0,63,8);
    }

    public void update(float delta){
        count = count - 1 * delta;

        actualizarBarra();

            if (count > 0.15f){
                count = 8f;
                timer = 8;
            }

        }

    private void actualizarBarra() {
        if (count < 8f && count >= 7.5f){

        }

        if (count < 7.5f && count >= 7.0f){

        }

        if (count < 7.0f && count >= 6.5f){

        }

        if (count < 6.5f && count >= 6.0f){

        }

        if (count < 6.0f && count >= 5.5f){

        }

        if (count < 5.5f && count >= 4.0f){

        }

        if (count < 4.0f && count >= 3.5f){

        }

        if (count < 3.5f && count >= 2.0f){

        }

        if (count < 2.0f && count >= 1.5f){

        }

        if (count < 1.5f && count >= 1.0f){

        }

        if (count < 1.0f && count >= 0.5f){

        }

        if (count < 0.5f && count >= 0.0f){

        }
    }

}
