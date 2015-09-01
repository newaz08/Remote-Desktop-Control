/*******************************************************
 * Copyright (C) 2015 {Newaz Sharif Amit} <{newazsharifamit@yahoo.com}>
 * 
 * This file is part of {Remote Desktop Control}.
 * 
 * {Remote Desktop Control} can not be copied and/or distributed without the express
 * permission of {Newaz Sharif Amit}
 *******************************************************/

import java.rmi.*; 
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
* This is an Interface.
* Here declare all the remote Services.
*/


/**
* @author: newaz Sharif Amit
*/
 
public interface RemoteServices extends Remote {  
  
	public File[] traverse() throws RemoteException;
	public void openDir(String dir) throws RemoteException,IOException;
	public File[] displayAllDirectory(File currDir) throws RemoteException,IOException;
	public byte[] getDesktop() throws RemoteException;
	public void move(int x, int y) throws RemoteException,AWTException;
	public void click(boolean x, boolean y, boolean z) throws RemoteException,AWTException;
	public void type(int s) throws RemoteException,AWTException;
  
}  