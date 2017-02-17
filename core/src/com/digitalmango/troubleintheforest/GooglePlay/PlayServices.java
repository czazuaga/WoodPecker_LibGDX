package com.digitalmango.troubleintheforest.GooglePlay;

import java.io.IOException;

/**
 * Created by Carlos Zamora on 13/02/2017.
 */

public interface PlayServices {
    public void signIn();
    public void signOut();
    public void rateGame();
    public void unlockAchievement(int logro);
    public void submitScore(int highScore);
    public void showAchievement();
    public void showScore();
    public void getUserScore();
    public boolean isSignedIn();
}
