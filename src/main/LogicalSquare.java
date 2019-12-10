package main;

public class LogicalSquare {
    private int id;
    private int posX;
    private int posY;
    private String color;

    public LogicalSquare(int id) {
        this.id = id;
        color = "BLUE";
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
