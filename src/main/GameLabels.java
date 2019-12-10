package main;

import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class GameLabels {
    private Map<String, Label> labelsMap;

    private Label colorQuantityDisplay;
    private Label colorQuantity;
    private Label gameStatusMassage;
    private Label boardFilledPercentageDisplay;
    private Label boardFilledPercentageLabel;

    GameLabels(){
        labelsMap = new HashMap<>();
        colorQuantityDisplay = new Label();
        colorQuantity = new Label();
        gameStatusMassage = new Label();
        boardFilledPercentageDisplay = new Label();
        boardFilledPercentageLabel = new Label();
        setProperties();
        addLabelsToMap();
    }

    public void setProperties(){
        colorQuantity.setTranslateX(885);
        colorQuantity.setTranslateY(175);
        colorQuantity.setScaleX(3);
        colorQuantity.setScaleY(3);
        colorQuantityDisplay.setTranslateX(1150);
        colorQuantityDisplay.setTranslateY(250);
        colorQuantityDisplay.setScaleX(3);
        colorQuantityDisplay.setScaleY(3);
        gameStatusMassage.setTranslateX(900);
        gameStatusMassage.setTranslateY(350);
        gameStatusMassage.setScaleX(3);
        gameStatusMassage.setScaleY(3);
        boardFilledPercentageLabel.setTranslateX(885);
        boardFilledPercentageLabel.setTranslateY(450);
        boardFilledPercentageLabel.setScaleX(3);
        boardFilledPercentageLabel.setScaleY(3);
        boardFilledPercentageDisplay.setTranslateX(1100);
        boardFilledPercentageDisplay.setTranslateY(450);
        boardFilledPercentageDisplay.setScaleX(3);
        boardFilledPercentageDisplay.setScaleY(3);
    }

    public void addLabelsToMap(){
        labelsMap.put(GUINames.colorQuantityDisplayLabel, colorQuantityDisplay);
        labelsMap.put(GUINames.colorQuantityLabel, colorQuantity);
        labelsMap.put(GUINames.gameStatusMassage, gameStatusMassage);
        labelsMap.put(GUINames.boardFilledPercentageLabel, boardFilledPercentageLabel);
        labelsMap.put(GUINames.boardFilledPercentageDisplay, boardFilledPercentageDisplay);
    }

    public Map<String, Label> getLabels() {
        return labelsMap;
    }
}
