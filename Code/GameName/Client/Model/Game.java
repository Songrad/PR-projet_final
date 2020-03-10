/*
 * 
 */

import java.util.ArrayList;

public class Game
{
	private ArrayList<Player> listPlayer;
	private ArrayList<GameObject> listObject;

	public Board() {
		listPlayer = new ArrayList<Player>();
		listObject = new ArrayList<GameObject>();
		listPlayer.add(new Player(10, 20, "TOTO"));

		for (int i = 0;i < 10 ; i++ ) {
			int x = (int)(Math.random()*300);
			int y = (int)(Math.random()*300);
			listObject.add(new GameObject(x, y));
		}
	}

	public ArrayList<Player> getlistPlayer()    {return listPlayer;}
	public ArrayList<GameObject> getlistObject(){return listObject;}
}
