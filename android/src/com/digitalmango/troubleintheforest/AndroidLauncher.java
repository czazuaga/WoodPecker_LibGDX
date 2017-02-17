package com.digitalmango.troubleintheforest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.digitalmango.troubleintheforest.GooglePlay.PlayServices;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.example.games.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements PlayServices {

	private static final String APP_PNAME = "com.digitalmango.troubleintheforest";
	private GameHelper gameHelper;
	private final static int requestCode = 1;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new com.digitalmango.troubleintheforest.MainGame(this), config);

		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(false);

		GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener()
		{
			@Override
			public void onSignInFailed(){ }

			@Override
			public void onSignInSucceeded(){

			}
		};

		gameHelper.setup(gameHelperListener);

	}

	@Override
	protected void onStart()
	{
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void signIn()
	{
		try
		{
			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		}
		catch (Exception e)
		{
			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void signOut()
	{
		try
		{
			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					gameHelper.signOut();
				}
			});
		}
		catch (Exception e)
		{
			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void rateGame()
	{
		String str = "market://details?id=" + APP_PNAME;
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override
	public void unlockAchievement(int logro)
	{
		if(isSignedIn()){
			switch (logro){
				case 1 : {
					Games.Achievements.unlock(gameHelper.getApiClient(),
							getString(R.string.achievement_baby_woodpecker));
					break;
				}
				case 2 : {
					Games.Achievements.unlock(gameHelper.getApiClient(),
							getString(R.string.achievement_little_woodpecker));
					break;
				}
				case 3 : {
					Games.Achievements.unlock(gameHelper.getApiClient(),
							getString(R.string.achievement_daddy_woodpecker));
					break;
				}
				case 4 : {
					Games.Achievements.unlock(gameHelper.getApiClient(),
							getString(R.string.achievement_crazy_woodpecker));
					break;
				}
				case 5 : {
					Games.Achievements.unlock(gameHelper.getApiClient(),
							getString(R.string.achievement_monster_woodpecker));
					break;
				}
			}
		}

	}

	@Override
	public void submitScore(int highScore)
	{
		if (isSignedIn() == true)
		{
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_ranking), highScore);
		}
	}

	@Override
	public void showAchievement()
	{
		if (isSignedIn() == true)
		{
			startActivityForResult(Games.Achievements.getAchievementsIntent(
					gameHelper.getApiClient()), 1);
		}
		else
		{
			signIn();
		}
	}

	@Override
	public void showScore()
	{
		if (isSignedIn() == true)
		{
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
					getString(R.string.leaderboard_ranking)), requestCode);
		}
		else
		{
			signIn();
		}
	}

	//Obtener puntuacion del ranking

	@Override
	public void getUserScore() {
		loadScoreOfLeaderBoard();
	}

	private void loadScoreOfLeaderBoard() {
		Games.Leaderboards.loadCurrentPlayerLeaderboardScore(gameHelper.getApiClient(), getString(R.string.leaderboard_ranking)
				, LeaderboardVariant.TIME_SPAN_ALL_TIME, LeaderboardVariant.COLLECTION_PUBLIC)
				.setResultCallback(new ResultCallback<Leaderboards.LoadPlayerScoreResult>() {
			@Override
			public void onResult(final Leaderboards.LoadPlayerScoreResult scoreResult) {
				if (isScoreResultValid(scoreResult)) {
					// here you can get the score like this
					MainGame.BEST_POINTS_GETED = scoreResult.getScore().getRawScore();
					MainGame.BEST_POINTS = MainGame.BEST_POINTS_GETED;
				}
			}
		});
	}

	private boolean isScoreResultValid(final Leaderboards.LoadPlayerScoreResult scoreResult) {
		return scoreResult != null && GamesStatusCodes.STATUS_OK == scoreResult.getStatus().getStatusCode() && scoreResult.getScore() != null;
	}

	//---------------------------------------------------

	@Override
	public boolean isSignedIn()
	{
		MainGame.loadBestScore();
		return gameHelper.isSignedIn();
	}


	@Override
	protected void onDestroy() {
		signOut();
	}
}
