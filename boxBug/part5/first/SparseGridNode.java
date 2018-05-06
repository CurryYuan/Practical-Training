package part5.first;

public class SparseGridNode<E> {
	private E occupant;
    private int col;
    private SparseGridNode<E> next;
    
    public SparseGridNode(int col,E occup) {
		this.col=col;
		this.occupant=occup;
		next=null;
	}
	public E getOccupant() {
		return occupant;
	}
	public void setOccupant(E occupant) {
		this.occupant = occupant;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public SparseGridNode<E> getNext() {
		return next;
	}
	public void setNext(SparseGridNode<E> next) {
		this.next = next;
	}
    
    
}
