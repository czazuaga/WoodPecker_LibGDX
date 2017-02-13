package com.digitalmango.wildman.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.digitalmango.wildman.MainGame;
import com.digitalmango.wildman.tools.BaseScreen;


/**
 * Created by Carlos Zamora on 06/11/2016.
 */
public class SplashScreen extends BaseScreen {

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public StretchViewport viewport;

    private MainGame mainGame;
    private Sprite logoMango, blackBit;
    private Sound sound;

    private boolean sonar = true;

    private float count = 0;
    private float fadeCounter = 0;

    public SplashScreen(Game game, MainGame mainGame) {
        super(game);
        this.mainGame = mainGame;

        batch = new SpriteBatch();

        camera = new OrthographicCamera(MainGame.SCREEN_WIDTH, MainGame.SCREEN_HEIGHT);
        camera.setToOrtho(false, MainGame.SCREEN_WIDTH, MainGame.SCREEN_HEIGHT);

        viewport = new StretchViewport(MainGame.SCREEN_WIDTH, MainGame.SCREEN_HEIGHT, camera);
        viewport.apply();

        logoMango = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("splash")),0,0,240,400);
        logoMango.setBounds(0,0,240,400);
        blackBit = new Sprite(new TextureRegion(mainGame.assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class).findRegion("black")),0,0,4,4);
        blackBit.setBounds(0,0,240,400);
        blackBit.setAlpha(0);

        sound = mainGame.assetLoader.getAssetManager().get("sound/rise.ogg",Sound.class);
    }

    //Screen interface

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(mainGame.hud.camera.combined);

        count = count + 1 * delta;
        updateFadeIn(delta);

        if(count > 2.0f){
            mainGame.setScreen(mainGame.gameplayScreen);
            mainGame.comenzar();
        }else if(sonar && count > 0.5f){
            sound.play(MainGame.SFX_VOL);
            sonar = false;
        }

        batch.begin();
        logoMango.draw(batch);
        blackBit.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height)
    {
        mainGame.hud.viewport.update(width, height);
        mainGame.hud.stage.getViewport().update(width, height);
    }


    @Override
    public void dispose() {
        batch.dispose();
    }

    private void updateFadeIn(float delta){

        if(count > 1f && count < 2f){
            fadeCounter = fadeCounter + 1f * delta;
            blackBit.setAlpha(fadeCounter);
        }else if (count > 1.9f){
            blackBit.setAlpha(1);
        }
    }
}
