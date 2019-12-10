package main;

import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;

public class GameButtons {
    private Map<String, Button> buttonsMap;

    private Button newGameBtn;

    GameButtons() {
        buttonsMap = new HashMap<>();
        newGameBtn = new Button("NOWA GRA");
        setProperties();
        addButtonsToMap();
    }

    public void setProperties() {
        newGameBtn.setTranslateX(925);
        newGameBtn.setTranslateY(50);
        newGameBtn.setScaleX(4);
        newGameBtn.setScaleY(4);
        System.out.println("Set buttons properties");
    }

    public void addButtonsToMap() {
        buttonsMap.put(GUINames.newGameButtonName, newGameBtn);
    }

    public Map<String, Button> getButtons() {
        return buttonsMap;
    }
}