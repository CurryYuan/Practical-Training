package Part2.DancingBug;
import java.util.Arrays;

import info.gridworld.actor.Bug;

public class DancingBug extends Bug {
	private int[] turns;
	private int t;
	
	public DancingBug(int[] temp) {
		turns=Arrays.copyOf(temp, temp.length);
		t=0;
	}
	
	public void act() {
		for(int i=0;i<t;++i) {
			turn();
		}
		move();
		++t;
		if(t>=turns.length) {
			t=0;
		}
	}
}
