package com.paintwar.client.view.pages.game.listeners ;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.paintwar.client.view.pages.game.elements.DrawZone;

public class DrawListener implements MouseListener, MouseMotionListener {

	private DrawZone drawZone;
	private String entityDrawnName;
	
	public DrawListener(DrawZone drawZone) {
		super();
		this.drawZone = drawZone;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		drawZone.updateEndPointDraw(entityDrawnName, e.getPoint());
		drawZone.repaint();
		
		//sending event to other listeners
		e.translatePoint(drawZone.getX(), drawZone.getY());
		drawZone.getParent().dispatchEvent(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//sending event to other listeners
		e.translatePoint(drawZone.getX(), drawZone.getY());
		drawZone.getParent().dispatchEvent(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		entityDrawnName = drawZone.initializeDraw(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		entityDrawnName = null;
		drawZone.setCurrentDrawing(null);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
