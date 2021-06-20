package com.noobian.scalarcreator;

import java.util.LinkedHashMap;
import java.util.Map;

public class EditorModel {
	public String id, name, author, version;
	Map<String, Skin> skins = new LinkedHashMap<String, Skin>();

	public Skin getSkinByModelName(ModelName mn) {
		if (mn.skin == null)
			return skins.get("default");
		else
			return skins.get(mn.skin);
	}
}
