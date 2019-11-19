package test;

import main.LogicalBoard;
import org.junit.Assert;
import org.junit.Test;

public class LogicalBoardTestSuite {

    @Test
    public void testTwoSquaresHaveTheSameColor(){
        //Given
        LogicalBoard logicalBoard = new LogicalBoard(5, 5);

        //When
        logicalBoard.setSquareColor(1, "RED");
        logicalBoard.setSquareColor(2, "RED");
        logicalBoard.setSquareColor(3, "GREEN");

        //Then
        boolean expectedTrue = logicalBoard.twoSquaresHaveTheSameColor(1, 2);
        boolean expectedFalse = logicalBoard.twoSquaresHaveTheSameColor(1, 3);
        Assert.assertTrue(expectedTrue);
        Assert.assertFalse(expectedFalse);
    }
}
