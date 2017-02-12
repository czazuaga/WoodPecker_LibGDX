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
        //assetManager.load("audio/music/heroic_hearth_theme.ogg",Music.class);

        //sounds
        //assetManager.load("audio/sounds/get_coin.ogg",Sound.class);

        //Atlas
        assetManager.load("atlas/game_assets.pack",TextureAtlas.class);


        //Img
        //assetManager.load("img/tittle.png",Texture.class);

        assetManager.finishLoading();

    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

}
