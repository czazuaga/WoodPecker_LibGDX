package com.digitalmango.wildman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.digitalmango.wildman.screens.GameplayScreen;
import com.digitalmango.wildman.screens.SplashScreen;
import com.digitalmango.wildman.tools.AssetLoader;
import com.digitalmango.wildman.tools.Hud;

public class MainGame extends Game {

	public static boolean MOSTRAR_BARRA = false;
	public static float TIEMPO = 8;
	public static final int BEST = 0;
	public static int POINTS = 0;
	public static int NUMERO_PARTIDAS = 0;
	public static boolean EN_PARTIDA = false;

	//Resoluciones

	public static final int SCREEN_WIDTH = 240;
	public static final int SCREEN_HEIGHT = 400;

	//Volumenes
	public static float SFX_VOL = 1f;
	public static float MUSIC_VOL = 0.8f;

	//Gestión de los assets
	public AssetLoader assetLoader;
	private TextureAtlas assetAtlas;
	public BitmapFont pixelFont;

	//Gestión de pantalla
	public GameplayScreen gameplayScreen;
	public SplashScreen splashScreen;

	public Hud hud;
	private Music levelMusic, gameOverMusic;

	@Override
	public void create () {

		//Assets
		assetLoader = new AssetLoader();
		assetAtlas = assetLoader.getAssetManager().get("atlas/game_assets.pack",TextureAtlas.class);
		pixelFont = new BitmapFont(Gdx.files.internal("font/pixel_big_font.fnt"),false);

		//Musica
		levelMusic = assetLoader.getAssetManager().get("sound/level.ogg",Music.class);
		levelMusic.setLooping(true);
		levelMusic.setVolume(MUSIC_VOL);

		gameOverMusic = assetLoader.getAssetManager().get("sound/gameover.ogg",Music.class);
		gameOverMusic.setLooping(true);
		gameOverMusic.setVolume(MUSIC_VOL);

		loadScreens();

		this.setScreen(splashScreen);

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
		splashScreen = new SplashScreen(this, this);
	}

	public void nuevaPartida(){
		MainGame.POINTS = 0;
		MainGame.TIEMPO = 8;
		MainGame.EN_PARTIDA = true;
		gameplayScreen.nuevaPartida();
		gameplayScreen.pajaro.isDEAD = false;
		reproducirMusica(0);
	}

	public void gameOver(){
		Gdx.app.log("Game over","");
		MainGame.EN_PARTIDA = false;
		silenciarMusicas();
		hud.menuCreator.contarGameover = true;

	}

	public void reproducirMusica(int pista){
		if(pista == 0){
			if(MainGame.NUMERO_PARTIDAS > 1){
				if (gameOverMusic.isPlaying()){
					gameOverMusic.stop();
				}
				levelMusic.stop();
				levelMusic.setLooping(true);
				levelMusic.setVolume(MainGame.MUSIC_VOL);
				levelMusic.play();
			}

		}else{
			if (levelMusic.isPlaying()){
				levelMusic.stop();
			}
			gameOverMusic.stop();
			gameOverMusic.setLooping(false);
			gameOverMusic.setVolume(MainGame.MUSIC_VOL);
			gameOverMusic.play();
		}
	}

	public void silenciarMusicas(){
		if(levelMusic.isPlaying()){
			levelMusic.stop();
		}else if(gameOverMusic.isPlaying()){
			gameOverMusic.stop();
		}

	}

	public void comenzar() {
		levelMusic.setLooping(true);
		levelMusic.setVolume(MainGame.MUSIC_VOL);
		levelMusic.play();
	}
}
