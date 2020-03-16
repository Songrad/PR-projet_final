package Model;
/**Classe Permetant la Generation de joueur dans le Jeu
 * Elle hérithe de la classe GameObject pour éviter une répétition du code
 * @author Jérémy AUZOU, Dylan DÉPERROIS, Patrice MAISONNEUVE, Edouard VARIN
 */
public class Player extends GameObject {
	/**Variable globale*/
	private String name;
	private int score;

	/**Constructeur par Default de la classe
	 * @param int x
	 * @param int y
   * @param String n
   */
	public Player(int x,int y,String n){
		super(x, y, 15);
		name = n;
		score = 0;
	}
	/**
	* Retourne la valeur nom du joueur
	* @return
	*/
	public String getName()        {return name;}

	/**
	* Remplace la valeur nom du joueur
	* @param String n
	*/
	public void   setName(String n){name = n;}
	/**
	* Retourne la valeur score du joueur
	* @return
	*/
	public int    getScore()       {return score;}

	/**
	* Ajoute +1 au score du joueur lorsqu'il récupere une boule
	*/
	public void   AddScore()       {score += 1;}

	/**
	* remplace la taille du joueur en prenant la plus petite valeur entre 50 et
	* le rayon actuelle du joueur +1 se qui permet de fixer une taille maximal au joueur
	*/
	public void   AddSize()        {this.setR(Math.min(50,this.getR()+1));}
}
