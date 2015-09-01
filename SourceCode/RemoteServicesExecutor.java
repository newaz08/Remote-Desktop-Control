/*******************************************************
 * Copyright (C) 2015 {Newaz Sharif Amit} <{newazsharifamit@yahoo.com}>
 * 
 * This file is part of {Remote Desktop Control}.
 * 
 * {Remote Desktop Control} can not be copied and/or distributed without the express
 * permission of {Newaz Sharif Amit}
 *******************************************************/

import java.rmi.*;  
import java.rmi.server.*; 
import java.io.File;
import javax.swing.filechooser.FileSystemView; 
import java.awt.Desktop;
import java.io.IOException;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Robot;
import javax.swing.JOptionPane;

/** 
* This class Responsible is to execute all the remote method.
* This is Server Side part.
*/

**
* @author: newaz Sharif Amit
*/

public class RemoteServicesExecutor extends UnicastRemoteObject implements RemoteServices {  
  
	File file;
	File[] subFiles;
	JFrame frame = null;
	Robot robot;

	RemoteServicesExecutor () throws RemoteException{  
		super();  
	}  
  
	public File[] traverse(){
		
		File[] roots = File.listRoots();
		return roots;
	}

	
	public void openDir(String dir) throws IOException, RemoteException {

		Desktop desktop = Desktop.getDesktop();
        	File dirToOpen = new File(dir);
        	desktop.open(dirToOpen);
		
		
	}

	public File[] displayAllDirectory(File currDir) throws IOException,RemoteException {

		
			File[] files = currDir.listFiles();
		
			
			return files;
	}

	public byte[] getDesktop() {

		try {


			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice screen = env.getDefaultScreenDevice();
			robot = new Robot(screen);
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			//int width = screen.getDisplayMode().getWidth();
			//int height = screen.getDisplayMode().getHeight();
			//frame.setSize(width, width);
			BufferedImage img = robot.createScreenCapture(new Rectangle(0,0,d.width,d.height));
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ImageIO.write(img, "JPG", bytes );
			bytes.flush();
			byte[] data = bytes.toByteArray();
			bytes.close();
			return data;

		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public void move(int x, int y) throws AWTException,RemoteException {
    		robot = new Robot();
    		robot.mouseMove(x, y);    
    		
	}

	public void click(boolean left, boolean midle, boolean right) throws AWTException,RemoteException {
		 robot = new Robot();
		
		if(left) {

			robot.mousePress(InputEvent.BUTTON1_MASK);
    			robot.mouseRelease(InputEvent.BUTTON1_MASK);	
		}
		else if(midle) {

			robot.mousePress(InputEvent.BUTTON2_MASK);
    			robot.mouseRelease(InputEvent.BUTTON2_MASK);	
		}

		else if(right) {

			robot.mousePress(InputEvent.BUTTON3_MASK);
    			robot.mouseRelease(InputEvent.BUTTON3_MASK);
		}


	} 

	public void type(int code) throws AWTException,RemoteException {

		robot = new Robot();
		//JOptionPane.showMessageDialog (null, code);
		//byte[] bytes = s.getBytes();
    		//for (byte b : bytes) {
      			//int code = b;
      			//if (code > 64 && code < 123) 
				//code = code - 32;

				robot.mouseMove(200,300);
				robot.delay(30);
				robot.keyPress(code);
				robot.keyRelease(code);
		//}	

		/* if(code == 115) {
			 robot.keyPress(KeyEvent.VK_ALT);
			 robot.keyPress(KeyEvent.VK_4);
			 robot.keyRelease(KeyEvent.VK_4);
			 robot.keyRelease(KeyEvent.VK_ALT);

		} */
			
	}
	
	
}  