package Model;
import Controler.GameStart;

import java.util.ArrayList;

/**
 * Classe permettant la gestion des joueurs et des objets
 * @author Jérémy AUZOU, Dylan DÉPERROIS, Patrice MAISONNEUVE, Edouard VARIN
 */

public class Game
{
	/**
	 * Variables
	 */
	private final int GAMESIZE = 600 ;

	private GameStart ctrl;

	private int nbObject;
	private int nbObjectVisible;
	private boolean toorique =true;
	private ArrayList<Player> listPlayer;
	private ArrayList<GameObject> listObject;

	/**
	 * Constructeur de la classe
	 * @param int nbO
	 */
	public Game(int nbO, GameStart controleur) {

		this.ctrl = controleur;
		this.nbObject = nbO;
		this.nbObjectVisible = nbO/10;
		listPlayer = new ArrayList<Player>();
		listObject = new ArrayList<GameObject>();
		listPlayer.add(new Player(300, 300, "TOTO", this.ctrl));
		Player pd = new Player(300, 300, "PD", this.ctrl);
		pd.setScore(9999);
		listPlayer.add(pd);
		Player nig = new Player(300, 300, "NIG", this.ctrl);
		nig.setScore(-5);
		listPlayer.add(nig);

		for (int i = 0;i < this.nbObject ; i++ ) {
			int x = (int)(Math.random()*(GAMESIZE - GameObject.DEFAULT_SIZE*8));
			int y = (int)(Math.random()*(GAMESIZE - GameObject.DEFAULT_SIZE*8));
			listObject.add(new GameObject(x, y));
		}
	}

	/**
	 * Méthodes pour récupérer la liste de joueur ou la liste d'objets
	 * @return
	 */
	public ArrayList<Player>     getlistPlayer(){return listPlayer;}
	public ArrayList<GameObject> getlistObject(){return listObject;}

	/**
	 * Méthode permettant de vérifier si le joueur a ramassé l'objet ou non
	 * @param int idP
	 * @return
	 */
	public boolean toucheItem(int idP) {
		boolean touche = false;
		Player p = listPlayer.get(idP);
		GameObject gom = null;

		for(int i = 0; i< Math.min(this.listObject.size(), this.nbObjectVisible); i++) {
			GameObject go = this.listObject.get(i);

			// Calculer la distance au carré (théorème de pythagore: a² + b² = c²)
			int dx = Math.abs(go.getX() - p.getX());
			int dy = Math.abs(go.getY() - p.getY());
			int dist = dx*dx + dy*dy;

			// Calculer la proximité minimale: le rayon du joueur
			int minDist = p.getR() * p.getR();

			if (dist <= minDist) {
				gom = go;
				p.AddScore();
				p.AddSize();
				touche = true;
			}
		}

		if (gom != null) {
			listObject.remove(gom);
		}

		return touche;
	}

	/**
	 * Méthode calculant les nouvelles coordonnées du joueur
	 * @param int x
	 * @param int y
	 * @param int idP
	 */
	public void setCoordPlayer(int x,int y, int idP) {
		if (toorique) {
			listPlayer.get(idP).setX(x+listPlayer.get(idP).getX()%(GAMESIZE));
			listPlayer.get(idP).setY(y+listPlayer.get(idP).getY()%(GAMESIZE));
			if(listPlayer.get(idP).getX() < 0) {listPlayer.get(idP).setX(GAMESIZE-listPlayer.get(idP).getR());}
			if(listPlayer.get(idP).getY() < 0) {listPlayer.get(idP).setY(GAMESIZE-listPlayer.get(idP).getR());}
		}
		else {
			listPlayer.get(idP).setX(x+listPlayer.get(idP).getX());
			listPlayer.get(idP).setY(y+listPlayer.get(idP).getY());
		}
	}

	public int getnbPaint() {
		return Math.min(this.listObject.size(), this.nbObjectVisible);
	}

	/**
	 * Méthode retournant la fin de partie si la liste d'objet a ramassé est vide
	 * @return
	 */
	public boolean gameDown(){return this.listObject.size() == 0;}

}
