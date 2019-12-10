package main;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphicalBoardAndGUI {
    private ActualValues actualValues;
    private int boardShiftX = 10;
    private int boardShiftY = 10;
    private int width = DefaultValues.width;
    private int height = DefaultValues.height;
    private int squareSize = DefaultValues.squareSize;
    private int colorsQuantity = DefaultValues.colorsQuantity;
    private List<Rectangle> graphicalBoard;
    private Map<String, Color> colorMap;
    private LogicalBoard logicalBoard;
    private Map<Rectangle, Integer> idRectangleList;
    private GameButtons gameButtons;
    private GameScrollBars gameScrollBars;
    private GameLabels gameLabels;

    GraphicalBoardAndGUI(){
        actualValues = new ActualValues();
        graphicalBoard = new ArrayList<>();
        idRectangleList = new HashMap<>();
        colorMap = new HashMap<>();
        gameButtons = new GameButtons();
        gameScrollBars = new GameScrollBars();
        gameLabels = new GameLabels();
        fillColorMap();
        initGuiElements();
    }

    public void initGuiElements() {
        setButtonEvents();
        setScrollBarsEvent();
        setLabelsEvent();
    }

    public void prepareRectanglesOnBoard(){
        graphicalBoard = new ArrayList<>();
        width = DefaultValues.width;
        height = DefaultValues.height;
        colorsQuantity = DefaultValues.colorsQuantity;
       logicalBoard = new LogicalBoard(width, height, colorsQuantity);
        logicalBoard.setRandomColors();
        idRectangleList.clear();
        Rectangle r = new Rectangle();
        graphicalBoard.add(r);

        for (int i = 1; i <= width * height; i++) {
            Rectangle rec = new Rectangle(getBoardShiftX(), getBoardShiftY(),
                    getSquareSize(), getSquareSize());
            rec.setTranslateX(((i - 1) % width) * squareSize + boardShiftX);
            rec.setTranslateY(((i - 1) / height) * squareSize + boardShiftY);
            rec.setStroke(Color.WHITE);
            idRectangleList.put(rec, i);
            graphicalBoard.add(rec);
        }
        setMouseEventsOnRectangles();
    }

    public void setMouseEventsOnRectangles(){
        graphicalBoard.stream().forEach(rectangle -> rectangle.setOnMouseClicked(event -> {
            int id = idRectangleList.get(rectangle);
            System.out.println(id);
            logicalBoard.mergeBoardAfterRemoveSquares(id);
            setColorsOnGraphicBoardFromTheirLogicalValues();
            if(logicalBoard.checkGameIsOver()){
                getGameLabels().getLabels().get(GUINames.gameStatusMassage).setText("...koniec gry");
            }
            double percent = logicalBoard.calculateFilledPercentage();
            getGameLabels().getLabels()
                    .get(GUINames.boardFilledPercentageDisplay)
                    .setText(new Double(percent).toString() + "%");
            System.out.println(percent);
            System.out.println("-------------------------------------------");
        }));
    }

    public void setButtonEvents(){
        Map<String, Button> buttons = gameButtons.getButtons();

        buttons.get(GUINames.newGameButtonName).setOnAction(event -> {          //New game
            System.out.println("new game");
            logicalBoard.setColorsQuantity(actualValues.getColorsQuantity());
            logicalBoard.setRandomColors();
            setColorsOnGraphicBoardFromTheirLogicalValues();
            getGameLabels().getLabels().get(GUINames.gameStatusMassage).setText("Gra trwa...");
        });
    }
    public void setScrollBarsEvent(){
        Map<String, ScrollBar> scrollBars = gameScrollBars.getScrollBars();
        Map<String, Label> labels = gameLabels.getLabels();
        scrollBars.get(GUINames.colorCountSetter)
                .valueProperty()
                .addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    actualValues.setColorsQuantity(new_val.intValue());
                    labels.get(GUINames.colorQuantityDisplayLabel)
                            .setText(new Integer(new_val.intValue()).toString());
                });
    }

    public void setLabelsEvent(){
        Map<String, Label> labels = gameLabels.getLabels();
        labels.get(GUINames.colorQuantityDisplayLabel)
                .setText(new Integer(colorsQuantity).toString());
        labels.get(GUINames.colorQuantityLabel)
                .setText("Liczba kolorów");
        labels.get(GUINames.boardFilledPercentageLabel)
                .setText("Wypełnienie:");
    }

    public void setColorsOnGraphicBoardFromTheirLogicalValues() {
        for (int i = 1; i<=width*height; i++) {
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

    public List<Rectangle> getRectangles(){
        return graphicalBoard;
    }

    public GameButtons getGameButtons() {
        return gameButtons;
    }

    public GameScrollBars getGameScrollBars(){
        return gameScrollBars;
    }

    public GameLabels getGameLabels() {
        return gameLabels;
    }

    public void fillColorMap(){
        colorMap.put(SquareColors.BLACK, Color.BLACK);
        colorMap.put(SquareColors.WHITE, Color.WHITE);
        colorMap.put(SquareColors.RED, Color.RED);
        colorMap.put(SquareColors.GREEN, Color.GREEN);
        colorMap.put(SquareColors.BLUE, Color.BLUE);
        colorMap.put(SquareColors.YELLOW, Color.YELLOW);
        colorMap.put(SquareColors.BROWN, Color.BROWN);
        colorMap.put(SquareColors.GRAY, Color.GRAY);
        colorMap.put(SquareColors.ORANGE, Color.ORANGE);
    }
}