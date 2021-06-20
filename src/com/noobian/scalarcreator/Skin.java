package com.noobian.scalarcreator;

import java.util.LinkedList;
import java.util.List;

public class Skin {
	String id;
	SubModel normal = null;
	List<SubModel> aroused, erect = new LinkedList<>();

	public SubModel getSubModelByName(ModelName mn) {
		if (mn.mood == ModelMood.NORMAL)
			return normal;
		if (mn.mood == ModelMood.ERECT) {
			if (mn.stage >= 0 && mn.stage < erect.size())
				return erect.get(mn.stage);
			else
				return null;
		}
		if (mn.mood == ModelMood.AROUSED) {
			if (mn.stage >= 0 && mn.stage < aroused.size())
				return aroused.get(mn.stage);
			else
				return null;
		}
		return null;
	}
}
