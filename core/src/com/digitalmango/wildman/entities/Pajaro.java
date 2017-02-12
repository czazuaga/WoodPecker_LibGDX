package com.digitalmango.wildman.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.digitalmango.wildman.MainGame;

/**
 * Created by Carlos Zamora on 12/02/2017.
 */

public class Pajaro {

    public DefinicionPajaro definicionPajaro;
    private MainGame mainGame;

    //Animations
    public enum State {IDLE, ATTACKING;}
    public State currentState;
    public State previousState;

    public float stateTimer;

    //Logic
    boolean isAtacking = false;
    public int posicion = 0;

    public Pajaro(MainGame mainGame){
        this.mainGame = mainGame;

        definicionPajaro = new DefinicionPajaro(mainGame);

        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;

        definicionPajaro.sprite.setBounds(0,58,96,96);
        definicionPajaro.currentFrame = new TextureRegion(mainGame.getAssetAtlas().findRegion("player_idle1"), 0 , 0, 96, 96);
        definicionPajaro.sprite.setRegion(definicionPajaro.currentFrame);
        definicionPajaro.sprite.setFlip(false, false);

    }

    public void update(float delta, Batch batch){

        definicionPajaro.currentFrame = getFrame(delta);
        definicionPajaro.sprite.setRegion(definicionPajaro.currentFrame);
        definicionPajaro.sprite.draw(batch);
    }

    //Logic

    public void atacar(int direction) {

        cambiarPosicion(direction);
        stateTimer = 0;
        isAtacking = true;
    }

    public void cambiarPosicion(int direction){
        if(direction == 1){
            definicionPajaro.sprite.setPosition(0,58);
            posicion = 0;
        }else{
            definicionPajaro.sprite.setPosition(144,58);
            posicion = 1;
        }
    }

    //PLAYER STATE MANAGER

    public TextureRegion getFrame(float delta) {
        currentState = getState();

        TextureRegion textureRegion;
        switch (currentState) {

            case ATTACKING:
                textureRegion = (TextureRegion) definicionPajaro.attackAnimation.getKeyFrame(stateTimer, false);
                break;

            case IDLE:
                textureRegion = (TextureRegion) definicionPajaro.idleAnimation.getKeyFrame(stateTimer, true);
                break;

            default:
                textureRegion = (TextureRegion) definicionPajaro.idleAnimation.getKeyFrame(stateTimer, true);

                break;
        }

        if(textureRegion.isFlipX() && posicion == 0){
            textureRegion.flip(true,false);
        }else if (!textureRegion.isFlipX() && posicion == 1) {
            textureRegion.flip(true,false);
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;

        return textureRegion;

    }

    public State getState() {

        if(isAtacking){
            if(definicionPajaro.attackAnimation.isAnimationFinished(stateTimer)){
                isAtacking = false;
                return State.IDLE;
            }

        return  State.ATTACKING;
        }

        return State.IDLE;

    }

}
