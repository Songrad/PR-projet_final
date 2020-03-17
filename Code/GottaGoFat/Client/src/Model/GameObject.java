package Model;
/**Classe Permetant la Generation d'object dans le Jeu
 * @author Jérémy AUZOU, Dylan DÉPERROIS, Patrice MAISONNEUVE, Edouard VARIN
 */
public class GameObject{
	/** Variable Static */
	public static final int DEFAULT_SIZE = 5;

	/**Variable globale*/
	private int r;
	private int x;
	private int y;

	/**Constructeur par Default de la classe
	 * @param int x
   * @param int y
   */
	public GameObject(int x, int y){
		this(x, y, DEFAULT_SIZE);
	}

	/**Constructeur Complet d'un object ou l'on peux interagir sur sa taille
	 * @param int x
	 * @param int y
   * @param int r
   */
	public GameObject(int x, int y, int r){
		this.x = x;
		this.y = y;
		this.r = r;
	}

	/**
	* Retourne la valeur de r
	* @return
	*/
	public int getR() { return r; }

	/**
	* Remplace la valeur de r
	* @param int r
	*/
	public void setR(int r) { this.r = r; }

	/**
	* Retourne la valeur de x
	* @return
	*/
	public int getX() { return x; }

	/**
	* Remplace la valeur de x
	* @param int x
	*/
	public void setX(int x) { this.x = x; }

	/**
	* Retourne la valeur de y
	* @return
	*/
	public int getY() { return y; }

	/**
	* Remplace la valeur de y
	* @param int y
	*/
	public void setY(int y) { this.y = y; }
}
