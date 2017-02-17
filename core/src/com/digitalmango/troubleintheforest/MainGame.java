package com.digitalmango.troubleintheforest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.digitalmango.troubleintheforest.GooglePlay.PlayServices;
import com.digitalmango.troubleintheforest.screens.GameplayScreen;

public class MainGame extends Game {

	public static long BEST_POINTS = 0;
	public static long BEST_POINTS_GETED = 0;
	public static boolean NEW_RECORD = false;
	public static boolean MOSTRAR_BARRA = false;
	public static float TIEMPO = 8;
	public static int POINTS = 0;
	public static int NUMERO_PARTIDAS = 0;
	public static boolean EN_PARTIDA = false;
	public static boolean EN_PAUSA = false;

	//Resoluciones

	public static final int SCREEN_WIDTH = 240;
	public static final int SCREEN_HEIGHT = 400;

	//Volumenes
	public static float SFX_VOL = 1f;
	public static float MUSIC_VOL = 0.8f;
	public static boolean IN_DESKTOP = false;

	//Gestión de los assets
	public com.digitalmango.troubleintheforest.tools.AssetLoader assetLoader;
	private TextureAtlas assetAtlas;
	public BitmapFont pixelFont;

	//Gestión de pantalla
	public GameplayScreen gameplayScreen;
	public com.digitalmango.troubleintheforest.screens.SplashScreen splashScreen;

	public com.digitalmango.troubleintheforest.tools.Hud hud;
	public Music levelMusic, gameOverMusic;
	public static PlayServices playServices;

	public MainGame(PlayServices playServices){

		this.playServices = playServices;
	}

	public MainGame() {
		IN_DESKTOP = true;
	}

	@Override
	public void create () {

		//Assets
		assetLoader = new com.digitalmango.troubleintheforest.tools.AssetLoader();
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

		hud = new com.digitalmango.troubleintheforest.tools.Hud(this);
		gameplayScreen = new GameplayScreen(this, this);
		splashScreen = new com.digitalmango.troubleintheforest.screens.SplashScreen(this, this);
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

	//Gestion score

	public static void loadBestScore() {
			playServices.getUserScore();
	}

	public static void submitBestScore(){
		if(!MainGame.IN_DESKTOP){
			playServices.submitScore((int) MainGame.BEST_POINTS);
			checkLogros();
		}

	}

	private static void checkLogros() {
		if(MainGame.BEST_POINTS > 100){
			playServices.unlockAchievement(1);
		}

		if(MainGame.BEST_POINTS > 300){
			playServices.unlockAchievement(2);
		}

		if(MainGame.BEST_POINTS > 500){
			playServices.unlockAchievement(3);
		}

		if(MainGame.BEST_POINTS > 1000){
			playServices.unlockAchievement(4);
		}

		if(MainGame.BEST_POINTS > 5000){
			playServices.unlockAchievement(5);
		}
	}

	public void setNewScore(int score){
		if(score > MainGame.BEST_POINTS){
			MainGame.BEST_POINTS = MainGame.POINTS;
			MainGame.NEW_RECORD = true;
			MainGame.submitBestScore();
		}
	}



}
