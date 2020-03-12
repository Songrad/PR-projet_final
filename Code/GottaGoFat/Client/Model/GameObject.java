package Model;
public class GameObject{
	public static final int SIZE = 10;
	private int CoordX;
	private int CoordY;
	private boolean taken;

	public GameObject(int x, int y){
		CoordX = x;
		CoordY = y;
		taken  = false;
	}

	public int getX(){return CoordX;}
	public int getY(){return CoordY;}
	public void setX(int x){CoordX = x;}
	public void setY(int y){CoordY = y;}
	public boolean getTaken(){return taken;}
	public void flipTaken()   {taken = true;}

	public int getSize(){return (int)(2*Math.PI*SIZE);}
}
