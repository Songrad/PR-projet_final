package Model;
public class GameObject{
	/** Default GameObject size */
	public static final int DEFAULT_SIZE = 5;
	private int r;
	private int x;
	private int y;
	private boolean taken;

	public GameObject(int x, int y){
		this(x, y, DEFAULT_SIZE);
	}

	public GameObject(int x, int y, int r)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		taken  = false;
	}

	public int getX(){return x;}
	public int getY(){return y;}
	public int getR(){return r;}

	public void setX(int x){ this.x = x; }
	public void setY(int y){ this.y = y; }
	public void setR(int r){ this.r = r; }
}
