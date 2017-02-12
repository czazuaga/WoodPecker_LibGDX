package com.digitalmango.wildman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.digitalmango.wildman.MainGame;

/**
 * Created by Carlos Zamora on 06/02/2017.
 */
public class TroncoRamaRight extends TroncoAbstracto {

    private MainGame mainGame;

    public TroncoRamaRight(MainGame mainGame, int id) {
        super(mainGame,id);
        super.tipo = 2;
        this.mainGame = mainGame;

        sprite = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("tronco_rama_right")),0,0,240,64);
        sprite.setPosition(0, MainGame.SCREEN_HEIGHT - 64);

    }

    @Override
    public void update(float delta) {

    }

}
