/*******************************************************
 * Copyright (C) 2015 {Newaz Sharif Amit} <{newazsharifamit@yahoo.com}>
 * 
 * This file is part of {Remote Desktop Control}.
 * 
 * {Remote Desktop Control} can not be copied and/or distributed without the express
 * permission of {Newaz Sharif Amit}
 *******************************************************/

import java.rmi.*;  
import java.rmi.registry.*;  

/**
* This is Server Side Code.
* Enter Server Computer IP Address and Port Number.
*/

/**
* @author: newaz Sharif Amit
*/
  
public class MyServer implements java.io.Serializable {  
  
	public static void main(String []cmd){ 
 
		try {  
  
			RemoteServices stub = new RemoteServicesExecutor();  
			Naming.rebind("rmi://localhost:port/amit",stub);  
  
		}catch(Exception ex){ 

			ex.printStackTrace();
		}  
	}  
  
}  