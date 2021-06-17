package com.noobian.scalarcreator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class MainPanel extends JPanel implements ActionListener, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	String selectedTexture;
	String txt;

	JLabel lbLabel0;
	JTextField tfAuthor;
	JButton btExport;
	JLabel lbLabel1;
	JCheckBox cbPrivate;
	JList<String> lsSubmodelList;
	DefaultComboBoxModel<String> skinList;
	JLabel lbLabel5;
	JButton btLoadJson;
	JButton btLoadTexture;
	JLabel lbLabel8;
	JLabel lbLabel9;
	JTextField tfDisplayName;
	JTextField tfModelId;
	JButton btAddAroused;
	JLabel lbLoadedTexture;
	JLabel lbTexture;
	JButton btClear;
	JButton btAddErect;
	JLabel lbLabel12;
	JComboBox<String> cmbSkinList;
	JLabel lbLabel16;
	JTextField tfVersion;
	JLabel lbPrivateDesc;
	JButton btBoundingBox;
	DefaultListModel<String> submodelModel = new DefaultListModel<String>();

	/**
	 * Constructor for the MainPanel object
	 */
	public MainPanel() {
		super();

		GridBagLayout gbMainPanel = new GridBagLayout();
		GridBagConstraints gbcMainPanel = new GridBagConstraints();
		setLayout(gbMainPanel);

		lbLabel0 = new JLabel("Author");
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 5;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(lbLabel0, gbcMainPanel);
		add(lbLabel0);

		tfAuthor = new JTextField();
		gbcMainPanel.gridx = 1;
		gbcMainPanel.gridy = 5;
		gbcMainPanel.gridwidth = 5;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(tfAuthor, gbcMainPanel);
		add(tfAuthor);

		btExport = new JButton("Export Model Pack");
		btExport.addActionListener(this);
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 0;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(btExport, gbcMainPanel);
		add(btExport);

		lbLabel1 = new JLabel("Private?");
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 7;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(lbLabel1, gbcMainPanel);
		add(lbLabel1);

		cbPrivate = new JCheckBox("");
		gbcMainPanel.gridx = 1;
		gbcMainPanel.gridy = 7;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(cbPrivate, gbcMainPanel);
		add(cbPrivate);

		lsSubmodelList = new JList<String>(submodelModel);
		JScrollPane scpSubmodelList = new JScrollPane(lsSubmodelList);
		lsSubmodelList.addListSelectionListener(this);
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 13;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(scpSubmodelList, gbcMainPanel);
		add(scpSubmodelList);

		lbLabel5 = new JLabel("Submodel List:");
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 12;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(lbLabel5, gbcMainPanel);
		add(lbLabel5);

		btLoadJson = new JButton("Load JSON");
		btLoadJson.addActionListener(this);
		gbcMainPanel.gridx = 2;
		gbcMainPanel.gridy = 12;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(btLoadJson, gbcMainPanel);
		add(btLoadJson);

		btLoadTexture = new JButton("Load Texture");
		btLoadTexture.addActionListener(this);
		gbcMainPanel.gridx = 3;
		gbcMainPanel.gridy = 12;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(btLoadTexture, gbcMainPanel);
		add(btLoadTexture);

		lbLabel8 = new JLabel("Display Name");
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 4;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(lbLabel8, gbcMainPanel);
		add(lbLabel8);

		lbLabel9 = new JLabel("Model ID");
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 3;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(lbLabel9, gbcMainPanel);
		add(lbLabel9);

		tfDisplayName = new JTextField();
		gbcMainPanel.gridx = 1;
		gbcMainPanel.gridy = 4;
		gbcMainPanel.gridwidth = 5;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(tfDisplayName, gbcMainPanel);
		add(tfDisplayName);

		tfModelId = new JTextField();
		gbcMainPanel.gridx = 1;
		gbcMainPanel.gridy = 3;
		gbcMainPanel.gridwidth = 5;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(tfModelId, gbcMainPanel);
		add(tfModelId);

		btAddAroused = new JButton("Add In-Between");
		btAddAroused.addActionListener(this);
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 14;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(btAddAroused, gbcMainPanel);
		add(btAddAroused);

		lbLoadedTexture = new JLabel("Loaded Textures:");
		gbcMainPanel.gridx = 3;
		gbcMainPanel.gridy = 13;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(lbLoadedTexture, gbcMainPanel);
		add(lbLoadedTexture);

		lbTexture = new JLabel("(none)");
		gbcMainPanel.gridx = 4;
		gbcMainPanel.gridy = 13;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(lbTexture, gbcMainPanel);
		add(lbTexture);

		btClear = new JButton("Clear");
		btClear.addActionListener(this);
		gbcMainPanel.gridx = 4;
		gbcMainPanel.gridy = 12;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(btClear, gbcMainPanel);
		add(btClear);

		btAddErect = new JButton("Add Erect Variant");
		btAddErect.addActionListener(this);
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 15;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(btAddErect, gbcMainPanel);
		add(btAddErect);

		lbLabel12 = new JLabel("Selected Skin:");
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 10;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbcMainPanel.insets = new Insets(25, 0, 0, 0);
		gbMainPanel.setConstraints(lbLabel12, gbcMainPanel);
		add(lbLabel12);

		skinList = new DefaultComboBoxModel<String>();
		cmbSkinList = new JComboBox<String>(skinList);
		cmbSkinList.addActionListener(this);
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 11;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(cmbSkinList, gbcMainPanel);
		add(cmbSkinList);

		lbLabel16 = new JLabel("Version");
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 6;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(lbLabel16, gbcMainPanel);
		add(lbLabel16);

		tfVersion = new JTextField();
		gbcMainPanel.gridx = 1;
		gbcMainPanel.gridy = 6;
		gbcMainPanel.gridwidth = 5;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(tfVersion, gbcMainPanel);
		add(tfVersion);

		lbPrivateDesc = new JLabel("(Anyone can use the model)");
		gbcMainPanel.gridx = 2;
		gbcMainPanel.gridy = 7;
		gbcMainPanel.gridwidth = 5;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(lbPrivateDesc, gbcMainPanel);
		add(lbPrivateDesc);

		btBoundingBox = new JButton("Configure Bounding Box");
		btBoundingBox.addActionListener(this);
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 8;
		gbcMainPanel.gridwidth = 1;
		gbcMainPanel.gridheight = 1;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 0;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(btBoundingBox, gbcMainPanel);
		add(btBoundingBox);
	}

	/**
	*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btExport) {
			// Action for btExport
		} else if (e.getSource() == btLoadJson) {
			loadJson();
		} else if (e.getSource() == btLoadTexture) {
			loadTexture();
		} else if (e.getSource() == btAddAroused) {
			ScalarCreator.model.getSkinByModelName(ModelNameParser.getGuiSelectedModelName(
					(String) cmbSkinList.getSelectedItem(), (String) lsSubmodelList.getSelectedValue())).aroused
							.add(new SubModel());
			this.loadSubmodelGuiForSkin(((String) cmbSkinList.getSelectedItem()).replace("<", "").replace(">", ""));
		} else if (e.getSource() == btClear) {
			// Action for btClear
		} else if (e.getSource() == btAddErect) {
			ScalarCreator.model.getSkinByModelName(ModelNameParser.getGuiSelectedModelName(
					(String) cmbSkinList.getSelectedItem(), (String) lsSubmodelList.getSelectedValue())).erect
							.add(new SubModel());
			this.loadSubmodelGuiForSkin(((String) cmbSkinList.getSelectedItem()).replace("<", "").replace(">", ""));
		} else if (e.getSource() == btBoundingBox) {
			// Action for btBoundingBox
		} else if (e.getSource() == cbPrivate) {
			if (cbPrivate.isSelected()) {
				lbPrivateDesc.setText("(Only the author can use the model)");
				tfVersion.setEnabled(false);
				tfVersion.setText("private");
			} else {
				lbPrivateDesc.setText("(Anyone can use the model)");
				tfVersion.setEnabled(true);
				tfVersion.setText("1.0");
			}
		} else if (e.getSource() == cmbSkinList) {
			if (cmbSkinList.getSelectedItem().equals("<add new>")) {
				String skin = JOptionPane.showInputDialog(ScalarCreator.modalDialogFrame,
						"Enter the name of your skin:");
				if (ModelUtils.validateSkinName(skin)) {
					// Create skin
					Skin newSkin = new Skin();
					newSkin.normal = new SubModel();
					newSkin.aroused = new ArrayList<SubModel>();
					newSkin.erect.add(new SubModel());
					ScalarCreator.model.skins.put(skin, newSkin);

					skinList.insertElementAt(skin, skinList.getSize() - 1);
					cmbSkinList.setSelectedItem(skin);
				} else {
					// Invalid skin name
					cmbSkinList.setSelectedItem("<default>");
				}
			}

			this.loadSubmodelGuiForSkin(((String) cmbSkinList.getSelectedItem()).replace("<", "").replace(">", ""));
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == lsSubmodelList && ScalarCreator.init) {
			this.updateTextureLabel();
		}
	}

	public void init() {
		cbPrivate.addActionListener(this);
		btClear.setVisible(false); // TODO add clear in v0.2
		tfVersion.setText("1.0");
		submodelModel.add(0, "Normal [Empty]");
		submodelModel.add(1, "Erect [Empty]");
		skinList.addElement("<default>");
		skinList.addElement("<add new>");
		lsSubmodelList.setSelectedIndex(0);
	}

	private void loadJson() {
		ModelName mn = ModelNameParser.getGuiSelectedModelName((String) cmbSkinList.getSelectedItem(),
				(String) lsSubmodelList.getSelectedValue());
		if (ModelUtils.subModelHasJson(mn)) {
			int confirmReplace = JOptionPane.showConfirmDialog(ScalarCreator.modalDialogFrame,
					"Replace this submodel's existing JSON? This will remove\nany textures imported to this submodel.",
					"JSON already exists", JOptionPane.YES_NO_OPTION);
			if (confirmReplace == JOptionPane.NO_OPTION || confirmReplace == JOptionPane.CLOSED_OPTION) {
				return;
			} else {
				// Delete textures
				System.out.println(ScalarCreator.model.getSkinByModelName(mn).getSubModelByName(mn).textures);
				ScalarCreator.model.getSkinByModelName(mn).getSubModelByName(mn).textures.clear();
				System.out.println(ScalarCreator.model.getSkinByModelName(mn).getSubModelByName(mn).textures);
				this.updateTextureLabel();
			}
		}

		// Load File
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setCurrentDirectory(ScalarCreator.openDir);
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new FileNameExtensionFilter("Blockbench JSON Output", "json"));
		int fcResult = fc.showOpenDialog(ScalarCreator.main);
		if (fcResult != JFileChooser.APPROVE_OPTION)
			return;
		ScalarCreator.openDir = fc.getCurrentDirectory();
		Reader reader;
		JsonElement json;
		try {
			reader = new FileReader(fc.getSelectedFile());
			json = JsonParser.parseReader(reader);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"Could not read the JSON file for some reason. It might be invalid", e.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (ModelUtils.validateJson(json)) {
			// We just validated the json, so it's safe to cast
			ScalarCreator.model.getSkinByModelName(mn).getSubModelByName(mn).json = (JsonObject) json;
			ScalarCreator.model.getSkinByModelName(mn).getSubModelByName(mn).jsonName = fc.getSelectedFile().getName();
			String[] split = ((String) submodelModel.get(lsSubmodelList.getSelectedIndex())).split(" ");
			split[1] = "[" + fc.getSelectedFile().getName() + "]";
			String str = split[0] + " " + split[1];
			submodelModel.set(lsSubmodelList.getSelectedIndex(), str);
		}
	}

	private void loadTexture() {
		ModelName mn = ModelNameParser.getGuiSelectedModelName((String) cmbSkinList.getSelectedItem(),
				(String) lsSubmodelList.getSelectedValue());

		// No JSON
		if (!ModelUtils.subModelHasJson(mn)) {
			UIManager.put("OptionPane.okButtonText", "well okay then");
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame, "You need to import a JSON here first.",
					"Whoops!", JOptionPane.INFORMATION_MESSAGE);
			UIManager.put("OptionPane.okButtonText", "OK");
			return;
		}
		List<String> tex = ModelUtils.listJsonTextures(ModelUtils.getSubModelJson(mn));

		// No textures to load
		if (tex.size() == 0) {
			UIManager.put("OptionPane.okButtonText", "idk");
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"This model doesn't ask for any textures!" + "\nDid you export from Blockbench correctly?"
							+ "\nOr is this model based entirely off of the player skin?",
					"That doesn't seem right...", JOptionPane.WARNING_MESSAGE);
			UIManager.put("OptionPane.okButtonText", "OK");
			return;
		}
		selectedTexture = null;

		if (tex.size() == 1) {
			selectedTexture = tex.get(0);
		} else {
			// Select which texture to overwrite
			JPanel texSelect = new JPanel();
			JButton cancel = new JButton("Cancel");
			JButton ok = new JButton("OK");
			texSelect.add(new JLabel("Texture to load:"));
			JComboBox<?> texBox = new JComboBox<Object>(tex.toArray());
			texSelect.add(texBox);
			texSelect.add(cancel);
			texSelect.add(ok);
			JDialog diag = new JDialog();
			diag.getContentPane().add(texSelect);
			diag.pack();
			diag.setModal(true);
			diag.setLocationRelativeTo(ScalarCreator.main);
			cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					diag.setVisible(false);
					diag.dispose();
				}
			});
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedTexture = (String) texBox.getSelectedItem();
					diag.setVisible(false);
					diag.dispose();
				}
			});
			diag.setVisible(true);
		}
		if (selectedTexture == null)
			return;
		if (ModelUtils.subModelHasTexture(mn, selectedTexture)) {
			int confirmReplace = JOptionPane.showConfirmDialog(ScalarCreator.modalDialogFrame,
					"Replace the currently loaded texture for \"" + selectedTexture + "\"?", "Texture already exists",
					JOptionPane.YES_NO_OPTION);
			if (confirmReplace == JOptionPane.NO_OPTION || confirmReplace == JOptionPane.CLOSED_OPTION) {
				return;
			}
		}

		// Load File
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setCurrentDirectory(ScalarCreator.openDir);
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new FileNameExtensionFilter("PNG Texture", "png"));
		int fcResult = fc.showOpenDialog(ScalarCreator.main);
		if (fcResult != JFileChooser.APPROVE_OPTION)
			return;
		ScalarCreator.openDir = fc.getCurrentDirectory();
		ByteBuffer texture;
		try {
			texture = ByteBuffer.wrap(IOUtils.toByteArray(FileUtils.openInputStream(fc.getSelectedFile())));
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"Could not read the texture file for some reason. It might be invalid", e.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		ScalarCreator.model.getSkinByModelName(mn).getSubModelByName(mn).textures.put(selectedTexture, texture);

		this.updateTextureLabel();
	}

	public void updateTextureLabel() {
		List<String> tex = ModelUtils.listLoadedTextures(ModelNameParser.getGuiSelectedModelName(
				(String) cmbSkinList.getSelectedItem(), (String) lsSubmodelList.getSelectedValue()));

		txt = "";
		System.out.println(tex);
		if (tex.size() == 0) {
			lbTexture.setText("(none)");
			return;
		}
		tex.forEach((s) -> {
			txt += s + "\n";
		});
		lbTexture.setText(txt);
	}

	public void loadSubmodelGuiForSkin(String skin) {
		ScalarCreator.init = false; // Prevent NPE related to updateTextureLabel
		submodelModel.clear();

		// Add all submodels in order of normal, aroused, erect
		System.out.println(ScalarCreator.model.skins.get(skin).normal);
		guiAddSubmodelEntry(ScalarCreator.model.skins.get(skin).normal, ModelMood.NORMAL, 0,
				ScalarCreator.model.skins.get(skin).normal.jsonName);
		int i = 0;
		System.out.println(ScalarCreator.model.skins.get(skin).aroused);
		for (SubModel aroused : ScalarCreator.model.skins.get(skin).aroused) {
			guiAddSubmodelEntry(aroused, ModelMood.AROUSED, i, aroused.jsonName);
			i++;
		}
		i = 0;
		System.out.println(ScalarCreator.model.skins.get(skin).erect);
		for (SubModel erect : ScalarCreator.model.skins.get(skin).erect) {
			guiAddSubmodelEntry(erect, ModelMood.ERECT, i, erect.jsonName);
			i++;
		}

		lsSubmodelList.setSelectedIndex(0);
		updateTextureLabel();
		ScalarCreator.init = true;
	}

	private void guiAddSubmodelEntry(SubModel sm, ModelMood mood, int stage, String jsonName) {
		String str = mood.toString().toLowerCase();
		str = str.substring(0, 1).toUpperCase() + str.substring(1);
		if (stage != 0) {
			str += "_" + String.valueOf(stage);
		}
		str += " [";
		if (jsonName == null) {
			str += "Empty";
		} else {
			str += jsonName;
		}
		str += "]";

		submodelModel.add(submodelModel.getSize(), str);
	}
}
