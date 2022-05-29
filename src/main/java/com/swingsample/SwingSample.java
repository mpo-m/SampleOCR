package com.swingsample;

import java.awt.BorderLayout;
import com.swingsample.panel.CenterPanel;

public class SwingSample {

	public static void main(String[] args) {
		var mainFrame = new MainFrame("SwingSample");
		var centerPanel = new CenterPanel();
		mainFrame.addComponent(centerPanel, BorderLayout.CENTER);
		mainFrame.show();
	}

}
