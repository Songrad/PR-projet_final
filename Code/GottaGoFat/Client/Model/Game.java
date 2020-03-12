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

		for (int i = 0;i < 100 ; i++ ) {
			int x = (int)(GameObject.SIZE*2+Math.random()*600-GameObject.SIZE);
			int y = (int)(GameObject.SIZE*2+Math.random()*600-GameObject.SIZE);
			listObject.add(new GameObject(x, y));
		}
	}

	public ArrayList<Player> getlistPlayer()    {return listPlayer;}
	public ArrayList<GameObject> getlistObject(){return listObject;}
}
