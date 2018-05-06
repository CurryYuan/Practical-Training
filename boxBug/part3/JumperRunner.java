package part3;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;

public final class JumperRunner {
	private JumperRunner() {
		
	}

	public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper bob = new Jumper();
        world.add(new Location(5, 5), bob);
        world.add(new Rock());
        world.show();
    }
}
