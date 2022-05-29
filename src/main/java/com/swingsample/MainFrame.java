package com.swingsample;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainFrame {

	private JFrame frame;
	private Container contentPane;

	public MainFrame(String title) {
		this.frame = new JFrame(title);
		this.contentPane = frame.getContentPane();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void show() {
		frame.setVisible(true);
	}

	public void addPanel(JPanel panel, String borderLayout) {
		contentPane.add(panel, borderLayout);
	}

}
