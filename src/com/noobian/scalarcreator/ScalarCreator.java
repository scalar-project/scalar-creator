package com.noobian.scalarcreator;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ScalarCreator {
	
	public static JFrame modalDialogFrame = new JFrame();
	public static MainPanel main;
	public static EditorModel model;
	public static boolean init = false;
	public static File openDir = null; // Defaults to home dir

	public static void main(String args[]) {
		initStartingModel();
		JFrame frame = new JFrame("Scalar Creator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main = new MainPanel();
        main.init();
        frame.add(main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        init = true;
	}

	private static void initStartingModel() {
		 model = new EditorModel();
		 Skin skin = new Skin();
		 skin.normal = new SubModel();
		 skin.aroused = new ArrayList<SubModel>();
		 skin.erect.add(new SubModel());
		 model.skins.put("default", skin);
	}
	
}
