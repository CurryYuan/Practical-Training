package part4.ModifiedChameleonCritter;
import java.util.ArrayList;
import java.awt.Color;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class ModifiedChameleonCritter extends Critter {
	 public void processActors(ArrayList<Actor> actors)
	    {
	        int n = actors.size();
	        if (n == 0) {
	        	Color c = this.getColor();
	    		int red = (int) ((double) c.getRed() * 0.9D);
	    		int green = (int) ((double) c.getGreen() * 0.9D);
	    		int blue = (int) ((double) c.getBlue() * 0.9D);
	    		this.setColor(new Color(red, green, blue));
	        }
	        else {
	        	int r = (int) (Math.random() * n);

	        	Actor other = actors.get(r);
	        	setColor(other.getColor());
	        }
	    }

	    /**
	     * Turns towards the new location as it moves.
	     */
	    public void makeMove(Location loc)
	    {
	        setDirection(getLocation().getDirectionToward(loc));
	        super.makeMove(loc);
	    }
}
