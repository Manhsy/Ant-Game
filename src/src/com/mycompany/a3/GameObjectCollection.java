package com.mycompany.a3;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection{
	private ArrayList<GameObject> collection;
	
	public GameObjectCollection() {
		collection = new ArrayList<GameObject>();
	}
	@Override
	public void add(Object newObject) {
		collection.add((GameObject) newObject);
	}

	@Override
	public IIterator getIterator() {
		return (IIterator) new GameObjectIterator();
	}
	private class GameObjectIterator implements IIterator{
		private int curr;
		public GameObjectIterator() {
			curr = - 1;
		}
		public boolean hasNext() {
			if(collection.size()<=0 || curr == collection.size()-1){
				return false;
			}
			return true;
		}
		@Override
		public Object getNext() {
			curr++;
			return(collection.get(curr));
		}
	}
}


