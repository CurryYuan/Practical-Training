package Part2.ZBug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class ZBug extends Bug{
	private int steps;
    private int sideLength;
    private int turns;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public ZBug(int length) {
		// TODO Auto-generated constructor stub
		steps = 0;
        sideLength = length;
        turns=0;
        turn();
        turn();
	}
    /**
     * Moves to the next location of the square.
     */
    public void act()
    {

        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
        	if(turns==2||!canMove()) {
        		return;
        	}
        	if(turns==0) {
        	turn();
            turn();
            turn();
            ++turns;
        	}
        	else {
        		turn();
                turn();
                turn();
                turn();
                turn();
                ++turns;
        	}
            steps = 0;
        }
    }
    
  
}
