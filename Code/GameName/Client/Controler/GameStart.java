package Controler;

import Model.*;
import View.*;

public class GameStart {
	public GameStart(){
		Game model = new Game();
		FrameGame view = new FrameGame(model);
	}

	public static void main(String[] args) {
		new GameStart();
	}
}
