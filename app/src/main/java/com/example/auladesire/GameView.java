package com.example.auladesire;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing = true;
    private Thread gameThread;
    private Player player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public GameView(Context context) {
        super(context);
        player = new Player(context);
        surfaceHolder = getHolder();
        paint = new Paint();
    }

    @Override
    public void run() {
        while(playing){
            update();
            draw();
            control();

        }
    }

    public void update(){
        player.update();
    }

    public void draw(){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint
            );
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void control(){

    }
}