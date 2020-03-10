package Model;
public class GameObject{
	private final int SIZE = 10;
	private int CoordX;
	private int CoordY;

	public GameObject(int x, int y){
		CoordX = x;
		CoordY = y;
	}

	public int getX(){return CoordX;}
	public int getY(){return CoordY;}
	public void setX(int x){CoordX = x;}
	public void setY(int y){CoordY = y;}

	public int getSize(){return (int)(2*Math.PI*SIZE);}
}
