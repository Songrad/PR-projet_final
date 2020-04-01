package Controler;

import Model.*;
import View.Menu.FrameHome;

import java.util.ArrayList;

/**
 * Classe servant de controleur pour le lancement du jeu et du visuel
 * @author Jérémy AUZOU, Dylan DÉPERROIS, Patrice MAISONNEUVE, Edouard VARIN
 */

public class GameStart {

	/**
	 * Variable
	 */
	private Game model;
	private String message = "";
	private FrameHome frameHome;

	/**
	 * Contructeur par défaut
	 */
	public GameStart() {
		model = new Game((int)(500+Math.random()*501), this);
		this.frameHome = new FrameHome(this);
	}



	public void envoyerMessage(String message){this.message += "\n[CHIENNE] " + message;}

	public String getMessagesChat(){return this.message;}
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

	public ArrayList<Player> getListPlayer()
	{
		return this.model.getlistPlayer();
	}

	public ArrayList<GameObject> getListObject(){
		return this.model.getlistObject();
	}

	public int getNbPaint(){
		return this.model.getnbPaint();
	}

	public void majIHM()
	{
		this.frameHome.majIHM();
	}



	/**
	 * Main de lancement du jeu
	 */
	public static void main(String[] args) {
		new GameStart();
	}
}
