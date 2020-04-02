package ggf.net.messages.server;

import ggf.game.Game;
import ggf.game.GameObject;
import ggf.game.Player;
import ggf.net.MemoryStream;
import ggf.net.messages.GameMessage;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends GameMessage
{
	private ArrayList<Player> players;
	private ArrayList<GameObject> visibleObjects;
	private int remainingObjectCount;
	private boolean invisible;

	public GameState(Game game) {
		this.players = game.getPlayerList();
		this.visibleObjects = game.getVisibleObjectList();
		this.remainingObjectCount = game.getRemainingObjectCount();
		this.invisible = game.getInvisible();
	}

	public GameState() {}


	@Override
	public int getMessageType()
	{
		return GameMessage.GAME_STATE;
	}

	@Override
	public byte[] getDataBytes()
	{
		MemoryStream ms = new MemoryStream();

		// Type de paquet
		ms.putInt(GameMessage.GAME_STATE);

		// Joueurs
		ms.putInt(this.players.size());

		for (Player p : players) {
			ms.putString(p.getName());

			ms.putShort(p.getX());
			ms.putShort(p.getY());
			ms.putShort(p.getRadius());

			ms.putInt(p.getScore());
			ms.putInt(p.getColor().getRGB());
		}

		// Objets
		ms.putInt(this.visibleObjects.size());
		for (GameObject go : visibleObjects) {
			ms.putShort(go.getX());
			ms.putShort(go.getY());
		}

		// Objets restants
		ms.putInt(this.remainingObjectCount);

		// Invisible

		ms.putBoolean(this.invisible);

		return ms.toArray();
	}

	@Override
	public GameMessage private_parseMessage(byte[] bytes)
	{
		MemoryStream ms = new MemoryStream(bytes);
		ms.seek(4);

		// Joueurs
		int playerCount = ms.getInt();
		this.players = new ArrayList<>();

		for (int i = 0; i < playerCount; i++) {
			String name = ms.getString();
			short x = ms.getShort(),
					y = ms.getShort(),
					radius = ms.getShort();

			int score = ms.getInt(),
					color = ms.getInt();

			Player p = new Player(x, y, name, new Color(color));

			p.setRadius(radius);
			p.setScore(score);

			this.players.add(p);
		}

		// Objets
		int visibleObjectCount = ms.getInt();
		this.visibleObjects = new ArrayList<>();

		for (int i = 0; i < visibleObjectCount; i++) {
			short x = ms.getShort();
			short y = ms.getShort();

			this.visibleObjects.add(new GameObject(x, y));
		}

		this.remainingObjectCount = ms.getInt();

		this.invisible = ms.getBoolean();

		return this;
	}

	public ArrayList<Player> getPlayers()
	{
		return players;
	}

	public ArrayList<GameObject> getVisibleObjects()
	{
		return visibleObjects;
	}

	public int getRemainingObjectCount()
	{
		return remainingObjectCount;
	}

	public boolean isInvisible()
	{
		return invisible;
	}
}
