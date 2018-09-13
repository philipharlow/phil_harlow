package model.Map;

import java.io.Serializable;

public abstract class MapObjects implements Serializable{
	private int locationX;
	private int locationY;
	private boolean traversed;//true means it can be traversed
	
	public MapObjects(int x, int y, boolean traversable) {
		locationX = x;
		locationY = y;
		traversed = traversable;
	}
	public int getlocationX() {
		return locationX;
	}
	public int getlocationY() {
		return locationY;
	}
	public boolean getTraverseStatus() {
		return traversed;
	}
	
}
