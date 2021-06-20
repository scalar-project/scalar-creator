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

	JLabel lbAuthor;
	JTextField tfAuthor;
	JLabel lbStep1;
	JLabel lbPrivate;
	JCheckBox cbPrivate;
	JList<String> lsSubmodelList;
	DefaultComboBoxModel<String> skinList;
	JLabel lbSubmodelList;
	JButton btLoadJson;
	JButton btLoadTexture;
	JLabel lbDisplayName;
	JLabel lbModelId;
	JTextField tfDisplayName;
	JTextField tfModelId;
	JButton btAddAroused;
	JLabel lbLoadedTexture;
	JLabel lbTexture;
	JButton btClear;
	JButton btAddErect;
	JLabel lbSkinList;
	JComboBox<String> cmbSkinList;
	JLabel lbVersion;
	JTextField tfVersion;
	JLabel lbPrivateDesc;
	JLabel lbStep2;
	DefaultListModel<String> submodelModel = new DefaultListModel<String>();
	JLabel lbContinue;
	JButton btContinue;

	/**
	 * Constructor for the MainPanel object
	 */
	public MainPanel() {
		super();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gb);

		lbAuthor = new JLabel("Author");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbAuthor, gbc);
		add(lbAuthor);

		tfAuthor = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(tfAuthor, gbc);
		add(tfAuthor);

		lbStep1 = new JLabel("Step 1: Basic Information");
		AwtUtils.setBold(lbStep1);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbStep1, gbc);
		add(lbStep1);

		lbPrivate = new JLabel("Private?");
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbPrivate, gbc);
		add(lbPrivate);

		cbPrivate = new JCheckBox("");
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(cbPrivate, gbc);
		add(cbPrivate);

		lsSubmodelList = new JList<String>(submodelModel);
		JScrollPane scpSubmodelList = new JScrollPane(lsSubmodelList);
		lsSubmodelList.addListSelectionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(scpSubmodelList, gbc);
		add(scpSubmodelList);

		lbSubmodelList = new JLabel("Submodel List:");
		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbSubmodelList, gbc);
		add(lbSubmodelList);

		btLoadJson = new JButton("Load JSON");
		btLoadJson.addActionListener(this);
		gbc.gridx = 2;
		gbc.gridy = 12;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(btLoadJson, gbc);
		add(btLoadJson);

		btLoadTexture = new JButton("Load Texture");
		btLoadTexture.addActionListener(this);
		gbc.gridx = 3;
		gbc.gridy = 12;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(btLoadTexture, gbc);
		add(btLoadTexture);

		lbDisplayName = new JLabel("Display Name");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbDisplayName, gbc);
		add(lbDisplayName);

		lbModelId = new JLabel("Model ID");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbModelId, gbc);
		add(lbModelId);

		tfDisplayName = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(tfDisplayName, gbc);
		add(tfDisplayName);

		tfModelId = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(tfModelId, gbc);
		add(tfModelId);

		btAddAroused = new JButton("Add Arousal");
		btAddAroused.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(btAddAroused, gbc);
		add(btAddAroused);

		lbLoadedTexture = new JLabel("Loaded Textures: ");
		gbc.gridx = 2;
		gbc.gridy = 13;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbLoadedTexture, gbc);
		add(lbLoadedTexture);

		lbTexture = new JLabel("(none)");
		gbc.gridx = 3;
		gbc.gridy = 13;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbTexture, gbc);
		add(lbTexture);

		btClear = new JButton("Clear");
		btClear.addActionListener(this);
		gbc.gridx = 4;
		gbc.gridy = 12;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(btClear, gbc);
		add(btClear);

		btAddErect = new JButton("Add Erect Variant");
		btAddErect.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 15;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(btAddErect, gbc);
		add(btAddErect);

		lbSkinList = new JLabel("Selected Skin:");
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(25, 0, 0, 0);
		gb.setConstraints(lbSkinList, gbc);
		add(lbSkinList);

		skinList = new DefaultComboBoxModel<String>();
		cmbSkinList = new JComboBox<String>(skinList);
		cmbSkinList.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(cmbSkinList, gbc);
		add(cmbSkinList);

		lbVersion = new JLabel("Version");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbVersion, gbc);
		add(lbVersion);

		tfVersion = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(tfVersion, gbc);
		add(tfVersion);

		lbPrivateDesc = new JLabel("(Anyone can use the model)");
		gbc.gridx = 2;
		gbc.gridy = 7;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbPrivateDesc, gbc);
		add(lbPrivateDesc);

		lbStep2 = new JLabel("Step 2: Load JSON and Textures");
		AwtUtils.setBold(lbStep2);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbStep2, gbc);
		add(lbStep2);

		lbContinue = new JLabel("Make sure everything is loaded before continuing!");
		AwtUtils.setBold(lbContinue);
		gbc.gridx = 0;
		gbc.gridy = 16;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(lbContinue, gbc);
		add(lbContinue);

		btContinue = new JButton("Continue");
		AwtUtils.setBold(btContinue);
		btContinue.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 17;
		gbc.gridwidth = 4;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTH;
		gb.setConstraints(btContinue, gbc);
		add(btContinue);
	}

	/**
	*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btLoadJson) {
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
				if (ModelValidator.validateSkinName(skin)) {
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
		} else if (e.getSource() == btContinue) {
			continueToEditing();
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
				ScalarCreator.model.getSkinByModelName(mn).getSubModelByName(mn).textures.clear();
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
		if (ModelValidator.validateLoadedJson(json)) {
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
			UIManager.put("OptionPane.okButtonText", "oops");
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame, "You need to import a JSON here first.",
					"Whoops!", JOptionPane.INFORMATION_MESSAGE);
			UIManager.put("OptionPane.okButtonText", "OK");
			return;
		}
		List<String> tex = ModelUtils.listJsonTextures(ModelUtils.getSubModelJson(mn));

		// No textures to load
		if (tex.size() == 0) {
			UIManager.put("OptionPane.okButtonText", "oops");
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame, "This model JSON doesn't load any textures!",
					"No Textures", JOptionPane.WARNING_MESSAGE);
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
		if (tex.size() == 0) {
			lbTexture.setText("(none)");
			return;
		}
		tex.forEach((s) -> {
			txt += s + (s.equals(tex.get(tex.size() - 1)) ? "" : ", ");
		});
		lbTexture.setText(txt);
	}

	public void loadSubmodelGuiForSkin(String skin) {
		ScalarCreator.init = false; // Prevent NPE related to updateTextureLabel
		submodelModel.clear();

		// Add all submodels in order of normal, aroused, erect
		guiAddSubmodelEntry(ScalarCreator.model.skins.get(skin).normal, ModelMood.NORMAL, 0,
				ScalarCreator.model.skins.get(skin).normal.jsonName);
		int i = 0;
		for (SubModel aroused : ScalarCreator.model.skins.get(skin).aroused) {
			guiAddSubmodelEntry(aroused, ModelMood.AROUSED, i, aroused.jsonName);
			i++;
		}
		i = 0;
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

	private void continueToEditing() {
		EditorModel model = ScalarCreator.model;
		model.id = tfModelId.getText().trim();
		model.name = tfDisplayName.getText().trim();
		model.author = tfAuthor.getText().trim();
		model.version = tfVersion.getText().trim();

		// TODO Deep clone shit in case of failed verification
		if (ModelValidator.validateCanContinueFromMain()) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame, "Passed verification");

		}
	}
}
