package ggf.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game
{
	public final static short MAP_SIZE = 600;

	private final int objectCount;
	private final int visibleObjectCount;
	private final boolean looping;
	private boolean invisible;

	private final ArrayList<Player> playerList;
	private final ArrayList<GameObject> objectList;

	/**
	 * Construit une nouvelle instance de Game
	 * @param objectCount nombre d'objets total à récupérer
	 */
	public Game(int objectCount, boolean invisible)
	{
		this.objectCount = objectCount;
		this.visibleObjectCount = Math.max(objectCount / 10, 10);
		this.looping = true;
		this.invisible = invisible;

		this.playerList = new ArrayList<>();
		this.objectList = new ArrayList<>();

		this.initGame();
	}

	/**
	 * Initialise la partie avec les paramètres passés aux constructeur.
	 */
	private void initGame() {
		for (int i = 0; i < objectCount; i++) {
			objectList.add(new GameObject(
					(short)(Math.random() * MAP_SIZE),
					(short)(Math.random() * MAP_SIZE)
			));
		}
	}

	/**
	 * Ajoute un Player à la partie.
	 * @param name nom du Player
	 * @return identifiant du Player
	 */
	public int addPlayer(String name, Color color)
	{
		this.playerList.add(new Player((short)(MAP_SIZE / 2), (short)(MAP_SIZE / 2), name, color));
		return this.playerList.size() - 1;
	}

	/**
	 * Calcule les collisions entre les GameObjects et les Players
	 */
	private void processCollisions() {
		boolean hit = false;
		for (Player p : playerList) {
			ArrayList<GameObject> hitObjects = new ArrayList<>();

			for (int i = 0, limit = Math.min(this.objectList.size(), this.visibleObjectCount);
				 i < limit;
				 i++) {
				GameObject go = this.objectList.get(i);

				short x = p.getX();
				short y = p.getY();
				short radius = p.getRadius();

				boolean goHit = isColliding(x, y, radius, go);
				// Débordement gauche/haut
				if (x < radius) {
					goHit |= isColliding((short) (x + Game.MAP_SIZE), y, radius, go);
				}

				if (y < radius) {
					goHit |= isColliding(x, (short)(y + Game.MAP_SIZE), radius, go);
				}

				// Débordement bas/droite
				if (x > Game.MAP_SIZE - radius) {
					goHit |= isColliding((short)(x - Game.MAP_SIZE), y, radius, go);
				}

				if (y > Game.MAP_SIZE - radius) {
					goHit |= isColliding(x, (short)(y - Game.MAP_SIZE), radius, go);
				}

				// Débordement des coins
				if (x < radius && y < radius) { // HAUT GAUCHE
					goHit |= isColliding((short)(x + Game.MAP_SIZE), (short)(y + Game.MAP_SIZE), radius, go);
				} else if (x < radius && y > Game.MAP_SIZE - radius) { // BAS GAUCHE
					goHit |= isColliding((short)(x + Game.MAP_SIZE), (short)(y - Game.MAP_SIZE), radius, go);
				} else if  (x > Game.MAP_SIZE - radius && y > Game.MAP_SIZE - radius) { // BAS DROITE
					goHit |= isColliding((short)(x - Game.MAP_SIZE), (short)(y - Game.MAP_SIZE), radius, go);
				} else if (x > Game.MAP_SIZE - radius && y < radius) { // HAUT DROITE
					goHit |= isColliding((short)(x - Game.MAP_SIZE), (short)(y + Game.MAP_SIZE), radius, go);
				}

				if (goHit) {
					hit = true;
					hitObjects.add(go);
					p.earnPoint();
					p.grow();
				}
			}

			this.objectList.removeAll(hitObjects);
		}
	}

	/**
	 * Détermine si il y a collision entre l'objet de paramètres (x, y, radius) et le GameObject passés en paramètres.
	 * @param x position X
	 * @param y position Y
	 * @param radius rayon
	 * @param object GameObject à comparer
	 * @return true si l'objet est en collision, false sinon.
	 */
	private boolean isColliding(short x, short y, short radius, GameObject object) {
		// Calculer la distance entre l'objet et le joueur
		int dx = Math.abs(object.getX() - x);
		int dy = Math.abs(object.getY() - y);
		int dist = dx*dx + dy*dy; // a² + b²

		// Calculer la proximité minimale: le rayon du joueur
		int minDist = radius * radius; // c²

		return dist <= minDist;
	}

	/**
	 * Déplace le Player d'identifiant id
	 * @param id identifiant du Player
	 * @param deltaX déplacement horizontal
	 * @param deltaY déplacement vertical
	 */
	public void movePlayer(int id, short deltaX, short deltaY)
	{
		Player p = this.playerList.get(id);

		short newX = (short) (p.getX() + deltaX);
		short newY = (short) (p.getY() + deltaY);
		short radius = p.getRadius();

		if (looping) {
			if (newX > MAP_SIZE) newX -= MAP_SIZE;
			if (newX < 0       ) newX += MAP_SIZE;

			if (newY > MAP_SIZE) newY -= MAP_SIZE;
			if (newY < 0       ) newY += MAP_SIZE;
		} else {
			if (newX > MAP_SIZE - radius) newX = (short) (MAP_SIZE - radius);
			if (newX < radius           ) newX = radius;

			if (newY > MAP_SIZE - radius) newY = (short) (MAP_SIZE - radius);
			if (newY < radius           ) newY = radius;
		}

		p.setX(newX);
		p.setY(newY);

		processCollisions();
	}

	/**
	 * Obtient le nombre d'objets restants.
	 * @return nombre d'objet
	 */
	public int getRemainingObjectCount() { return this.objectList.size(); }

	/**
	 * Permet de savoir si la partie est terminée.
	 * @return true s'il ne reste aucun GameObject à collecter, false sinon.
	 */
	public boolean gameOver() {
		return this.objectList.size() == 0;
	}

	/**
	 * Obtient la liste des objets visibles.
	 * @return liste d'objet
	 */
	public ArrayList<GameObject> getVisibleObjectList()
	{
		ArrayList<GameObject> al = new ArrayList<>();

		for (int i = 0, limit = Math.min(this.visibleObjectCount, this.objectList.size());
			 i < limit; i++) {
			al.add(this.objectList.get(i));
		}

		return al;
	}

	/**
	 * Obtient la liste des joueurs.
	 * @return liste de joueurs
	 */
	public ArrayList<Player> getPlayerList()
	{
		return playerList;
	}

	public boolean getInvisible()
	{
		return this.invisible;
	}
}
