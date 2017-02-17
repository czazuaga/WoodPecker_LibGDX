package com.digitalmango.troubleintheforest.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.digitalmango.troubleintheforest.MainGame;

/**
 * Created by Carlos Zamora on 06/02/2017.
 */
public class Tronco extends TroncoAbstracto {

    private MainGame mainGame;

    public Tronco(MainGame mainGame, int id) {
        super(mainGame, id);
        super.tipo = 0;
        this.mainGame = mainGame;

        sprite = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("tronco")),0,0,240,64);
        sprite.setPosition(0, MainGame.SCREEN_HEIGHT - 64);
    }

    @Override
    public void update(float delta) {

    }



}
