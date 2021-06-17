package com.noobian.scalarcreator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BoundingBox extends JDialog {
	private static final long serialVersionUID = 1L;

	static BoundingBox theBoundingBox;

	JPanel pnPanel0;
	JLabel lbLabel0;
	JTextField tfEyeHeight;
	JLabel lbLabel1;
	JLabel lbLabel2;
	JLabel lbLabel3;
	JLabel lbLabel4;
	JLabel lbLabel5;
	JLabel lbLabel6;
	JLabel lbLabel7;
	JTextField tfStandingWidth;
	JTextField tfStandingHeight;
	JTextField tfSneakingWidth;
	JTextField tfSneakingHeight;
	JButton btCancel;
	JButton btOk;

	/**
	 */
	public BoundingBox() {
		super();

		pnPanel0 = new JPanel();
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout(gbPanel0);

		lbLabel0 = new JLabel("Eye Height");
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 1;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel0, gbcPanel0);
		pnPanel0.add(lbLabel0);

		tfEyeHeight = new JTextField();
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 1;
		gbcPanel0.gridwidth = 7;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(tfEyeHeight, gbcPanel0);
		pnPanel0.add(tfEyeHeight);

		lbLabel1 = new JLabel("Bounding Box");
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 4;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel1, gbcPanel0);
		pnPanel0.add(lbLabel1);

		lbLabel2 = new JLabel("Standing");
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 6;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel2, gbcPanel0);
		pnPanel0.add(lbLabel2);

		lbLabel3 = new JLabel("Sneaking");
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 9;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel3, gbcPanel0);
		pnPanel0.add(lbLabel3);

		lbLabel4 = new JLabel("Width");
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 7;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel4, gbcPanel0);
		pnPanel0.add(lbLabel4);

		lbLabel5 = new JLabel("Height");
		gbcPanel0.gridx = 6;
		gbcPanel0.gridy = 7;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel5, gbcPanel0);
		pnPanel0.add(lbLabel5);

		lbLabel6 = new JLabel("Width");
		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 10;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel6, gbcPanel0);
		pnPanel0.add(lbLabel6);

		lbLabel7 = new JLabel("Height");
		gbcPanel0.gridx = 6;
		gbcPanel0.gridy = 10;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(lbLabel7, gbcPanel0);
		pnPanel0.add(lbLabel7);

		tfStandingWidth = new JTextField();
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 7;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(tfStandingWidth, gbcPanel0);
		pnPanel0.add(tfStandingWidth);

		tfStandingHeight = new JTextField();
		gbcPanel0.gridx = 8;
		gbcPanel0.gridy = 7;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(tfStandingHeight, gbcPanel0);
		pnPanel0.add(tfStandingHeight);

		tfSneakingWidth = new JTextField();
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 10;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(tfSneakingWidth, gbcPanel0);
		pnPanel0.add(tfSneakingWidth);

		tfSneakingHeight = new JTextField();
		gbcPanel0.gridx = 8;
		gbcPanel0.gridy = 10;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(tfSneakingHeight, gbcPanel0);
		pnPanel0.add(tfSneakingHeight);

		btCancel = new JButton("Cancel");
		gbcPanel0.gridx = 6;
		gbcPanel0.gridy = 14;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btCancel, gbcPanel0);
		pnPanel0.add(btCancel);

		btOk = new JButton("OK");
		gbcPanel0.gridx = 8;
		gbcPanel0.gridy = 14;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(btOk, gbcPanel0);
		pnPanel0.add(btOk);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setContentPane(pnPanel0);
		pack();
		setVisible(true);
	}
}
