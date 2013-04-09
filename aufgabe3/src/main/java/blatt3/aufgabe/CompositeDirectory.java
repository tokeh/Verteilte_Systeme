package blatt3.aufgabe;

import java.util.HashMap;

public class CompositeDirectory extends Directory {

	private CompositeDirectory myParent;
	private HashMap<Integer, Directory> myEntities;

	protected CompositeDirectory(CompositeDirectory parent) {
		super(parent);
		this.myParent = parent;
		this.myEntities = new HashMap<Integer,Directory>();
	}

	@Override
	Cell lookup(int entity) {
		if (this.myEntities.containsKey(entity)) {
			return this.myEntities.get(entity).lookup(entity);
		} else if (this.myParent != null) {
			return this.myParent.lookup(entity);
		} else {
			return null;
		}
	}

	void addChild(Integer entity, Directory dir) {
		this.myEntities.put(entity, dir);
		if (this.myParent != null) {
			this.myParent.addChild(entity, this);
		}
	}

}
