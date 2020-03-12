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

		for (int i = 0;i < 30 ; i++ ) {
			int x = (int)(GameObject.SIZE*2+Math.random()*600-GameObject.SIZE);
			int y = (int)(GameObject.SIZE*2+Math.random()*600-GameObject.SIZE);
			listObject.add(new GameObject(x, y));
		}
	}

	public ArrayList<Player> getlistPlayer()    {return listPlayer;}
	public ArrayList<GameObject> getlistObject(){return listObject;}

	public boolean toucheItem(int idP){
		boolean touche = false;
		Player p = listPlayer.get(idP);
		int r    = GameObject.SIZE/2;
		for (GameObject go : listObject ) {
			if ((go.getX() >= (p.getX() - r) && go.getX() <= (p.getX() + r) )&&
					(go.getY() >= (p.getY() - r) && go.getY() <= (p.getY() + r) )  ) {
						System.out.println("Object prie");
						go.flipTaken();
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
