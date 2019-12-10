package main;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class GameScene {
    private int screenWidth = DefaultValues.screenWidth;
    private int screenHeight = DefaultValues.screenHeight;
    private GridPane root;
    private GraphicalBoardAndGUI graphicalBoardAndGUI;
    private Scene scene;

    GameScene(){
        root = new GridPane();
        scene = new Scene(root, screenWidth, screenHeight);
        graphicalBoardAndGUI = new GraphicalBoardAndGUI();
        graphicalBoardAndGUI.prepareRectanglesOnBoard();
        graphicalBoardAndGUI.setMouseEventsOnRectangles();
        addButtonsToScene();
        addScrollBarsToScene();
        addLabelsToScene();
        addRectanglesToScene();
    }

    public Scene getScene() {
        return scene;
    }

    public void addRectanglesToScene(){
        graphicalBoardAndGUI.getRectangles().stream()
                .forEach(rectangle -> root.getChildren().add(rectangle));
    }

    public void addButtonsToScene(){
        graphicalBoardAndGUI.getGameButtons().getButtons()
                .forEach((s, button) -> root.getChildren().add(button));
    }

    public void addScrollBarsToScene(){
        graphicalBoardAndGUI.getGameScrollBars().getScrollBars()
                .forEach((s, sb)->root.getChildren().add(sb));
    }

    public void addLabelsToScene(){
        graphicalBoardAndGUI.getGameLabels().getLabels()
                .forEach((s, l)->root.getChildren().add(l));
    }
}
