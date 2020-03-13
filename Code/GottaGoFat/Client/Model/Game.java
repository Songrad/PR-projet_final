/*
 *
 */
package Model;
import java.util.ArrayList;

public class Game
{
	private int nbObject;
	private int nbObjectVisible;
	private ArrayList<Player> listPlayer;
	private ArrayList<GameObject> listObject;

	public Game(int nbO) {
		this.nbObject = nbO;
		this.nbObjectVisible = nbO/10;
		listPlayer = new ArrayList<Player>();
		listObject = new ArrayList<GameObject>();
		listPlayer.add(new Player(300, 300, "TOTO"));

		for (int i = 0;i < this.nbObject ; i++ ) {
			int x = (int)(GameObject.DEFAULT_SIZE *2+Math.random()*600-GameObject.DEFAULT_SIZE);
			int y = (int)(GameObject.DEFAULT_SIZE *2+Math.random()*600-GameObject.DEFAULT_SIZE);
			listObject.add(new GameObject(x, y));
		}
	}

	public ArrayList<Player> getlistPlayer()    {return listPlayer;}
	public ArrayList<GameObject> getlistObject(){return listObject;}

	public boolean toucheItem(int idP){
		boolean touche = false;
		Player p = listPlayer.get(idP);
		GameObject gom = null;
		for(int i = 0; i< Math.min(this.listObject.size(), this.nbObjectVisible);i++){
				GameObject go = this.listObject.get(i);
			// Calculer la distance au carré (théorème de pythagore: a² + b² = c²)
			int dx = Math.abs(go.getX() - p.getX());
			int dy = Math.abs(go.getY() - p.getY());
			int dist = dx*dx + dy*dy;

			// Calculer la proximité minimale: le rayon du joueur
			int minDist = p.getR() * p.getR();

			if (dist <= minDist){
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
	public void setCoordPlayer(int x,int y, int idP){
		listPlayer.get(idP).setX(x+listPlayer.get(idP).getX());
		listPlayer.get(idP).setY(y+listPlayer.get(idP).getY());
	}
	public int getnbPaint(){
			return Math.min(this.listObject.size(), this.nbObjectVisible);
		}

	public boolean gameDown(){return this.listObject.size() == 0;}
}
