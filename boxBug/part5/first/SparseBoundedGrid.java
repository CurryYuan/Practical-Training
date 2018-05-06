package part5.first;

import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {
	private Object[] sparseArray;
	private int maxCol;
	
	public SparseBoundedGrid(int rows,int cols) {
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		} else if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}else {
			this.sparseArray=new Object[rows];
			maxCol=cols;
		}
	}
	
	public int getNumRows() {
		return this.sparseArray.length;
	}
	
	public int getNumCols() {
		return maxCol;
	}
	
	public boolean isValid(Location loc) {
		return 0 <= loc.getRow() && loc.getRow() < this.getNumRows() && 0 <= loc.getCol()
				&& loc.getCol() < this.getNumCols();
	}
	
	public ArrayList<Location> getOccupiedLocations() {
		ArrayList<Location> theLocations = new ArrayList<Location>();

		for (int r = 0; r < this.getNumRows(); ++r) {
			for (int c = 0; c < this.getNumCols(); ++c) {
				Location loc = new Location(r, c);
				if (this.get(loc) != null) {
					theLocations.add(loc);
				}
			}
		}

		return theLocations;
	}
	
	public E get(Location loc) {
		if (!this.isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		} else {
			SparseGridNode<E> cGridNode=(SparseGridNode<E>) sparseArray[loc.getRow()];
			while(cGridNode!=null) {
				if(cGridNode.getCol()==loc.getCol()) {
					return cGridNode.getOccupant();
				}
				cGridNode=cGridNode.getNext();
			}
			return null;
		}
	}
	
	public E put(Location loc, E obj) {
		if (!this.isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		} else if (obj == null) {
			throw new NullPointerException("obj == null");
		} else {
			E oldOccupant = this.get(loc);
			SparseGridNode<E> cGridNode=(SparseGridNode<E>) sparseArray[loc.getRow()];
			if(cGridNode==null) {
				sparseArray[loc.getRow()]=new SparseGridNode<E>(loc.getCol(), obj);
				return oldOccupant;
			}
			while(cGridNode.getNext()!=null) {
				cGridNode=cGridNode.getNext();
			}
			cGridNode.setNext(new SparseGridNode<E>(loc.getCol(), obj));
			
			return oldOccupant;
		}
	}
	
	public E remove(Location loc) {
		if (!this.isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		} else {
			E r = this.get(loc);
			
			SparseGridNode<E> cGridNode=(SparseGridNode<E>) sparseArray[loc.getRow()];
			if(cGridNode.getCol()==loc.getCol()) {
				sparseArray[loc.getRow()]=cGridNode.getNext();
				return r;
			}
			SparseGridNode<E> dGridNode=cGridNode.getNext();
			while(dGridNode!=null) {
				if(dGridNode.getCol()==loc.getCol()) {
					cGridNode.setNext(dGridNode.getNext());
					break;
				}
				cGridNode=cGridNode.getNext();
				dGridNode=dGridNode.getNext();
			}
			
			return r;
		}
	}
}
