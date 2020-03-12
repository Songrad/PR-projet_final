/*
 *
 */
package Model;
import java.util.ArrayList;

public class Game
{
	private ArrayList<Player> listPlayer;
	private ArrayList<GameObject> listObject;

	public Game() {
		listPlayer = new ArrayList<Player>();
		listObject = new ArrayList<GameObject>();
		listPlayer.add(new Player(300, 300, "TOTO"));

		for (int i = 0;i < 3000 ; i++ ) {
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
		for (GameObject go : listObject ) {
			// Calculer la distance au carré (théorème de pythagore: a² + b² = c²)
			int dx = Math.abs(go.getX() - p.getX());
			int dy = Math.abs(go.getY() - p.getY());
			int dist = dx*dx + dy*dy;

			// Calculer la proximité minimale: le rayon du joueur
			int minDist = p.getR() * p.getR();

			if (dist <= minDist)
			{
				System.out.println("Objet pris!");
				go.setTaken();
				p.AddScore();
				touche = true;
			}
		}
		return touche;
	}
	public void setCoordPlayer(int x,int y, int idP){
		listPlayer.get(idP).setX(x+listPlayer.get(idP).getX());
		listPlayer.get(idP).setY(y+listPlayer.get(idP).getY());
	}
}
