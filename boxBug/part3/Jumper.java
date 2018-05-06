package part3;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Jumper extends Bug {
	public void act() {
		if (this.canMove()&&this.canMoveTwo()) {
			this.move();
			this.move();
		}else if(!this.canMove()&&this.canMoveTwo()) {
			this.jump();
		}
		else {
			this.turn();
		}
	}
	public boolean canMove() {
		Grid gr = this.getGrid();
		if (gr == null) {
			return false;
		} else {
			Location loc = this.getLocation();
			Location next = loc.getAdjacentLocation(this.getDirection());
			if (!gr.isValid(next)) {
				return false;
			} else {
				Actor neighbor = (Actor) gr.get(next);
				return neighbor == null;
			}
		}
	}
	public boolean canMoveTwo() {
		Grid gr = this.getGrid();
		if (gr == null) {
			return false;
		} else {
			Location loc = this.getLocation();
			Location next = loc.getAdjacentLocation(this.getDirection());
			Location nextTwo=next.getAdjacentLocation(this.getDirection());
			if (!gr.isValid(nextTwo)) {
				return false;
			} else {
				Actor neighbor = (Actor) gr.get(nextTwo);
				return neighbor == null;
			}
		}
	}
	public void jump() {
		Grid gr = this.getGrid();
		if (gr != null) {
			Location loc = this.getLocation();
			Location next = loc.getAdjacentLocation(this.getDirection());
			Location nextTwo=next.getAdjacentLocation(this.getDirection());
			if (gr.isValid(nextTwo)) {
				this.moveTo(nextTwo);
			} else {
				this.removeSelfFromGrid();
			}
		}
	}
}
