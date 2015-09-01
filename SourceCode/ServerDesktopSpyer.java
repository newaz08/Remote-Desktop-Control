/*******************************************************
 * Copyright (C) 2015 {Newaz Sharif Amit} <{newazsharifamit@yahoo.com}>
 * 
 * This file is part of {Remote Desktop Control}.
 * 
 * {Remote Desktop Control} can not be copied and/or distributed without the express
 * permission of {Newaz Sharif Amit}
 *******************************************************/

import java.rmi.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.naming.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.util.Scanner;

/**
* This class Responsible is to Spy Server Computer.
* From Here all the remote method are called.
*/

/**
* @author: newaz Sharif Amit
*/

public class ServerDesktopSpyer extends JFrame implements java.io.Serializable,MouseListener,KeyListener {

	boolean leftClicked = false;
	boolean rightClicked = false;
	boolean midleClicked = false;
	MouseEvent event ;
	private JPanel cPanel = null;

	class ImageThreader extends Thread {

		public void run() {
			while(true) {

				try {

					byte[] data = stub.getDesktop();
					InputStream in = new ByteArrayInputStream(data);
					BufferedImage img = ImageIO.read(in);
					setIcon(img);
					//Thread.sleep(10000);

					int a = MouseInfo.getPointerInfo().getLocation().x;
					int b = MouseInfo.getPointerInfo().getLocation().y;

					stub.move(a,b);
					//mouseClicked(this);
					
					
				}catch(Exception ex) {

					ex.printStackTrace();
				}
			}
		}

	}	
	
	private JLabel frame;
	RemoteServices stub;
	Scanner scan;
	public ServerDesktopSpyer(RemoteServices stub)throws Exception {

		//this.stub = stub;
		new Scanner(System.in);
		frame = new JLabel();
		//JTextField nameTextField = new JTextField();
		add(frame);
		//frame.setVisible(true);
		InitialContext cnt = new InitialContext();
		this.stub = stub;
		
		frame.addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);

		ImageThreader th = new ImageThreader();
		th.setPriority(Thread.MAX_PRIORITY);
		th.start();
	}



	public void setIcon(BufferedImage img) {
		frame.setIcon(new ImageIcon(img));
		//frame.setIconImage(icon.getImage());
	}

	public void mouseClicked(MouseEvent e) {

		try {

        		switch(e.getModifiers()) {

      				case InputEvent.BUTTON1_MASK: {
        				leftClicked = true; 
					stub.click(leftClicked, midleClicked, rightClicked);    
        				break;
        			}
      				case InputEvent.BUTTON2_MASK: {
        				midleClicked = true; 
					stub.click(leftClicked, midleClicked, rightClicked);    
        				break;
        			}
      				case InputEvent.BUTTON3_MASK: {
        				rightClicked = true; 
					stub.click(leftClicked, midleClicked, rightClicked);    
        				break;
        			}
      			}
		
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
    	}

	public void mousePressed(MouseEvent e) {}
  	public void mouseReleased(MouseEvent e) {}
  	public void mouseEntered(MouseEvent e) {}
  	public void mouseExited(MouseEvent e) {}

	public void keyPressed(KeyEvent ke) {
		try {
        		int keyCode = ke.getKeyCode();
			//JOptionPane.showMessageDialog (null, keyCode);
        		stub.type(keyCode);
			//String s = KeyEvent.getKeyText(ke.getKeyCode());
			
	
		}catch(Exception ex) {

		}
    	}
	
	public void keyReleased(KeyEvent ke) {

		/*try {
			int keycode = ke.getKeyCode(); 
			stub.type(keycode);
			
		}catch(Exception ex) {
	
		}*/
	} 

	public void keyTyped(KeyEvent ke) {}

}               