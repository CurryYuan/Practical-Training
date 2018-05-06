package part5.first;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
/**
 * This class runs a world with additional grid choices.
 */
public final class SparseGridRunner
{
    private SparseGridRunner() {
    	
	}

	public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("part5.first.SparseBoundedGrid");
        world.addGridClass("part5.second.SparseBoundedGrid2");

        world.addGridClass("part5.third.UnboundedGrid2");
       
        world.add(new Location(2, 3), new Critter());
        world.add(new Location(3, 3),new Rock());
        world.add(new Location(3, 6),new Bug());
        world.show();
    }
}
