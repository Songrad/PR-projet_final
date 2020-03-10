package GameName.Client.Controler;

import GameName.Client.Model.*;
import GameName.Client.View.*;

public class GameStart {
	public GameStart(){
		Game model = new Board();
		FrameGame view = new FrameGame(model);
	}

	public static void main(String[] args) {
		new GameStart();
	}
}
