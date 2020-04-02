package ggf.net;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Classe pour gérer un flux de données en mémoire.
 *
 * @author Jérémy AUZOU
 * @version 1.0
 */
public class MemoryStream
{
	private byte[] buffer;
	private int position;
	private int length;

	private boolean canSeek;
	private boolean canWrite;
	private boolean canRead;

	/**
	 * Crée une nouvelle instance de MemoryStream pour écrire dedans.
	 */
	public MemoryStream()
	{
		this.buffer = new byte[256];
		this.position = 0;
		this.length = 0;

		this.canWrite = true;
		this.canRead = false;
		this.canSeek = false;
	}

	/**
	 * Crée une nouvelle instance de MemoryStream avec des données pour les lire.
	 *
	 * @param data tableau de données
	 */
	public MemoryStream(byte[] data)
	{
		this.buffer = new byte[data.length];
		System.arraycopy(data, 0, this.buffer, 0, data.length);
		this.position = 0;
		this.length = data.length;

		this.canWrite = false;
		this.canSeek = true;
		this.canRead = true;
	}

	/**
	 * Multiplie la taille du tampon de données par 1.5
	 */
	private void expand()
	{
		byte[] tmp = new byte[(int) (buffer.length * 1.5)];
		System.arraycopy(this.buffer, 0, tmp, 0, this.buffer.length);
		this.buffer = tmp;
	}

	// ECRITURE

	/**
	 * Ecrit un tableau d'octets au bout de ce MemoryStream.
	 *
	 * @param bytes octets à écrire
	 * @throws UnsupportedOperationException si ce Stream n'est pas accessible en écriture
	 */
	public void putBytes(byte[] bytes)
	{
		if (!canWrite) throw new UnsupportedOperationException();

		if (bytes.length + this.position + 1 > buffer.length)
			expand();

		System.arraycopy(bytes, 0, this.buffer, this.position, bytes.length);

		this.position += bytes.length;
		this.length += bytes.length;
	}

	/**
	 * Ecrit un booléen au bout de ce MemoryStream.
	 *
	 * @param b booléen à écrire.
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en écriture
	 */
	public void putBoolean(boolean b)
	{
		putByte((byte) (b ? 1 : 0));
	}

	/**
	 * Ecrit un octet au bout de ce MemoryStream.
	 *
	 * @param b octet à écrire.
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en écriture
	 */
	public void putByte(byte b)
	{
		putBytes(new byte[]{b});
	}

	/**
	 * Ecrit un entier court au bout de ce MemoryStream.
	 *
	 * @param s entier court à écrire
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en écriture
	 */
	public void putShort(short s)
	{
		putBytes(ByteBuffer.allocate(Short.SIZE / 8).putShort(s).array());
	}

	/**
	 * Ecrit un entier au bout de ce MemoryStream.
	 *
	 * @param i entier à écrire
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en écriture
	 */
	public void putInt(int i)
	{
		putBytes(ByteBuffer.allocate(Integer.SIZE / 8).putInt(i).array());
	}

	/**
	 * Ecrit un entier long au bout de ce MemoryStream.
	 *
	 * @param l entier long à écrire
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en écriture
	 */
	public void putLong(long l)
	{
		putBytes(ByteBuffer.allocate(Long.SIZE / 8).putLong(l).array());
	}

	/**
	 * Ecrit une chaîne de caractères au bout de ce MemoryStream.
	 *
	 * @param str chaîne à écrire
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en écriture
	 */
	public void putString(String str)
	{
		byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
		putInt(strBytes.length);
		putBytes(strBytes);
	}

	// LECTURE

	/**
	 * Lit un nombre donné d'octets dans le MemoryStream.
	 *
	 * @param count nombre d'octets à lire
	 * @return octets lus
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en lecture
	 * @throws UnsupportedOperationException si le nombre d'octets à lire dépasse de la fin du stream
	 */
	public byte[] getBytes(int count)
	{
		if (!canRead) throw new UnsupportedOperationException();

		if (this.position + count > buffer.length)
			throw new UnsupportedOperationException();

		byte[] data = new byte[count];

		System.arraycopy(this.buffer, this.position, data, 0, count);
		this.position += count;

		return data;
	}

	/**
	 * Obtient un booléen du MemoryStream.
	 *
	 * @return un booléen
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en lecture
	 * @throws UnsupportedOperationException si le nombre d'octets à lire dépasse de la fin du stream
	 */
	public boolean getBoolean()
	{
		return getByte() == 1;
	}

	/**
	 * Obtient un octet du MemoryStream.
	 *
	 * @return un octet
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en lecture
	 * @throws UnsupportedOperationException si le nombre d'octets à lire dépasse de la fin du stream
	 */
	public byte getByte()
	{
		return getBytes(1)[0];
	}

	/**
	 * Obtient un entier court du MemoryStream.
	 *
	 * @return un entier court
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en lecture
	 * @throws UnsupportedOperationException si le nombre d'octets à lire dépasse de la fin du stream
	 */
	public short getShort()
	{
		return ByteBuffer.wrap(getBytes(Short.SIZE / 8)).getShort();
	}

	/**
	 * Obtient un entier standard du MemoryStream.
	 *
	 * @return un entier standard
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en lecture
	 * @throws UnsupportedOperationException si le nombre d'octets à lire dépasse de la fin du stream
	 */
	public int getInt()
	{
		return ByteBuffer.wrap(getBytes(Integer.SIZE / 8)).getInt();
	}

	/**
	 * Obtient un entier long du MemoryStream.
	 *
	 * @return un entier long
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en lecture
	 * @throws UnsupportedOperationException si le nombre d'octets à lire dépasse de la fin du stream
	 */
	public long getLong()
	{
		return ByteBuffer.wrap(getBytes(Long.SIZE / 8)).getLong();
	}

	/**
	 * Obtient une chaîne de caractères du MemoryStream.
	 *
	 * @return une chaîne de caractères
	 * @throws UnsupportedOperationException si ce stream n'est pas accessible en lecture
	 * @throws UnsupportedOperationException si le nombre d'octets à lire dépasse de la fin du stream
	 */
	public String getString()
	{
		int length = getInt();
		return new String(getBytes(length), StandardCharsets.UTF_8);
	}

	/**
	 * Retourne les octets contenus dans ce MemoryStream sous forme de tableau d'octets.
	 *
	 * @return le contenu du MemoryStream
	 */
	public byte[] toArray()
	{
		byte[] data = new byte[this.length];
		System.arraycopy(this.buffer, 0, data, 0, this.length);
		return data;
	}

	/**
	 * Replace le curseur dans le MemoryStream.
	 *
	 * @param position nouvelle position
	 * @throws UnsupportedOperationException si on ne peut pas naviguer dans ce stream.
	 * @throws UnsupportedOperationException si la nouvelle position est en dehors du stream.
	 */
	public void seek(int position)
	{
		if (!canSeek) throw new UnsupportedOperationException();

		if (position < 0 || position > this.length) throw new UnsupportedOperationException();

		this.position = position;
	}

	/**
	 * Replace le curseur dans le MemoryStream.
	 *
	 * @param position nouvelle position
	 * @param relative indique si la position est une position relative ou absolue dans le MemoryStream
	 * @throws UnsupportedOperationException si on ne peut pas naviguer dans ce stream.
	 * @throws UnsupportedOperationException si la nouvelle position est en dehors du stream.
	 */
	public void seek(int position, boolean relative)
	{
		if (relative)
		{
			position = +this.position;
		}

		seek(position);
	}
}
