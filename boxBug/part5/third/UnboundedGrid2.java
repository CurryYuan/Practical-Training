package part5.third;

 import java.util.ArrayList;
import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.NEW;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
 
public class UnboundedGrid2<E> extends AbstractGrid<E> {
	private Object[][] occupantArray;
	
	public UnboundedGrid2() {
		occupantArray=new Object[16][16];
	}
	public int getNumRows() {
		return -1;
	}

	public int getNumCols() {
		return -1;
	}
	
	public boolean isValid(Location loc) {
		return !(loc.getRow()<0||loc.getCol()<0);
	}
	
	private void resize() {
		//System.out.println(occupantArray.length);
		occupantArray=Arrays.copyOf(occupantArray, occupantArray.length*2);
		for(int i=0;i<occupantArray.length/2;++i) {
			occupantArray[i]=Arrays.copyOf(occupantArray[i], occupantArray.length);
		}
		for(int i=occupantArray.length/2;i<occupantArray.length;++i) {
			occupantArray[i]=new Object[occupantArray.length];
		}
	}
	
	public ArrayList<Location> getOccupiedLocations() {
		ArrayList<Location> theLocations = new ArrayList<Location>();

		for (int r = 0; r < occupantArray.length; ++r) {
			for (int c = 0; c < occupantArray.length; ++c) {
				Location loc = new Location(r, c);
				if (this.get(loc) != null) {
					theLocations.add(loc);
				}
			}
		}

		return theLocations;
	}
	
	public E get(Location loc) {
		if (loc == null) {
			throw new NullPointerException("loc == null");
		} else if (!this.isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		} else {

			if(loc.getCol()>=occupantArray.length||loc.getRow()>=occupantArray.length) {
				return null;
			}
			if(this.occupantArray[loc.getRow()][loc.getCol()]==null) {
				return null;
			}
			else {
				//System.out.println(occupantArray[loc.getRow()][loc.getCol()]);
				return (E) this.occupantArray[loc.getRow()][loc.getCol()];
			}
		}
	}
	
	public E put(Location loc, E obj) {
		if (loc == null) {
			throw new NullPointerException("loc == null");
		} else if (!this.isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		} else if (obj == null) {
			throw new NullPointerException("obj == null");
		} else {
			while(loc.getCol()>=occupantArray.length||loc.getRow()>=occupantArray.length) {
				resize();
			}
			Object oldOccupant = this.get(loc);
			//System.out.println(obj);
			this.occupantArray[loc.getRow()][loc.getCol()] = obj;

			return (E) oldOccupant;
		}
	}

	public E remove(Location loc) {
		if (!this.isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		} else {
			Object r = this.get(loc);
			this.occupantArray[loc.getRow()][loc.getCol()] = null;
			return (E) r;
		}
	}
}
 