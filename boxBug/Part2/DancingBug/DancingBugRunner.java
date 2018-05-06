package Part2.DancingBug;
import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public final class DancingBugRunner {
	private DancingBugRunner() {
		
	}

	public static void main(String[] args)
    {
		int[] arr={2,3,5,7};
        ActorWorld world = new ActorWorld();
        DancingBug alice = new DancingBug(arr);
        alice.setColor(Color.ORANGE);
        world.add(new Location(7, 8), alice);
        world.show();
    }
}
