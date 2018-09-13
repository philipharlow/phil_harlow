package model.Map;

import java.io.Serializable;

public abstract class Map implements Serializable{
	private int length =50;
	private int width  =50;
	
	private String  name;
	private boolean multiplayer;
	protected MapObjects[][] mapLayout;
	
	public Map(String name1, boolean multiplayer1) {
		this.name        = name1;
		this.multiplayer = multiplayer1;
		this.mapLayout   = new MapObjects[50][50];
		
	}
	public String getName() {
		return this.name;
	}
	public boolean getMultiplayerStatus() {
		return this.multiplayer;
	}
	public MapObjects[][] getMapObjects() {
		return this.mapLayout;
	}
	
	public int getXdimension() {
		return length;
	}	
	
	public int getYdimension() {
		return width;
	}
	
	public MapObjects getSingleMapObject(int i, int j) {
		return this.getMapObjects()[i][j];
	}
	

	protected abstract void buildTheMap();
	
	
	
}
