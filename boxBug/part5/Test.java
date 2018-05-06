package part5;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class Test {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		Actor at=new Actor();
		Location location=new Location(2, 2);
		at.putSelfInGrid(world.getGrid(),location);
		//at.putSelfInGrid(world.getGrid(),location);
		at.removeSelfFromGrid();
		at.removeSelfFromGrid();
		at.putSelfInGrid(world.getGrid(),location);
		world.show();
	}

}
