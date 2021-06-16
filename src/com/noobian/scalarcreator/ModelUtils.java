package com.noobian.scalarcreator;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ModelUtils {
	public static boolean subModelHasJson(ModelName mn) {
		Skin skin = ScalarCreator.model.getSkinByModelName(mn);
		if (skin == null)
			return false;
		SubModel sm = skin.getSubModelByName(mn);
		return (sm != null && sm.json != null && sm.json.isJsonObject());
	}

	public static JsonObject getSubModelJson(ModelName mn) {
		Skin skin = ScalarCreator.model.getSkinByModelName(mn);
		if (skin == null)
			return null;
		SubModel sm = skin.getSubModelByName(mn);
		if (sm != null && sm.json != null && sm.json.isJsonObject())
			return sm.json;
		else
			return null;
	}

	public static boolean subModelHasTexture(ModelName mn, String texture) {
		Skin skin = ScalarCreator.model.getSkinByModelName(mn);
		if (skin == null)
			return false;
		SubModel sm = skin.getSubModelByName(mn);
		return (sm != null && sm.textures.containsKey(texture));
	}

	public static ByteBuffer getSubModelTexture(ModelName mn, String texture) {
		Skin skin = ScalarCreator.model.getSkinByModelName(mn);
		if (skin == null)
			return null;
		SubModel sm = skin.getSubModelByName(mn);
		if (sm != null && sm.textures != null)
			return sm.textures.get(texture);
		else
			return null;
	}

	public static boolean validateJson(JsonElement json) {
		if (!json.isJsonObject()) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame, "The JSON is not a valid CPM object.",
					"Invalid JSON", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		JsonObject root = (JsonObject) json;

		// Validate Bones
		JsonElement bones = root.get("bones");
		if (listBones(root).size() == 0) {
			JOptionPane.showMessageDialog(ScalarCreator.modalDialogFrame,
					"There doesn't seem to be any bones in this model.", "Invalid JSON", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Warn missing skeleton
		JsonElement skeleton = root.get("skeleton");
		if (!skeleton.isJsonObject() || ((JsonObject) skeleton).size() == 0) {
			if (JOptionPane.showConfirmDialog(ScalarCreator.modalDialogFrame,
					"The model does not appear to have a skeleton. This usually means you didn't\n"
							+ "name your bones correctly in Blockbench. If you continue, it is\n"
							+ "likely your model won't be animated unless you have some kind of\n"
							+ "crazy manual custom animation setup in mind.",
					"No skeleton found", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION)
				return false;
		}

		return true;
	}

	public static List<String> listBones(JsonObject json) {
		ArrayList<String> list = new ArrayList<String>();
		JsonElement boneElement = json.get("bones");
		if (!boneElement.isJsonArray())
			return list; // Empty list
		JsonArray bones = (JsonArray) boneElement;
		bones.forEach((JsonElement e) -> {
			if (e.isJsonObject() && e.getAsJsonObject().get("id") != null
					&& e.getAsJsonObject().get("id").isJsonPrimitive()) {
				try {
					list.add(e.getAsJsonObject().get("id").getAsString());
				} catch (ClassCastException ex) {
				}
			}
		});
		return list;
	}

	public static List<String> listJsonTextures(JsonObject json) {
		ArrayList<String> list = new ArrayList<String>();
		JsonElement boneElement = json.get("bones");
		if (!boneElement.isJsonArray())
			return list; // Empty list
		JsonArray bones = (JsonArray) boneElement;
		bones.forEach((JsonElement e) -> {
			if (e.isJsonObject() && e.getAsJsonObject().get("texture") != null
					&& e.getAsJsonObject().get("texture").isJsonPrimitive()) {
				try {
					String str = e.getAsJsonObject().get("texture").getAsString().replace("tex.", "");
					if (!list.contains(str))
						list.add(str);
				} catch (ClassCastException ex) {
				}
			}
		});
		return list;
	}

	public static List<String> listLoadedTextures(ModelName mn) {
		Map<String, ByteBuffer> list = ScalarCreator.model.getSkinByModelName(mn).getSubModelByName(mn).textures;
		List<String> str = new ArrayList<String>();
		for (String s : list.keySet()) {
			str.add(s);
		}
		return str;
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
}
