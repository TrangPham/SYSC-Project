package gameCore;
import java.awt.Point;
import java.util.*;

/**
 * A Tile is the minimal unit in the game. They have a location and 
 * edges. It can contain items (in the form of an inventory) and a character.
 * 
 * @author Group D
 * @author Main author: Darrell Penner
 * 
 * Group D Members
 * ---------------
 * Karen Madore
 * Trang Pham
 * Darrell Penner
 * 
 *
 * @version 1.0
 */

public class Tile {
	
	//------------Fields------------//
	private Point location;
	private Map<String, Edge> edges; //CONSIDER: String (direction) can be enum?
	private Inventory inventory;
	private Character character;	
	private Room containingRoom;
	
	//------------Constructors------------//
    /**
	 * Constructs a Tile form a point and room
	 * @param Location of the Tile
	 */
	public Tile(Point location, Room containingRoom)
	{
		if (location == null || containingRoom == null){
			throw new IllegalArgumentException("A tile must have a location and a containing room");
		}
		
		edges = new HashMap<String, Edge>();
		inventory = new Inventory(); //start empty, avoids null pointers
		character = null; //no character
		
		this.location = location;
		this.containingRoom = containingRoom;
	}
	
	//------------Getters------------//
	//Note: no getter for containingRoom since that would allow 
	//arbitrary access to other tiles in the room
	
	/**
	 * 
	 * @return the items on the tile
	 */
	public Inventory getInventory(){
		return inventory;
	}
	
	public Inventory getInventory(String direction){
		if (!canCrossEdge(direction)){
			throw new IllegalArgumentException("Cannot cross that edge!");
		}
		
		return getNextTile(direction).getInventory();
	}
	/**
	 * Null if no character resides on the tile
	 * @return The character if one exists, null otherwise
	 */
	public Character getCharacter(){
		return character;
	}
	
	public Character getCharacter(String direction)
	{
		if (!canCrossEdge(direction)){
			throw new IllegalArgumentException("Cannot cross that edge!");
		}
		
		return getNextTile(direction).getCharacter();
	}
	
	public Point getLocation(){
		return new Point(location); //deep copy, no modifying
	}

	private Edge getEdge(String direction){
		if (!edges.containsKey(direction))
		{
			throw new IllegalArgumentException("Edge does not exist in that direction");
		}
		return edges.get(direction);
	}
	
	/**
	 * Gets the next tile in the specified direction. Throws if the edge between the two
	 * tiles cannot be crossed.
	 * 
	 * @param direction The direction of the next tile
	 * @return the Tile in the specified direction
	 */
	private Tile getNextTile(String direction){
		if (!canCrossEdge(direction))
		{
			throw new IllegalArgumentException("Tried get next tile, but edge between is not crossable");
		}
		return getEdge(direction).getOtherTile(this);
	}
	
	//------------Setters------------//
	public void setEdge(String direction, Edge edge){
		if (edges.containsKey(direction)){
			throw new IllegalArgumentException("This edge has already been set");
		}
		
		edges.put(direction, edge);
	}
	
	//------------Adding and Removing------------//
	/**
	 * Adds a character to the tile
	 * @param c the Character to be added
	 */
	public void addCharacter(Character c){
		if (hasCharacter()){
			throw new UnsupportedOperationException("Can't add character to tile - there already is one");
		}
		
		character = c;
	}
	
	/**
	 * Removes the Character on the tile
	 * @return the Character that was on the tile, null if none
	 */
	public Character removeCharacter(){
		Character removed = character;
		character = null;
		return removed;
	}
	public void addItem(Inventory item){ //TEMP
		//inventory.add(item);
	}
	
	//------------Character Movement------------//
	/**
	 * Checks whether the edge in the given direction is crossable by the character in the tile
	 * @param direction The direction of desired crossing
	 * @return True if the edge is crossable, false otherwise
	 */
	private boolean canCrossEdge(String direction){
		if (!hasCharacter()){
			throw new UnsupportedOperationException("There is no character on this tile!");
		}
		return getEdge(direction).canCross(character);
	}
	
	/**
	 * Can only move if capable of crossing the edge, and another character is not
	 * currently occupying the destination tile.
	 * @param direction The direction of possible movement 
	 * @return whether or not the character can move in that direction
	 */
	public boolean canMove(String direction){
		return canCrossEdge(direction) && !getNextTile(direction).hasCharacter();
	}

	/**
	 * Moves the character to the tile in the given direction.
	 * @param direction The direction to move the player
	 * @throws IllegalArgumentException When the player cannot move in the given direction. Try calling canMove(direction) first.
	 * @return The new tile of the character
	 */
	public Tile moveCharacter(String direction) throws IllegalArgumentException {
		if (!canMove(direction)){
			throw new IllegalArgumentException("Cannot move in that direction, something is blocking the way!");
		}
		
		return getEdge(direction).cross(this, character); //cross and return the character's new Tile
	}
	
	//------------Checks------------//
	public boolean hasCharacter(){
		return character != null;
	}
	/**
	 * Checks if the tile in the given direction has a Character
	 * @param direction
	 * @return
	 */
	public boolean hasCharacter(String direction)
	{
		return canCrossEdge(direction) && getNextTile(direction).hasCharacter();
	}
	
	public boolean hasItems(){ //TEMP
		return false; /* inventory.isEmpty() && */
	}
	
	public boolean isEmpty(){
		return !hasItems() && !hasCharacter();
	}
	
	/* REMOVED - Character class should handle these
	public void attackCharacter(String direction) throws IllegalArgumentException{
		if (!hasCharacter(direction)){
			throw new IllegalArgumentException("Cannot attack in that direction");
		}
		
		//This tile's character attacks the next tile's character
		//character.attack(getNextTile(direction).getCharacter()); TEMP
		//TEMP check for death
	}
	
	//------------Item Handling------------//
	public boolean takeItemFromCharacter(Item i){ //TEMP
		return false;
	}
	public boolean giveItemToCharacter(Item i){ //TEMP
		return false;
	}
	
	//------------Looking------------//
	//Note that all "lookFor*object*" methods return a copy so that the original
	//cannot be modified
	public Character lookForCharacter(String direction){
		if (!hasCharacter(direction)){
			return null;
		}

		return null; //TEMP deep copy of character
	}
	
	public Inventory lookForItems(String direction){
		return null; //TEMP deep copy of inventory
	}*/
	
	
}
