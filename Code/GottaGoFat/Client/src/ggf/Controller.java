package ggf;

import ggf.game.Game;
import ggf.game.GameObject;
import ggf.game.Player;
import ggf.gui.menu.FrameHome;

import java.util.ArrayList;

public class Controller
{
	/** Instance de Game */
	private Game game;
	/** Instance de FrameHome */
	private FrameHome frame;

	/** Messages */
	private String chat = "";

	/**
	 * Construit un Controller avec un nombre de GameObjects compris entre 50 et 150.
	 */
	public Controller() {
		this((int) ((Math.random() * 100) + 50));
	}

	/**
	 * Construit un Controller avec un nombre donné de GameObjects
	 * @param objectCount nombre de GameObjects
	 */
	public Controller(int objectCount) {
		this.game = new Game(this, objectCount);
		this.game.addPlayer("Joueur 1");
		this.game.addPlayer("Joueur 2");
		this.frame = new FrameHome(this);
	}

	/**
	 * Déplace le Player d'identifiant donné
	 * @param id identifiant du Player
	 * @param deltaX déplacement horizontal
	 * @param deltaY déplacement vertical
	 */
	public void movePlayer(int id, short deltaX, short deltaY) {
		this.game.movePlayer(id, deltaX, deltaY);
	}

	/**
	 * Obtient le nombre de GameObjects visibles
	 * @return nombre de GameObjects
	 */
	public int getVisibleObjectCount() {
		return this.game.getVisibleObjectCount();
	}

	/**
	 * Obtient la liste complète des GameObjects
	 * @return liste de GameObjects
	 */
	public ArrayList<GameObject> getObjectList() {
		return this.game.getObjectList();
	}

	/**
	 * Obtient la liste des Players
	 * @return liste de Players
	 */
	public ArrayList<Player> getPlayerList() {
		return this.game.getPlayerList();
	}

	/**
	 * Envoyer un message texte.
	 * @param message message
	 */
	public void sendMessage(String message)
	{
		this.chat += "[Local] " + message;
	}

	/**
	 * Obtenir l'historique des messages.
	 * @return messages
	 */
	public String getMessages()
	{
		return this.chat;
	}

	/**
	 * Méthode principale.
	 * @param args arguments de ligne de commande
	 */
	public static void main(String[] args) {
		new Controller();
	}

	/**
	 * Met à jour l'interface GUI.
	 */
	public void updateGui()
	{
		this.frame.majIHM();
	}
}
