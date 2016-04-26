/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiantwarstournament;

import aiantwars.impl.Board;
import aiantwars.impl.Location;
import java.util.Random;

/**
 *
 * @author Tobias Grundtvig
 */
public class BoardBuilder
{
    private static final Random rnd = new Random();
    
    public static Board buildTwoPlayerSymmetric(int xSize, int ySize, int soil, int rock, int minFood, int maxFood, int startFood)
    {
        if(xSize % 2 != 0) throw new RuntimeException("x must be even");
        if(xSize < 4) throw new RuntimeException("x must be greater than 4");
        if(ySize < 3) throw new RuntimeException("y must be at least 3");
        Board res = new Board(xSize, ySize);
        int halfX = xSize / 2;
        for(int x = 0; x < halfX; ++x)
        {
            for(int y = 0; y < ySize; ++y)
            {
                Location loc = res.getLocation(x, y);
                int r = rnd.nextInt(100);
                if(r < rock + soil)
                {
                    loc.setFilled(true);
                    if(r < rock)
                    {
                        loc.setRock(true);
                    }
                }
                if(!loc.isRock())
                {
                    loc.setFoodCount(rnd.nextInt(maxFood-minFood+1)+minFood);
                }
            }
        }
        int yHalf = ySize / 2;
        createStartField(res, 0, yHalf + 1, startFood);
        createStartField(res, 0, yHalf, startFood);
        createStartField(res, 0, yHalf - 1, startFood);
        createStartField(res, 1, yHalf, startFood);
        mirror(res);
        return res;
    }
    
    private static void createStartField(Board b, int x, int y, int food)
    {
        Location loc = b.getLocation(x, y);
        loc.setFilled(false);
        loc.setRock(false);
        loc.setFoodCount(food);
    }
    
    private static void mirror(Board board)
    {
        int xSize = board.getSizeX();
        int ySize = board.getSizeY();
        int halfXSize = xSize / 2;
        for(int x = 0; x < halfXSize; ++x)
        {
            for(int y = 0; y < ySize; ++y)
            {
                Location src = board.getLocation(x, y);
                Location dst = board.getLocation(xSize - 1 - x, ySize - 1 - y);
                dst.copyFrom(src);
            }
        }
    }
}