package com.digitalmango.wildman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.digitalmango.wildman.screens.GameplayScreen;
import com.digitalmango.wildman.tools.AssetLoader;
import com.digitalmango.wildman.tools.Hud;

public class MainGame extends Game {

	public static int POINTS = 0;
	public static boolean EN_PARTIDA = false;

	//Resoluciones

	public static final int SCREEN_WIDTH = 240;
	public static final int SCREEN_HEIGHT = 400;

	//Volumenes
	public static float SFX_VOL = 1f;
	public static float MUSIC_VOL = 0.3f;
	public static float UI_VOL = 1f;

	//Gestión de los assets
	public AssetLoader assetLoader;
	private TextureAtlas assetAtlas;
	public BitmapFont pixelFont;

	//Gestión de pantalla
	public GameplayScreen gameplayScreen;

	public Hud hud;

	@Override
	public void create () {

		//Assets
		assetLoader = new AssetLoader();
		assetAtlas = assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class);
		pixelFont = new BitmapFont(Gdx.files.internal("font/pixel_big_font.fnt"),false);

		loadScreens();

		this.setScreen(gameplayScreen);

	}
	
	@Override
	public void dispose () {
		assetLoader.getAssetManager().dispose();
		pixelFont.dispose();
	}

	//Métodos

	public TextureAtlas getAssetAtlas(){
		return assetAtlas;
	}

	//Carga de recursos

	private void loadScreens() {

		hud = new Hud(this);
		gameplayScreen = new GameplayScreen(this, this);
	}

	public void gameOver(){
		Gdx.app.log("Game over","");
		MainGame.EN_PARTIDA = false;
	}
}
