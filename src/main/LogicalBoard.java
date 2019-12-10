package main;

import java.util.*;

public class LogicalBoard {
    private int width;
    private int height;
    private int colorsQuantity;
    private double filledPercentage;
    private List<LogicalSquare> squares;

    public LogicalBoard(int width, int height, int colorsQuantity) {
        filledPercentage = 100;
        this.width = width;
        this.height = height;
        this.colorsQuantity = colorsQuantity;
        squares = new ArrayList<>();
        squares.add(new LogicalSquare(0));
        for (int i=1; i<=width*height; i++) {
            squares.add(new LogicalSquare(i));
        }
    }

    //Getters
    String getSquareColor(int id){
        return squares.get(id).getColor();
    }

    //Setters
    public void setSquareColor(int id, String color){
        squares.get(id).setColor(color);
    }

    public void setAllSquaresBlack(){
        squares.stream()
                .forEach(s->s.setColor("BLACK"));    }

    public void setRandomColors(){
        Random gen = new Random();
        for (int i=1; i<=width*height; i++){
            switch (gen.nextInt(colorsQuantity)){
                case 0: {
                    setSquareColor(i, "RED");
                    break;
                }
                case 1: {
                    setSquareColor(i, "GREEN");
                    break;
                }
                case 2: {
                    setSquareColor(i, "BLUE");
                    break;
                }
                case 3: {
                    setSquareColor(i, "YELLOW");
                    break;
                }
                case 4: {
                    setSquareColor(i, "BROWN");
                    break;
                }
                case 5: {
                    setSquareColor(i, "GRAY");
                    break;
                }
                case 6: {
                    setSquareColor(i, "ORANGE");
                    break;
                }
                default:{
                    setSquareColor(i, "WHITE");
                    break;
                }
            }
        }
    }

    public void setColorsQuantity(int colorsQuantity) {
        this.colorsQuantity = colorsQuantity;
    }

    //Other function
    public boolean twoSquaresHaveTheSameColor(int id1, int id2){
        return squares.get(id1).getColor().equals(squares.get(id2).getColor());
    }

    public List<Integer> findTopBottomLeftAndRightNeighborsInTheSameColor(int id){
        List<Integer> result = new ArrayList<>();
        int posX;
        int posY;
        posX = (id-1) % width;
        posY = (id-1) / height;
        posX++;
        posY++;

        //Top
        int topId = id - width;
        if(posY > 1) {
            if(twoSquaresHaveTheSameColor(id, topId)) {
                result.add(topId);
            }
        }
        //Bottom
        int bottomId = id + width;
        if(posY < height) {
            if (twoSquaresHaveTheSameColor(id, bottomId)){
                result.add(bottomId);
            }
        }
        //Left
        int leftId = id - 1;
        if(posX > 1) {
            if(twoSquaresHaveTheSameColor(id, leftId)){
                result.add(leftId);
            }
        }
        //Right
        int rightId = id + 1;
        if(posX < width) {
            if(twoSquaresHaveTheSameColor(id, rightId)){
                result.add(rightId);
            }
        }
        return result;
    }

    //Based on BFS algorithm
    public Set<Integer> findGroupSquaresInTheSameColor (int id){
        Set<Integer> result = new HashSet<>();
        Queue<Integer> squareQueue = new ArrayDeque<>();
        Vector<Boolean> visited = new Vector<>();
        for (int i=0; i<=width*height; i++){
            visited.add(false);
        }

        squareQueue.add(id);
        List<Integer> neighbors = new ArrayList<>();
        while (!squareQueue.isEmpty()){
            id = squareQueue.poll();
            if(visited.elementAt(id) == false) {
                visited.set(id, true);
                neighbors = findTopBottomLeftAndRightNeighborsInTheSameColor(id);
                result.add(id);
            }
            for(int i: neighbors){
                if(visited.elementAt(i) == false){
                    squareQueue.add(i);
                    result.add(i);
                }
            }
        }
        return result;
    }

    // If clicked square has, or more neighbors in the same color,
    // move down squares in the columns first (like a gravitation),
    // and then move columns from right to left if board have empty column between full.
    public void mergeBoardAfterRemoveSquares(int id){
        Set<Integer> squaresGroup = findGroupSquaresInTheSameColor(id);
        if((getSquareColor(id) != "BLACK") && (squaresGroup.size() > 1)){
            List<List<String>> columnColorList = new ArrayList<>();
            squaresGroup.stream().forEach(square -> setSquareColor(square, "BLACK"));

            for (int i=1; i<=width; i++){
                List<String> columnColor = new ArrayList<>();
                for (int j=0; j<height; j++){
                    int pos = j*width + i;
                    String color;
                    if(getSquareColor(pos) != "BLACK"){
                        color = getSquareColor(pos);
                        columnColor.add(color);
                    }
                }
                if(columnColor.size()> 0){
                    columnColorList.add(columnColor);
                }
            }
            setAllSquaresBlack();

            for (int i=0; i<columnColorList.size(); i++) {
                List<String> columnColor = columnColorList.get(i);
                int h = height - 1;
                for (int j = columnColor.size() - 1; j >= 0; j--) {
                    int iidd = h * width + i + 1;
                    String color = columnColor.get(j);
                    setSquareColor(iidd, color);
                    h--;
                }
            }
        }
    }

    // Return true, when player don't have any moves (all squares don't have any neighbours in the same colour).
    boolean checkGameIsOver(){
        boolean isOver = true;
        List<Integer> idList;
        for (LogicalSquare s: squares){
            if(s.getColor() != "BLACK") {
                idList = findTopBottomLeftAndRightNeighborsInTheSameColor(s.getId());
                if(idList.size() > 1){
                    isOver = false;
                }
            }
        }
        return isOver;
    }

    double calculateFilledPercentage(){
        double per;
        long blacks;
        blacks = squares.stream()
                .filter(s->s.getColor().equals(SquareColors.BLACK))
                .count();
        blacks--;
        per = width*height;
        per -= blacks;
        per /= width*height;
        per *= 100;
        per = (int)(per*10);
        per /= 10;
        System.out.println(blacks + "   " + per);

        return per;
    }
}
