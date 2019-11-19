package main;

public class LogicalSquare {
    private int id;
    private int posX;
    private int posY;
    private String color;

    enum Color{
        BLACK,              // -> disabled square
        WHITE,
        RED,
        GREEN,
        BLUE
    }

    public LogicalSquare(int id) {
        this.id = id;
        color = "BLACK";
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
