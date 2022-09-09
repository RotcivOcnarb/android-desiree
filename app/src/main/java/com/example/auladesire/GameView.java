package com.example.auladesire;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements Runnable, View.OnTouchListener {

    volatile boolean playing = true;
    private Thread gameThread;
    private Player player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Matrix rotationMatrix;

    private int FPS = 60;
    private final float targetMili = 1000/(float)FPS;
    private float delta;
    private Vector2 screenSize;

    public GameView(Context context, Vector2 screenSize) {
        super(context);
        this.screenSize = screenSize;
        player = new Player(this);
        surfaceHolder = getHolder();
        paint = new Paint();
        rotationMatrix = new Matrix();
        this.setOnTouchListener(this);
    }

    public Vector2 getScreenSize(){
        return screenSize;
    }

    @Override
    public void run() {
        while(playing){
            try {
                long time = System.nanoTime();
                input();
                update();
                draw();
                float elapsed = (System.nanoTime() - time) / 1000000000;
                float wait = Math.max(0, targetMili - elapsed);
                Thread.sleep((long) wait);
                delta = (System.nanoTime() - time)/1000000000f;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        player.update(delta);
    }

    private void resetMatrix(){
        rotationMatrix.setValues(new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        });
    }

    public void draw(){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.GRAY);
            resetMatrix();

            rotationMatrix.setRotate(
                    (float)Math.toDegrees(player.getRotation()),
                    player.getBitmap().getWidth()/2f,
                    player.getBitmap().getHeight()/2f
            );

            rotationMatrix.postTranslate(
                    player.getX() - player.getBitmap().getWidth()/2f,
                    player.getY() - player.getBitmap().getHeight()/2f);

            canvas.drawBitmap(
                    player.getBitmap(),
                    rotationMatrix,
                    paint
            );


            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void input(){

    }

    public void pause(){
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        player.onTouch(motionEvent.getAction() & MotionEvent.ACTION_MASK, new Vector2(motionEvent.getX(), motionEvent.getY()));
        return true;
    }
}
