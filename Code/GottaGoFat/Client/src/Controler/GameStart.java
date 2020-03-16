package Controler;

import Model.*;
import View.*;

/**
 * Classe servant de controleur pour le lancement du jeu et du visuel
 * @author Jérémy AUZOU, Dylan DÉPERROIS, Patrice MAISONNEUVE, Edouard VARIN
 */

public class GameStart {

	/**
	 * Variable
	 */
	private Game model;

	/**
	 * Contructeur par défaut
	 */
	public GameStart() {
		model = new Game((int)(500+Math.random()*501));
		FrameGame view = new FrameGame(model);
	}

	/**
	 * Méthode demandant le changement de coordonnées au métier
	 * @param int x
	 * @param int y
	 * @param int idP
	 */
	public void setCoordPlayer(int x,int y, int idP) {
		model.setCoordPlayer(x, y, idP);
	}

	/**
	 * Méthode demandant au métier si le joueur à touché l'objet
	 * @param int idP
	 */
	public boolean toucheItem(int idP) {
		return model.toucheItem(idP);
	}

	/**
	 * Main de lancement du jeu
	 */
	public static void main(String[] args) {
		new GameStart();
	}
}
