package Controler;

import Model.*;
import View.*;

public class GameStart {
	private Game model;
	public GameStart(){
		model = new Game();
		FrameGame view = new FrameGame(model);
	}

	public static void main(String[] args) {
		new GameStart();
	}

	public void setCoordPlayer(int x,int y, int idP){
		model.setCoordPlayer(x, y, idP);
	}

	public boolean toucheItem(int idP){
		return model.toucheItem(idP);
	}
}
