package main;

public class ActualValues {
    private int screenWidth = 1200;
    private int screenHeight = 900;
    private int width = 10;           //board dimensions
    private int height = 10;
    private int squareSize = 50;
    private int colorsQuantity = 3;

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }

    public int getColorsQuantity() {
        return colorsQuantity;
    }

    public void setColorsQuantity(int colorsQuantity) {
        this.colorsQuantity = colorsQuantity;
    }
}
