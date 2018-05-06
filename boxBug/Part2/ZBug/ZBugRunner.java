package Part2.ZBug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public final class ZBugRunner {
	private ZBugRunner() {

	}

	public static void main(String[] args)
	{
	        ActorWorld world = new ActorWorld();
	        ZBug bob = new ZBug(5);
	        world.add(new Location(2, 5), bob);
	        world.show();
	}
}
