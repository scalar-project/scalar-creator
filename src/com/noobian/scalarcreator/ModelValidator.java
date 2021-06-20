package com.noobian.scalarcreator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ModelValidator {
	public static boolean validateLoadedJson(JsonElement json) {
		if (!json.isJsonObject()) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame, "The loaded data is not a valid JSON object.",
					"Invalid JSON", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		JsonObject root = (JsonObject) json;

		// Validate Bones
		if (ModelUtils.listBones(root).size() == 0) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"There doesn't seem to be any bones in this JSON.\n" + "As such, it cannot be loaded",
					"Invalid JSON", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Warn missing skeleton
		JsonElement skeleton = root.get("skeleton");
		if (!skeleton.isJsonObject() || ((JsonObject) skeleton).size() == 0) {
			UIManager.put("OptionPane.okButtonText", "I know what I'm doing!");
			int option = JOptionPane.showConfirmDialog(ScalarCreator.modalDialogFrame,
					"The JSON does not appear to have a skeleton. This usually means you didn't\n"
							+ "name your bones correctly in Blockbench. If you continue, it is\n"
							+ "likely your model won't be animated unless you are an advanced\n"
							+ "user and plan to create one manually in Scalar Creator...",
					"No Skeleton Found", JOptionPane.OK_CANCEL_OPTION);
			UIManager.put("OptionPane.okButtonText", "OK");
			if (option != JOptionPane.OK_OPTION)
				return false;
		}

		// Warn no textures
		if (ModelUtils.listJsonTextures((JsonObject) json).isEmpty()) {
			UIManager.put("OptionPane.okButtonText", "I know what I'm doing!");
			int option = JOptionPane.showConfirmDialog(ScalarCreator.modalDialogFrame,
					"This JSON loads no textures and the model will appear\n"
							+ "a solid color. Did you export from Blockbench correctly?",
					"No Textures Found", JOptionPane.OK_CANCEL_OPTION);
			UIManager.put("OptionPane.okButtonText", "OK");
			if (option != JOptionPane.OK_OPTION)
				return false;
		}

		return true;
	}

	public static boolean validateSkinName(String skin) {
		if (skin == null) {
			// Cancel was pressed, don't make a dialog for it
			return false;
		}
		if (skin.isEmpty()) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame, "Skin name cannot be blank.");
			return false;
		}
		if (!skin.matches("^[a-z0-9]*$")) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"Skin name must be lowercase letters and numbers only.");
			return false;
		}
		if (ScalarCreator.model.skins.containsKey(skin)) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame, "Skin name already taken.");
			return false;
		}
		return true;
	}

	public static boolean validateCanContinueFromMain() {
		List<String> ignoredTextures = new ArrayList<>();

		// Validate basic info
		EditorModel model = ScalarCreator.model;
		if (model.id.equals("") || !model.id.matches("^[a-z0-9]*$")) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"Model id must only be lowercase letters and numbers.", "Cannot Continue",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (model.name.equals("") || !model.name.matches("^[a-zA-Z0-9 ]*$")) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"Model display name must only be letters, numbers, and spaces.", "Cannot Continue",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (model.author.equals("") || !model.author.matches("^[a-zA-Z0-9 _]*$")) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"Author must only be letters, numbers,\nspaces and underscores.", "Cannot Continue",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (model.version.equals("") || !model.version.matches("^[a-zA-Z0-9 _.]*$")) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"Version must only be letters, numbers,\nspaces, underscores, and periods.", "Cannot Continue",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Validate default JSON
		ModelName mn = new ModelName();
		if (!ModelUtils.subModelHasJson(mn)) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"The default skin has no JSON loaded for it's normal stage.", "Cannot Continue",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Validate all JSON and textures in all skins
		Iterator<Skin> skins = model.skins.values().iterator();
		while (skins.hasNext()) {
			Skin skin = skins.next();
			mn.skin = (skin.id == null ? "default" : skin.id);

			// Check normal JSON and Texture
			mn.mood = ModelMood.NORMAL;
			mn.stage = 0;
			if (!ModelUtils.subModelHasJson(mn)) {
				// Normal has no JSON, ask to discard skin altogether
				UIManager.put("OptionPane.okButtonText", "Delete");
				int option = JOptionPane.showConfirmDialog(ScalarCreator.modalDialogFrame,
						"Skin \"" + mn.skin + "\" has no JSON loaded for it's normal state.\n"
								+ "If you continue, the entire skin will be deleted.",
						"Missing JSON", JOptionPane.OK_CANCEL_OPTION);
				UIManager.put("OptionPane.okButtonText", "OK");
				if (option == JOptionPane.CANCEL_OPTION)
					return false;
				else {
					skins.remove();
					continue;
				}
			}
			if (!checkMissingTextures(mn, ignoredTextures))
				return false;

			// Check erect JSON and textures
			mn.mood = ModelMood.ERECT;
			if (!ModelUtils.subModelHasJson(mn)) {
				// Erect has no JSON, confirm no lewd
				UIManager.put("OptionPane.okButtonText", "Delete Variants");
				int option = JOptionPane.showConfirmDialog(ScalarCreator.modalDialogFrame,
						"Skin \"" + mn.skin + "\" has no JSON loaded for it's erect state.\n"
								+ "This means that this skin will have no lewd versions of it.\n"
								+ "Any loaded arousal or erect variant submodels will be deleted.\n"
								+ "Do you wish to continue?",
						"Is your character a wholesome bean?", JOptionPane.OK_CANCEL_OPTION);
				UIManager.put("OptionPane.okButtonText", "OK");
				if (option == JOptionPane.CANCEL_OPTION)
					return false;
				else {
					// Activate wholesome 100 mode
					skin.erect.clear();
					skin.aroused.clear();
					continue;
				}
			}
			if (!checkMissingTextures(mn, ignoredTextures))
				return false;

			// Check aroused JSON and textures
			mn.mood = ModelMood.AROUSED;
			Iterator<SubModel> aroused = skin.aroused.iterator();
			while (aroused.hasNext()) {
				aroused.next();

				// Have we failed verification and deleting all models?
				if (mn.stage == -1) {
					aroused.remove();
					continue;
				}

				if (!ModelUtils.subModelHasJson(mn)) {
					// This arousal has no JSON, ask to delete this and subsequent submodels
					UIManager.put("OptionPane.okButtonText", "Delete Submodels");
					int option = JOptionPane.showConfirmDialog(ScalarCreator.modalDialogFrame,
							"Stage Aroused" + (mn.stage == 0 ? "" : "_" + mn.stage) + " for skin \"" + mn.skin
									+ "\" has no JSON loaded.\n"
									+ "If you continue, this and any subsequent aroused submodels\n"
									+ "will be deleted. Do you wish to continue?",
							"Missing JSON", JOptionPane.OK_CANCEL_OPTION);
					UIManager.put("OptionPane.okButtonText", "OK");
					if (option == JOptionPane.CANCEL_OPTION)
						return false;
					else {
						// Set stage to -1, to enable deletion mode for this iterator
						mn.stage = -1;
						aroused.remove();
						continue;
					}
				}
				if (!checkMissingTextures(mn, ignoredTextures))
					return false;

				mn.stage++;
			}

			// Check erect variant JSON and textures
			mn.mood = ModelMood.ERECT;
			mn.stage = 1; // Skip normal erect
			Iterator<SubModel> erect = skin.erect.iterator();
			erect.next(); // Skip normal erect
			while (erect.hasNext()) {
				erect.next();

				// Have we failed verification and deleting all models?
				if (mn.stage == -1) {
					erect.remove();
					continue;
				}

				if (!ModelUtils.subModelHasJson(mn)) {
					// This erect variant has no JSON, ask to delete this and subsequent submodels
					UIManager.put("OptionPane.okButtonText", "Delete Submodels");
					int option = JOptionPane.showConfirmDialog(ScalarCreator.modalDialogFrame,
							"Stage Erect_" + mn.stage + " for skin \"" + mn.skin + "\" has no JSON loaded.\n"
									+ "If you continue, this and any subsequent erect variant submodels\n"
									+ "will be deleted. Do you wish to continue?",
							"Missing JSON", JOptionPane.OK_CANCEL_OPTION);
					UIManager.put("OptionPane.okButtonText", "OK");
					if (option == JOptionPane.CANCEL_OPTION)
						return false;
					else {
						// Set stage to -1, to enable deletion mode for this iterator
						mn.stage = -1;
						erect.remove();
						continue;
					}
				}
				if (!checkMissingTextures(mn, ignoredTextures))
					return false;

				mn.stage++;
			}

		}

		return true;
	}

	private static boolean checkMissingTextures(ModelName mn, List<String> ignore) {
		List<String> required = ModelUtils.listJsonTextures(ModelUtils.getSubModelJson(mn));

		// Remove loaded texture from the list
		for (String loaded : ModelUtils.listLoadedTextures(mn)) {
			required.remove(loaded);
		}

		// Warn for each unsatisfied reference
		for (String missing : required) {
			if (ignore.contains(missing))
				continue;

			UIManager.put("OptionPane.yesButtonText", "Ignore All");
			UIManager.put("OptionPane.noButtonText", "Continue");
			int option = JOptionPane.showConfirmDialog(
					ScalarCreator.modalDialogFrame, ModelNameParser.generateEditorModelName(mn)
							+ " references texture \n\"" + missing + "\" but it is not loaded.",
					"Missing Texture", JOptionPane.YES_NO_CANCEL_OPTION);
			UIManager.put("OptionPane.yesButtonText", "Yes");
			UIManager.put("OptionPane.noButtonText", "No");
			if (option == JOptionPane.CANCEL_OPTION)
				return false;
			else if (option == JOptionPane.YES_OPTION)
				ignore.add(missing);

			// Continue
		}

		return true;
	}
}
