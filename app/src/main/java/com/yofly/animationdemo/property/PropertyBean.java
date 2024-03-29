package com.yofly.animationdemo.property;

public class PropertyBean {
    int backgroundColor;
    float rotationX;
    float size;

    public PropertyBean(int backgroundColor, float rotationX, float size) {
        this.backgroundColor = backgroundColor;
        this.rotationX = rotationX;
        this.size = size;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public float getRotationX() {
        return rotationX;
    }

    public void setRotationX(float rotationX) {
        this.rotationX = rotationX;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
