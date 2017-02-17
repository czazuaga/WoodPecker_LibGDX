package com.digitalmango.troubleintheforest.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Carlos Zamora on 06/02/2017.
 */
public class TroncoRamaLeft extends TroncoAbstracto {

    private com.digitalmango.troubleintheforest.MainGame mainGame;

    public TroncoRamaLeft(com.digitalmango.troubleintheforest.MainGame mainGame, int id) {
        super(mainGame,id);
        super.tipo = 1;
        this.mainGame = mainGame;

        sprite = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("tronco_rama_left")),0,0,240,64);
        sprite.setPosition(0, com.digitalmango.troubleintheforest.MainGame.SCREEN_HEIGHT - 64);

    }

    @Override
    public void update(float delta) {

    }

}
