package model.Map;

import java.io.Serializable;

public abstract class Barrier extends MapObjects implements Serializable{
	
	public Barrier(int x, int y, boolean traversed) {
		super(x, y, traversed);
	}

	
}
