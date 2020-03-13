package Model;
public class Player extends GameObject {
	private String name;
	private int score;

	public Player(int x,int y,String n){
		super(x, y, 15);
		name = n;
		score = 0;
	}

	public String getName(){return name;}
	public void   setName(String n){name = n;}
	public int    getScore(){return score;}
	public void   AddScore(){score += 1;}
	public void   AddSize(){this.setR(Math.min(30,this.getR()+1));}
}
