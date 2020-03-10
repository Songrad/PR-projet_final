public class Player extends GameObject {
	private String name;

	public Player(int x,int y,String n){
		super(x, y);
		name = n;
	}

	public String getName(){return name;}
	public void   setName(String n){name = n;}
}
