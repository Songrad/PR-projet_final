package ggf.game;

public class GameObject
{
	/** Taille par défaut du GameObject */
	private static final short DEFAULT_SIZE = 5;

	/** Rayon du GameObject */
	protected short radius;
	/** Position X du GameObject */
	protected short x;
	/** Position Y du GameObject */
	protected short y;

	/**
	 * Construit un nouveau GameObject avec une position donnée et le rayon par défaut.
	 * @param x Position X
	 * @param y Position Y
	 */
	public GameObject(short x, short y) { this(x, y, DEFAULT_SIZE); }

	/**
	 * Construit un nouveau GameObject avec une position et un rayon donné.
	 * @param x Position X
	 * @param y Position Y
	 * @param radius Rayon
	 */
	public GameObject(short x, short y, short radius) {
		this.x      = x;
		this.y      = y;
		this.radius = radius;
	}

	/**
	 * Obtient le rayon de ce GameObject
	 * @return rayon
	 */
	public short getRadius() { return radius; }

	/**
	 * Définit le rayon du GameObject
	 * @param radius nouveau rayon
	 */
	public void setRadius(short radius) { this.radius = radius; }

	/**
	 * Obtient la position X du GameObject
	 * @return position X
	 */
	public short getX() { return x; }

	/**
	 * Définit la nouvelle position X du GameObject
	 * @param x nouvelle position X
	 */
	public void setX(short x) { this.x = x; }

	/**
	 * Obtient la position Y du GameObject
	 * @return position Y
	 */
	public short getY() { return y; }

	/**
	 * Définit la nouvelle position Y du GameObject
	 * @param y nouvelle position Y
	 */
	public void setY(short y) { this.y = y; }
}
