package com.example.auladesire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Debug;
import android.view.MotionEvent;

public class Player {

    private Bitmap bitmap;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 direction;
    private GameView gameView;
    private float speed;

    public Player(GameView gameView){
        this.gameView = gameView;
        position = new Vector2(75, 50);
        speed = 500;
        velocity = Vector2.ONE.clone().mul(speed);
        direction = velocity.clone().normalize();
        bitmap = BitmapFactory.decodeResource(gameView.getContext().getResources(), R.drawable.player);
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public int getX(){
        return (int)position.x;
    }

    public int getY(){
        return (int)position.y;
    }

    public float getRotation(){ return direction.getAngle(); }

    public void update(float delta){

        position.add(velocity.clone().mul(delta));

        //Parede Direita
        if(position.x + bitmap.getWidth()/2f > gameView.getScreenSize().x){
            velocity.x *= -1;
            position.x = gameView.getScreenSize().x - bitmap.getWidth()/2f;
        }

        //Parede Esquerda
        if(position.x - bitmap.getWidth()/2f < 0){
            velocity.x *= -1;
            position.x = bitmap.getWidth()/2f;
        }

        //Parede Topo
        if(position.y - bitmap.getHeight()/2f < 0){
            velocity.y *= -1;
            position.y = bitmap.getHeight()/2f;
        }

        //Parede Chão
        if(position.y + bitmap.getHeight()/2f > gameView.getScreenSize().y){
            velocity.y *= -1;
            position.y = gameView.getScreenSize().y - bitmap.getHeight()/2f;
        }

        direction.add(
                velocity.clone().normalize().sub(direction).div(10).mul(delta * speed/5f)
        );
        direction.normalize();

    }

    public void onTouch(int action, Vector2 position){
        switch (action){
            case MotionEvent.ACTION_UP:
                speed = 500;
                velocity = velocity.normalize().mul(speed);

                break;
            case MotionEvent.ACTION_DOWN:
                speed = 2000;
                velocity = velocity.normalize().mul(speed);
                break;
        }
    }

}
