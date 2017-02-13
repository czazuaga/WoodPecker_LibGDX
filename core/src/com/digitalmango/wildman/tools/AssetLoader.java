package com.digitalmango.wildman.tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Carlos Zamora on 04/10/2016.
 */
public class AssetLoader {

    private AssetManager assetManager;

    public AssetLoader(){
        //Cargar assets
        assetManager = new AssetManager();

        //music
        assetManager.load("sound/level.ogg",Music.class);
        assetManager.load("sound/gameover.ogg",Music.class);

        //sounds
        assetManager.load("sound/hit.ogg",Sound.class);
        assetManager.load("sound/death.ogg",Sound.class);
        assetManager.load("sound/laser.ogg",Sound.class);
        assetManager.load("sound/rise.ogg",Sound.class);



        //Atlas
        assetManager.load("atlas/game_assets.pack",TextureAtlas.class);


        //Finish loading
        assetManager.finishLoading();

    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

}
