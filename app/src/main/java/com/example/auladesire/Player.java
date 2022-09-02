package com.example.auladesire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    private Bitmap bitmap;
    private int x, y;
    private int speed = 0;

    public Player(Context context){
        x = 75;
        y = 50;
        speed = 0;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getSpeed(){
        return speed;
    }

    public void update(){

    }

}
