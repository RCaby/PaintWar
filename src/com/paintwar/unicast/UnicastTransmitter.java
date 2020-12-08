package com.paintwar.unicast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.util.HashMap;

import com.paintwar.server.logger.Logger;

public class UnicastTransmitter implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int transmissionPort;
	private InetAddress transmissionAddress;
	private transient DatagramSocket transmissionSocket;
	private String clientIP;

	public UnicastTransmitter(final InetAddress targetAddress, final int transmissionPort, String clientIP) throws RemoteException
	{
		this.transmissionPort = transmissionPort;
		this.clientIP = clientIP;
		Logger.print("Transmition on port " + transmissionPort + " to client : " + targetAddress);
		transmissionAddress = targetAddress;
		transmissionSocket = null;

		try
		{
			transmissionSocket = new DatagramSocket();
		} catch (IOException e)
		{
			Logger.print(e);
		}

		Logger.print("Socket emission : " + transmissionSocket.getLocalPort() + " " + transmissionAddress);
	}

	public void diffuseMessage(String packageName, String command, String name, HashMap<String, Object> hm)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try
		{
			oos = new ObjectOutputStream(baos);
			oos.writeObject(packageName);
			oos.writeObject(command);
			oos.writeObject(name);
			oos.writeObject(hm);
			oos.flush();
		} catch (IOException e)
		{
			Logger.print(e);
		}

		DatagramPacket packet = new DatagramPacket(baos.toByteArray(), baos.toByteArray().length, transmissionAddress,
				transmissionPort);

		try
		{
			transmissionSocket.send(packet);
		} catch (IOException e)
		{
			Logger.print(e);
		}
	}

	public int getTransmissionPort() throws RemoteException
	{
		return transmissionPort;
	}
	
	public String getClientIP() throws RemoteException
	{
		return clientIP;
	}
}