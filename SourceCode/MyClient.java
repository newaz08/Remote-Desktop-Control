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
import java.util.Scanner;
import javax.swing.JOptionPane; 
import java.net.*;

/**
* This is Client Side Code.
* Enter Ip Address and port number of Server Computer.
* This class calls ServerDesktopSpyer. 
*/

/**
* @author: newaz Sharif Amit
*/

public class MyClient  {  
  
	public static void main(String [] cmd){

		
		
		try {  
			//String str = JOptionPane.showInputDialog(" Enter Remote Computer IP Address To Access And Control");
			//InetAddress ip = InetAddress.getByName(str);
			
			
  			RemoteServices stub = (RemoteServices)Naming.lookup("ip:port/amit");

			ServerDesktopSpyer serverDesktopSpyer = new ServerDesktopSpyer(stub);
			serverDesktopSpyer.setVisible(true); 
 
			
		}catch(Exception ex){
	
			ex.printStackTrace();
		}  

	}  
  
}  