package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;

public class DisappearingSquares extends Application {
    private int boardShiftX = 10;
    private int boardShiftY = 10;
    private int width = 15;
    private int height = 15;
    private int squareSize = 50;
    private List<Rectangle> graphicalBoard;
    private Map<String, Color> colorMap;
    public LogicalBoard logicalBoard;
    private Map<Rectangle, Integer> idRectangleList;

    public void init() {
        logicalBoard = new LogicalBoard(width, height);

        //logicalBoard.setDefaultColors();
        logicalBoard.setRandomColors();

        graphicalBoard = new ArrayList<>();
        idRectangleList = new HashMap<>();
        colorMap = new HashMap<>();
        colorMap.put("BLACK", Color.BLACK);
        colorMap.put("WHITE", Color.WHITE);
        colorMap.put("RED", Color.RED);
        colorMap.put("GREEN", Color.GREEN);
        colorMap.put("BLUE", Color.BLUE);
        colorMap.put("BLACK", Color.BLACK);
        colorMap.put("WHITE", Color.WHITE);
        colorMap.put("RED", Color.RED);
        colorMap.put("GREEN", Color.GREEN);

        graphicalBoard.add(new Rectangle());
        for (int i = 1; i <= width * height; i++) {
            Rectangle rec = new Rectangle(getBoardShiftX(), getBoardShiftY(),
                    getSquareSize(), getSquareSize());
            rec.setTranslateX(((i - 1) % width) * squareSize + boardShiftX);
            rec.setTranslateY(((i - 1) / height) * squareSize + boardShiftY);
            rec.setStroke(Color.WHITE);

            idRectangleList.put(rec, i);

            rec.setOnMouseClicked((event -> {
                int id = idRectangleList.get(rec);
                //int posX = (int) rec.getTranslateX() / squareSize;
                //int posY = (int) rec.getTranslateY() / squareSize;

                logicalBoard.mergeBoardAfterRemoveSquares(id);
                setColorsOnGraphicBoardFromTheirLogicalValues();

                System.out.println("-------------------------------------------");
            }));


            graphicalBoard.add(rec);
        }
        setColorsOnGraphicBoardFromTheirLogicalValues();
    }

    public void setColorsOnGraphicBoardFromTheirLogicalValues() {
        for (int i = 1; i < graphicalBoard.size(); i++) {
            String color = logicalBoard.getSquareColor(i);
            graphicalBoard.get(i).setFill(colorMap.get(color));
        }
    }

    //Getters
    public int getBoardShiftX() {
        return boardShiftX;
    }

    public int getBoardShiftY() {
        return boardShiftY;
    }

    public int getSquareSize() {
        return squareSize;
    }


    //Setters
    public void setBoardShift(int boardShiftX, int boardShiftY) {
        this.boardShiftX = boardShiftX;
        this.boardShiftY = boardShiftY;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init();

        GridPane root = new GridPane();
        for (int i = 0; i < graphicalBoard.size(); i++) {
            root.getChildren().add(graphicalBoard.get(i));
        }

        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
