package Model;

import Controler.GameStart;

import java.util.Comparator;

/**Classe Permetant la Generation de joueur dans le Jeu
 * Elle hérithe de la classe GameObject pour éviter une répétition du code
 * @author Jérémy AUZOU, Dylan DÉPERROIS, Patrice MAISONNEUVE, Edouard VARIN
 */
public class Player extends GameObject implements Comparable<Player> {
	/**Variable globale*/
	private String name;
	private int score;
	private GameStart ctrl;

	/**Constructeur par Default de la classe
	 * @param int x
	 * @param int y
   * @param String n
   */
	public Player(int x, int y, String n, GameStart controleur){
		super(x, y, 15);
		name = n;
		score = 0;
		this.ctrl = controleur;
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
	public void   AddScore(){
		score += 1;
		this.ctrl.majIHM();
	}

	//Pour débug le programme
	public void setScore(int score)
	{
		this.score = score;
	}

	/**
	* remplace la taille du joueur en prenant la plus petite valeur entre 50 et
	* le rayon actuelle du joueur +1 se qui permet de fixer une taille maximal au joueur
	*/
	public void   AddSize()        {this.setR(Math.min(50,this.getR()+1));}

	@Override
	public int compareTo(Player anotherInstance)
	{
		return this.score - anotherInstance.score;
	}
}

class ScorePlayerComparator implements Comparator<Player>{
	public int compare(Player player1, Player player2) {
		return player1.getScore() - player2.getScore();
	}
}
