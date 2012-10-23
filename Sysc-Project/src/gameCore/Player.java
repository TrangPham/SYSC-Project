
package gameCore;

import java.util.Scanner;
/**
 * A Character is a superclass for all of the animated creatures inside the game.
 * Player, and Monsters (subclass of NPC) are subclasses of character.
 * 
 * @author Group D
 * @author Main author: Karen Madore
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
public class Player extends Character
{	//------------Fields------------//
	private int stamina;
	
	//------------Constructors------------//
    /*
	 * Constructs a Player with a name, health, attack, stamina, and a current Tile position inside the game.
	 * @param name - the Name of the Player
	 * @param health - the health of the player.  When the health reaches zero, the player dies.  When the player attacks a monster, the players health=health-monstersAttackValue;
	 * @param attach - the attack value of the player.  
	 * @param stamina - the stamina determines how much inventory the player can carry.  Player's total inventory weight cannot exceed stamina.		
	 **/
	public Player(String name, int health, int attack, int stamina, Tile myPosition){
		super(name,health,attack,myPosition);
		this.stamina=stamina;
	}
	
	/*
	 * Moves the player in the direction specified by the direction string.  ie north, south, east,etc
	 * @param direction - direction string ie north, south, etc.
	 */
	public boolean move(String direction){
		boolean hasMoved=false;
		if(myPosition.canMove(direction)){
			this.myPosition=myPosition.moveCharacter(direction);  //move character to the next tile
			hasMoved=true;
		}
		return hasMoved;
	}
	
	/*
	 * Prompt the player to choose the item to pick up
	 * @return the item chosen 
	
	private int promptForItem(){
		Scanner s;
		String itemName;
			
		String pString="Items available for pick-up are: ";
		pString += this.inventory.toString()+ "\n";
		pString+="Please enter the name of the item to pick-up.\n";
		System.out.println(pString);
		
		s = new Scanner(System.in);
		itemName=s.next();
		int index = myPosition.getInventory().getIndex(itemName);
		if (index>=0){
			return index;
		}else{
			System.out.println("No such item to pick up.");
			return -1;
		}
	}
	*/
	
	/**
	 * Pickup method picks up the item on the current tile
	 *  
	 * 	Pick up the item and add it to the player's inventory if the itemName exists
	 * 
	 * @param 	name of the item the player wants to pick up
	 * @return	true if the item was picked up, false if the item does not exists.
	 */
	public boolean pickUpItem(String itemName){
		
		boolean itemPickedUp=false;
		//check to see if the item is a valid item
		int index = myPosition.getInventory().getIndex(itemName);
		if (index>=0){ //got valid item
			//pick-up the single item from the tile
			Item itemToPickup = myPosition.getInventory().getItem(index);
			this.inventory.addItem(itemToPickup);
			myPosition.removeItem(itemToPickup);
			itemPickedUp=true;
		}
		return itemPickedUp;
	}
	
	/**
	 * Drop method drops the item 
	 * @param 	name of the item the player want to drop
	 * @return	true if the item was dropped successfully, false if the item does not exists.
	 */
	public boolean drop(String itemName){
		boolean itemDropped=false;
		
		int index=inventory.getIndex(itemName);
		if(index>=0){//got a valid item
			//pick-up the single item 
			Item itemToDrop = inventory.getItem(index);
			this.inventory.removeItem(itemToDrop);
			itemDropped=true;
		}
		return itemDropped;
	}
	
	/**
	 * Look method returns a string of characters it sees in that direction
	 * @param 	direction to move
	 * @return	string of characters it sees
	 */
	public String look(String direction){
		String retString;
		if(myPosition.canMove(direction) && myPosition.hasCharacter(direction)){//there is a character in the direction the player wishes to move
			retString =  myPosition.getCharacter(direction).toString() + " is located " + direction +" of you.";
		}else{
			retString = "No characters.";
		}
		retString +="\n";
		return retString;
	}
	

	/**
	 * ViewInventory method returns an string representation of the player's inventory
	 * 
	 * @return	a list of items in the player's inventory as a string
	 */
	public String viewInventory(){
		String retString;
		retString = "";
		if(this.inventory.isEmpty()){
			retString = "Nothing in your inventory.";
		}else{
			retString = "The following items are in your inventory: " + this.inventory.toString();
		}
		
		retString +="\n";
		return retString;
	}
	
	public String viewHealth(){
		return  "Your health is: " + this.health +"\n";
	}
	
}
