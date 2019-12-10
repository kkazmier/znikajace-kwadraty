package main;

import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;

import java.util.HashMap;
import java.util.Map;

public class GameScrollBars {
    private Map<String, ScrollBar> gameScrollBars;
    private ScrollBar colorCountSetter;

    GameScrollBars(){
        gameScrollBars = new HashMap<>();
        colorCountSetter = new ScrollBar();
        setProperties();
        addScrollBarsToMap();
    }

    public void setProperties(){
        colorCountSetter.setTranslateX(890);
        colorCountSetter.setTranslateY(250);
        colorCountSetter.setScaleX(3);
        colorCountSetter.setScaleY(3);
        colorCountSetter.setMin(2);
        colorCountSetter.setMax(7);
        colorCountSetter.setOrientation(Orientation.HORIZONTAL);
    }

    public void addScrollBarsToMap(){
        gameScrollBars.put(GUINames.colorCountSetter, colorCountSetter);
    }

    Map<String, ScrollBar> getScrollBars(){
        return gameScrollBars;
    }
}
