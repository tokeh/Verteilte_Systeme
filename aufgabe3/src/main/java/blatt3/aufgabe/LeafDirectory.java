package blatt3.aufgabe;

public class LeafDirectory extends Directory {

	private CompositeDirectory myParent;
	private Cell myCell;
	private int myEntity;

	protected LeafDirectory(Cell cell, CompositeDirectory parent) {
		super(parent);
		this.myCell = cell;
		this.myParent = parent;
	}

	@Override
	Cell lookup(int entity) {
		if (entity == this.myEntity) {
			return this.myCell;
		} else {
			return this.myParent.lookup(entity);
		}
	}

	public void insert(int entity) {
		this.myEntity = entity;
		this.myParent.addChild(entity, this);
	}

	public Cell getCell() {
		return this.myCell;
	}

}
