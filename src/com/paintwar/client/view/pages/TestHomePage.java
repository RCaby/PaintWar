package com.paintwar.client.view.pages;

import java.awt.Dimension;

import javax.swing.JFrame;

public class TestHomePage {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(new Dimension(2000, 1000));
		window.add(new Home("player"));
		window.setVisible(true);
	}

}
