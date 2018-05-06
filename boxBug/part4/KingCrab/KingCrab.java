package part4.KingCrab;

import sourceCode.CrabCritter;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Iterator;

public class KingCrab extends CrabCritter {
	public void processActors(ArrayList<Actor> actors) {
		Iterator i$ = actors.iterator();

		while (i$.hasNext()) {
			Actor a = (Actor) i$.next();
			if (!(a instanceof Rock) && !(a instanceof Critter)) {

				ArrayList<Location> temp=this.getGrid().getEmptyAdjacentLocations(a.getLocation());
				Location newLoc = selectMoveLocation(temp);
				if(temp.size()==0) {
					a.removeSelfFromGrid();
				}
				else {
					makeActorMove(a, newLoc);
				}
				
			}
		}

	}
	
	private void makeActorMove(Actor a,Location loc) {
		a.moveTo(loc);
	}
}
