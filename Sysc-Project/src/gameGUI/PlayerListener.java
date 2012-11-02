package gameGUI;

public interface PlayerListener {
	public void itemAdded(PlayerEvent e);
	public void itemDropped(PlayerEvent e);
	public void statsChanged(PlayerEvent e);
}
