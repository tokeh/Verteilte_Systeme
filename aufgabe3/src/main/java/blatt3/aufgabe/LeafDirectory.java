package blatt3.aufgabe;

import java.util.HashMap;

public class LeafDirectory extends Directory {

	private CompositeDirectory myParent;
	private HashMap<Integer, Cell> myEntities;
	private Cell myCell;

	protected LeafDirectory(Cell cell, CompositeDirectory parent) {
		super(parent);
		this.myParent = parent;
		this.myEntities = new HashMap<Integer, Cell>();
		this.myCell = cell;
	}

	@Override
	Cell lookup(int entity) {
		if (this.myEntities.containsKey(entity)) {
			return this.myEntities.get(entity);
		} else {
			return this.myParent.lookup(entity);
		}
	}

	public void insert(int entity) {
		this.myEntities.put(entity, this.myCell);
		this.myParent.addChild(entity, this);
	}

}
