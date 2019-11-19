package main;

import java.util.*;

public class LogicalBoard {
    private int width;
    private int height;
    private List<LogicalSquare> squares;

    public LogicalBoard(int width, int height) {
        squares = new ArrayList<>();
        squares.add(new LogicalSquare(0));
        this.width = width;
        this.height = height;
        for (int i=1; i<=width*height; i++) {
            squares.add(new LogicalSquare(i));
        }
    }

    //Getters

    String getSquareColor(int id){
        return squares.get(id).getColor();
    }

    String getSquareColor(int posX, int posY){
        return squares.get((posY-1)*width + (posX - 1)).getColor();
    }

    //Setters

    public void setSquareColor(int id, String color){
        squares.get(id).setColor(color);
    }

    public void setSquareColor(int posX, int posY, String color){
        squares.get((posY-1)*width + (posX - 1)).setColor(color);
    }

    public void setDefaultColors(){
        setSquareColor(1, "RED");
        setSquareColor(2, "RED");
        setSquareColor(3, "GREEN");
        setSquareColor(7, "GREEN");
        setSquareColor(8, "GREEN");
        setSquareColor(9, "GREEN");
        setSquareColor(12, "GREEN");
        setSquareColor(13, "GREEN");
        setSquareColor(14, "GREEN");
        setSquareColor(17, "GREEN");
        setSquareColor(18, "GREEN");
        setSquareColor(23, "GREEN");
        setSquareColor(25, "BLUE");
    }

    public void setRandomColors(){
        Random gen = new Random();
        for (int i=1; i<=width*height; i++){
            switch (gen.nextInt(3)){
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
                default:{
                    setSquareColor(i, "WHITE");
                    break;
                }
            }
        }
    }

    //Other function

    public boolean twoSquaresHaveTheSameColor(LogicalSquare s1, LogicalSquare s2){
        return s1.getColor().equals(s2.getColor());
    }

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
                System.out.println("top: " + topId);
            }
        }
        //Bottom
        int bottomId = id + width;
        if(posY < height) {
            if (twoSquaresHaveTheSameColor(id, bottomId)){
                result.add(bottomId);
                System.out.println("bottom: " + bottomId);
            }
        }
        //Left
        int leftId = id - 1;
        if(posX > 1) {
            if(twoSquaresHaveTheSameColor(id, leftId)){
                result.add(leftId);
                System.out.println("left: " + (leftId));
            }
        }
        //Right
        int rightId = id + 1;
        if(posX < width) {
            if(twoSquaresHaveTheSameColor(id, rightId)){
                result.add(rightId);
                System.out.println("right: " + rightId);
            }
        }
        //System.out.println("posX=" + posX + ", posY=" + posY);
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
    // and then move columns from left to right if board have empty column between full.
    public void mergeBoardAfterRemoveSquares(int id){
        Set<Integer> squaresGroup = findGroupSquaresInTheSameColor(id);
        if((getSquareColor(id) != "BLACK") && (squaresGroup.size() > 1)){
            squaresGroup.stream().forEach(square -> setSquareColor(square, "BLACK"));
            //List<List<Integer>> columnIdList = new ArrayList<>();
            //List<List<String>> columnColorList = new ArrayList<>();
            //Stack<List<String>> colorListsStack = new Stack<>();
/*
            for (int i=0; i<width; i++){
                List<Integer> columnId = new ArrayList<>();
                List<String> columnColor = new ArrayList<>();
                for (int j=1; j<=height; j++){
                    int pos = i*width + j;
                    String color;
                    if(getSquareColor(pos) != "BLACK"){
                        columnId.add(pos);
                        color = getSquareColor(pos);
                        columnColor.add(color);
                        setSquareColor(pos, "BLACK");
                    }

                }
                columnIdList.add(columnId);
                colorListsStack.add(columnColor);
            }
            int counterId = 1;
*/
        }
    }

    // Return true, when player don't have any moves (all squares don't have any neighbours in the same colour).
    boolean checkGameIsOver(){
        boolean isOver = false;



        return isOver;
    }
}