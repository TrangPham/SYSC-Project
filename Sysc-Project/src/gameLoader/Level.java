package gameLoader;

import gameCore.Item;
import gameCore.Room;
import gameCore.Tile;

import java.util.ArrayList;
import java.util.List;


/**
* A level holds all the data pertaining to the current game state:
* configuration of the rooms, tiles, and exits,
* location of items, and characters
* timer value
*
* @author Group D
* @author Main Author: Trang Pham
*
* Group D Members
* ---------------
* Karen Madore
* Trang Pham
* Darrell Penner
*
*
* @version 1.0
*
*/

public class Level {
	
	private int timer;
	private int gridWidth;
	private int gridHeight;
	
	private List<Room> roomList;
	private Tile[][] tileGrid;
	private Room elevator;
	
	
	//copy constructor
	public Level(Level l)
	{
		this.timer = l.timer;
		
	}
	public Level()
	{
		roomList = new ArrayList<Room>();
		timer = 0; 
	}
	/**
	 * Adds a room to the current level
	 * 
	 * @param r room to add
	 */
	public void addRoom(Room r)
	{
		roomList.add(r);
	}
	
	/**
	 * Sets the size of the level which determines how many tiles are on this level
	 * 
	 * @param width the width of the level's map
	 * @param height the height of the level's map
	 */
	public void setLevelSize(int width, int height)
	{
		if(tileGrid == null)
		{
			gridWidth = width;
			gridHeight = height;
			tileGrid = new Tile[height][width];
		}
	}
	/**
	 * Sets the tile at a location
	 * 
	 * @param x position
	 * @param y position
	 * @param t tile to set to
	 */
	public boolean setTile(int x, int y, Tile t)
	{
		if(x < gridWidth)
			if(y < gridHeight)
				if(tileGrid != null)
				{
					tileGrid[y][x] = t;
					return true;
				}
		return false;
	}
	
	/**
	 * Gets tile at a location
	 * 
	 * @param x position
	 * @param y position
	 * @return tile
	 */
	public Tile getTile(int x, int y)
	{
		if(tileGrid != null)
			return tileGrid[y][x];
		else return null;
	}
	
	public void addEdge(Tile tile1, Tile tile2, Item key)
	{
		
	}
	
	/**
	 * @return the timer
	 */
	public int getTimer() {
		return timer;
	}
	/**
	 * @param timer the timer to set
	 */
	public void setTimer(int timer) {
		if(timer == 0)
		{
			this.timer = timer;
		}
	}
	/**
	 * @return the gridWidth
	 */
	public int getGridWidth() {
		return gridWidth;
	}
	/**
	 * @return the gridHeight
	 */
	public int getGridHeight() {
		return gridHeight;
	}
	/**
	 * @return the elevator
	 */
	public Room getElevator() {
		return elevator;
	}
	/**
	 * @param elevator the elevator to set
	 */
	public void setElevator(Room elevator) {
		if(elevator != null) this.elevator = elevator;
	}
}
