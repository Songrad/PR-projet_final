package ggf.game;

public class Player extends GameObject implements Comparable<Player>
{

	/** Nom du Player */
	private final String name;
	/** Score du Player */
	private int score;

	/**
	 * Construit une nouvelle instance de Player
	 * @param x position X
	 * @param y position Y
	 * @param name nom
	 */
	public Player(short x, short y, String name)
	{
		super(x, y, (short) 15);
		this.name = name;
		this.score = 0;
	}

	/**
	 * Obtient le nom du Player.
	 * @return le nom
	 */
	public String getName() { return name; }

	/**
	 * Obtient le score du Player.
	 * @return le score
	 */
	public int getScore() { return this.score; }

	/**
	 * Agrandit le rayon du Player d'une unité, sans dépasser 50.
	 */
	public void grow() {
		if (this.radius < 50)
			this.radius++;
	}

	/**
	 * Met à jour le score du Player.
	 * @param score nouveau score
	 */
	public void setScore(int score) { this.score = score; }

	/**
	 * Ajoute un point au score du Player.
	 */
	public void earnPoint()
	{
		this.score++;
	}

	/**
	 * Compare un Player à un autre.
	 * @param player Player avec lequel comparer.
	 * @return positif si le Player courant a un score plus élevé, négatif si
	 * l'autre joueur a un score plus élevé, zéro sinon.
	 */
	@Override
	public int compareTo(Player player)
	{
		return this.score - player.score;
	}
}


