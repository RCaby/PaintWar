package com.paintwar.client.view.pages.game.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.paintwar.client.logger.Logger;
import com.paintwar.client.view.pages.game.listeners.MinimapMvtListener;

public class Minimap extends JPanel {
	
	private HashMap<String, Drawing> miniDraws;
	private double ratio;
	private JPanel cameraFrame;
	
	public Minimap(double aspectRatio, int drawZoneSize, int minimapSize) {
		this.miniDraws = new HashMap<String, Drawing>();
		this.cameraFrame = new JPanel();
		
		this.ratio = (double) minimapSize/drawZoneSize;
		this.setPreferredSize(new Dimension((int) aspectRatio*minimapSize, minimapSize));
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.setLayout(null);
		
		cameraFrame.setOpaque(false);
		cameraFrame.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		this.add(cameraFrame);

	}
	
	public void setDZPlaceholder(DrawZonePlaceholder dzPlaceHolder) {
		MinimapMvtListener list = new MinimapMvtListener(dzPlaceHolder, this);
		this.addMouseListener(list);
		this.addMouseMotionListener(list);
	}

	public void reScale(Point p) {
		p.setLocation((int) (p.x*ratio), (int) (p.y*ratio));
	}
	
	public void unScaleAndCenter(Point p) {
		p.setLocation((int) ((p.x - cameraFrame.getWidth()/2)/ratio), (int) ((p.y - cameraFrame.getHeight()/2)/ratio));
	}
	
	public void paint(String name, Point oldP, Point oldP2, Color black) {
		Point p = oldP.getLocation();
		Point p2 = oldP2.getLocation();
		reScale(p);
		reScale(p2);
		Drawing newDraw = new Drawing(Color.black);
		miniDraws.put(name, newDraw);
		Rectangle r = new Rectangle(p);
		r.add(p2);
		newDraw.setBounds(r);
		newDraw.setInitPoint(p);
		newDraw.setEndPoint(p);
		add(newDraw);
	}

	public void updateEndPointPaint(String name, Point oldP) {
		Point newP = oldP.getLocation();
		reScale(newP);
		Drawing drawToUpdate = miniDraws.get(name);
		if (drawToUpdate != null) {
			Rectangle r = new Rectangle(drawToUpdate.getInitPoint());
			r.add(newP);
			drawToUpdate.setBounds(r);
			drawToUpdate.setEndPoint(newP);
		} else if (name != null) {
			Logger.print("[Game/minimap] Couldn't find drawing to change coord");
		}
	}	
	
	public void updateCameraFrame(int newWidth, int newHeight) {
		cameraFrame.setSize((int) (newWidth*ratio), (int) (newHeight*ratio));
	}
	
	public void moveCameraFrame(int x, int y) {
		Point newPos = new Point(x, y);
		reScale(newPos);
		//System.out.println("New pos : " + x + "; " + y + " rescaled to " + newPos.x + " ; " + newPos.y);
		cameraFrame.setLocation(newPos);
	}


	
}