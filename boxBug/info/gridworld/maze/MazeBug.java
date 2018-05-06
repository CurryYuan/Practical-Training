package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private Location next;
	private Location last;
	private boolean isEnd = false;
	private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	private Map<Location, Boolean> isVisited=new HashMap<Location,Boolean>();
	private Integer stepCount = 0;
	private boolean hasShown = false;//final message has been shown
	int[] posibility=new int[4];

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
		ArrayList<Location> temp=new ArrayList<Location>();
		temp.add(getLocation());
		
		isVisited.put(getLocation(), true);
		crossLocation.push(temp);
		for(int i=0;i<4;++i) {
			posibility[i]=1;
		}
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd) {
		//to show step count when reach the goal		
			if (!hasShown ) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			if(isEnd) {
				return;
			}
			selectMovePosition();
			isVisited.put(next, true);
			move();
			
			//increase step count when move 
			stepCount++;
		} else {
			crossLocation.pop();
			ArrayList<Location> temp=crossLocation.lastElement();
			next=temp.get(0);
			move();
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		
		int[] dirs =
            { Location.LEFT, Location.RIGHT, Location.AHEAD, Location.HALF_CIRCLE };
		for (int i=0;i<4;++i)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + dirs[i]);
            if (gr.isValid(neighborLoc)&&isVisited.get(neighborLoc)==null) {
            	Actor tActor=gr.get(neighborLoc);
            	if(tActor instanceof Rock) {
            		if(tActor.getColor().equals(Color.red)) {
            			isEnd=true;
            		}      		
            	}
            	else {
            		valid.add(neighborLoc);
            		continue;
            	}
            }
            valid.add(null);
        }
        
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		for(Location c:getValid(getLocation())) {
			if(c!=null) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		Location loc = getLocation();
		last=loc;
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	
	private void selectMovePosition() {
		ArrayList<Location> temp=getValid(getLocation());
		int sum=posibility[0]+posibility[1]+posibility[2]+posibility[3];
		next=null;
		int i=-1;
		while(next==null) {
			double r=Math.random()*sum;
			if(r<=posibility[0]) {
				i=0;
			}
			else if(r<=posibility[0]+posibility[1]) {
				i=1;
			}
			else if(r<=posibility[0]+posibility[1]+posibility[2]) {
				i=2;
			}
			else{
				i=3;
			}
			next=temp.get(i);
		}		
		posibility[i]++;
		temp.clear();
		temp.add(next);
		crossLocation.push(temp);
	}
}
