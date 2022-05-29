package com.swingsample.panel;

import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class CenterPanel extends JPanel {

	private JTextField inputPath;
	private JButton loadPath;
	private JTextArea textArea;

	public CenterPanel() {
		setLayout(new BorderLayout());
		setHeader();
		setTextArea();
		setButton();
	}

	private void setHeader() {
		this.inputPath = new JTextField("画像のパスを入力", 25);
		this.loadPath = new JButton("読込");
		var header = new JPanel();
		loadPath.addActionListener(e -> textArea.setText(parseImage(inputPath.getText())));
		header.add(inputPath);
		header.add(loadPath);
		add(header, BorderLayout.NORTH);
	}

	private String parseImage(String path) {
		var file = new File(path);
		if (!file.exists()) {
			return "指定したパスが存在しません";
		}
		try {
			BufferedImage img = ImageIO.read(file);
			ITesseract tesseract = new Tesseract();
			tesseract.setDatapath("");
			tesseract.setLanguage("jpn");
			return tesseract.doOCR(img);
		} catch (IOException e) {
			return "画像ファイル作成失敗";
		} catch (TesseractException e) {
			return "解析失敗";
		}
	}

	private void setTextArea() {
		this.textArea = new JTextArea();
		textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		textArea.setLineWrap(true);
		add(textArea, BorderLayout.CENTER);
	}

	private void setButton() {
		var buttonPanel = new JPanel();
		var clearText = new JButton("Clear");
		var copyText = new JButton("Copy");
		copyText.addActionListener(e -> {
			textArea.selectAll();
			textArea.copy();
		});
		clearText.addActionListener(e -> {
			var accept = new JLabel("clear?");
			int result = JOptionPane.showConfirmDialog(this, accept);
			if (result == JOptionPane.YES_OPTION) {
				textArea.setText("");
			}
		});
		buttonPanel.add(clearText);
		buttonPanel.add(copyText);
		add(buttonPanel, BorderLayout.SOUTH);
	}
}
