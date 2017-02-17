package com.digitalmango.troubleintheforest.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.digitalmango.troubleintheforest.MainGame;

/**
 * Created by Carlos Zamora on 13/02/2017.
 */

public class Barra {
    public Sprite sprite;
    private MainGame mainGame;
    private TextureRegion[] regions;

    public Barra(MainGame mainGame){
        this.mainGame = mainGame;

        regions = new TextureRegion[17];
        for(int i = 1; i < 17; i++){
            regions[i] = new TextureRegion(mainGame.assetLoader.getAssetManager()
                    .get("atlas/game_assets.pack",TextureAtlas.class).findRegion("bar" + i));
        }

        sprite = new Sprite();
        sprite.setBounds(57,20,126,16);
        sprite.setRegion(regions[1],0,0,63,8);
    }

    public void update(float delta){

        actualizarBarra();

        if(MainGame.EN_PARTIDA && !MainGame.EN_PAUSA && !mainGame.gameplayScreen.tapTap.isAlive){
            MainGame.TIEMPO = MainGame.TIEMPO - 2.8f * delta;
        }

        if(MainGame.MOSTRAR_BARRA && !mainGame.gameplayScreen.tapTap.isAlive){
            sprite.setAlpha(1);
        }else{
            sprite.setAlpha(0);
        }

            if (MainGame.TIEMPO < 0f && MainGame.EN_PARTIDA){
                mainGame.gameOver();
                mainGame.gameplayScreen.pajaro.death();
            }

        }

    private void actualizarBarra() {
        if (MainGame.TIEMPO < 8f){
            sprite.setRegion(regions[1],0,0,63,8);
        }

        if (MainGame.TIEMPO < 7.5f){
            sprite.setRegion(regions[2],0,0,63,8);
        }

        if (MainGame.TIEMPO < 7.0f){
            sprite.setRegion(regions[3],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 6.5f){
            sprite.setRegion(regions[4],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 6.0f){
            sprite.setRegion(regions[5],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 5.5f){
            sprite.setRegion(regions[6],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 5.0f){
            sprite.setRegion(regions[7],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 4.5f){
            sprite.setRegion(regions[8],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 4.0f){
            sprite.setRegion(regions[9],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 3.5f){
            sprite.setRegion(regions[10],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 3.0f){
            sprite.setRegion(regions[11],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 2.5f){
            sprite.setRegion(regions[12],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 2.0f){
            sprite.setRegion(regions[13],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 1.5f){
            sprite.setRegion(regions[14],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 1.0f){
            sprite.setRegion(regions[15],0,0,63,8);
        }

        if (MainGame.TIEMPO <= 0.5f){
            sprite.setRegion(regions[16],0,0,63,8);
        }


        Gdx.app.log("time: " + MainGame.TIEMPO,"");
    }

}
