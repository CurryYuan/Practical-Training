package part5.second;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
	private Map<Location, E> occupantMap = new HashMap<Location, E>();
	private int maxRow, maxCol;
	
	public SparseBoundedGrid2(int rows,int cols) {
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		} else if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}else {
			maxRow=rows;
			maxCol=cols;
		}
	}
	public int getNumRows() {
		return this.maxRow;
	}

	public int getNumCols() {
		return this.maxCol;
	}
	public boolean isValid(Location loc) {
		return 0 <= loc.getRow() && loc.getRow() < this.getNumRows() && 0 <= loc.getCol()
				&& loc.getCol() < this.getNumCols();
	}
	
	public ArrayList<Location> getOccupiedLocations() {
		ArrayList<Location> a = new ArrayList<Location>();
		Iterator<Location> i$ = this.occupantMap.keySet().iterator();

		while (i$.hasNext()) {
			Location loc = (Location) i$.next();
			a.add(loc);
		}

		return a;
	}
	
	public E get(Location loc) {
		if (loc == null) {
			throw new NullPointerException("loc == null");
		} else {
			return this.occupantMap.get(loc);
		}
	}

	public E put(Location loc, E obj) {
		if (loc == null) {
			throw new NullPointerException("loc == null");
		} else if (obj == null) {
			throw new NullPointerException("obj == null");
		} else {
			return this.occupantMap.put(loc, obj);
		}
	}

	public E remove(Location loc) {
		if (loc == null) {
			throw new NullPointerException("loc == null");
		} else {
			return this.occupantMap.remove(loc);
		}
	}
}
