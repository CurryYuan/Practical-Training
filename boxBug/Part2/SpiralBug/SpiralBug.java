package Part2.SpiralBug;
import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class SpiralBug extends Bug {
	private int steps;
    private int sideLength;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public SpiralBug(int length) {
		// TODO Auto-generated constructor stub
		steps = 0;
        sideLength = length;
	}
    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (steps < sideLength+1 && canMove())
        {
            move();
            steps++;
        }
        else
        {
        	sideLength+=1;
            turn();
            turn();
            steps = 0;
        }
    }
    
}
