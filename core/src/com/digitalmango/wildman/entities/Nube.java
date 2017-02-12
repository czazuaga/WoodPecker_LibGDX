package com.digitalmango.wildman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.digitalmango.wildman.MainGame;

import java.util.Random;

/**
 * Created by Carlos Zamora on 12/02/2017.
 */

public class Nube {

    public Sprite sprite;
    public boolean isAlive = false;
    private float speed;

    public Nube(MainGame mainGame){
        sprite = new Sprite();
        int r = randomizarEnRangoInt(0,3);
        int x = randomizarEnRangoInt(MainGame.SCREEN_WIDTH, MainGame.SCREEN_WIDTH * 4);
        int y = randomizarEnRangoInt(254, MainGame.SCREEN_HEIGHT - 64);
        speed = randomizarEnRango(-0.6f, -0.1f);
        switch (r){
            case 0 : {
                sprite.setBounds(x,y,64,32);
                sprite.setRegion(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("cloud1")),0,0,64,32);
                break;
            }
            case 2 : {

                sprite.setBounds(x,y,32,16);
                sprite.setRegion(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("cloud2")),0,0,32,16);
                break;
            }
            case 1 : {

                sprite.setBounds(x,y,128,64);
                sprite.setRegion(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("cloud3")),0,0,128,64);
                break;
            }
        }

        isAlive = true;
    }

    public void update(){
        if(isAlive){
            sprite.translateX(speed);
            if (sprite.getX() < -MainGame.SCREEN_WIDTH){
                sprite.setPosition(randomizarEnRangoInt(MainGame.SCREEN_WIDTH, MainGame.SCREEN_WIDTH * 2),randomizarEnRangoInt(254, MainGame.SCREEN_HEIGHT - 64));
            }
        }
    }

    //Randoms
    public int randomizarEnRangoInt (int min, int max){
        if (min >= max) {
            throw new IllegalArgumentException("Max debería ser mayor que Min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public float randomizarEnRango (float min, float max){
        if (min >= max) {
            throw new IllegalArgumentException("Max debería ser mayor que Min");
        }

        return MathUtils.random(min, max);
    }

}
