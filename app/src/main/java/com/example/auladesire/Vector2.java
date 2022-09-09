package com.example.auladesire;

import android.graphics.Point;

public class Vector2 {

    public static final Vector2 UP = new Vector2(0, 1);
    public static final Vector2 DOWN = new Vector2(0, -1);
    public static final Vector2 LEFT = new Vector2(-1, 0);
    public static final Vector2 RIGHT = new Vector2(1, 0);
    public static final Vector2 ONE = new Vector2(1, 1);
    public static final Vector2 ZERO = new Vector2(0, 0);

    public float x = 0;
    public float y = 0;

    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2(Point point){
        this.x = point.x;
        this.y = point.y;
    }

    public float getAngle(){
        return (float)Math.atan2(y, x);
    }

    public float getMagnitude(){
        return (float)Math.sqrt(x*x+y*y);
    }

    public Vector2 clone(){
        return new Vector2(x, y);
    }

    public Vector2 add(Vector2 other){
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2 sub(Vector2 other){
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector2 mul(Vector2 other){
        this.x *= other.x;
        this.y *= other.y;
        return this;
    }

    public Vector2 mul(float scalar){
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2 div(Vector2 other){
        this.x /= other.x;
        this.y /= other.y;
        return this;
    }

    public Vector2 div(float scalar){
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    public Vector2 normalize(){
        float mag = getMagnitude();
        this.x /= mag;
        this.y /= mag;
        return this;
    }

    public float dot(Vector2 other){
        return (this.x * other.x) + (this.y * other.y);
    }

    public Vector2 rotate(float radians){
        float sin = (float)Math.sin(radians);
        float cos = (float)Math.cos(radians);

        float tx = this.x;
        float ty = this.y;
        this.x = (cos * tx) - (sin * ty);
        this.y = (sin * tx) + (cos * ty);
        return this;
    }

}
