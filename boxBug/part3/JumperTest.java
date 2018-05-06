package part3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class JumperTest {
	private ActorWorld world;
	private Jumper jumper = new Jumper();
	@Before
	public void Before() {
		world = new ActorWorld();
	}
	@Test
	public void testAct() {
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3,5),new Rock());
		world.add(new Location(2,8),new Flower());
		world.add(new Location(2,9),new Flower());
		world.add(new Location(6,9),new Rock());
		for(int i=0;i<6;++i) {
			world.step();
		}
		assertTrue(jumper.getLocation().equals(new Location(1,9)));
	}

	@Test
	public void testCanMove() {
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3,5),new Rock());
		assertTrue(jumper.canMove());
	}

	@Test
	public void testCanMoveTwo() {
		world.add(new Location(5, 5), jumper);
		world.add(new Location(3,5),new Rock());
		assertFalse(jumper.canMoveTwo());
	}

	@Test
	public void testJump() {
		world.add(new Location(5, 5), jumper);
		world.add(new Location(4,5),new Rock());
		world.step();
		assertTrue(jumper.getLocation().equals(new Location(3,5)));
		
	}
	
}
